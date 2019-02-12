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

        <form action="<%=TUBE_CREATE_URL%>" method="post">
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1>Create Tube!</h1>
                </div>
            </div>

            <br>

            <div class="row">
                <div class="col col-md-12 ">
                    <div class="row d-flex justify-content-center">
                        <label for="titleInput">Title</label>
                    </div>
                    <div class="row d-flex justify-content-center">
                        <input type="text" name="<%=PARAMETER_NAME%>" id="titleInput" required>
                    </div>
                </div>
            </div>

            <hr>

            <div class="row">
                <div class="col col-md-12">
                    <div class="row d-flex justify-content-center">
                        <label for="descrTextarea">Description</label>
                    </div>
                    <div class="row d-flex justify-content-center">
                        <textarea type="text" name="<%=PARAMETER_DESC%>" id="descrTextarea" cols="22" rows="4"></textarea>
                    </div>
                </div>
            </div>

            <br>

            <div class="row">
                <div class="col col-md-12 ">
                    <div class="row d-flex justify-content-center">
                        <label for="linkInput">YouTube Link</label>
                    </div>
                    <div class="row d-flex justify-content-center">
                        <input type="url" name="<%=PARAMETER_LINK%>" id="linkInput" required>
                    </div>
                </div>
            </div>

            <br>

            <div class="row">
                <div class="col col-md-12 ">
                    <div class="row d-flex justify-content-center">
                        <label for="uploaderInput">Uploader</label>
                    </div>
                    <div class="row d-flex justify-content-center">
                        <input type="text" name="<%=PARAMETER_UPLOADER%>" id="uploaderInput" required>
                    </div>
                </div>
            </div>

            <br>

            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <button class="btn btn-primary" type="submit">Create Tube</button>
                </div>
            </div>
        </form>

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

