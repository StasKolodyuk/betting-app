
package by.bsu.kolodyuk.bettingapp.db.dao.mysql;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.db.table.ExtraTableColumn;
import by.bsu.kolodyuk.bettingapp.db.table.BetTableColumn;
import by.bsu.kolodyuk.bettingapp.db.dao.ComplexBetDAO;
import by.bsu.kolodyuk.bettingapp.db.table.ComplexBetTableColumn;
import by.bsu.kolodyuk.bettingapp.model.entity.BetResultType;
import by.bsu.kolodyuk.bettingapp.model.entity.BetType;
import by.bsu.kolodyuk.bettingapp.model.entity.ComplexBetWrapper;
import by.bsu.kolodyuk.bettingapp.utility.container.Triple;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MySQLComplexBetDAO implements ComplexBetDAO {

    private static final String INSERT_COMPLEX_BET_QUERY = "INSERT INTO user_bet (user_id, bet_money_amount, user_bet_coefficient) "
                                                         + "VALUES (?, ?, ?)";
    
    private static final String INSERT_BET_IN_COMPLEX_BET = "INSERT INTO user_bet_consist "
                                                          + "VALUES (?, ?)";
    
    private static final String UPDATE_COMPLEX_BET_RESULTS_QUERY = "UPDATE user_bet ub "
                                                                 + "JOIN "
                                                                 + "(SELECT c.user_bet_id, SUM(b.bet_result != 1) = 0 AS lostBetCount "
                                                                 + "FROM user_bet_consist c "
                                                                 + "JOIN bet b "
                                                                 + "ON b.bet_id = c.bet_id "
                                                                 + "GROUP BY user_bet_id "
                                                                 + "HAVING SUM(b.bet_result IS NULL) = 0) res "
                                                                 + "USING(user_bet_id) "
                                                                 + "JOIN user "
                                                                 + "USING(user_id) "
                                                                 + "SET ub.user_bet_result = res.lostBetCount "
                                                                 + "WHERE user_bet_result IS NULL"; 
    
    private static final String FIND_ALL_COMPLEX_BETS_WRAPPED_QUERY = "SELECT ub.user_bet_id, ub.bet_money_amount, ub.user_bet_result, "
                                                                    + "ub.user_bet_coefficient, "
                                                                    + "ROUND(ub.bet_money_amount*ub.user_bet_coefficient*COALESCE(user_bet_result)) bet_prize, "
                                                                    + "b.bet_type, b.bet_result, CONCAT_WS(\": \", se.event_description, se.event_result) description "
                                                                    + "FROM user_bet ub "
                                                                    + "JOIN user_bet_consist ubc "
                                                                    + "USING(user_bet_id) "
                                                                    + "JOIN bet b "
                                                                    + "USING(bet_id) "
                                                                    + "JOIN sport_event se "
                                                                    + "USING(event_id) "
                                                                    + "WHERE user_id = ? "
                                                                    + "ORDER BY ub.user_bet_id DESC";
    
    private Connection connection;

    public MySQLComplexBetDAO(Connection connection) {
        this.connection = connection;
    }
    
    
    @Override
    public void addComplexBet(int userId, List<Integer> betIdList, int moneyAmount, double coefficient) throws DatabaseException {
        try (PreparedStatement query = connection.prepareStatement(INSERT_COMPLEX_BET_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            query.setInt(1, userId);
            query.setInt(2, moneyAmount);
            query.setDouble(3, coefficient);
            query.executeUpdate();
            ResultSet generatedKeysSet = query.getGeneratedKeys();
            if(generatedKeysSet.next()) {
                int complexBetId = generatedKeysSet.getInt(1);
                for (int i = 0; i < betIdList.size(); i++) {
                    addBetToComplexBet(complexBetId, betIdList.get(i));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void updateComplexBetResults() throws DatabaseException {
        try(PreparedStatement query = connection.prepareStatement(UPDATE_COMPLEX_BET_RESULTS_QUERY)) {
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<ComplexBetWrapper> findAll(int userId) throws DatabaseException {
        try(PreparedStatement query = connection.prepareStatement(FIND_ALL_COMPLEX_BETS_WRAPPED_QUERY)) {
            query.setInt(1, userId);
            ResultSet complexBetResultSet = query.executeQuery();
            List<ComplexBetWrapper> complexBets = new ArrayList<>();
            ComplexBetWrapper currentBetWrapper = new ComplexBetWrapper();
            int currentComplexBetId = -1;
            while(complexBetResultSet.next()) {
                int complexBetId = complexBetResultSet.getInt(ComplexBetTableColumn.ID.getName());
                Triple<BetType, BetResultType, String> betInfo = createBetInfo(complexBetResultSet);
                if(complexBetId != currentComplexBetId) {
                    currentComplexBetId = complexBetId;
                    currentBetWrapper = createComplexBetWrapper(complexBetResultSet);
                    complexBets.add(currentBetWrapper);
                }
                currentBetWrapper.addBetInfo(betInfo.getFirst(), betInfo.getSecond(), betInfo.getThird());
            }
            return complexBets;
        } catch(SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
    private Triple<BetType, BetResultType, String> createBetInfo(ResultSet resultSet) throws SQLException {
        BetType betType = BetType.forValue(resultSet.getString(BetTableColumn.TYPE.getName()));
        BetResultType betResult = BetResultType.fromValue(resultSet.getBoolean(BetTableColumn.RESULT.getName()));
        if (resultSet.wasNull()) {
            betResult = BetResultType.UNDEFINED;
        }
        String betDescription = resultSet.getString(ExtraTableColumn.DESCRIPTION.getName());
        return new Triple<>(betType, betResult, betDescription);
    }
    
    private ComplexBetWrapper createComplexBetWrapper(ResultSet complexBetResultSet) throws SQLException {
        int moneyAmount = complexBetResultSet.getInt(ComplexBetTableColumn.MONEY_AMOUNT.getName());
        double coefficient = complexBetResultSet.getDouble(ComplexBetTableColumn.COEFFICIENT.getName());
        BetResultType complexBetResult = BetResultType.fromValue(complexBetResultSet.getBoolean(ComplexBetTableColumn.RESULT.getName()));
        if (complexBetResultSet.wasNull()) {
            complexBetResult = BetResultType.UNDEFINED;
        }
        int betPrize = complexBetResultSet.getInt(ExtraTableColumn.BET_PRIZE_MONEY.getName());
        return new ComplexBetWrapper(moneyAmount, complexBetResult, coefficient, betPrize);
    }
    
    private void addBetToComplexBet(int complexBetId, int betId) throws SQLException {
        try(PreparedStatement query = connection.prepareStatement(INSERT_BET_IN_COMPLEX_BET)) {
            query.setInt(1, complexBetId);
            query.setInt(2, betId);
            query.executeUpdate();
        }
    }
    
}