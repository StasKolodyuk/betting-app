
package by.bsu.kolodyuk.bettingapp.db.table;


public enum UserTableColumn {
    
    ID("user_id"), TYPE("user_type"), FIRST_NAME("first_name"), LAST_NAME("last_name"),
    LOGIN("login"), PASSWORD("password"), MONEY_AMOUNT("user_money");
    
    private String name;
    
    private UserTableColumn(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
