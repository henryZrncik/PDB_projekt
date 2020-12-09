<%--
  Created by IntelliJ IDEA.
  User: henri
  Date: 12/1/2020
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav role="navigation" class="navbar navbar-default">
    <div class="">
        <a href="" class="navbar-brand">The $ Bank</a>
    </div>
    <div class="navbar-collapse">
        <ul class="nav navbar-nav">
            <li class="active"><a href="welcome.jsp">Home</a></li>
            <li><a href="Manipulations">Manipulations</a></li>
            <li><a href="transaction.jsp">New Transaction</a></li>
        </ul>
        <ul class="nav navbar-nav">
            <li><a href="index.jsp">Logout</a></li>
        </ul>
    </div>
</nav>
<%
    // Disable cache load of page
    // (thus proper handling of logging out bypass by back button)
    // for htp 1.1
    response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
    // for htp 1.0
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    System.out.println(session.getAttribute("isLogged"));
    if(session.getAttribute("isLogged") == null){
        response.sendRedirect("index.jsp");
    }
%>

<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
      rel="stylesheet">
<style>
    .footer {
        background-color: black;
        color: black;
        height: 100px;
        text-align: center;
        position: fixed;
        left: 0;
        bottom: 0;
        width: 100%;

    }
</style>

