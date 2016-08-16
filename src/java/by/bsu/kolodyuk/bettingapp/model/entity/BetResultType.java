
package by.bsu.kolodyuk.bettingapp.model.entity;


public enum BetResultType {
    
    WIN("bet.result.win"), LOSS("bet.result.loss"), UNDEFINED("bet.result.undefined");
    
    private String key;

    private BetResultType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
    
    public static BetResultType fromValue(boolean result) {
        return result ? WIN : LOSS;
    }
    
}
