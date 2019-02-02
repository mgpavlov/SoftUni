<%@ page import="java.util.Date" %>
<%@ page import="viewmodels.HomeViewModel" %>
<%@ page import="constants.ViewConstants" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
</head>
<body>
<h1>It works on <p><%= request.getAttribute("currentDate") %></p></h1>

<% viewmodels.HomeViewModel viewModel = (HomeViewModel) request.getAttribute(ViewConstants.VIEW_MODEL_ATTRIBUTE_NAME); %>

<h2>Hello <%= viewModel.getName() %> </h2>
<h2>Hello <%= viewModel.getAge() %> </h2>

<% for(int i = 0; i < 5; i ++) { %>
    <li><%= i %></li>
<% } %>


<script>
    // GET /users
    // Async JavaScript & XML
    const generateList = (users) => {
        return "<ul>" +
            users.map(user => "<li>" + user.name + "</li>")
                .join("\n") +
            "</ul>"
    };
    $.ajax({
        url: '/api/users',
        method: 'GET',
        success: (users) => {
            const list = generateList(users);
            $('body').append(list);
        },
    });
</script>
</body>
</html>