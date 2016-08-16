
package by.bsu.kolodyuk.bettingapp.validation;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.exception.TechnicalException;



public abstract class Validator {
    
    private String errorMessageKey;

    public Validator() {
        this.errorMessageKey = "";
    }
    
    public abstract boolean checkValidity() throws DatabaseException, TechnicalException;

    public String getErrorMessageKey() {
        return errorMessageKey;
    }

    public void setErrorMessageKey(String errorMessageKey) {
        this.errorMessageKey = errorMessageKey;
    }
    
}
