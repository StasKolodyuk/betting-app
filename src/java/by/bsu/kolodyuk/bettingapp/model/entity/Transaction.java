
package by.bsu.kolodyuk.bettingapp.model.entity;

import java.io.Serializable;
import java.util.Calendar;


public class Transaction implements Serializable {
    
    private int id;
    private Calendar time;
    private TransactionType type;
    private int moneyAmount;

    public Transaction() {
    }
    
    public Transaction(int id, Calendar time, TransactionType type, int moneyAmount) {
        this.id = id;
        this.time = time;
        this.type = type;
        this.moneyAmount = moneyAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }
    
}
