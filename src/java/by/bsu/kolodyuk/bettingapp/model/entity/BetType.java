
package by.bsu.kolodyuk.bettingapp.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;


public enum BetType{
    
    HOME_TEAM("bet.type.homeTeam", (ht, gt) -> ht > gt), 
    DRAW("bet.type.draw", (ht, gt) -> ht.equals(gt)),
    GUEST_TEAM("bet.type.guestTeam", (ht, gt) -> ht < gt), 
    HOME_TEAM_OR_DRAW("bet.type.homeTeamOrDraw", (ht, gt) -> ht >= gt),
    GUEST_TEAM_OR_DRAW("bet.type.guestTeamOrDraw",(ht, gt) -> ht <= gt), 
    HOME_OR_GUEST_TEAM("bet.type.homeOrGuestTeam", (ht, gt) -> !ht.equals(gt)),
    TOTAL_GREATER("bet.type.totalGreater", (ht, gt) -> ht + gt > 2.5), 
    TOTAL_LESS("bet.type.totalLess", (ht, gt) -> ht + gt < 2.5);
    
    
    private static Map<String, BetType> nameToValueMap = new HashMap<>();
    
    
    static {
        for (BetType value : BetType.values()) {
            nameToValueMap.put(value.name(), value);
        }
    }

    private String key;
    private BiPredicate<Integer, Integer> winningRule;
    
    private BetType(String key, BiPredicate<Integer, Integer> winningRule) {
        this.key = key;
        this.winningRule = winningRule;
    }

    public String getKey() {
        return key;
    }
    
    public boolean hasWon(Score score) {
        return winningRule.test(score.getHomeTeamScored(), score.getGuestTeamScored());
    }
    
    
    public static List<String> getKeys() {
        return Arrays.stream(BetType.values()).map(i -> i.key).collect(Collectors.toList());
    }
    
    @JsonCreator
    public static BetType forValue(String betTypeName) {
        return nameToValueMap.get(betTypeName.toUpperCase());
    }
    
}