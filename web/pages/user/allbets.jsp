
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/imports.jspf" %>

<!DOCTYPE html>

<script src="${root}/js/makeBetTable.js" type="text/JavaScript"></script>
<fmt:message key="allbets.title" var="title"/>
<t:userpage title="${title}">
    <c:choose>
        <c:when test="${not empty bets}">
            <aside class="bettable">
                <table id="selectableTable" class="flat-table blue">
                    <tr>
                        <th><fmt:message key="allbets.label.startTime"/></th>
                        <th><fmt:message key="allbets.label.description"/></th>
                            <c:forEach items="${betTypes}" var="betType">
                                <th><fmt:message key="${betType.key}"/></th>
                            </c:forEach>
                    </tr>
                    <c:forEach items="${bets}" var="betWrapper">
                        <tr>
                            <td>
                                <fmt:formatDate value="${betWrapper.startTime.time}" pattern="dd.MM HH:mm" />
                                <input type="hidden" value="${betWrapper.startTime.timeInMillis}"/>
                            </td>
                            <td> ${betWrapper.description} </td>
                            <c:forEach items="${betTypes}" var="betType">
                                <td>
                                    <c:set var="betEntry" value="${betWrapper.map[fn:toLowerCase(betType)]}" />
                                    <span>${betEntry.second}</span>
                                    <input type="hidden" value="${betEntry.first}" />
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>          
                <form method="POST" action="${root}/Controller">
                    <input type="hidden" name="command" value="make_bet" />
                    <input type="hidden" name="resultBet" id="resultBet" />
                    <input type="hidden" name="resultBetCoefficient" id="resultBetCoefficient"/>
                    <input type="hidden" name="earliestBetStartTime" id="earliestBetStartTime"/>
                    <span id="resultBetCoefficientCounter">1.000</span> * 
                    <input type="number" min="1" name="moneyAmount" class="mediuminput" placeholder="<fmt:message key="allbets.input.betAmount"/>" oninvalid="setCustomValidity('<fmt:message key="error.label.wrongMoneyAmount"/>')" oninput="setCustomValidity('')" required />
                    <input type="submit" class="smallbutton" onclick="getSelectedBets()" value="<fmt:message key="allbets.button.makeBet"/>" />
                </form>
            </aside>
        </c:when>
        <c:otherwise>
            <p class="mediumText"><fmt:message key="allbets.label.noBetsAvailable" /></p>
        </c:otherwise>
    </c:choose>
</t:userpage>
