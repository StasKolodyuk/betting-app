
<%@tag description="Bet Generic Page Tag" pageEncoding="UTF-8" %>

<%@attribute name="title" required="false" %>
<%@attribute name="bodyclass" required="false" %>

<%@include file="/WEB-INF/jspf/imports.jspf"%>

<t:genericpage bodyclass="${bodyclass}">
    
    <jsp:attribute name="head">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title}</title>
        <link href="${root}/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    </jsp:attribute>
        
    <jsp:attribute name="header">
        <t:betheader/>
    </jsp:attribute>

    <jsp:attribute name="footer">
        <t:betfooter/>
    </jsp:attribute>

    <jsp:body>
        <jsp:doBody/>
        <c:if test="${not empty errorMessageKey}">
            <p class="errorText"><fmt:message key="${errorMessageKey}"/></p>
        </c:if>
    </jsp:body>

</t:genericpage>