
package by.bsu.kolodyuk.bettingapp.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SimpleUser extends User implements Serializable {
    
    private int moneyAmount;
    private List<ComplexBet> complexBets;

    public SimpleUser() {
        super(UserType.USER);
    }

    public SimpleUser(int id, String firstName, String lastName, String login, String password, int moneyAmount) {
        super(id, firstName, lastName, login, password, UserType.USER);
        this.moneyAmount = moneyAmount;
        this.complexBets = new ArrayList<>();
    }
    
    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public List<ComplexBet> getComplexBets() {
        return Collections.unmodifiableList(complexBets);
    }

    public void setComplexBets(List<ComplexBet> complexBets) {
        this.complexBets = complexBets;
    }
    
}