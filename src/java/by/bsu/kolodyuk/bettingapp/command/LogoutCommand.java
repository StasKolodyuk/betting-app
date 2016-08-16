
package by.bsu.kolodyuk.bettingapp.command;

import by.bsu.kolodyuk.bettingapp.constant.PageType;
import by.bsu.kolodyuk.bettingapp.utility.PathManager;
import by.bsu.kolodyuk.bettingapp.constant.RequestAttributeType;
import by.bsu.kolodyuk.bettingapp.constant.SessionAttributeType;
import by.bsu.kolodyuk.bettingapp.controller.SessionRequestContent;


public class LogoutCommand implements Command {

    @Override
    public String execute(SessionRequestContent requestContent) {
        Object sessionLocale = requestContent.getSessionAttribute(SessionAttributeType.LOCALE.getName());
        requestContent.setRequestAttribute(RequestAttributeType.LOCALE.getName(), sessionLocale);
        requestContent.invalidateSession();
        return PathManager.getPagePath(PageType.LOGIN.getPageName());
    }
    
}
