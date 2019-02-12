<%@ page import="static metube.constants.Constants.*" %><%--
  Created by IntelliJ IDEA.
  User: LAPD
  Date: 5.2.2019 г.
  Time: 12:29 ч.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>

<main>
    <div class="jumbotron">
        <div class="row">
            <div class="col col-md-12 d-flex justify-content-center">
                <h1>Welcome to MeTube!</h1>
            </div>
        </div>

        <hr>

        <div class="row">
            <div class="col col-md-12 d-flex justify-content-center">
                <p>Cool app in beta version</p>
            </div>
        </div>

        <hr>

        <div class="row">
            <div class="col col-md-6 d-flex justify-content-center">
                <a href="<%=TUBE_CREATE_URL%>" class="btn btn-primary" type="submit">Create Tube</a>
            </div>

            <div class="col col-md-6 d-flex justify-content-center">
                <a href="<%=TUBE_ALL_URL%>" class="btn btn-primary" type="submit">All Tubes</a>
            </div>
        </div>
    </div>
</main>

<footer>
    <c:import url="templates/footer.jsp"/>
</footer>
</body>
</html>

