
package by.bsu.kolodyuk.bettingapp.command;

import by.bsu.kolodyuk.bettingapp.utility.PathManager;
import by.bsu.kolodyuk.bettingapp.constant.RequestParameterType;
import by.bsu.kolodyuk.bettingapp.controller.SessionRequestContent;
import by.bsu.kolodyuk.bettingapp.utility.URIUtil;


public class RefreshCommand implements Command {

    @Override
    public String execute(SessionRequestContent requestContent) {
        String callerURI = requestContent.getRequestParameter(RequestParameterType.CALLER.getName());
        String pageName = URIUtil.getPageFromURI(callerURI);
        return PathManager.getPagePath(pageName);
    }
    
}