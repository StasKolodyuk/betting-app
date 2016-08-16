
package by.bsu.kolodyuk.bettingapp.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.HashMap;
import java.util.Map;


public enum SportType {
    
    FOOTBALL("sport.type.football"), HOCKEY("sport.type.hockey"), TENNIS("sport.type.tennis");

    private static Map<String, SportType> nameToValueMap = new HashMap<>();

    static {
        for (SportType value : SportType.values()) {
            nameToValueMap.put(value.name(), value);
        }
    }
    
    private String key;

    private SportType(String key) {
        this.key = key;
    }

    @JsonCreator
    public static SportType forValue(String sportTypeName) {
        return nameToValueMap.get(sportTypeName.toUpperCase());
    }

    public String getKey() {
        return key;
    }
    
}