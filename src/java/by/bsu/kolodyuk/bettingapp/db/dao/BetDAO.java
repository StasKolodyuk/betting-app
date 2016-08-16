
package by.bsu.kolodyuk.bettingapp.db.dao;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.model.entity.BetType;
import by.bsu.kolodyuk.bettingapp.model.entity.BetWrapper;
import by.bsu.kolodyuk.bettingapp.utility.container.Pair;
import java.util.List;


public interface BetDAO {
    
    List<BetWrapper> findAllAndWrap() throws DatabaseException;
    List<BetWrapper> findAllAndWrapIncludingSpare() throws DatabaseException;
    void addBet(int sportEventId, BetType betType, double coefficient) throws DatabaseException;
    void updateBetResult(int betId, boolean result) throws DatabaseException;
    List<Pair<Integer, BetType>> findAllConnectedBets(int sportEventId) throws DatabaseException;
}
