
package by.bsu.kolodyuk.bettingapp.constant;


public enum SessionAttributeType {
    
    USER("user"), LOCALE("locale"), ADMIN_BETS("adminBets"), BETS("bets"), 
    ENDED_SPORT_EVENTS("endedSportEvents"), TRANSACTIONS("transactions"), 
    COMPLEX_BETS("complexBets");
    
    private String name;

    private SessionAttributeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
