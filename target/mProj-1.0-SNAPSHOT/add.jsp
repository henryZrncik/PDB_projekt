<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: henri
  Date: 9/16/2020
  Time: 6:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add.jsp</title>
</head>
<body>
<h1>Output</h1>
<%--<% out.print("Today is:"+java.util.Calendar.getInstance().getTime()); %>--%>
<%  out.print("jalala");  %>
<%

  int i = Integer.parseInt(request.getParameter("num1"));
  int j = Integer.parseInt(request.getParameter("num2"));
    System.out.println(i);
    System.out.println(j);
    System.out.println(i + j);

%>


</body>
</html>
