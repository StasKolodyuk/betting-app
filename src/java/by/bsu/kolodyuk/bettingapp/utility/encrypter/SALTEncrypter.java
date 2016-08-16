
package by.bsu.kolodyuk.bettingapp.utility.encrypter;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import by.bsu.kolodyuk.bettingapp.exception.TechnicalException;



public class SALTEncrypter implements Encrypter {

    public static final String SALT = "vhg234fgfg4566";
    public static final String ENCRYPTER_ALGORITHM = "SHA-256";
    
    @Override
    public String encrypt(String stringToEncrypt) throws TechnicalException {
        try{
            MessageDigest messageDigest = MessageDigest.getInstance(ENCRYPTER_ALGORITHM);
            messageDigest.update(stringToEncrypt.concat(SALT).getBytes());
            byte[] encryptedStringBytes = messageDigest.digest();
            return DatatypeConverter.printHexBinary(encryptedStringBytes);
        } catch(NoSuchAlgorithmException e) {
            throw new TechnicalException(e);
        }
    }
    
}