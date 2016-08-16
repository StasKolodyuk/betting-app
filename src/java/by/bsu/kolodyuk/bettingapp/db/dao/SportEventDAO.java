
package by.bsu.kolodyuk.bettingapp.db.dao;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.model.entity.SportEvent;
import java.util.List;


public interface SportEventDAO {
        
    void addSportEvent(SportEvent sportEvent) throws DatabaseException;
    List<SportEvent> findAllEndedSportEvents() throws DatabaseException;
    void updateSportEventResult(int sportEventId, String result) throws DatabaseException;
    
}
