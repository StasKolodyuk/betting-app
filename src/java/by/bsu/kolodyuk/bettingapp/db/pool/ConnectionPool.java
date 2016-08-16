
package by.bsu.kolodyuk.bettingapp.db.pool;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;


public interface ConnectionPool<T> {
    
    ConnectionWrapper<T> getConnection() throws DatabaseException;
    void releaseConnection(ConnectionWrapper<T> connection);
    void close();
    
}