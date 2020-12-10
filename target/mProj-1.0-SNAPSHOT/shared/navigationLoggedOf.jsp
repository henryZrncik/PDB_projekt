<%--
  Created by IntelliJ IDEA.
  User: henri
  Date: 12/1/2020
  Time: 12:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav role="navigation" class="navbar navbar-default">
    <div class="">
        <a href="bankCreate.jsp" class="navbar-brand">Top$ Bank</a>
    </div>
    <div class="navbar-collapse">
        <ul class="nav navbar-nav">
            <li class="active"><a href="track.jsp">T-Tracking</a></li>
            <li class="active"><a href="us.jsp">Contact</a></li>

            <li class="active"><a href="index.jsp">Login</a></li>
        </ul>
    </div>
</nav>

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

<%
    // Disable cache load of page
    // (thus proper handling of logging out bypass by back button)
    // for htp 1.1
    response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
    // for htp 1.0
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
%>

