<%@ page import="metube.domain.models.view.AllTubesViewModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:import url="templates/head.jsp"/></head>
<body>
<% List<AllTubesViewModel> tubes = (List<AllTubesViewModel>) request.getAttribute("allTubes"); %>
<main>
    <div class="jumbotron">
        <div class="row">
            <div class="col col-md-12 d-flex justify-content-center">
                <h1>
                    All Tubes
                </h1>
            </div>
        </div>
        <hr/>
        <div class="row">
            <div class="col col-md-12 d-flex justify-content-center">
                <h3>
                    Check our tubes
                </h3>
            </div>
        </div>
        <hr/>
        <div class="row">
            <div class="col col-md-12 d-flex justify-content-center">
                <% if (tubes.size() == 0) { %>
                <p>No Tubes - <a href="/tubes/create">Create Some</a></p>
                <% } else { %>
                <ul>
                    <% for (AllTubesViewModel tube : tubes) { %>
                    <li><a href="/tubes/details?name=<%= tube.getName() %>"/><%= tube.getName() %>
                    </li>
                    <% } %>
                </ul>
                <% } %>
            </div>
        </div>
        <hr/>
        <div class="row">
            <div class="col col-md-12 d-flex justify-content-center">
                <a href="/">Back to home</a>
            </div>
        </div>
    </div>
</main>
<footer>
    <c:import url="templates/footer.jsp"/>
</footer>
</body>
</html>
