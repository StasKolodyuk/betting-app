
package by.bsu.kolodyuk.bettingapp.command;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionWrapper;
import by.bsu.kolodyuk.bettingapp.db.dao.DAOFactory;
import by.bsu.kolodyuk.bettingapp.db.dao.SportEventDAO;
import by.bsu.kolodyuk.bettingapp.model.entity.SportEvent;
import by.bsu.kolodyuk.bettingapp.constant.PageType;
import by.bsu.kolodyuk.bettingapp.utility.PathManager;
import by.bsu.kolodyuk.bettingapp.constant.SessionAttributeType;
import by.bsu.kolodyuk.bettingapp.controller.SessionRequestContent;
import java.util.List;

public class ShowAllEndedSportEventsCommand extends CloseableCommand {

    @Override
    public String executeAndClose(SessionRequestContent requestContent, ConnectionWrapper connection) throws DatabaseException {
        SportEventDAO sportEventDAO = DAOFactory.newInstance().getSportEventDAO(connection);
        List<SportEvent> endedSportEvents = sportEventDAO.findAllEndedSportEvents();
        requestContent.setSessionAttribute(SessionAttributeType.ENDED_SPORT_EVENTS.getName(), endedSportEvents);
        return PathManager.getPagePath(PageType.ADMIN_SET_RESULTS.getPageName());
    }
    
}
