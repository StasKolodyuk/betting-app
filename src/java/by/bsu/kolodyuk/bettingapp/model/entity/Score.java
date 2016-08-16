
package by.bsu.kolodyuk.bettingapp.model.entity;

import java.io.Serializable;


public class Score implements Serializable {
    
    private int homeTeamScored;
    private int guestTeamScored;

    public Score() {
    }
    
    public Score(int homeTeamScored, int guestTeamScored) {
        this.homeTeamScored = homeTeamScored;
        this.guestTeamScored = guestTeamScored;
    }

    public int getHomeTeamScored() {
        return homeTeamScored;
    }

    public void setHomeTeamScored(int homeTeamScored) {
        this.homeTeamScored = homeTeamScored;
    }

    public int getGuestTeamScored() {
        return guestTeamScored;
    }

    public void setGuestTeamScored(int guestTeamScored) {
        this.guestTeamScored = guestTeamScored;
    }

    @Override
    public String toString() {
        return homeTeamScored + "-" + guestTeamScored;
    }
    
    
    
}
