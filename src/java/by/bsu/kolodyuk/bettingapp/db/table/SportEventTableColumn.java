
package by.bsu.kolodyuk.bettingapp.db.table;


public enum SportEventTableColumn {
    
    ID("event_id"), TYPE("event_type"), SPORT_TYPE("sport_type"), DESCRIPTION("event_description"),
    START_TIME("event_start_time"), END_TIME("event_end_time");
    
    private String name;

    private SportEventTableColumn(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
