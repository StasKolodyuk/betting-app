
package by.bsu.kolodyuk.bettingapp.model.entity;

import java.io.Serializable;
import java.util.Calendar;


public class SportEvent implements Serializable {
    
    private int id;
    private String description;
    private SportType sportType;
    private Calendar startTime;
    private Calendar endTime;
    
    public SportEvent() {
    }

    public SportEvent(int id, String description, SportType sportType, Calendar startTime, Calendar endTime) {
        this.id = id;
        this.description = description;
        this.sportType = sportType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }
    
}