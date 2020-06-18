<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.UserType" %>
<%@ page import="model.Task" %><%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 16.06.2020
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>

<%
    List<User> users = (List<User>) request.getAttribute("users");
    User user = (User) session.getAttribute("user");
    List<Task> tasks = (List<Task>) request.getAttribute("tasks");
%>
Welcome to <%=user.getName()%>  <a href="/logout">Logout</a><br><br>
<a href="/register">Add User</a><br>
<a href="/add">Add Task</a><br><br>
All Tasks <br>
<table border="1">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Description</td>
        <td>Deadline</td>
        <td>Status</td>
        <td>UserName</td>
        <td>Surname</td>
        <td>Delete Task</td>

    </tr>
    <% for (Task task : tasks) {%>
    <tr>
        <td><%=task.getId()%>
        </td>
        <td><%=task.getName()%>
        </td>
        <td><%=task.getDescription()%>
        </td>
        <td><%=task.getDeadline()%>
        </td>
        <td><%=task.getStatus()%>
        </td>
        <td><%=task.getUser().getName()%>
        </td>
        <td><%=task.getUser().getSurname()%>
        </td>
        <td><a href="/removeTask?id=<%=task.getId()%>">Delete</a></td>
    </tr>
     <%
    } %>
</table>

<br><br><br>
All Users

<table border="1">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Email</td>
        <td>Delete</td>

    </tr>
    <% for (User user1 : users) {
         if (user1.getUserType() == UserType.USER) {
    %>
    <tr>
        <td><%=user1.getId()%>
        </td>
        <td><%=user1.getName()%>
        </td>
        <td><%=user1.getSurname()%>
        </td>
        <td><%=user1.getEmail()%>
        </td>
        <td><a href="/removeUser?id=<%=user1.getId()%>">Delete</a></td>
    </tr>
    <%}
        } %>
</table>
<br>
<br>

</body>
</html>
