<%@tag description="Generic Page Tag" pageEncoding="UTF-8"%>

<%@attribute name="head" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="bodyclass"%>

<html>
    <head>
        <jsp:invoke fragment="head"/>
    </head>
    <body class="${bodyclass}">
        <div class="page-wrap">
            <div id="page-header">
                <jsp:invoke fragment="header"/>
            </div>
            <div id="body">
                <jsp:doBody/>
            </div>
            <div id="page-footer">
                <jsp:invoke fragment="footer"/>
            </div>
        </div>
    </body>
</html>