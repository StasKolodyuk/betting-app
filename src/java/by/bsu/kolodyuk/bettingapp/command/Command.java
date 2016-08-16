
package by.bsu.kolodyuk.bettingapp.command;

import by.bsu.kolodyuk.bettingapp.controller.SessionRequestContent;


public interface Command {
    
    String execute(SessionRequestContent requestContent);
    
}
