<%@ page import="model.Request" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contact Requests</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h2 {
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        .archive-btn {
            background-color: #888;
            color: white;
            padding: 5px 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .archive-btn:hover {
            background-color: #666;
        }
    </style>
</head>
<body>
<h2>Active Contact Requests</h2>

<%
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    List<Request> activeRequests = (List<Request>) request.getAttribute("activeRequests");
    List<Request> archivedRequests = (List<Request>) request.getAttribute("archivedRequests");

    if (activeRequests == null || activeRequests.isEmpty()) {
%>
<p>No active requests found.</p>
<%
} else {
%>
<table>
    <tr>
        <th>Full Name</th>
        <th>Email</th>
        <th>Message</th>
        <th>Created At</th>
        <th>Action</th>
    </tr>
    <%
        for (Request requestItem : activeRequests) {
    %>
    <tr>
        <td><%= requestItem.getFullName() %>
        </td>
        <td><%= requestItem.getEmail() %>
        </td>
        <td><%= requestItem.getMessage() %>
        </td>
        <td><%= requestItem.getCreatedAt()%>
        </td>
        <td>
            <form action="dashboard" method="post">
                <input type="hidden" name="action" value="1">
                <input type="hidden" name="requestId" value="<%= requestItem.getId() %>">
                <button type="submit" class="archive-btn">Archive</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>

<h2>Archived Contact Requests</h2>

<%
    if (archivedRequests == null || archivedRequests.isEmpty()) {
%>
<p>No archived requests found.</p>
<%
} else {
%>
<table>
    <tr>
        <th>Full Name</th>
        <th>Email</th>
        <th>Message</th>
        <th>Created At</th>
        <th>Action</th>
    </tr>
    <%
        for (Request requestItem : archivedRequests) {
    %>
    <tr>
        <td><%= requestItem.getFullName() %>
        </td>
        <td><%= requestItem.getEmail() %>
        </td>
        <td><%= requestItem.getMessage() %>
        </td>
        <td>
            <%= requestItem.getCreatedAt() %>
        </td>
        <td>
            <form action="dashboard" method="post">
                <input type="hidden" name="action" value="0">
                <input type="hidden" name="requestId" value="<%= requestItem.getId() %>">
                <button type="submit" class="archive-btn">Active</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>


<form action="logout" method="post">
    <button type="submit" style="position: absolute; top: 20px; right: 20px;">Logout</button>
</form>
</body>
</html>
