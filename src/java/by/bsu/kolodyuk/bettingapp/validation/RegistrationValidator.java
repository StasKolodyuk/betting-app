
package by.bsu.kolodyuk.bettingapp.validation;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionWrapper;
import by.bsu.kolodyuk.bettingapp.db.dao.DAOFactory;
import by.bsu.kolodyuk.bettingapp.db.dao.UserDAO;
import by.bsu.kolodyuk.bettingapp.model.entity.User;


public class RegistrationValidator extends Validator {
    
    private static final String WRONG_LOGIN_ERROR_KEY = "error.label.wrongLogin";
    private static final String WRONG_PASSWORD_ERROR_KEY = "error.label.wrongPassword";
    private static final String BLANK_FIELD_ERROR_KEY = "error.label.blankFieldDetected";
    
    private static final String PASSWORD_CHECK_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])\\S{8,}$";

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private ConnectionWrapper connection;

    public RegistrationValidator(String login, String password, String firstName, String lastName, ConnectionWrapper connection) {
        super();
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.connection = connection;
    }
    
    @Override
    public boolean checkValidity() throws DatabaseException {
        return checkAllFieldsFilling() && checkPassword() && checkLogin();
    }
    
    private boolean checkPassword() {
        boolean valid = password.matches(PASSWORD_CHECK_REGEX); 
        if(!valid) {
            setErrorMessageKey(WRONG_PASSWORD_ERROR_KEY);
        }
        return valid;
    }
    
    private boolean checkLogin() throws DatabaseException {
        DAOFactory daoFactory = DAOFactory.newInstance();
        UserDAO userDAO = daoFactory.getUserDAO(connection);
        User user = userDAO.findUser(login);
        boolean valid = (user == null);
        if(!valid) {
            setErrorMessageKey(WRONG_LOGIN_ERROR_KEY);
        }
        return valid;
    }
    
    private boolean checkAllFieldsFilling() {
        boolean notNull = (login != null) && (password != null) && (firstName != null) && (lastName != null);
        boolean notEmpty = !login.isEmpty() && !password.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty();
        boolean valid = notNull && notEmpty;
        if (!valid) {
            setErrorMessageKey(BLANK_FIELD_ERROR_KEY);
        }
        return valid;
    }
    
}