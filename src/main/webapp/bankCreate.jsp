<%--
  Created by IntelliJ IDEA.
  User: henri
  Date: 12/4/2020
  Time: 10:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TOP$</title>
</head>
<body>
<%@ include file="shared/navigationLoggedOf.jsp"%>
    <h3>Personal manipulation New York Bank</h3>




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

                    <form role="form" action="BankManipulation">
                        <div class="form-group">
                            <label for="destinationAccountId">
                                User's acount id</label>
                            <div class="input-group">
                                <input name="destinationAccountId" type="text" class="form-control" id="destinationAccountId" placeholder="your account id..."
                                       required autofocus />
                                <span class="input-group-addon"><span class="	glyphicon glyphicon-user"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sum">
                                Amount</label>
                            <div class="input-group">
                                <input name="sum" type="text" class="form-control" id="sum" placeholder="ammount you intend to manipulate"
                                       required autofocus />
                                <span class="input-group-addon"><span class="	glyphicon glyphicon-euro"></span></span>
                            </div>
                        </div>
                        <input type="checkbox" id="deposit" name="deposit" value="deposit">
                        <label for="deposit"> Deposit</label><br>
                        <button type="submit" class="btn btn-primary btn-lg btn-block" id="btnLogin" value="Transaction">Manipulate</button>
                        <%--                        <a href="Transaction" class="btn btn-primary btn-lg btn-block" type="submit" role="button">Send</a>--%>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>











</body>


<%@ include file="shared/footer.jsp"%>
</html>
