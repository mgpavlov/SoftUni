
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Nonexistent cat</title>
    <link rel="stylesheet" href="/style.css">
</head>
<body>
<%String name=request.getParameter("catName");%>

<section>
    <h1>Cat with name - <%=name%> was not found.</h1>
    <hr>
    <button type="button" onclick="location.href='/cats/all'">Back to all Cats</button>
</section>
</body>
</html>
