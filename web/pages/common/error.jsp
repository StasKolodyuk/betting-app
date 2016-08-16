<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/imports.jspf" %>

<!DOCTYPE html>
<fmt:message key="error.title" var="title"/>
<t:betgenericpage title="${title}">
    <aside>
        <img src="${root}/images/erroricon.png" alt="" />
    </aside>
    <p class="bigMajorText">
        <br/><br/>
        <fmt:message key="error.label.smthWentWrong" />
    </p>
</t:betgenericpage>