<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@attribute name="adminuser" required="true" type="by.bsu.kolodyuk.bettingapp.model.entity.Admin" %>

<%@include file="/WEB-INF/jspf/imports.jspf"%>

<aside>
    <div class="profile">
        <div class="profile_image_slider">	
        </div>
        <div class="profile_txt_body">
            <h4>${adminuser.login}</h4>
            <form method="POST" action="${root}/Controller">
                <input type="hidden" name="command" value="forward" />
                <input type="hidden" name="page" value="adminaddsportevents" />
                <input type="submit" class="smallbutton" value="<fmt:message key="adminmenu.button.addSportEvents"/>" />
            </form>
            <form method="POST" action="${root}/Controller">
                <input type="hidden" name="command" value="show_all_admin_bets" />
                <input type="hidden" name="page" value="adminaddbets" />
                <input type="submit" class="smallbutton" value="<fmt:message key="adminmenu.button.addBets"/>" />
            </form>
            <form method="POST" action="${root}/Controller">
                <input type="hidden" name="command" value="show_all_ended_sport_events" />
                <input type="submit" class="smallbutton" value="<fmt:message key="adminmenu.button.addResults"/>" />
            </form>
        </div>
    </div>
</aside>