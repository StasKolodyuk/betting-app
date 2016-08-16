
package by.bsu.kolodyuk.bettingapp.validation;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.exception.TechnicalException;
import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionWrapper;
import by.bsu.kolodyuk.bettingapp.db.dao.DAOFactory;
import by.bsu.kolodyuk.bettingapp.db.dao.UserDAO;
import by.bsu.kolodyuk.bettingapp.model.entity.User;
import by.bsu.kolodyuk.bettingapp.utility.encrypter.SALTEncrypter;


public class LogInValidator extends Validator {
    
    private static final String WRONG_LOGIN_ERROR_KEY = "login.label.logInErrorMessage";
    
    private String login;
    private String password;
    private ConnectionWrapper connection;
    private User user;
    
    public LogInValidator(String login, String password, ConnectionWrapper connection) {
        super();
        this.login = login;
        this.password = password;
        this.connection = connection;
    }
    
    @Override
    public boolean checkValidity() throws DatabaseException, TechnicalException {
        DAOFactory daoFactory = DAOFactory.newInstance();
        UserDAO userDAO = daoFactory.getUserDAO(connection);
        user = userDAO.findUser(login, new SALTEncrypter().encrypt(password));
        boolean valid = (user != null);
        if(!valid) {
            setErrorMessageKey(WRONG_LOGIN_ERROR_KEY);
        }
        return valid;
    }
    
    public User getLoggedInUser() {
        return user;
    }
    
}