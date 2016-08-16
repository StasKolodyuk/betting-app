
package by.bsu.kolodyuk.bettingapp.command;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.exception.TechnicalException;
import by.bsu.kolodyuk.bettingapp.db.dao.BetDAO;
import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionWrapper;
import by.bsu.kolodyuk.bettingapp.db.dao.DAOFactory;
import by.bsu.kolodyuk.bettingapp.model.entity.BetType;
import by.bsu.kolodyuk.bettingapp.validation.AddBetsValidator;
import by.bsu.kolodyuk.bettingapp.utility.JSONUtil;
import by.bsu.kolodyuk.bettingapp.constant.PageType;
import by.bsu.kolodyuk.bettingapp.utility.PathManager;
import by.bsu.kolodyuk.bettingapp.constant.RequestAttributeType;
import by.bsu.kolodyuk.bettingapp.constant.RequestParameterType;
import by.bsu.kolodyuk.bettingapp.controller.SessionRequestContent;
import by.bsu.kolodyuk.bettingapp.utility.container.Triple;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import org.apache.log4j.Logger;

public class AddBetsCommand extends CloseableCommand {

    private static final Logger LOGGER = Logger.getLogger(AddBetsCommand.class);
    
    @Override
    public String executeAndClose(SessionRequestContent requestContent, ConnectionWrapper connection) throws DatabaseException {
        PageType page;
        try {
            List<Triple<Integer, BetType, Double>> betsInfo = fetchBetsInfo(requestContent);
            AddBetsValidator addBetsValidator = new AddBetsValidator(betsInfo);
            checkValidity(addBetsValidator, requestContent);
            List<Triple<Integer, BetType, Double>> validBetsInfo = addBetsValidator.getValidBetsInfo();
            addBetsToDB(validBetsInfo, connection);
            page = PageType.ADMIN_ACCOUNT;
        } catch (TechnicalException ex) {
            LOGGER.error(ex);
            page = PageType.ERROR;
        }
        return PathManager.getPagePath(page.getPageName());
    }
    
    private void addBetsToDB(List<Triple<Integer, BetType, Double>> validBetsInfo, ConnectionWrapper connection) throws DatabaseException{
        BetDAO betDAO = DAOFactory.newInstance().getBetDAO(connection);
        for (Triple<Integer, BetType, Double> betInfo : validBetsInfo) {
            betDAO.addBet(betInfo.getFirst(), betInfo.getSecond(), betInfo.getThird());
        }        
    }
    
    private void checkValidity(AddBetsValidator addBetsValidator, SessionRequestContent requestContent) throws DatabaseException{
        if (!addBetsValidator.checkValidity()) {
            requestContent.setRequestAttribute(RequestAttributeType.ERROR_MESSAGE_KEY.getName(), addBetsValidator.getErrorMessageKey());
        }
    }
    
    private List<Triple<Integer, BetType, Double>> fetchBetsInfo(SessionRequestContent requestContent) throws TechnicalException {
        String jsonNewBets = requestContent.getRequestParameter(RequestParameterType.NEW_BETS.getName());
        return JSONUtil.fromJSON(jsonNewBets, new TypeReference<List<Triple<Integer, BetType, Double>>>() {});
    }
    
}
