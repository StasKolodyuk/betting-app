
package by.bsu.kolodyuk.bettingapp.db.table;


public enum BetTableColumn {
    
    ID("bet_id"), TYPE("bet_type"), COEFFICIENT("bet_coefficient"), RESULT("bet_result");

    private String name;

    private BetTableColumn(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
