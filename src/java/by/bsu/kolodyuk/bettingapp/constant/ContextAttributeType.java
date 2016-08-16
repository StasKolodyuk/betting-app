
package by.bsu.kolodyuk.bettingapp.constant;


public enum ContextAttributeType {
    
    BET_TYPES("betTypes"), TRANSACTION_TYPES("transactionTypes"), SPORT_TYPES("sportTypes");
    
    private String name;

    private ContextAttributeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
