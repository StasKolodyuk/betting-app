<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/imports.jspf" %>

<fmt:message key="mybets.title" var="title"/>
<t:userpage title="${title}">
    <c:choose>
        <c:when test="${not empty complexBets}">
            <aside class="bettable"> 
                <table id="selectableTable" class="flat-table blue">
                    <tr>
                        <th><fmt:message key="mybets.label.description"/></th>
                        <th><fmt:message key="mybets.label.betType"/></th>
                        <th><fmt:message key="mybets.label.betResult"/></th>
                        <th><fmt:message key="mybets.label.betMoney"/></th>
                        <th><fmt:message key="mybets.label.coefficient"/></th>
                        <th><fmt:message key="mybets.label.prizeMoney"/></th>
                    </tr>
                    <c:forEach items="${complexBets}" var="complexBet">
                        <c:choose>
                            <c:when test="${complexBet.result == 'LOSS'}">
                                <tr class="red">
                            </c:when>
                            <c:when test="${complexBet.result == 'WIN'}">
                                <tr class="orange">
                            </c:when>
                            <c:when test="${complexBet.result == 'UNDEFINED'}">
                                <tr class="sky">
                            </c:when>
                        </c:choose>
                        <td colspan="6"><fmt:message key="${complexBet.result.key}"/></td></tr>
                        <c:forEach items="${complexBet.betsInfo}" var="betInfo">
                            <tr>
                                <td>${betInfo.third}</td>
                                <td><fmt:message key="${betInfo.first.key}"/></td>
                                <td><fmt:message key="${betInfo.second.key}"/></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>${complexBet.moneyAmount}</td>
                            <td><fmt:formatNumber value="${complexBet.coefficient}" maxFractionDigits="3"/></td>
                            <td>${complexBet.prizeMoney}</td>
                        </tr>
                    </c:forEach>
                </table>
            </aside>
        </c:when>
        <c:otherwise>
            <p class="mediumText"><fmt:message key="mybets.label.noPreviousBets" /></p>
        </c:otherwise>
    </c:choose>
</t:userpage>