<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@tag import="by.bsu.kolodyuk.bettingapp.model.entity.SimpleUser"%>
<%@attribute name="simpleUser" required="true" type="by.bsu.kolodyuk.bettingapp.model.entity.SimpleUser" %>


<%@include file="/WEB-INF/jspf/imports.jspf"%>

<aside>
    <div class="profile">
        <div class="profile_image_slider">	
        </div>
        <div class="profile_txt_body">
            <h4>${simpleUser.login}</h4>
            <p>${simpleUser.moneyAmount}</p>
            <form method="POST" action="${root}/Controller">
                <input type="hidden" name="command" value="show_all_bets" />
                <input type="submit" class="smallbutton" value="<fmt:message key="usermenu.button.allBets"/>" />
            </form>
            <form method="POST" action="${root}/Controller">
                <input type="hidden" name="command" value="show_my_bets" />
                <input type="submit" class="smallbutton" value="<fmt:message key="usermenu.button.myBets"/>" />
            </form>
            <form method="POST" action="${root}/Controller">
                <input type="hidden" name="command" value="show_all_transactions" />
                <input type="submit" class="smallbutton" value="<fmt:message key="usermenu.button.transactions"/>" />
            </form>
        </div>
    </div>
</aside>