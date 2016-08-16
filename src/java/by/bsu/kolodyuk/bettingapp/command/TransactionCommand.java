
package by.bsu.kolodyuk.bettingapp.command;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionPool;
import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionWrapper;
import by.bsu.kolodyuk.bettingapp.db.dao.DAOFactory;
import by.bsu.kolodyuk.bettingapp.constant.PageType;
import by.bsu.kolodyuk.bettingapp.utility.PathManager;
import by.bsu.kolodyuk.bettingapp.controller.SessionRequestContent;
import org.apache.log4j.Logger;


public abstract class TransactionCommand implements Command {
    
    private static final Logger LOGGER = Logger.getLogger(TransactionCommand.class);

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        DAOFactory daoFactory = DAOFactory.newInstance();
        ConnectionPool connectionPool = daoFactory.getConnectionPool();
        ConnectionWrapper connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            page = makeTransactionAndClose(requestContent, connection);
            connection.commit();
        } catch (DatabaseException e) {
            safeRollback(connection);
            page = PathManager.getPagePath(PageType.ERROR.getPageName());
            LOGGER.error(e);
        } finally {
            safeRelease(connectionPool, connection);
        }
        return page;
    }
    
    public abstract String makeTransactionAndClose(SessionRequestContent requestContent, ConnectionWrapper connection) throws DatabaseException;

    
    private void safeRollback(ConnectionWrapper connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (DatabaseException ex) {
            LOGGER.error(ex);
        }
    }
    
    private void safeRelease(ConnectionPool connectionPool, ConnectionWrapper connection) {
        try {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
                connection.setAutoCommit(true);
            }
        } catch (DatabaseException ex) {
            LOGGER.error(ex);
        }
    }
    
}
