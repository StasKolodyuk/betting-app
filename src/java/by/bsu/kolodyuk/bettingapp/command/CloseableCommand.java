
package by.bsu.kolodyuk.bettingapp.command;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionPool;
import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionWrapper;
import by.bsu.kolodyuk.bettingapp.db.dao.DAOFactory;
import by.bsu.kolodyuk.bettingapp.constant.PageType;
import by.bsu.kolodyuk.bettingapp.utility.PathManager;
import by.bsu.kolodyuk.bettingapp.controller.SessionRequestContent;
import org.apache.log4j.Logger;


public abstract class CloseableCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(CloseableCommand.class);

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        DAOFactory daoFactory = DAOFactory.newInstance();
        ConnectionPool connectionPool = daoFactory.getConnectionPool();
        ConnectionWrapper connection = null;
        try {
            connection = connectionPool.getConnection();
            page = executeAndClose(requestContent, connection);
        } catch (DatabaseException e) {
            LOGGER.error(e);
            page = PathManager.getPagePath(PageType.ERROR.getPageName());
        } finally {
            if(connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return page;
    }
    
    public abstract String executeAndClose(SessionRequestContent requestContent, ConnectionWrapper connection) throws DatabaseException;
    
}
