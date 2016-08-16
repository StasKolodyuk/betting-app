
package by.bsu.kolodyuk.bettingapp.db.table;


public enum ExtraTableColumn {
    
    BET_PRIZE_MONEY("bet_prize"), DESCRIPTION("description");
    
    private String name;

    private ExtraTableColumn(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
