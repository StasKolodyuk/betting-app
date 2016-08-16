
package by.bsu.kolodyuk.bettingapp.constant;


public enum RequestParameterType {
    
    COMMAND("command"), NEW_BETS("newBets"), SPORT_EVENT_RESULTS("sportEventResults"), PAGE("page"), LOGIN("login"), PASSWORD("password"), RESULT_BET("resultBet"),
    RESULT_BET_COEFFICIENT("resultBetCoefficient"), EARLIEST_BET_START_TIME("earliestBetStartTime"), MONEY_AMOUNT("moneyAmount"), 
    CREDIT_CARD_TYPE("creditCardType"), CREDIT_CARD_NUMBER("creditCardNumber"), SECURITY_CODE("securityCode"),TRANSACTION_TYPE("transactionType"),
    CALLER("caller"), FIRST_NAME("firstName"), LAST_NAME("lastName");
    
    private String name;

    private RequestParameterType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
