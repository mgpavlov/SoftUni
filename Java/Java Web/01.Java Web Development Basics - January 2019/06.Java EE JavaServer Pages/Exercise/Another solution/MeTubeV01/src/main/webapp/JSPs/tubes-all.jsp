<%@ page import="static metube.constants.Constants.*" %>
<%@ page import="metube.domain.models.view.TubeAllViewModel" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: LAPD
  Date: 5.2.2019 г.
  Time: 12:29 ч.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% List<TubeAllViewModel> tavmArr = (List<TubeAllViewModel>) request.getAttribute(MAIN_ENTITIES);%>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>

<main>
    <div class="jumbotron">

        <div class="row">
            <div class="col col-md-12 d-flex justify-content-center">
                <h1>All Tubes</h1>
            </div>
        </div>

        <br>

        <div class="row">
            <div class="col col-md-12 ">
                <h3>Check our tubes below.</h3>
            </div>
        </div>

        <hr>

        <div class="row">
            <div class="col col-md-12">
                <ul>
                    <% for (TubeAllViewModel tavm : tavmArr) {%>
                    <li>
                        <a href="<%=TUBE_DETAILS_URL_QUERY+tavm.getName()%>">
                            <%=tavm.getName()%>
                        </a>
                    </li>
                    <%} %>
                </ul>
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

