<%@tag description="User Page Template" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true"%>

<%@include file="/WEB-INF/jspf/imports.jspf"%>

<t:betgenericpage title="${title}" bodyclass="account">
    <jsp:body>
        <t:adminmenu adminuser="${user}"/>
        <jsp:doBody/>
    </jsp:body>
</t:betgenericpage>