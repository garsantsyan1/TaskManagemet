<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Task" %><%--
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
<% List<User> allUsers = (List<User>) request.getAttribute("allUsers"); %>
<% List<Task> allTasks = (List<Task>) request.getAttribute("allTasks");%>

<a href="/logout">Logout</a>
<form action="/addUser" method="post" enctype="multipart/form-data">
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

        <br><label for="type">Type</label>
        <br><select name="type" id="type">
            <option value="USER">USER</option>
            <option value="MANAGER">MANAGER</option>
        </select><br>

        <br><label for="image">Image</label>
        <br><input type="file" name="image" id="image">

        <br> <input type="submit" value="Add User">
    </fieldset>
</form>


<form action="/addTask" method="post">
    <fieldset>
        <legend>Add Task</legend>

        <label for="taskName">Name</label>
        <br><input type="text" id="taskName" name="name">

        <br><label for="description">Description</label>
        <br><input type="text" id="description" name="description">

        <br><label for="date">Deadline</label>
        <br><input type="date" id="date" name="date">

        <br><label for="status">Status</label>
        <br><select name="statusType" id="status">
        <option value="NEW">NEW</option>
        <option value="IN_PROGRESS">IN_PROGRESS</option>
        <option value="FINISHED">FINISHED</option>
    </select>

        <br><label for="user">User</label>
        <br><select name="user_id" id="user">
            <%
                for (User user : allUsers) { %>
            <option value="<%=user.getId()%>"><%=user.getName()%> <%=user.getSurname()%></option>
            <% }
            %>
        </select><br>

        <input type="submit" value="Add Task">
    </fieldset>
</form>


<table border="1">
    <caption>All Users</caption>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Email</th>
        <th>Password</th>
        <th>Type</th>
        <th>Image</th>
        <th>Delete User</th>
    </tr>

    <% for(User user: allUsers) { %>
    <tr>
        <td><%=user.getId() %></td>
        <td><%=user.getName() %></td>
        <td><%=user.getSurname() %></td>
        <td><%=user.getEmail() %></td>
        <td><%=user.getPassword() %></td>
        <td><%=user.getType().name()%></td>
        <% if(user.getPictureUrl() != null) { %>
        <td> <img src="/image?path=<%=user.getPictureUrl()%>" width="50" /> </td>
        <% } %>
        <td><a href="/deleteUser?id=<%=user.getId()%>">DELETE</a></td>
    </tr>
    <% } %>
</table>


<table border="1">
    <caption>All Tasks</caption>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Status</th>
        <th>Deadline</th>
        <th>User Id</th>
        <th>Change User Id</th>
    </tr>

    <% for(Task task: allTasks) { %>
    <tr>
        <td name=""><%=task.getId() %></td>
        <td><%=task.getName() %></td>
        <td><%=task.getDescription() %></td>
        <td><%=task.getStatus().name() %></td>
        <td><%=task.getDeadline() %></td>
        <td><%=task.getUserID()%></td>
        <td><form action="/changeUserId?taskId=<%=task.getId()%>" method="post">
            <select name="user_id">
                <%
                    for (User user : allUsers) { %>
                <option value="<%=user.getId()%>"><%=user.getName()%> <%=user.getSurname()%></option>
                <% }
                %>
            </select>
            <input type="submit" value="Change">
        </form></td>
    </tr>
    <%} %>
</table>


</body>
</html>
