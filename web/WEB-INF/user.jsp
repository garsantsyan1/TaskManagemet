<%@ page import="model.Task" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Martin
  Date: 14.02.2022
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<% List<Task> tasks = (List<Task>) request.getAttribute("tasks"); %>

<table border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>description</th>
        <th>status</th>
        <th>deadline</th>
    </tr>

    <%
        for (Task task : tasks) { %>
    <tr>
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
    </tr>
    <% }
    %>


</table>

</body>
</html>
