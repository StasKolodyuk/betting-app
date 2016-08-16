<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/imports.jspf" %>

<fmt:message key="transactions.title" var="title"/>
<t:userpage title="${title}">
    <aside class="bettable">
        <c:choose>
            <c:when test="${not empty transactions}">
                <table id="setResultsTable" class="flat-table blue">
                    <tr>
                        <th><fmt:message key="transactions.label.time"/></th>
                        <th><fmt:message key="transactions.label.transactionType"/></th>
                        <th><fmt:message key="transactions.label.moneyAmount"/></th>
                    </tr>
                    <c:forEach items="${transactions}" var="transaction">
                        <tr>
                            <td><fmt:formatDate value="${transaction.time.time}" pattern="dd.MM HH:mm" /></td>
                            <td><fmt:message key="${transaction.type.key}"/></td>
                            <td>${transaction.moneyAmount}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p class="mediumText"><fmt:message key="transactions.label.noPreviousTransactions" /></p>
            </c:otherwise>
        </c:choose>
        <form method="POST" action="${root}/Controller">
            <input type="hidden" name="command" value="make_transaction" />
            <select class="mediuminput" name="creditCardType">
                <option value="MasterCard">MasterCard</option>
                <option value="Visa">Visa</option>
            </select>
            <input type="text" pattern="[0-9]{13,16}" class="mediuminput" placeholder="<fmt:message key="transactions.label.creditCardNumber"/>" name="creditCardNumber" oninvalid="setCustomValidity('<fmt:message key="error.label.wrongCreditCardNumber"/>')" oninput="setCustomValidity('')" required />
            <input type="text" pattern="[0-9]{3}" class="mediuminput" placeholder="<fmt:message key="transactions.label.securityCode"/>" name="securityCode" oninvalid="setCustomValidity('<fmt:message key="error.label.wrongSecurityCode"/>')" oninput="setCustomValidity('')" required />
            <br/><br/>
            <input type="number" min="1" class="mediuminput" placeholder="<fmt:message key="transactions.label.moneyAmount"/>" name="moneyAmount" oninvalid="setCustomValidity('<fmt:message key="error.label.wrongMoneyAmount"/>')" oninput="setCustomValidity('')" required />
            <select class="mediuminput" name="transactionType">
                <c:forEach items="${transactionTypes}" var="transaction">
                    <option value="${transaction}"><fmt:message key="${transaction.key}"/></option>
                </c:forEach>
            </select>
            <input type="submit" class="smallbutton" value="<fmt:message key="transactions.button.makeTransaction"/>" />
        </form>
    </aside>
</t:userpage>