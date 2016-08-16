
package by.bsu.kolodyuk.bettingapp.model.entity;

import java.io.Serializable;


public class Bet implements Serializable {
    
    private int id;
    private SportEvent sportEvent;
    private BetType type;
    private double coefficient;
    private boolean succeed;

    public Bet() {
        
    }
    
    public Bet(int id, SportEvent sportEvent, BetType type, double coefficient, boolean succeed) {
        this.id = id;
        this.sportEvent = sportEvent;
        this.type = type;
        this.coefficient = coefficient;
        this.succeed = succeed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SportEvent getSportEvent() {
        return sportEvent;
    }

    public void setSportEvent(SportEvent sportEvent) {
        this.sportEvent = sportEvent;
    }

    public BetType getType() {
        return type;
    }

    public void setType(BetType type) {
        this.type = type;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }
    
}