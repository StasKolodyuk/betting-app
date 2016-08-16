
package by.bsu.kolodyuk.bettingapp.command;


import by.bsu.kolodyuk.bettingapp.constant.PageType;
import by.bsu.kolodyuk.bettingapp.utility.PathManager;
import by.bsu.kolodyuk.bettingapp.controller.SessionRequestContent;

public class EmptyCommand implements Command {

    @Override
    public String execute(SessionRequestContent requestContent) {
        return PathManager.getPagePath(PageType.LOGIN.getPageName());
    }
    
}
