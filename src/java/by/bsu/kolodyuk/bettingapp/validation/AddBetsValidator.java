
package by.bsu.kolodyuk.bettingapp.validation;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.model.entity.BetType;
import by.bsu.kolodyuk.bettingapp.utility.container.Triple;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AddBetsValidator extends Validator {
    
    private static final String WRONG_BETS_ADDED_ERROR_KEY = "error.label.wrongBetsAdded";
    private static final String NO_BETS_ADDED_ERROR_KEY = "error.label.noBetsAdded";


    private List<Triple<Integer, BetType, Double>> betsInfo;
    private List<Triple<Integer, BetType, Double>> validBetsInfo;
    
    public AddBetsValidator(List<Triple<Integer, BetType, Double>> betsInfo) {
        this.betsInfo = betsInfo;
        this.validBetsInfo = new ArrayList<>();
    }
    
    
    @Override
    public boolean checkValidity() throws DatabaseException {
        return checkEmptiness() && checkBetsInfo();
    }
    
    private boolean checkBetsInfo() {
        validBetsInfo = betsInfo.stream().filter(i -> checkBetInfo(i)).collect(Collectors.toList());
        boolean valid = validBetsInfo.size() == betsInfo.size();
        if (!valid) {
            setErrorMessageKey(WRONG_BETS_ADDED_ERROR_KEY);
        }
        return valid;
    }
    
    private boolean checkEmptiness() {
        boolean valid = (betsInfo != null && !betsInfo.isEmpty());
        if(!valid) {
            setErrorMessageKey(NO_BETS_ADDED_ERROR_KEY);
        }
        return valid;
    }
    
    private boolean checkBetInfo(Triple<Integer, BetType, Double> betInfo) {
        return checkSportEventId(betInfo.getFirst()) && checkBetCoefficient(betInfo.getThird());
    }
    
    private boolean checkSportEventId(int sportEventId) {
        return sportEventId > 0;
    }
    
    private boolean checkBetCoefficient(double betCoefficient) {
        return betCoefficient > 0;
    }

    public List<Triple<Integer, BetType, Double>> getValidBetsInfo() {
        return validBetsInfo;
    }
    
}
