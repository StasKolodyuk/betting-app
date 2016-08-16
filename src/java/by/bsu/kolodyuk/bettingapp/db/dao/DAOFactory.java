
package by.bsu.kolodyuk.bettingapp.db.dao;

import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionPool;
import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionWrapper;
import by.bsu.kolodyuk.bettingapp.db.DatabaseType;
import by.bsu.kolodyuk.bettingapp.db.dao.mysql.MySQLDAOFactory;
import by.bsu.kolodyuk.bettingapp.db.pool.mysql.MySQLConnectionPool;
import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import com.sun.prism.PixelFormat;
import java.util.ResourceBundle;


public abstract class DAOFactory<T> {
    
    private static DatabaseType type;
    
    private static final String DB_PROPERTIES_FILE_NAME = "database";
    private static final String DB_TYPE_PROPERTY = "db.type";

    
    static {
        String dbType = ResourceBundle.getBundle(DB_PROPERTIES_FILE_NAME).getString(DB_TYPE_PROPERTY);
        type = DatabaseType.forValue(dbType);
    }
    
    private static class DAOFactoryHolder {
        private static final DAOFactory INSTANCE;
        static {
            try {
                INSTANCE = getDAOFactory();
            } catch (DatabaseException e) {
                throw new ExceptionInInitializerError(e);
            }
        }
    }
    
    public abstract UserDAO getUserDAO(ConnectionWrapper<T> connectionWrapper);
    
    public abstract BetDAO getBetDAO(ConnectionWrapper<T> connectionWrapper);
    
    public abstract ComplexBetDAO getComplexBetDAO(ConnectionWrapper<T> connectionWrapper);
    
    public abstract SportEventDAO getSportEventDAO(ConnectionWrapper<T> connectionWrapper);
    
    public abstract TransactionDAO getTransactionDAO(ConnectionWrapper<T> connectionWrapper);
    
    public ConnectionPool getConnectionPool() {
        switch (type) {
            case MYSQL:
                return MySQLConnectionPool.newInstance();
            case ORACLE:
                throw new UnsupportedOperationException("Oracle DB is not supported yet");
            case MONGODB:
                throw new UnsupportedOperationException("Mongo DB is not supported yet");
            default:
                throw new EnumConstantNotPresentException(PixelFormat.DataType.class, String.valueOf(type));
        }
    }
    
    public static DAOFactory newInstance() {
        return DAOFactoryHolder.INSTANCE;
    }
    
    private static DAOFactory getDAOFactory() throws DatabaseException {
        switch(type) {
            case MYSQL:
                return new MySQLDAOFactory();
            case ORACLE:
                throw new UnsupportedOperationException("Oracle DB is not supported yet");
            case MONGODB:
                throw new UnsupportedOperationException("Mongo DB is not supported yet");
            default:
                throw new EnumConstantNotPresentException(PixelFormat.DataType.class, String.valueOf(type));
        }
    }
    
}
