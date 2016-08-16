

package by.bsu.kolodyuk.bettingapp.db.dao;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.model.entity.User;
import by.bsu.kolodyuk.bettingapp.utility.container.Pair;
import java.util.List;


public interface UserDAO {
    
    User findUser(String login) throws DatabaseException;
    User findUser(String login, String password) throws DatabaseException;
    void addUser(User user) throws DatabaseException;
    void updateUserMoney(int userId, int moneyAmount) throws DatabaseException;
    void addUserMoney(int userId, int moneyAmount) throws DatabaseException;
    int findUserMoney(int userId) throws DatabaseException;
    List<Pair<Integer, Integer>> findUserPrizeMoney() throws DatabaseException;
}
