
package by.bsu.kolodyuk.bettingapp.db.pool.mysql;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionWrapper;
import java.sql.Connection;
import java.sql.SQLException;


public class MySQLConnectionWrapper extends ConnectionWrapper<Connection> {

    public MySQLConnectionWrapper(Connection connection) {
        super(connection);
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws DatabaseException {
        try {
            getConnection().setAutoCommit(autoCommit);
        } catch(SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void rollback() throws DatabaseException {
        try {
            getConnection().rollback();
        } catch(SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void commit() throws DatabaseException {
        try {
            getConnection().commit();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
}
