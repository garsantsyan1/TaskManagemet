<%--
  Created by IntelliJ IDEA.
  User: Martin
  Date: 14.02.2022
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="/login" method="post">
    <label for="login">Login(email)</label>
    <br><input type="email" id="login" name="login">
    <br><label for="password">Password</label>
    <br><input type="password" id="password" name="password">
    <br><input type="submit" value="Enter">
</form>
</body>
</html>
