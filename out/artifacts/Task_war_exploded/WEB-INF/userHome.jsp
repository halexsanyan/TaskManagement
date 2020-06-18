<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Task" %>
<%@ page import="model.TaskStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%

    User user = (User) session.getAttribute("user");
    List<Task> tasks = (List<Task>) request.getAttribute("tasks");
%>
Welcome to <%=user.getName()%> <%=user.getSurname()%>  <a href="/logout">Logout</a><br>
My Tasks <br>
<table border="1">

    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Description</td>
        <td>Deadline</td>
        <td>Status</td>



    </tr>
    <% for (Task task : tasks) {%>
    <tr>

        <td><%=task.getId()%></td>
        <td><%=task.getName()%></td>
        <td><%=task.getDescription()%></td>
        <td><%=task.getDeadline()%></td>
        <td><%=task.getStatus()%></td>

    </tr>
    <% } %>
</table>

</body>
</html>
