package by.bsu.kolodyuk.bettingapp.validation;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.exception.TechnicalException;
import by.bsu.kolodyuk.bettingapp.model.entity.SimpleUser;
import by.bsu.kolodyuk.bettingapp.utility.JSONUtil;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class MakeBetValidator extends Validator {
    
    private static final String NO_USER_ERROR_KEY = "error.label.noUser";
    private static final String NOT_ENOUGH_MONEY_ERROR_KEY = "error.label.notEnoughMoney";
    private static final String SPORT_EVENT_STARTED_ERROR_KEY = "error.label.sportEventAlreadyStarted";
    private static final String WRONG_BET_TIME_ERROR_KEY = "error.label.wrongBetTime";
    private static final String NO_SELECTED_BETS_ERROR_KEY = "error.label.noBetsSelected";
    private static final String WRONG_BET_COEFFICIENT_ERROR_KEY = "error.label.wrongBetCoefficient";
    
    private String betMoney;
    private SimpleUser user;
    private String resultBet;
    private String betStartTime;
    private String betCoefficient;
    
    private int validBetMoneyAmount;
    private List<Integer> validBetIdList;
    private double validBetCoefficient;
    
    public MakeBetValidator(String betMoney, String betCoefficient,  SimpleUser user, String resultBet, String betStartTime) {
        this.betMoney = betMoney;
        this.betCoefficient = betCoefficient;
        this.user = user;
        this.resultBet = resultBet;
        this.betStartTime = betStartTime;
    }
    
    @Override
    public boolean checkValidity() throws DatabaseException {
        return checkUser() && checkResultBet() && checkBetCoefficient() && checkMoneyAmount() && checkTime();
    }
    
    private boolean checkUser() {
        boolean valid = (user != null);
        if(!valid) {
            setErrorMessageKey(NO_USER_ERROR_KEY);
        }
        return valid;
    }
    
    private boolean checkMoneyAmount() {
        boolean valid;
        try {
            validBetMoneyAmount = Integer.parseInt(betMoney);
            valid = validBetMoneyAmount > 0 ? user.getMoneyAmount() >= validBetMoneyAmount : false;
        } catch(NumberFormatException ex) {
            valid = false;
        }
        if(!valid) {
            setErrorMessageKey(NOT_ENOUGH_MONEY_ERROR_KEY);
        }
        return valid;
    }
    
    private boolean checkTime() {
        boolean valid;
        try {
            long startTimeMillis = Long.parseLong(betStartTime);
            Calendar betStartTimeCalendar = Calendar.getInstance();
            betStartTimeCalendar.setTimeInMillis(startTimeMillis);
            valid = betStartTimeCalendar.after(Calendar.getInstance());
            if(!valid) {
                setErrorMessageKey(SPORT_EVENT_STARTED_ERROR_KEY);
            }
            return valid;
        } catch(NumberFormatException e) {
            valid = false;
            setErrorMessageKey(WRONG_BET_TIME_ERROR_KEY);
        }
        return valid;
    }
    
    private boolean checkResultBet() {
        boolean valid = true;
        try {
            if(resultBet == null) return false;    
            String[] bets = JSONUtil.fromJSON(resultBet, String[].class);
            if(bets.length != 0) {
                validBetIdList = Arrays.stream(bets).map(i -> Integer.parseInt(i)).collect(Collectors.toList());
            } else {
                valid = false;
            }
        } catch (TechnicalException | NumberFormatException ex) {
            valid = false;
        }
        if(!valid) {
            setErrorMessageKey(NO_SELECTED_BETS_ERROR_KEY);
        }
        return valid;
    }
    
    private boolean checkBetCoefficient() {
        boolean valid;
        try {
            double parsedCoefficient = Double.parseDouble(betCoefficient);
            valid = parsedCoefficient > 0;
            if(!valid) {
                setErrorMessageKey(WRONG_BET_COEFFICIENT_ERROR_KEY);
            } else {
                validBetCoefficient = parsedCoefficient;
            }
        } catch(NumberFormatException e) {
            valid = false;
        }
        return valid;
    }

    public int getValidBetMoneyAmount() {
        return validBetMoneyAmount;
    }

    public List<Integer> getValidBetIdList() {
        return validBetIdList;
    }

    public double getValidBetCoefficient() {
        return validBetCoefficient;
    }
    
}
