
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/imports.jspf" %>

<!DOCTYPE html>

<script src="${root}/js/addBetsTable.js" type="text/JavaScript"></script>
<fmt:message key="adminaddbets.title" var="title"/>
<t:adminpage title="${title}">
    <c:choose>
        <c:when test="${not empty adminBets}">
        <aside class="bettable">
                <form method="POST" action="${root}/Controller">
                    <table id="addBetsTable" class="flat-table blue">
                        <tr>
                            <th><fmt:message key="adminaddbets.label.description" /></th>
                                <c:forEach items="${betTypes}" var="betType">
                                <th>
                                    <input type="hidden" id="betTypes" value="${betType}"/>
                                    <fmt:message key="${betType.key}"/>
                                </th>
                            </c:forEach>
                        </tr>
                        <c:forEach items="${adminBets}" var="betWrapper">
                            <tr>
                                <td>
                                    <input type="hidden" value="${betWrapper.sportEventId}"/>
                                    ${betWrapper.description}
                                </td>
                                <c:forEach items="${betTypes}" var="betType">
                                    <td>
                                        <c:set var="betEntry" value="${betWrapper.map[fn:toLowerCase(betType)]}" />
                                        <c:choose>
                                            <c:when test="${not empty betEntry}">
                                                ${betEntry.second}
                                            </c:when>
                                            <c:otherwise>
                                                <input type="number" min="1.01" step="0.01" class="smallinput" oninvalid="this.setCustomValidity('<fmt:message key="error.label.wrongBetCoefficient"/>')" oninput="setCustomValidity('')"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>          
                    <input type="hidden" name="command" value="add_bets" />
                    <input type="hidden" name="newBets" id="newBets" />
                    <input type="submit" class="smallbutton" onclick="getAddedBets()" value="<fmt:message key="adminaddbets.button.addBets"/>" />
                </form>
            </aside>
        </c:when>
        <c:otherwise>
            <p class="mediumText"><fmt:message key="adminaddbets.label.noSportEventsAvailable" /></p>
        </c:otherwise>
    </c:choose>
</t:adminpage>