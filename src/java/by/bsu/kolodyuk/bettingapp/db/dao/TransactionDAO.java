
package by.bsu.kolodyuk.bettingapp.db.dao;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.model.entity.Transaction;
import by.bsu.kolodyuk.bettingapp.model.entity.TransactionType;
import java.util.List;


public interface TransactionDAO {
    
    List<Transaction> findAll(int userId) throws DatabaseException;
    void addTransaction(int userId, int moneyAmount, TransactionType transactionType) throws DatabaseException; 
}
