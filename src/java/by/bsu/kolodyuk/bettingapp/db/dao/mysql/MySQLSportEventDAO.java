
package by.bsu.kolodyuk.bettingapp.db.dao.mysql;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.db.dao.SportEventDAO;
import by.bsu.kolodyuk.bettingapp.db.table.SportEventTableColumn;
import by.bsu.kolodyuk.bettingapp.model.entity.SportEvent;
import by.bsu.kolodyuk.bettingapp.model.entity.SportType;
import by.bsu.kolodyuk.bettingapp.utility.TimeUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class MySQLSportEventDAO implements SportEventDAO {
    
    private static final String INSERT_SPORT_EVENT_QUERY = "INSERT INTO sport_event "
                                                         + "(sport_type, event_description, event_start_time, event_end_time) "
                                                         + "VALUES (?, ?, ?, ?)";
    
    private static final String FIND_ALL_ENDED_SPORT_EVENTS_QUERY = "SELECT * "
                                                                  + "FROM sport_event "
                                                                  + "WHERE event_end_time < NOW() "
                                                                  + "AND event_result IS NULL";
    
    private static final String UPDATE_SPORT_EVENT_RESULT_QUERY = "UPDATE sport_event "
                                                                + "SET event_result = ? "
                                                                + "WHERE event_id = ?";
    
    private Connection connection;
    
    public MySQLSportEventDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addSportEvent(SportEvent sportEvent) throws DatabaseException {
        try(PreparedStatement query = connection.prepareStatement(INSERT_SPORT_EVENT_QUERY)) {
            query.setString(1, sportEvent.getSportType().name().toLowerCase());
            query.setString(2, sportEvent.getDescription());
            query.setTimestamp(3, TimeUtil.convertToTimestamp(sportEvent.getStartTime()));
            query.setTimestamp(4, TimeUtil.convertToTimestamp(sportEvent.getEndTime()));
            query.executeUpdate();
        } catch(SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void updateSportEventResult(int sportEventId, String result) throws DatabaseException {
        try(PreparedStatement query = connection.prepareStatement(UPDATE_SPORT_EVENT_RESULT_QUERY)) {
            query.setString(1, result);
            query.setInt(2, sportEventId);
            query.executeUpdate();
        } catch(SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
    @Override
    public List<SportEvent> findAllEndedSportEvents() throws DatabaseException {
        try(PreparedStatement query = connection.prepareStatement(FIND_ALL_ENDED_SPORT_EVENTS_QUERY)) {
            ResultSet sportEventsResultSet = query.executeQuery();
            List<SportEvent> sportEvents = new ArrayList<>();
            while(sportEventsResultSet.next()) {
                sportEvents.add(createSportEvent(sportEventsResultSet));
            }
            return sportEvents;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
    private SportEvent createSportEvent(ResultSet resultSet) throws SQLException {
        SportEvent sportEvent = new SportEvent();
        sportEvent.setId(resultSet.getInt(SportEventTableColumn.ID.getName()));
        Timestamp startTime = resultSet.getTimestamp(SportEventTableColumn.START_TIME.getName());
        sportEvent.setStartTime(TimeUtil.convertToCalendar(startTime));
        Timestamp endTime = resultSet.getTimestamp(SportEventTableColumn.END_TIME.getName());
        sportEvent.setEndTime(TimeUtil.convertToCalendar(endTime));
        sportEvent.setSportType(SportType.valueOf(resultSet.getString(SportEventTableColumn.SPORT_TYPE.getName()).toUpperCase()));
        sportEvent.setDescription(resultSet.getString(SportEventTableColumn.DESCRIPTION.getName()));
        return sportEvent;
    }
    
}
