
<%@tag description="Header Tag" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/imports.jspf"%>

<header>  	   
    <div class="wrap">	      	   
        <div class="logo">
            <a href="${root}/index.jsp"><img src="${root}/images/logo.png" alt="" /></a>
        </div>	
        <div class="menu">
            <ul class="nav" id="nav">
                <li class="home">
                    <a href="${root}/index.jsp">
                        <fmt:message key="header.label.home" />
                    </a>
                </li>
                <li class="registration">
                    <a href="${root}/pages/common/registration.jsp">
                        <fmt:message key="header.label.registration" />
                    </a>
                </li>
                <li class="account">
                    <form method="POST" action="${root}/Controller">
                        <input type="hidden" name="command" value="show_account" />
                        <a><input type="submit" class="anchor" value="<fmt:message key="header.label.account" />" /></a>
                    </form>
                </li>
                <li class="about">
                    <a href="${root}/pages/common/about.jsp">
                        <fmt:message key="header.label.about" />
                    </a>
                </li>																
                <li class="contact">
                    <a href="${root}/pages/common/contact.jsp">
                        <fmt:message key="header.label.contact" />
                    </a>
                </li>
                <li>
                    <form class="languagebar" method="POST">
                        <input type="hidden" name="caller" value="${pageContext.request.requestURI}" />
                        <input type="hidden" name="command" value="refresh" />
                        <select name="locale"  onchange="submit()">
                            <option value="en_EN" ${locale == 'en_EN' ? 'selected' : ''}>En</option>
                            <option value="ru_RU" ${locale == 'ru_RU' ? 'selected' : ''}>Ru</option>
                        </select>
                    </form>
                </li>
                <li>
                <c:choose>
                    <c:when test="${not empty user}">
                        <form  method="POST" action="${root}/Controller">
                            <input type="hidden" name="command" value="logout" />
                            <input type="submit" class="smallbutton" value="<fmt:message key="header.button.logOut"/>" />
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form  method="POST" action="${root}/Controller">
                            <input type="hidden" name="command" value="forward" />
                            <input type="hidden" name="page" value="login" />
                            <input type="submit" class="smallbutton" value="<fmt:message key="header.button.logIn"/>" />
                        </form>
                    </c:otherwise>
                </c:choose>
                </li>
            </ul>
        </div>
    </div>
</header>