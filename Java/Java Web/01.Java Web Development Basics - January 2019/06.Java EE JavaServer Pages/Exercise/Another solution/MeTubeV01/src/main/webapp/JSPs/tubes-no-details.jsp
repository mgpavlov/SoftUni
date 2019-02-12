<%@ page import="static metube.constants.Constants.*" %>
Created by IntelliJ IDEA.
User: LAPD
Date: 5.2.2019 г.
Time: 12:29 ч.
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String tubeName = (String) request.getAttribute(PARAMETER_NAME); %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>

<main>
    <div class="jumbotron">

        <div class="row">
            <div class="col col-md-12 d-flex justify-content-center">
                <h1>No Tube with name <%=tubeName%> - <a href="<%=TUBE_CREATE_URL%>">Create some</a>!</h1>
            </div>
        </div>

        <br>

        <div class="row">
            <div class="col col-md-12 d-flex justify-content-center">
                <a href="<%=INDEX_URL%>">Back to Home.</a>
            </div>
        </div>
    </div>
</main>

<footer>
    <c:import url="templates/footer.jsp"/>
</footer>
</body>
</html>

