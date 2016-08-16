
package by.bsu.kolodyuk.bettingapp.command;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.db.dao.ComplexBetDAO;
import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionWrapper;
import by.bsu.kolodyuk.bettingapp.db.dao.DAOFactory;
import by.bsu.kolodyuk.bettingapp.db.dao.UserDAO;
import by.bsu.kolodyuk.bettingapp.model.entity.SimpleUser;
import by.bsu.kolodyuk.bettingapp.validation.MakeBetValidator;
import by.bsu.kolodyuk.bettingapp.model.logic.UserMoneyLogic;
import by.bsu.kolodyuk.bettingapp.constant.PageType;
import by.bsu.kolodyuk.bettingapp.utility.PathManager;
import by.bsu.kolodyuk.bettingapp.constant.RequestAttributeType;
import by.bsu.kolodyuk.bettingapp.constant.RequestParameterType;
import by.bsu.kolodyuk.bettingapp.constant.SessionAttributeType;
import by.bsu.kolodyuk.bettingapp.controller.SessionRequestContent;


public class MakeBetCommand extends TransactionCommand {
    

    @Override
    public String makeTransactionAndClose(SessionRequestContent requestContent, ConnectionWrapper connection) throws DatabaseException {
        UserMoneyLogic.updateUserMoney(requestContent, connection);
        SimpleUser currentUser = (SimpleUser) requestContent.getSessionAttribute(SessionAttributeType.USER.getName());
        MakeBetValidator makeBetValidator = createMakeBetValidator(currentUser,  requestContent);
        if(makeBetValidator.checkValidity()) {
            makeBet(makeBetValidator, currentUser, connection);
        } else {
            requestContent.setRequestAttribute(RequestAttributeType.ERROR_MESSAGE_KEY.getName(), makeBetValidator.getErrorMessageKey());
        }
        return PathManager.getPagePath(PageType.ACCOUNT.getPageName());
    }
    
    private MakeBetValidator createMakeBetValidator(SimpleUser currentUser, SessionRequestContent requestContent) {
        String resultBet = requestContent.getRequestParameter(RequestParameterType.RESULT_BET.getName());
        String resultBetCoefficient = requestContent.getRequestParameter(RequestParameterType.RESULT_BET_COEFFICIENT.getName());
        String earliestBetStartTime = requestContent.getRequestParameter(RequestParameterType.EARLIEST_BET_START_TIME.getName());
        String betMoney = requestContent.getRequestParameter(RequestParameterType.MONEY_AMOUNT.getName());
        return new MakeBetValidator(betMoney, resultBetCoefficient, currentUser, resultBet, earliestBetStartTime);
    }
    
    private void makeBet(MakeBetValidator makeBetValidator, SimpleUser currentUser, ConnectionWrapper connection) throws DatabaseException {
        addComplexBetToDB(makeBetValidator, currentUser, connection);
        updateUserMoney(makeBetValidator, currentUser, connection);
    }
    
    private void addComplexBetToDB(MakeBetValidator makeBetValidator, SimpleUser currentUser, ConnectionWrapper connection) throws DatabaseException {
        ComplexBetDAO complexBetDAO = DAOFactory.newInstance().getComplexBetDAO(connection);
        complexBetDAO.addComplexBet(currentUser.getId(), makeBetValidator.getValidBetIdList(), 
                makeBetValidator.getValidBetMoneyAmount(), makeBetValidator.getValidBetCoefficient());
    }
    
    private void updateUserMoney(MakeBetValidator makeBetValidator, SimpleUser currentUser, ConnectionWrapper connection) throws DatabaseException {
        UserDAO userDAO = DAOFactory.newInstance().getUserDAO(connection);
        int currentMoneyAmount = currentUser.getMoneyAmount() - makeBetValidator.getValidBetMoneyAmount();
        userDAO.updateUserMoney(currentUser.getId(), currentMoneyAmount);
        currentUser.setMoneyAmount(currentMoneyAmount);
    }
    
}