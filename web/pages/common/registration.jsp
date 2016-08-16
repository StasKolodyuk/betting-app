<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/imports.jspf" %>

<!DOCTYPE html>
<fmt:message key="registration.title" var="title"/>
<t:betgenericpage title="${title}" bodyclass="registration">
    <c:choose>
        <c:when test="${empty user}">
            <form class="registerform" method="POST" action="${root}/Controller">
                <input type="hidden" name="command" value="register">
                <input type="text" name="login" class="biginput" placeholder="<fmt:message key="registration.input.login"/>" oninvalid="this.setCustomValidity('<fmt:message key="error.label.blankFieldDetected"/>')" oninput="setCustomValidity('')" required /> 
                <input type="password" name="password" pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,})" placeholder="<fmt:message key="registration.input.password"/>" oninvalid="this.setCustomValidity('<fmt:message key="error.label.wrongPassword"/>')" oninput="setCustomValidity('')" class="biginput" />
                <input type="text" name="firstName" class="biginput" placeholder="<fmt:message key="registration.input.firstName"/>" oninvalid="this.setCustomValidity('<fmt:message key="error.label.blankFieldDetected"/>')" oninput="setCustomValidity('')" required/> 
                <input type="text" name="lastName" class="biginput" placeholder="<fmt:message key="registration.input.lastName"/>" oninvalid="this.setCustomValidity('<fmt:message key="error.label.blankFieldDetected"/>')" oninput="setCustomValidity('')" required/>
                <input type="submit" class="bigbutton" value="<fmt:message key="registration.button.register"/>" />
            </form>
        </c:when>
        <c:otherwise>
            <p class="majorText"><fmt:message key="registration.label.alreadyLoggedIn" /></p>
            <form class="logoutform" method="POST" action="${root}/Controller">
                <input type="hidden" name="command" value="logout" />
                <input type="submit" class="bigbutton" value="<fmt:message key="registration.button.logOut"/>" />
            </form>
        </c:otherwise>
    </c:choose>
</t:betgenericpage>