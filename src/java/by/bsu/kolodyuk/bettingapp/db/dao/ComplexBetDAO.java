
package by.bsu.kolodyuk.bettingapp.db.dao;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.model.entity.ComplexBetWrapper;
import java.util.List;


public interface ComplexBetDAO {
    
    void addComplexBet(int userId, List<Integer> betIdList, int moneyAmount, double coefficient) throws DatabaseException;
    void updateComplexBetResults() throws DatabaseException;
    List<ComplexBetWrapper> findAll(int userId) throws DatabaseException;
    
}
