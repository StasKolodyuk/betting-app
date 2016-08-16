<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/imports.jspf" %>

<!DOCTYPE html>
<script src="${root}/js/addSportEventsTable.js" type="text/JavaScript"></script>
<script>init("${locale}");</script>
<fmt:message key="adminaddsportevents.title" var="title"/>
<t:adminpage title="${title}">
    <aside class="bettable">
        <form method="POST" action="${root}/Controller">
            <table id="addSportEventsTable" class="flat-table blue">
                <tr>
                    <th><fmt:message key="adminaddsportevents.label.startTime"/></th>
                    <th><fmt:message key="adminaddsportevents.label.endTime"/></th>
                    <th><fmt:message key="adminaddsportevents.label.sportType"/></th>
                    <th><fmt:message key="adminaddsportevents.label.description"/></th>
                </tr>
            </table>
            <input type="hidden" id="newSportEvents" name="newSportEvents" />
            <input type="hidden" name="command" value="add_sport_events" />
            <input type="button" value="<fmt:message key="adminaddsportevents.button.addSportEvent"/>" class="smallbutton" onclick="addRow()"/>
            <input type="submit" value="<fmt:message key="adimnaddsportevents.button.addSportEvents"/>" class="smallbutton" onclick="addSportEvents()" />
        </form>
    </aside>
</t:adminpage>