
package by.bsu.kolodyuk.bettingapp.db.pool.mysql;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.exception.TechnicalException;
import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionPool;
import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionWrapper;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;

public class MySQLConnectionPool implements ConnectionPool<Connection> {
    
    private static final Logger LOGGER = Logger.getLogger(MySQLConnectionPool.class);
    
    private static class ConnectionPoolHolder {
        private static final MySQLConnectionPool INSTANCE;
        static {
            try {
                INSTANCE = new MySQLConnectionPool();
            } catch (DatabaseException e) {
                throw new ExceptionInInitializerError(e);
            }
        }        
    }
    
    private static final int POOL_CAPACITY = 10;
    private static final int POOL_START_SIZE = 3;
    private static final int MAX_WAIT_TIME_SECONDS = 5;
    private static final int MAX_CLOSING_TIME_MILLIS = 1000;
    private static final String DB_PROPERTIES_FILE_NAME = "database.properties";
    
    private BlockingQueue<Connection> pool;
    private BlockingQueue<Connection> usedConnections;
    private AtomicInteger connectionCount;
    private Lock lock;
    private boolean enabled;
    private DBConnector dbConnector;

    private MySQLConnectionPool() throws DatabaseException {
        try {
            DriverManager.registerDriver(new Driver());
            enabled = true;
            dbConnector = new DBConnector(DB_PROPERTIES_FILE_NAME);
            pool = new ArrayBlockingQueue<>(POOL_CAPACITY);
            usedConnections = new ArrayBlockingQueue<>(POOL_CAPACITY);
            connectionCount = new AtomicInteger();
            lock = new ReentrantLock();
            for(int i = 0; i < POOL_START_SIZE; i++) {
                pool.offer(dbConnector.getConnection());
                connectionCount.incrementAndGet();
            }
        } catch(SQLException | TechnicalException e) {
            throw new DatabaseException(e);
        }
    }
    
    @Override
    public ConnectionWrapper<Connection> getConnection() throws DatabaseException {
        try {
            if(!enabled) {
                throw new DatabaseException("Connection pool is disabled");
            }
            Connection connection = pool.poll(MAX_WAIT_TIME_SECONDS, TimeUnit.SECONDS);
            lock.lock();
            if(connection == null) {
                if(connectionCount.get() < POOL_CAPACITY) {
                    connection = dbConnector.getConnection();
                    connectionCount.incrementAndGet();
                }
            }
            usedConnections.offer(connection);
            return new MySQLConnectionWrapper(connection);
        } catch(InterruptedException e) {
            throw new DatabaseException(e);
        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public void releaseConnection(ConnectionWrapper<Connection> connection) {
        usedConnections.remove(connection.getConnection());
        pool.offer(connection.getConnection());
    }

    @Override
    public void close() {
        enabled = false;
        try {
            Thread.sleep(MAX_CLOSING_TIME_MILLIS);
        } catch (InterruptedException ex) {
            LOGGER.error("Closing connection pool thread was suddenly interrupted", ex);
        }
        Iterator<Connection> usedConnectionsIterator = usedConnections.iterator();
        while(usedConnectionsIterator.hasNext()) {
            releaseConnection(new MySQLConnectionWrapper(usedConnectionsIterator.next()));
        }
        Iterator<Connection> poolIterator = pool.iterator();
        while(poolIterator.hasNext()) {
            closeConnection(poolIterator.next());
        }
    }
    
    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Closing a connection failed", e);
        }
    }
    
    public static MySQLConnectionPool newInstance() {
        return ConnectionPoolHolder.INSTANCE;
    }
}