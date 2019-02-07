<%@ page import="metube.domain.models.view.TubeDetailsViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<% TubeDetailsViewModel tdvm = (TubeDetailsViewModel) request.getAttribute("tubeDetailsViewModel"); %>
<main>
    <div class="jumbotron">


        <div class="row">
            <div class="col col-md-12 d-flex justify-content-center">
                <h1>
                    <%= tdvm.getName()%>
                </h1>
            </div>
        </div>
        <hr/>

        <div class="row">
            <div class="col col-md-12 d-flex justify-content-center">
                <h3>
                    <%= tdvm.getDescription()%>
                </h3>
            </div>
        </div>
        <hr/>

        <div class="row">
            <div class="col col-md-6 d-flex justify-content-center">
                <a href="<%= tdvm.getYouTubeLink() %>" class="btn btn-primary">Link to Video</a>
            </div>
            <div class="col col-md-6 d-flex justify-content-center">
                <p><%= tdvm.getUploader() %></p>
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
