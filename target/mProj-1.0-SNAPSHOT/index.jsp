<%@ page import="dao.UserDao" %>
<%@ page import="domain.User" %><%--
  Created by IntelliJ IDEA.
  User: henri
  Date: 9/15/2020
  Time: 1:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
errorPage="error.jsp" %>
<%--<%!--%>
<%--    UserDao userDao1 = new UserDao();--%>
<%--    User u1 = new User("peter", 1);--%>
<%--    userDao1.saveUser(u1);--%>
<%--%>--%>
<html>
<head>
    <title>Titlee</title>
</head>
<%--<%  int j = 5/0 ;  %>--%>
<%--<%--%>
<%--    System.out.printf("called scriptlet");--%>
<%--    UserDao userDao = new UserDao();--%>
<%--    User u = new User("peter", 1);--%>
<%--    userDao.saveUser(u);--%>
<%--%>--%>
<body>
<%--    <p>Input form:</p>--%>
<%--    <form action="arg" method="get">--%>
<%--        <input type="text" name="num1">--%>
<%--        <input type="number" name="num2">--%>
<%--        <input type="submit">--%>
<%--    </form>--%>
<%--<%@ include file="shared/header.jsp"%>--%>
<%@ include file="shared/navigationLoggedOf.jsp"%>
<%--<form action="Login">--%>
<%--    Enter account id: <input type="text" name="accountId">--%>
<%--    Enter password: <input type="password" name="password">--%>
<%--    <input type="submit" value="login">--%>
<%--</form>--%>


<div class="container py-5">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-6 mx-auto">

                    <!-- form card login -->
                    <div class="card rounded-0">
                        <div class="card-header">
                            <h3 class="mb-0">Login</h3>
                        </div>
                        <div class="card-body">
                            <form class="form" role="form" action="Login">
                                <div class="form-group">
                                    <label for="accountId">account number (id)</label>
                                    <input type="text" class="form-control form-control-lg rounded-0" name="accountId" id="accountId">
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="password" class="form-control form-control-lg rounded-0" id="password" >

                                </div>
                                <button type="submit" class="btn btn-primary btn-lg float-right" id="btnLogin" value="login">Login</button>
                            </form>
                        </div>
                        <!--/card-block-->
                    </div>
                    <!-- /form card login -->

                </div>


            </div>
            <!--/row-->

        </div>
        <!--/col-->
    </div>




<%@ include file="shared/footer.jsp"%>


<%
    System.out.println("i am back at loggin");
    session.removeAttribute("isLogged");
    session.removeAttribute("answer");
    System.out.println(session.getAttribute("isLogged"));

%>

</body>
</html>
