<%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 15.06.2020
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>

<%
    String msg = "";
    if (session.getAttribute("msg") != null) {
        msg = (String) session.getAttribute("msg");
        session.removeAttribute("msg");
    }

%>
<p style="color: red">
    <%=msg%>
</p>
Login:
<form action="/login" method="post">
    <input type="text" name="email" placeholder="email" > <br>
    <input type="password" name="password" placeholder="password" > <br>
    <input type="submit" value="Login">
</form>
</body>
</html>
