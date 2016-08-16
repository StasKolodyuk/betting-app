
package by.bsu.kolodyuk.bettingapp.db.table;


public enum TransactionTableColumn {
    
    ID("transaction_id"), TYPE("transaction_type"), MONEY_AMOUNT("money_amount"), TIME("transaction_time");
    
    private String name;


    private TransactionTableColumn(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
