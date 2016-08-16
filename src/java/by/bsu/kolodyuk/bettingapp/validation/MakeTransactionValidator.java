

package by.bsu.kolodyuk.bettingapp.validation;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.model.entity.TransactionType;


public class MakeTransactionValidator extends Validator {

    private static final String WRONG_CREDIT_CARD_NUMBER_ERROR_KEY = "error.label.wrongCreditCardNumber";
    private static final String WRONG_SECURITY_CODE_ERROR_KEY = "error.label.wrongSecurityCode";
    private static final String WRONG_CREDIT_CARD_TYPE_ERROR_KEY = "error.label.wrongCreditCardType";
    private static final String WRONG_TRANSACTION_TYPE_ERROR_KEY = "error.label.wrongTransactionType";
    private static final String WRONG_MONEY_AMOUNT_ERROR_KEY = "error.label.wrongMoneyAmount";
    
    private static final String CREDIT_CARD_NUMBER_REGEX = "^\\d{13,16}";
    private static final String SECURITY_CODE_REGEX = "^\\d{3}";
    
    private String creditCardType;
    private String creditCardNumber;
    private String securityCode;
    private String moneyAmount;
    private String transactionType;
    
    private int validMoneyAmount;
    private TransactionType validTransactionType;

    public MakeTransactionValidator(String creditCardType, String creditCardName, String securityCode, String moneyAmount, String transactionType) {
        this.creditCardType = creditCardType;
        this.creditCardNumber = creditCardName;
        this.securityCode = securityCode;
        this.moneyAmount = moneyAmount;
        this.transactionType = transactionType;
    }
    
    @Override
    public boolean checkValidity() throws DatabaseException {
        return checkMoneyAmount() && checkCreditCardNumber() && checkCreditCardType() 
                                    && checkSecurityCode() && checkTransactionType();
    }
    
    private boolean checkCreditCardNumber() {
        boolean valid = creditCardNumber.matches(CREDIT_CARD_NUMBER_REGEX);
        if(!valid) {
            setErrorMessageKey(WRONG_CREDIT_CARD_NUMBER_ERROR_KEY);
        }
        return valid;
    }
    
    private boolean checkSecurityCode() {
        boolean valid = securityCode.matches(SECURITY_CODE_REGEX);
        if(!valid) {
            setErrorMessageKey(WRONG_SECURITY_CODE_ERROR_KEY);
        }
        return valid;
    }
    
    private boolean checkCreditCardType() {
        boolean valid = !creditCardType.isEmpty();
        if(!valid) {
            setErrorMessageKey(WRONG_CREDIT_CARD_TYPE_ERROR_KEY);
        }
        return valid;
    }
    
    private boolean checkTransactionType() {
        validTransactionType = TransactionType.fromValue(transactionType);
        boolean valid = validTransactionType != null;
        if(!valid) {
            setErrorMessageKey(WRONG_TRANSACTION_TYPE_ERROR_KEY);
        }
        return valid;
    }
    
    private boolean checkMoneyAmount() {
        boolean valid = true;
        try {
            validMoneyAmount = Integer.parseUnsignedInt(moneyAmount);
        } catch(NumberFormatException e) {
            valid = false;
            setErrorMessageKey(WRONG_MONEY_AMOUNT_ERROR_KEY);
        }
        return valid;
    }

    public int getValidMoneyAmount() {
        return validMoneyAmount;
    }

    public TransactionType getValidTransactionType() {
        return validTransactionType;
    }
    
}
