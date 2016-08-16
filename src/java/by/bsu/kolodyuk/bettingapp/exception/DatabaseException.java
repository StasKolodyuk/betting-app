
package by.bsu.kolodyuk.bettingapp.exception;

import java.sql.SQLException;


public class DatabaseException extends SQLException {

    public DatabaseException(String string, String string1) {
        super(string, string1);
    }

    public DatabaseException(String string) {
        super(string);
    }

    public DatabaseException(Throwable thrwbl) {
        super(thrwbl);
    }

    public DatabaseException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
}
