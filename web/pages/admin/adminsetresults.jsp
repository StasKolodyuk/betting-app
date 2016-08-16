<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/imports.jspf" %>

<!DOCTYPE html>

<script src="${root}/js/addSportEventResultsTable.js" type="text/JavaScript"></script>
<fmt:message key="adminsetresults.title" var="title"/>
<t:adminpage title="${title}">
    <c:choose>
        <c:when test="${not empty endedSportEvents}">
            <aside class="bettable">
                <form method="POST" action="${root}/Controller">
                    <table id="setResultsTable" class="flat-table blue">
                        <tr>
                            <th><fmt:message key="adminsetresults.label.startTime"/></th>
                            <th><fmt:message key="adminsetresults.label.endTime"/></th>
                            <th><fmt:message key="adminsetresults.label.sportType"/></th>
                            <th><fmt:message key="adminsetresults.label.description"/></th>
                            <th><fmt:message key="adminsetresults.label.result" /></th>
                        </tr>
                        <c:forEach items="${endedSportEvents}" var="sportEvent">
                            <tr>
                                <td>
                                    <input type="hidden" value="${sportEvent.id}"/>
                                    <fmt:formatDate value="${sportEvent.startTime.time}" pattern="dd.MM HH:mm" />
                                </td>
                                <td><fmt:formatDate value="${sportEvent.endTime.time}" pattern="dd.MM HH:mm" /></td>
                                <td><fmt:message key="${sportEvent.sportType.key}"/></td>
                                <td>${sportEvent.description}</td>
                                <td>
                                    <input type="number" min="0" class="smallinput" oninvalid="this.setCustomValidity('<fmt:message key="error.label.wrongScore"/>')" oninput="setCustomValidity('')" />
                                    -
                                    <input type="number" min="0" class="smallinput" oninvalid="this.setCustomValidity('<fmt:message key="error.label.wrongScore"/>')" oninput="setCustomValidity('')" />
                                </td>  
                            </tr>
                        </c:forEach>
                    </table>
                    <input type="hidden" id="sportEventResults" name="sportEventResults" />
                    <input type="hidden" name="command" value="add_sport_event_results" />
                    <input type="submit" value="<fmt:message key="adminsetresults.button.addResults"/>" class="smallbutton" onclick="getSportEventResults()" />
                </form>
            </aside>
        </c:when>
        <c:otherwise>
            <p class="mediumText"><fmt:message key="adminsetresults.label.noResultsToSet" /></p>
        </c:otherwise>
    </c:choose>
</t:adminpage>