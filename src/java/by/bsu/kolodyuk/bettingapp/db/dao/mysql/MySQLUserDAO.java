
package by.bsu.kolodyuk.bettingapp.db.dao.mysql;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.db.table.ExtraTableColumn;
import by.bsu.kolodyuk.bettingapp.db.dao.UserDAO;
import by.bsu.kolodyuk.bettingapp.db.table.UserTableColumn;
import by.bsu.kolodyuk.bettingapp.model.entity.Admin;
import by.bsu.kolodyuk.bettingapp.model.entity.SimpleUser;
import by.bsu.kolodyuk.bettingapp.model.entity.User;
import by.bsu.kolodyuk.bettingapp.model.entity.UserType;
import by.bsu.kolodyuk.bettingapp.utility.container.Pair;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class MySQLUserDAO implements UserDAO {
    
    private final String FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT * "
                                                               + "FROM user "
                                                               + "WHERE login = ? "
                                                               + "AND password = ? ";
    
    private final String FIND_USER_BY_LOGIN_QUERY = "SELECT * "
                                                  + "FROM user "
                                                  + "WHERE login = ? ";
    
    private final String INSERT_USER_QUERY = "INSERT INTO user "
                                           + "(user_type, first_name, last_name, login, password) "
                                           + "VALUES (?, ?, ?, ?, ?) ";
    
    private final String UPDATE_USER_MONEY_QUERY = "UPDATE user "
                                                 + "SET user_money = ? "
                                                 + "WHERE user_id = ?";
    
    private final String ADD_USER_MONEY_QUERY = "UPDATE user "
                                              + "SET user_money = user_money + ? "
                                              + "WHERE user_id = ?";
    
    private final String FIND_USER_MONEY = "SELECT user_money "
                                         + "FROM user "
                                         + "WHERE user_id = ?";
    
    private final String FIND_USER_PRIZE_MONEY = "SELECT user_id, "
                                               + "ROUND(bet_money_amount*betResult*user_bet_coefficient) "
                                               + "AS bet_prize FROM user_bet ub "
                                               + "JOIN "
                                               + "(SELECT c.user_bet_id, SUM(b.bet_result != 1) = 0 AS betResult "
                                               + "FROM user_bet_consist c "
                                               + "JOIN bet b "
                                               + "ON b.bet_id = c.bet_id "
                                               + "GROUP BY user_bet_id "
                                               + "HAVING SUM(b.bet_result IS NULL) = 0 "
                                               + "AND betResult = 1) res "
                                               + "USING(user_bet_id) "
                                               + "JOIN user "
                                               + "USING(user_id) "
                                               + "WHERE user_bet_result IS NULL";
    
    private Connection connection;

    public MySQLUserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findUser(String login) throws DatabaseException {
        try (PreparedStatement query = connection.prepareStatement(FIND_USER_BY_LOGIN_QUERY)) {
            query.setString(1, login);
            ResultSet userResultSet = query.executeQuery();
            User user = null;
            if (userResultSet.next()) {
                user = createUser(userResultSet);
            }
            return user;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
    
    @Override
    public User findUser(String login, String password) throws DatabaseException {
        try (PreparedStatement query = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY)) {
            query.setString(1, login);
            query.setString(2, password);
            ResultSet userResultSet = query.executeQuery();
            User user = null;
            if(userResultSet.next()) {
                user = createUser(userResultSet);
            }
            return user;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void addUser(User user) throws DatabaseException {
        try (PreparedStatement query = connection.prepareStatement(INSERT_USER_QUERY)) {
            query.setString(1, user.getUserType().toString().toLowerCase());
            query.setString(2, user.getFirstName());
            query.setString(3, user.getLastName());
            query.setString(4, user.getLogin());
            query.setString(5, user.getPassword());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void updateUserMoney(int userId, int moneyAmount) throws DatabaseException {
        try(PreparedStatement query = connection.prepareStatement(UPDATE_USER_MONEY_QUERY)) {
            query.setInt(1, moneyAmount);
            query.setInt(2, userId);
            query.executeUpdate();
        } catch(SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void addUserMoney(int userId, int moneyAmount) throws DatabaseException {
        try (PreparedStatement query = connection.prepareStatement(ADD_USER_MONEY_QUERY)) {
            query.setInt(1, moneyAmount);
            query.setInt(2, userId);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
    @Override
    public int findUserMoney(int userId) throws DatabaseException {
        try(PreparedStatement query = connection.prepareStatement(FIND_USER_MONEY)) {
            query.setInt(1, userId);
            ResultSet userMoneyResultSet = query.executeQuery();
            int moneyAmount = -1;
            if(userMoneyResultSet.next()) {
                moneyAmount = userMoneyResultSet.getInt(UserTableColumn.MONEY_AMOUNT.getName());
            }
            return moneyAmount;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
    
    private User createUser(ResultSet resultSet) throws SQLException {
        UserType userType = UserType.valueOf(resultSet.getString(UserTableColumn.TYPE.getName()).toUpperCase());
        int userId = resultSet.getInt(UserTableColumn.ID.getName()); 
        String login = resultSet.getString(UserTableColumn.LOGIN.getName());
        String password = resultSet.getString(UserTableColumn.PASSWORD.getName());
        String firstName = resultSet.getString(UserTableColumn.FIRST_NAME.getName());
        String lastName = resultSet.getString(UserTableColumn.LAST_NAME.getName());
        return createDeterminedUser(userType, userId, firstName, lastName, login, password, resultSet);
    }
    
    private User createDeterminedUser(UserType userType, int userId, String firstName, String lastName, String login, String password, ResultSet resultSet) throws SQLException {
        User user;
        switch (userType) {
            case ADMIN:
                user = new Admin(userId, firstName, lastName, login, password);
                break;
            case USER:
                int moneyAmount = resultSet.getInt(UserTableColumn.MONEY_AMOUNT.getName());
                user = new SimpleUser(userId, firstName, lastName, login, password, moneyAmount);
                break;
            default:
                throw new EnumConstantNotPresentException(UserType.class, String.valueOf(userType));
        }
        return user;
    }

    @Override
    public List<Pair<Integer, Integer>> findUserPrizeMoney() throws DatabaseException {
        try(PreparedStatement query = connection.prepareStatement(FIND_USER_PRIZE_MONEY)) {
            ResultSet usersPrizeMoneyResultSet = query.executeQuery();
            List<Pair<Integer, Integer>> usersPrizeMoney = new ArrayList<>();
            while(usersPrizeMoneyResultSet.next()) {
                int userId = usersPrizeMoneyResultSet.getInt(UserTableColumn.ID.getName());
                int prizeMoney = usersPrizeMoneyResultSet.getInt(ExtraTableColumn.BET_PRIZE_MONEY.getName());
                usersPrizeMoney.add(new Pair<>(userId, prizeMoney));
            }
            return usersPrizeMoney;
        } catch(SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
}