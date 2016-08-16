
package by.bsu.kolodyuk.bettingapp.db.pool.mysql;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.exception.TechnicalException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class DBConnector {

    private static final String DB_PROPERTIES_FILE_NAME = "database";
    private static final String DB_URL_PROPERTY = "db.url";
    private static final String DB_USER_PROPERTY = "db.user";
    private static final String DB_PASSWORD_PROPERTY = "db.password";
    
    private String url;
    private String user;
    private String password;

    public DBConnector(String propertiesFilePath) throws TechnicalException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(DB_PROPERTIES_FILE_NAME);
        url = resourceBundle.getString(DB_URL_PROPERTY);
        user = resourceBundle.getString(DB_USER_PROPERTY);
        password = resourceBundle.getString(DB_PASSWORD_PROPERTY);
    }
    
    public Connection getConnection() throws DatabaseException {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch(SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
}