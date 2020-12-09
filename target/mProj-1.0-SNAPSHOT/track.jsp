<%--
  Created by IntelliJ IDEA.
  User: henri
  Date: 12/6/2020
  Time: 9:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="shared/navigationLoggedOf.jsp"%>
<h3> Relations between acounts</h3>
<h3>To find otu whether there is a path of funds between two accounts enter these accounts </h3>



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

                    <form role="form" action="Track">
                        <div class="form-group">
                            <label for="acc1Id">
                                Account 1 id: </label>
                            <div class="input-group">
                                <input name="acc1Id" type="text" class="form-control" id="acc1Id" placeholder="first account id..."
                                       required autofocus />
                                <span class="input-group-addon"><span class="	glyphicon glyphicon-user"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="acc2Id">
                                Account 2 id:</label>
                            <div class="input-group">
                                <input name="acc2Id" type="text" class="form-control" id="acc2Id" placeholder=" second account id "
                                       required autofocus />
                                <span class="input-group-addon"><span class="	glyphicon glyphicon-euro"></span></span>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary btn-lg btn-block" id="btnLogin" value="Transaction">Find</button>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>
<c:choose>
    <c:when test="${answer == true }"> <p style="color:green;"> connection exist </p> </c:when>
    <c:when test="${answer == false }"> <p style="color:red;"> connection does not exist </p> </c:when>
    <c:otherwise> ?</c:otherwise>
</c:choose>

<p>the shortest distance between two accounts is : ${answer2}   </p>







</body>


<%@ include file="shared/footer.jsp"%>
</html>
