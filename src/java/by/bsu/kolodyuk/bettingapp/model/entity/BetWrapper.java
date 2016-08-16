
package by.bsu.kolodyuk.bettingapp.model.entity;

import by.bsu.kolodyuk.bettingapp.utility.container.Pair;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class BetWrapper implements Serializable {

    private int sportEventId;
    private String description;
    private Calendar startTime;
    private Map<String, Pair<Integer, Double>> map;

    public BetWrapper() {
        this.map = new HashMap<>();
    }

    public BetWrapper(int sportEventId, String description, Calendar startTime) {
        this.sportEventId = sportEventId;
        this.description = description;
        this.startTime = startTime;
        this.map = new HashMap<>();
    }
    
    
    
    public void addBetInfo(int id, String type, double coefficient) {
        map.put(type, new Pair<>(id, coefficient));
    }
    
    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Pair<Integer, Double>> getMap() {
        return map;
    }

    public void setMap(Map<String, Pair<Integer, Double>> map) {
        this.map = map;
    }

    public int getSportEventId() {
        return sportEventId;
    }

    public void setSportEventId(int sportEventId) {
        this.sportEventId = sportEventId;
    }
    
}