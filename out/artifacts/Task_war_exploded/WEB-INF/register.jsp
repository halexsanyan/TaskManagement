<%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 16.06.2020
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<%
    String msg = "";
    if (session.getAttribute("msg") != null) {
        msg = (String) session.getAttribute("msg");
        session.removeAttribute(msg);
    }

%>

<p style="color: red"><%=msg%></p>

<form action="/register" method="post">
    <input type="text" name="name" placeholder="please input name" required><br>
    <input type="text" name="surname" placeholder="please input surname" required><br>
    <input type="text" name="email" placeholder="please input email" required><br>
    <input type="password" name="password" placeholder="please input password" required><br>
    <input type="submit" value="Register">
</form>
<br>
<a href="/home">Home</a>
</body>
</html>
