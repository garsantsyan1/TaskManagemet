<%@ page import="model.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Martin
  Date: 14.02.2022
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Home</title>
</head>
<body>

<% List<Task> tasks = (List<Task>) request.getAttribute("tasks"); %>
<% User user = (User) request.getAttribute("user"); %>

<a href="/logout">Logout</a>
<img src="/image?path=<%=user.getPictureUrl()%>" width="50" />
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Status</th>
        <th>Deadline</th>
        <th>Change Status</th>
    </tr>

    <%
        for (Task task : tasks) { %>
    <% if (task.isExpired()) { %>
    <tr style="background-color: red">
        <td><%=task.getId()%>
        </td>
        <td><%=task.getName()%>
        </td>
        <td><%=task.getDescription()%>
        </td>
        <td><%=task.getStatus()%>
        </td>
        <td><%=task.getDeadline()%>
        </td>
        <td>
            <form action="/updateStatus" method="post">
                <input type="hidden" name="taskId" value="<%=task.getId()%>">
                <select name="status">
                    <option value="NEW">NEW</option>
                    <option value="IN_PROGRESS">IN_PROGRESS</option>
                    <option value="FINISHED">FINISHED</option>
                </select><br>
                <input type="submit" value="Update">
            </form>
        </td>
    </tr>
    <% } else {%>
    <tr style="background-color: green">
        <td><%=task.getId()%>
        </td>
        <td><%=task.getName()%>
        </td>
        <td><%=task.getDescription()%>
        </td>
        <td><%=task.getStatus()%>
        </td>
        <td><%=task.getDeadline()%>
        </td>
        <td>
            <form action="/updateStatus" method="post">
                <input type="hidden" name="taskId" value="<%=task.getId()%>">
                <select name="status">
                    <option value="NEW">NEW</option>
                    <option value="IN_PROGRESS">IN_PROGRESS</option>
                    <option value="FINISHED">FINISHED</option>
                </select><br>
                <input type="submit" value="Update">
            </form>
        </td>
    </tr>
    <% }%>
    <% }
    %>


</table>

</body>
</html>
