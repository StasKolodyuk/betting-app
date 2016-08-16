
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/imports.jspf" %>

<!DOCTYPE html>
<fmt:message key="login.title" var="title"/>
<t:betgenericpage title="${title}" bodyclass="home">
    <c:choose>
        <c:when test="${empty user}">
            <form class="loginform" method="POST" action="${root}/Controller">
                <input type="hidden" name="command" value="login">
                <input type="hidden" name="caller" value="login">
                <input type="text" name="login" class="biginput" placeholder="<fmt:message key="login.input.login"/>" oninvalid="this.setCustomValidity('<fmt:message key="error.label.blankFieldDetected"/>')" oninput="setCustomValidity('')" required/> 
                <input type="password" name="password" class="biginput" placeholder="<fmt:message key="login.input.password"/>" oninvalid="this.setCustomValidity('<fmt:message key="error.label.blankFieldDetected"/>')" oninput="setCustomValidity('')" required />
                <input type="submit" class="bigbutton" value="<fmt:message key="login.button.logIn"/>" />
            </form>
        </c:when>
        <c:otherwise>
            <p class="majorText"><fmt:message key="login.label.alreadyLoggedIn" /></p>
            <form class="logoutform" method="POST" action="${root}/Controller">
                <input type="hidden" name="command" value="logout" />
                <input type="submit" class="bigbutton" value="<fmt:message key="login.button.logOut"/>" />
            </form>
        </c:otherwise>
    </c:choose>
</t:betgenericpage>