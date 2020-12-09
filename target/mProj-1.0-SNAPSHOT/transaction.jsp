<%@ page import="domain.Account" %><%--
  Created by IntelliJ IDEA.
  User: henri
  Date: 11/30/2020
  Time: 4:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<%@ include file="shared/navigations.jsp"%>

<h1>new Transaction</h1>
<h3>From account: ${account.getId()}</h3>
<h3>Current Balance: ${account.getBalance()}</h3>


<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<div class="container">
    <div class="row">
        <div class="col-xs-12 col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        Transaction Details
                    </h3>
                </div>
                <div class="panel-body">

                    <form role="form" action="Transaction">
                        <div class="form-group">
                            <label for="destinationAccountId">
                                Destination Account id</label>
                            <div class="input-group">
                                <input name="destinationAccountId" type="text" class="form-control" id="destinationAccountId" placeholder="Valid account id"
                                       required autofocus />
                                <span class="input-group-addon"><span class="	glyphicon glyphicon-user"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sum">
                                Amount</label>
                            <div class="input-group">
                                <input name="sum" type="text" class="form-control" id="sum" placeholder="ammount you intend to send"
                                       required autofocus />
                                <span class="input-group-addon"><span class="	glyphicon glyphicon-euro"></span></span>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary btn-lg btn-block" id="btnLogin" value="Transaction">Transaction</button>
<%--                        <a href="Transaction" class="btn btn-primary btn-lg btn-block" type="submit" role="button">Send</a>--%>
                    </form>
                </div>
            </div>

             </div>
    </div>
</div>


<%@ include file="shared/footer.jsp"%>

</body>
</html>
