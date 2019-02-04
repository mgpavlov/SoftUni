<%@ page import="fdmc.domain.entities.Cat" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link rel="stylesheet" href="/style.css">
</head>
<body>
<%
    String name=request.getParameter("catName");
    Cat cat = ((Map<String,Cat>) application.getAttribute("cats")).get(name);
    if(cat==null){
        request.getRequestDispatcher("/cats/nonexistent.jsp").forward(request,response);
    }
%>
<section>
    <div>
        <h1>Cat - </h1>
        <span><%=cat.getName()%></span>
    </div>
    <hr>
    <div>
        <h2>Breed: </h2>
        <span><%=cat.getBreed()%></span>
    </div>
    <div>
        <h2>Color: </h2>
        <span><%=cat.getColor()%></span>
    </div>
    <div>
        <h2>Age: </h2>
        <span><%=cat.getAge()%></span>
    </div>

    <hr>
    <button type="button" onclick="location.href='/cats/all'">Back to all Cats</button>
    <button type="button" onclick="location.href='..'">Home</button>
</section>
</body>
</html>
