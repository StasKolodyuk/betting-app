
package by.bsu.kolodyuk.bettingapp.listener;

import by.bsu.kolodyuk.bettingapp.db.pool.ConnectionPool;
import by.bsu.kolodyuk.bettingapp.db.dao.DAOFactory;
import by.bsu.kolodyuk.bettingapp.model.entity.BetType;
import by.bsu.kolodyuk.bettingapp.model.entity.SportType;
import by.bsu.kolodyuk.bettingapp.model.entity.TransactionType;
import by.bsu.kolodyuk.bettingapp.constant.ContextAttributeType;
import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.xml.DOMConfigurator;


public class ContextListener implements ServletContextListener { 

    private static final String LOG4J_CONFIG_LOCATION = "log4j-config-location";
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute(ContextAttributeType.BET_TYPES.getName(), BetType.values());
        context.setAttribute(ContextAttributeType.SPORT_TYPES.getName(), SportType.values());
        context.setAttribute(ContextAttributeType.TRANSACTION_TYPES.getName(), TransactionType.values());
        String log4jConfigFile = context.getInitParameter(LOG4J_CONFIG_LOCATION);
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
        DOMConfigurator.configure(fullPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DAOFactory daoFactory = DAOFactory.newInstance();
        ConnectionPool connectionPool = daoFactory.getConnectionPool();
        connectionPool.close();
    }
    
}
