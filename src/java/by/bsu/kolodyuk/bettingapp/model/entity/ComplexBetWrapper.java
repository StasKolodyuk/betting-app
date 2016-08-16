
package by.bsu.kolodyuk.bettingapp.model.entity;

import by.bsu.kolodyuk.bettingapp.utility.container.Triple;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ComplexBetWrapper implements Serializable {
    
    private int moneyAmount;
    private BetResultType result;
    private double coefficient;
    private int prizeMoney;
    private List<Triple<BetType, BetResultType, String>> betsInfo;

    public ComplexBetWrapper() {
        this.betsInfo = new ArrayList<>();
    }

    public ComplexBetWrapper(int moneyAmount, BetResultType result, double coefficient, int prizeMoney) {
        this.moneyAmount = moneyAmount;
        this.result = result;
        this.coefficient = coefficient;
        this.prizeMoney = prizeMoney;
        this.betsInfo = new ArrayList<>();
    }
    
    public ComplexBetWrapper(int moneyAmount, BetResultType result, double coefficient, int prizeMoney, List<Triple<BetType, BetResultType, String>> betsInfo) {
        this.moneyAmount = moneyAmount;
        this.result = result;
        this.coefficient = coefficient;
        this.prizeMoney = prizeMoney;
        this.betsInfo = betsInfo;
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

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public List<Triple<BetType, BetResultType, String>> getBetsInfo() {
        return betsInfo;
    }

    public void setBetsInfo(List<Triple<BetType, BetResultType, String>> betsInfo) {
        this.betsInfo = betsInfo;
    }
    
    public void addBetInfo(BetType betType, BetResultType result, String description) {
        betsInfo.add(new Triple(betType, result, description));
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public void setPrizeMoney(int prizeMoney) {
        this.prizeMoney = prizeMoney;
    }
    
    @Override
    public String toString() {
        return "ComplexBetWrapper{" + "moneyAmount=" + moneyAmount + ", result=" + result + ", coefficient=" + coefficient + ", betsInfo=" + betsInfo + '}';
    }
    
}