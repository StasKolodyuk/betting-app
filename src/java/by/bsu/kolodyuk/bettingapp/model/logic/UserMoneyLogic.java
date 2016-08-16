
package by.bsu.kolodyuk.bettingapp.model.logic;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionWrapper;
import by.bsu.kolodyuk.bettingapp.db.dao.DAOFactory;
import by.bsu.kolodyuk.bettingapp.db.dao.UserDAO;
import by.bsu.kolodyuk.bettingapp.model.entity.SimpleUser;
import by.bsu.kolodyuk.bettingapp.controller.SessionRequestContent;


public class UserMoneyLogic {
    
    public static void updateUserMoney(SessionRequestContent requestContent, ConnectionWrapper connection) throws DatabaseException {   
        SimpleUser user = (SimpleUser) requestContent.getSessionAttribute("user");
        UserDAO userDAO = DAOFactory.newInstance().getUserDAO(connection);
        int moneyAmount = userDAO.findUserMoney(user.getId());
        if (moneyAmount != -1) {
            user.setMoneyAmount(moneyAmount);
        }
    }
    
}