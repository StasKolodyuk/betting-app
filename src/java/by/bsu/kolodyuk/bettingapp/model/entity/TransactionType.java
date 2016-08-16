
package by.bsu.kolodyuk.bettingapp.model.entity;


public enum TransactionType {
    
    DEPOSIT("transaction.type.deposit"), WITHDRAWAL("transaction.type.withdrawal");
    
    private static final TransactionType[] VALUES_COPY = values();

    public static TransactionType fromValue(String transactionTypeName) {
        for (TransactionType transactionType : VALUES_COPY) {
            if(transactionType.name().equals(transactionTypeName.toUpperCase())) {
                return transactionType;
            }
        }
        return null;
    }
    
    private String key;

    private TransactionType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
    
}
