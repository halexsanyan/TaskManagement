<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.swing.*" %>
<%@ page import="model.UserType" %><%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 16.06.2020
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<%
    String msg = "";
    if (session.getAttribute("msg") != null) {
        msg = (String) session.getAttribute("msg");
        session.removeAttribute(msg);
    }

%>

<p style="color: red"><%=msg%>
</p>
<%
    List<User> users = (List<User>) request.getAttribute("users");

%>
<form action="/add" method="post">
    <input type="text" name="name" placeholder="name"><br>
    <input type="text" name="description" placeholder="description"><br>
    <input type="date" name="deadline" placeholder="deadline"><br>
    <select name="userId">
        <% for (User user : users) {
            if (user.getUserType() != UserType.MANAGER){ %>

        <option value="<%=user.getId()%>"><%=user.getName()%> <%=user.getSurname()%>
        </option>
        <br>

        <% }
        } %>
    </select> <br>
    TODO <input type="radio" value="TODO" name="status">
    IN_PROGRESS <input type="radio" value="IN_PROGRESS" name="status">
    FINISHED <input type="radio" value="FINISHED" name="status"><br>

    <input type="submit" value="Ok">
</form>
<br>
<a href="/home">Home</a>
</body>
</html>
