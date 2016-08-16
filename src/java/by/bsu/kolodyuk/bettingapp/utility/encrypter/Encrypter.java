
package by.bsu.kolodyuk.bettingapp.utility.encrypter;

import by.bsu.kolodyuk.bettingapp.exception.TechnicalException;

public interface Encrypter {
    
    String encrypt(String stringToEncrypt) throws TechnicalException;
    
}

