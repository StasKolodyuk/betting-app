
package by.bsu.kolodyuk.bettingapp.db.dao.mysql;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.db.dao.BetDAO;
import by.bsu.kolodyuk.bettingapp.db.table.BetTableColumn;
import by.bsu.kolodyuk.bettingapp.db.table.SportEventTableColumn;
import by.bsu.kolodyuk.bettingapp.model.entity.BetType;
import by.bsu.kolodyuk.bettingapp.model.entity.BetWrapper;
import by.bsu.kolodyuk.bettingapp.utility.container.Pair;
import by.bsu.kolodyuk.bettingapp.utility.TimeUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * The {@code MySQLBetDAO} class is the implementation of BetDAO class that 
 * represents Data Access Object for Bet entity class.
 * The class {@code MySQLBetDAO} includes methods for adding 
 * Bet entity objects to MySQL DB and fetching them from DB.
 * @author Stas Kolodyuk
 * @see BetDAO
 */
public class MySQLBetDAO implements BetDAO {

    private static final String INSERT_BET_QUERY = "INSERT INTO bet (event_id, bet_type, bet_coefficient) "
                                                 + "VALUES (?, ?, ?)";
    
    private static final String FIND_ALL_BETS_QUERY = "SELECT* " + 
                                                      "FROM sport_event " + 
                                                      "JOIN bet " +
                                                      "USING(event_id)";
    
    private static final String FIND_ALL_BETS_WRAPPED_QUERY = "SELECT s.event_id, s.event_start_time, "
                                                            + "s.event_description, b.bet_id, b.bet_type, b.bet_coefficient "
                                                            + "FROM sport_event s " 
                                                            + "JOIN bet b " 
                                                            + "ON s.event_id = b.event_id "
                                                            + "WHERE s.event_start_time > NOW()"
                                                            + "ORDER BY s.event_start_time, s.event_description";
    
    private static final String FIND_ALL_BETS_WRAPPED_INCLUDING_SPARE_QUERY = "SELECT s.event_id, s.event_start_time, "
                                                                            + "s.event_description, b.bet_id, b.bet_type, b.bet_coefficient "
                                                                            + "FROM sport_event s "
                                                                            + "LEFT JOIN bet b "
                                                                            + "ON s.event_id = b.event_id "
                                                                            + "WHERE s.event_start_time > NOW()"
                                                                            + "ORDER BY s.event_start_time, s.event_description";
    
    private static final String UPDATE_BET_RESULT_QUERY = "UPDATE bet "
                                                        + "SET bet_result = ? "
                                                        + "WHERE bet_id = ?";

    private static final String FIND_ALL_CONNECTED_BETS = "SELECT bet_id, bet_type "
                                                        + "FROM bet "
                                                        + "WHERE event_id = ?";
    
    private Connection connection;

    public MySQLBetDAO(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Finds all bets in MySQL DB and wraps them into {@code BetWrapper}
     * @return {@code List} of {@code BetWrapper} entity objects
     * @throws DatabaseException in case of DB connection failure
     */
    @Override
    public List<BetWrapper> findAllAndWrap() throws DatabaseException {
        return findAllAndWrap(FIND_ALL_BETS_WRAPPED_QUERY);
    }

    /**
     * Finds all bets in MySQL DB, including spare for existing sport events,
     * and wraps them into {@code BetWrapper}
     *
     * @return {@code List} of {@code BetWrapper} entity objects
     * @throws DatabaseException in case of DB connection failure
     */
    @Override
    public List<BetWrapper> findAllAndWrapIncludingSpare() throws DatabaseException {
        return findAllAndWrap(FIND_ALL_BETS_WRAPPED_INCLUDING_SPARE_QUERY);
    }
    
    /**
     * Adds {@code Bet} entity object to MySQL DB
     * @param sportEventId id of sport event
     * @param betType type of Bet
     * @param coefficient coefficient of Bet
     * @throws DatabaseException in case of DB connection failure
     */
    @Override
    public void addBet(int sportEventId, BetType betType, double coefficient) throws DatabaseException {
        try(PreparedStatement query = connection.prepareStatement(INSERT_BET_QUERY)) {
            query.setInt(1, sportEventId);
            query.setString(2, betType.name());
            query.setDouble(3, coefficient);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
    /**
     * Finds all bets according to the {@code sqlQuery} and wraps them
     * @param sqlQuery sql query
     * @return {@code List} of {@code BetWrapper} entity objects
     * @throws DatabaseException in case of DB connection failure
     */
    private List<BetWrapper> findAllAndWrap(String sqlQuery) throws DatabaseException {
        try (PreparedStatement query = connection.prepareStatement(sqlQuery)) {
            ResultSet betResultSet = query.executeQuery();
            List<BetWrapper> betList = new ArrayList<>();
            BetWrapper currentBetWrapper = new BetWrapper();
            while (betResultSet.next()) {
                int sportEventId = betResultSet.getInt(SportEventTableColumn.ID.getName());
                Calendar startTimeCalendar = TimeUtil.convertToCalendar(betResultSet.getTimestamp(SportEventTableColumn.START_TIME.getName()));
                String description = betResultSet.getString(SportEventTableColumn.DESCRIPTION.getName());
                int betId = betResultSet.getInt(BetTableColumn.ID.getName());
                double betCoefficient = betResultSet.getDouble(BetTableColumn.COEFFICIENT.getName());
                String betType = betResultSet.getString(BetTableColumn.TYPE.getName());
                if (sportEventId != currentBetWrapper.getSportEventId()) {
                    currentBetWrapper = new BetWrapper(sportEventId, description, startTimeCalendar);
                    betList.add(currentBetWrapper);
                }
                currentBetWrapper.addBetInfo(betId, betType, betCoefficient);
            }
            return betList;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
 
    /**
     * Finds all bets specified for a particular sport event
     * @param sportEventId id of sport event
     * @return {@code List} of {@code Pair<Integer, BetType>} representing bet type for each bet id
     * @throws DatabaseException in case of DB connection failure
     */
    @Override
    public List<Pair<Integer, BetType>> findAllConnectedBets(int sportEventId) throws DatabaseException {
        try (PreparedStatement query = connection.prepareStatement(FIND_ALL_CONNECTED_BETS)) {
            query.setInt(1, sportEventId);
            ResultSet betsInfoResultSet = query.executeQuery();
            List<Pair<Integer, BetType>> betsInfo = new ArrayList<>();
            while (betsInfoResultSet.next()) {
                Pair<Integer, BetType> betInfo = new Pair<>();
                betInfo.setFirst(betsInfoResultSet.getInt(BetTableColumn.ID.getName()));
                betInfo.setSecond(BetType.forValue(betsInfoResultSet.getString(BetTableColumn.TYPE.getName())));
                betsInfo.add(betInfo);
            }
            return betsInfo;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    /**
     * Updates result for a particular bet
     * @param betId bet id
     * @param result bet result
     * @throws DatabaseException in case of DB connection failure 
     */
    @Override
    public void updateBetResult(int betId, boolean result) throws DatabaseException {
        try (PreparedStatement query = connection.prepareStatement(UPDATE_BET_RESULT_QUERY)) {
            query.setBoolean(1, result);
            query.setInt(2, betId);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
}
