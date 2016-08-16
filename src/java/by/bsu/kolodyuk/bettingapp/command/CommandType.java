
package by.bsu.kolodyuk.bettingapp.command;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public enum CommandType {
    
    EMPTY(new EmptyCommand()), LOGIN(new LoginCommand()), LOGOUT(new LogoutCommand()), REGISTER(new RegisterCommand()),
    REFRESH(new RefreshCommand()), FORWARD(new ForwardCommand()), SHOW_ALL_BETS(new ShowAllBetsCommand()), MAKE_BET(new MakeBetCommand()),
    SHOW_MY_BETS(new ShowMyBetsCommand()), ADD_SPORT_EVENTS(new AddSportEventsCommand()), 
    SHOW_ALL_ADMIN_BETS(new ShowAllAdminBetsCommand()), ADD_BETS(new AddBetsCommand()), SHOW_ALL_ENDED_SPORT_EVENTS(new ShowAllEndedSportEventsCommand()),
    ADD_SPORT_EVENT_RESULTS(new AddSportEventResultsCommand()), SHOW_ALL_TRANSACTIONS(new ShowAllTransactionsCommand()),
    MAKE_TRANSACTION(new MakeTransactionCommand()), SHOW_ACCOUNT(new ShowAccountCommand());
    
    private static final Map<String, CommandType> nameToValueMap = new HashMap<>();

    static {
        for (CommandType value : CommandType.values()) {
            nameToValueMap.put(value.name(), value);
        }
    }
    
    private Command command;  
    
    private CommandType(Command command) {
        this.command = command;
    }
    
    public Command getCommand() {
        return command;
    }
    
    public static CommandType forValue(String commandName) {
        if(commandName != null) {
            return nameToValueMap.get(commandName.toUpperCase());
        }
        return null;
    }
    
}
