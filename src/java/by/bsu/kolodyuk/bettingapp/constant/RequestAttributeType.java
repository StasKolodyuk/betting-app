
package by.bsu.kolodyuk.bettingapp.constant;


public enum RequestAttributeType {
    
    ERROR_MESSAGE_KEY("errorMessageKey"), LOCALE("locale");
    
    private String name;

    private RequestAttributeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
