
package by.bsu.kolodyuk.bettingapp.command;

import by.bsu.kolodyuk.bettingapp.model.entity.User;
import by.bsu.kolodyuk.bettingapp.model.entity.UserType;
import static by.bsu.kolodyuk.bettingapp.model.entity.UserType.ADMIN;
import static by.bsu.kolodyuk.bettingapp.model.entity.UserType.USER;
import by.bsu.kolodyuk.bettingapp.constant.PageType;
import by.bsu.kolodyuk.bettingapp.utility.PathManager;
import by.bsu.kolodyuk.bettingapp.constant.SessionAttributeType;
import by.bsu.kolodyuk.bettingapp.controller.SessionRequestContent;


public class ShowAccountCommand implements Command {
    
    @Override
    public String execute(SessionRequestContent requestContent) {
        PageType page;
        User user = (User)requestContent.getSessionAttribute(SessionAttributeType.USER.getName());
        if(user != null) {
            UserType userType = user.getUserType();
            page = determineUserAccountPage(userType);
        } else {
            page = PageType.LOGIN;
        }
        return PathManager.getPagePath(page.getPageName());
    }
    
    private PageType determineUserAccountPage(UserType userType) {
        PageType page;
        switch(userType) {
                case USER:
                    page = PageType.ACCOUNT;
                    break;
                case ADMIN:
                    page = PageType.ADMIN_ACCOUNT;
                    break;
                default:
                    throw new EnumConstantNotPresentException(userType.getClass(), userType.name());
        }
        return page;
    }
    
}
