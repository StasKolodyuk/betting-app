
package by.bsu.kolodyuk.bettingapp.command;

import by.bsu.kolodyuk.bettingapp.utility.PathManager;
import by.bsu.kolodyuk.bettingapp.constant.RequestParameterType;
import by.bsu.kolodyuk.bettingapp.controller.SessionRequestContent;

public class ForwardCommand implements Command {

    @Override
    public String execute(SessionRequestContent requestContent) {
        String pageName = requestContent.getRequestParameter(RequestParameterType.PAGE.getName());
        return PathManager.getPagePath(pageName);
    }
    
}