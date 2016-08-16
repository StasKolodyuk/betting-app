
package by.bsu.kolodyuk.bettingapp.db.table;


public enum ComplexBetTableColumn {
    
    ID("user_bet_id"), MONEY_AMOUNT("bet_money_amount"), RESULT("user_bet_result"),
    COEFFICIENT("user_bet_coefficient");
    
    private String name;

    private ComplexBetTableColumn(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}