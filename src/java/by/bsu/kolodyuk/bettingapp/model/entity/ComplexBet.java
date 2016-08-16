
package by.bsu.kolodyuk.bettingapp.model.entity;

import java.io.Serializable;
import java.util.List;


public class ComplexBet implements Serializable {
    
    private int id;
    private List<Bet> bets;
    private int moneyAmount;
    private BetResultType result;

    public ComplexBet(int id, List<Bet> bets, int moneyAmount, BetResultType result) {
        this.id = id;
        this.bets = bets;
        this.moneyAmount = moneyAmount;
        this.result = result;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public BetResultType getResult() {
        return result;
    }

    public void setResult(BetResultType result) {
        this.result = result;
    }
    
}
