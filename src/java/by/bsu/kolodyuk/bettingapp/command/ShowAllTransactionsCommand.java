
package by.bsu.kolodyuk.bettingapp.command;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionWrapper;
import by.bsu.kolodyuk.bettingapp.db.dao.DAOFactory;
import by.bsu.kolodyuk.bettingapp.db.dao.TransactionDAO;
import by.bsu.kolodyuk.bettingapp.model.entity.Transaction;
import by.bsu.kolodyuk.bettingapp.model.entity.User;
import by.bsu.kolodyuk.bettingapp.model.logic.UserMoneyLogic;
import by.bsu.kolodyuk.bettingapp.constant.PageType;
import by.bsu.kolodyuk.bettingapp.utility.PathManager;
import by.bsu.kolodyuk.bettingapp.constant.SessionAttributeType;
import by.bsu.kolodyuk.bettingapp.controller.SessionRequestContent;
import java.util.List;


public class ShowAllTransactionsCommand extends CloseableCommand {

    @Override
    public String executeAndClose(SessionRequestContent requestContent, ConnectionWrapper connection) throws DatabaseException {
        UserMoneyLogic.updateUserMoney(requestContent, connection);
        TransactionDAO transactionDAO = DAOFactory.newInstance().getTransactionDAO(connection);
        User currentUser = (User) requestContent.getSessionAttribute(SessionAttributeType.USER.getName());
        List<Transaction> transactions = transactionDAO.findAll(currentUser.getId());
        requestContent.setSessionAttribute(SessionAttributeType.TRANSACTIONS.getName(), transactions);
        return PathManager.getPagePath(PageType.TRANSACTIONS.getPageName());
    }
    
}
