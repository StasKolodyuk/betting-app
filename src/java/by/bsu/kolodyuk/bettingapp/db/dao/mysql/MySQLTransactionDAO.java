
package by.bsu.kolodyuk.bettingapp.db.dao.mysql;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.db.dao.TransactionDAO;
import by.bsu.kolodyuk.bettingapp.db.table.TransactionTableColumn;
import by.bsu.kolodyuk.bettingapp.model.entity.Transaction;
import by.bsu.kolodyuk.bettingapp.model.entity.TransactionType;
import by.bsu.kolodyuk.bettingapp.utility.TimeUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MySQLTransactionDAO implements TransactionDAO {

    private static final String FIND_ALL_TRANSACTIONS_QUERY = "SELECT * "
                                                            + "FROM transaction "
                                                            + "WHERE user_id = ?";
    
    private static final String ADD_TRANSACTION_QUERY = "INSERT INTO transaction "
                                                      + "(user_id, transaction_type, money_amount) "
                                                      + "VALUES(?, ?, ?)";
    
    private Connection connection;

    public MySQLTransactionDAO(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public List<Transaction> findAll(int userId) throws DatabaseException {
        try(PreparedStatement query = connection.prepareStatement(FIND_ALL_TRANSACTIONS_QUERY)) {
            query.setInt(1, userId);
            ResultSet transactionResultSet = query.executeQuery();
            List<Transaction> transactions = new ArrayList<>();
            while(transactionResultSet.next()) {
                transactions.add(createTransaction(transactionResultSet));
            }
            return transactions;
        } catch(SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void addTransaction(int userId, int moneyAmount, TransactionType transactionType) throws DatabaseException {
        try(PreparedStatement query = connection.prepareStatement(ADD_TRANSACTION_QUERY)) {
            query.setInt(1, userId);
            query.setString(2, transactionType.name().toLowerCase());
            query.setInt(3, moneyAmount);
            query.executeUpdate();
        } catch(SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
    private Transaction createTransaction(ResultSet transactionResultSet) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(transactionResultSet.getInt(TransactionTableColumn.ID.getName()));
        transaction.setTime(TimeUtil.convertToCalendar(transactionResultSet.getTimestamp(TransactionTableColumn.TIME.getName())));
        transaction.setType(TransactionType.fromValue(transactionResultSet.getString(TransactionTableColumn.TYPE.getName())));
        transaction.setMoneyAmount(transactionResultSet.getInt(TransactionTableColumn.MONEY_AMOUNT.getName()));
        return transaction;
    }
    
}
