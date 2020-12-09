<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.Account" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.bson.Document" %>
<%@ page import="com.mongodb.client.AggregateIterable" %><%--
  Created by IntelliJ IDEA.
  User: henri
  Date: 11/30/2020
  Time: 4:02 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TOP$</title>
</head>
<%@ include file="shared/navigations.jsp"%>
<body>

<%--<h1>Welcome Mr. ${account.getOwner().getFirstName()}</h1>--%>

<h1>Welcome Mr. ${accountMongo.get("owner").get("ownerFirstName")}</h1>
<h3>Your monthly movements on account</h3>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Period</th>
        <th scope="col">Cash turnover</th>
    </tr>
    </thead>
    <tbody>
<c:forEach items="${trendsMongo}" var="s">
    <tr>
    <%--                    <td><small>${s.getId()} </small></td>--%>
    <td><small>${s.get("_id")} </small></td>
    <%--                    <td><small>${s.getTimeasString()}</small></td>--%>
    <td><small>${s.get("total")}</small></td>
    </tr>
</c:forEach>
    </tbody>
</table>

<%--//accountId, balance,--%>
<%--//accountManipulations, Owner--%>
<%--//        Document ownerDocument = (Document) accountDocument.get("owner");--%>
<%--//        System.out.println(ownerDocument.get("ownerFirstName"));--%>
<%--//--%>
<%--//        System.out.println( (List)accountDocument.get("accountManipulations"));--%>

<%@ include file="shared/footer.jsp"%>
</body>
</html>
