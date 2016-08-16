
package by.bsu.kolodyuk.bettingapp.command;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.db.dao.ComplexBetDAO;
import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionWrapper;
import by.bsu.kolodyuk.bettingapp.db.dao.DAOFactory;
import by.bsu.kolodyuk.bettingapp.model.entity.ComplexBetWrapper;
import by.bsu.kolodyuk.bettingapp.model.entity.User;
import by.bsu.kolodyuk.bettingapp.constant.PageType;
import by.bsu.kolodyuk.bettingapp.utility.PathManager;
import by.bsu.kolodyuk.bettingapp.constant.SessionAttributeType;
import by.bsu.kolodyuk.bettingapp.controller.SessionRequestContent;
import java.util.List;


public class ShowMyBetsCommand extends CloseableCommand {

    @Override
    public String executeAndClose(SessionRequestContent requestContent, ConnectionWrapper connection) throws DatabaseException {
        ComplexBetDAO complexBetDAO = DAOFactory.newInstance().getComplexBetDAO(connection);
        User user = (User)requestContent.getSessionAttribute(SessionAttributeType.USER.getName());
        List<ComplexBetWrapper> complexBets = complexBetDAO.findAll(user.getId());
        requestContent.setSessionAttribute(SessionAttributeType.COMPLEX_BETS.getName(), complexBets);
        return PathManager.getPagePath(PageType.MY_BETS.getPageName());
    }
    
}
