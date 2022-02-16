<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Martin
  Date: 14.02.2022
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager Home</title>
</head>
<body>
<form action="/addUser" method="post">
    <fieldset>
        <legend>Add User</legend>
        <label for="name">Name</label>
        <br><input type="text" id="name" name="name">

        <br><label for="surname">Surname</label>
        <br><input type="text" id="surname" name="surname">

        <br><label for="email">Email</label>
        <br><input type="email" id="email" name="email">

        <br><label for="password">Password</label>
        <br><input type="password" id="password" name="password">
        <br> <input type="submit" value="Add User">
    </fieldset>
</form>


<form action="/addTask" method="post">
    <fieldset>
        <legend>Add Task</legend>
        <label for="taskName">Name</label>
        <br><input type="text" id="taskName" name="name">

        <br><label for="desc">Description</label>
        <br><input type="text" id="desc" name="desc">

        <br><label for="deadline">Deadline</label>
        <br><input type="date" id="deadline" name="deadline">

        <br><label for="users">Users</label>
        <br><input type="text" id="users" name="userId">

    </select>

        <br><label for="status">Status</label>
        <br><select name="statusType" id="status">
        <option value="NEW">NEW</option>
        <option value="IN_PROGRESS">IN_PROGRESS</option>
        <option value="FINISHED">FINISHED</option>
    </select>

        <input type="submit" value="Add Task">
    </fieldset>
</form>


</body>
</html>
