<%@ page import="java.util.List" %>
<%@ page import="domain.Manipulation" %>
<%@ page import="java.util.LinkedList" %><%--
  Created by IntelliJ IDEA.
  User: henri
  Date: 11/30/2020
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<%@ include file="shared/navigations.jsp"%>
<h1>manipulations</h1>
<a href="" class="btn btn-primary">Create new transaction</a>
<div class="table-responsive shadow p-3 mb-5 bg-white rounded">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Manipulation id</th>
                <th>date</th>
                <th>type of action</th>
                <th>sum</th>
                <th>account / Bank</th>
            </tr>
        </thead>
        <h1>Welcome Mr. ${accountMongo.get("owner").get("ownerFirstName")}</h1>
        <tbody>
<%--            <c:forEach items="${manipulations}" var="s">--%>
                <c:forEach items="${accountMongo.get('accountManipulations')}" var="s">
                <tr>
<%--                    <td><small>${s.getId()} </small></td>--%>
                    <td><small>${s.get("transactionId")} </small></td>
<%--                    <td><small>${s.getTimeasString()}</small></td>--%>
                    <td><small>${s.get("date")}</small></td>
                <td><small >
                    <c:choose>
                        <c:when test="${s.get('isTransaction') == true }"> Transaction </c:when>
                        <c:when test="${s.get('isTransaction') == false }"> Bank manipulation </c:when>
                        <c:otherwise> should not be possible1</c:otherwise>
                    </c:choose>
                </small></td>
                    <td><small >
                        <c:choose>
                            <c:when test="${s.get('isIN') == true }"> <p style="color:green;"> ${s.get('sum')} </p> </c:when>
                            <c:when test="${s.get('isIN') == false }"> <p style="color:red;"> ${s.get('sum')} </p> </c:when>
                            <c:otherwise> should not be possible2</c:otherwise>
                        </c:choose>
                    </small></td>
                <td><small >
                    <c:choose>
                        <c:when test="${s.get('isTransaction') == true && s.get('isIN') == false }"> ${s.get('destinationAccountId')} </c:when>
                        <c:when test="${s.get('isTransaction') == true && s.get('isIN') == true }"> ${s.get('sourceAccountId')} </c:when>
                        <c:when test="${s.get('isTransaction') == false }"> ${s.get('bankName')} </c:when>
                        <c:otherwise> should not be possible1</c:otherwise>
                    </c:choose>
                </small></td>

                </tr>

            </c:forEach>
        </tbody>
    </table>

</div>
<%--            <c:forEach items="${manipulations}" var="s">--%>
<%--                    <td><small>${s.getId()} </small></td>--%>
<%--<td><small>${s.getTimeasString()}</small></td>&ndash;%&gt;--%>
<%--                        <c:choose>--%>
<%--                            <c:when test="${s.getClass().name == 'domain.BankManipulation' }">Bank</c:when>--%>
<%--                            <c:when test="${s.getClass().name == 'domain.OnlineTransaction'}">Transaction</c:when>--%>
<%--                            <c:otherwise> smt else</c:otherwise>--%>

<%--                        </c:choose>--%>
<%--${manipulations}--%>
<%@ include file="shared/footer.jsp"%>
</body>

</html>
