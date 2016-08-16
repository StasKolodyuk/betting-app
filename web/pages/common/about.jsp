
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/imports.jspf" %>

<!DOCTYPE html>
<fmt:message key="about.title" var="title"/>
<t:betgenericpage title="${title}" bodyclass="about">
    <aside>
        <img src="${root}/images/sporticons.png" alt="" />
    </aside>
    <p class="majorText">
        <br/><br/>
        <fmt:message key="about.text" />
    </p>
</t:betgenericpage>