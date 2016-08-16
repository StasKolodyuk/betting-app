
package by.bsu.kolodyuk.bettingapp.command;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.db.dao.BetDAO;
import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionWrapper;
import by.bsu.kolodyuk.bettingapp.db.dao.DAOFactory;
import by.bsu.kolodyuk.bettingapp.model.entity.BetWrapper;
import by.bsu.kolodyuk.bettingapp.model.logic.UserMoneyLogic;
import by.bsu.kolodyuk.bettingapp.constant.PageType;
import by.bsu.kolodyuk.bettingapp.utility.PathManager;
import by.bsu.kolodyuk.bettingapp.constant.SessionAttributeType;
import by.bsu.kolodyuk.bettingapp.controller.SessionRequestContent;
import java.util.List;

public class ShowAllBetsCommand extends CloseableCommand {

    @Override
    public String executeAndClose(SessionRequestContent requestContent, ConnectionWrapper connection) throws DatabaseException {
        UserMoneyLogic.updateUserMoney(requestContent, connection);
        DAOFactory daoFactory = DAOFactory.newInstance();
        BetDAO betDAO = daoFactory.getBetDAO(connection);
        List<BetWrapper> bets = betDAO.findAllAndWrap();
        requestContent.setSessionAttribute(SessionAttributeType.BETS.getName(), bets);
        return PathManager.getPagePath(PageType.ALL_BETS.getPageName());
    }
    
}