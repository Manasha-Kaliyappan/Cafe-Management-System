<%@ page import="java.util.*, com.user.UserDAO, com.user.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Management System</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to right, #c6ffdd, #fbd786, #f7797d);
            margin: 0;
            padding: 40px;
        }

        .container {
            max-width: 1000px;
            margin: auto;
            background-color: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        form, table {
            width: 100%;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 15px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .radio-group, .checkbox-group {
            margin-bottom: 15px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 8px;
            font-weight: bold;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        table {
            margin-top: 30px;
            border-collapse: collapse;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        a {
            text-decoration: none;
            padding: 6px 10px;
            background-color: #28a745;
            color: white;
            border-radius: 5px;
        }

        a.delete {
            background-color: #dc3545;
        }

        a:hover {
            opacity: 0.85;
        }
    </style>
</head>
<body>
<%
    com.user.User editUser = (User) request.getAttribute("editUser");
    boolean isEdit = (editUser != null);
%>
<div class="container">
    <h2><%= isEdit ? "Edit User" : "User Registration Form" %></h2>
    <form action="UserServlet" method="post">
        <input type="hidden" name="action" value="<%= isEdit ? "update" : "add" %>">
        <input type="hidden" name="id" value="<%= isEdit ? editUser.getId() : "" %>">

        <label>First Name:</label>
        <input type="text" name="first_name" required value="<%= isEdit ? editUser.getFirstName() : "" %>">

        <label>Last Name:</label>
        <input type="text" name="last_name" required value="<%= isEdit ? editUser.getLastName() : "" %>">

        <div class="radio-group">
            <label>Gender:</label><br>
            <input type="radio" name="gender" value="Male" <%= isEdit && "Male".equals(editUser.getGender()) ? "checked" : "" %>> Male
            <input type="radio" name="gender" value="Female" <%= isEdit && "Female".equals(editUser.getGender()) ? "checked" : "" %>> Female
        </div>

        <div class="checkbox-group">
            <label>Skills:</label><br>
            <%
                String[] allSkills = {"Java", "Python", "HTML", "CSS", "JavaScript"};
                String[] selectedSkills = isEdit ? editUser.getSkills().split(", ") : new String[0];
                List<String> selectedList = Arrays.asList(selectedSkills);
                for (String skill : allSkills) {
            %>
                <input type="checkbox" name="skills" value="<%= skill %>" <%= selectedList.contains(skill) ? "checked" : "" %>> <%= skill %>
            <%
                }
            %>
        </div>

        <input type="submit" value="<%= isEdit ? "Update User" : "Register" %>">
    </form>

    <h2>Registered Users</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Gender</th>
            <th>Skills</th>
            <th>Actions</th>
        </tr>
        <%
            List<User> userList = UserDAO.getAllUsers();
            for (User u : userList) {
        %>
        <tr>
            <td><%= u.getId() %></td>
            <td><%= u.getFirstName() %></td>
            <td><%= u.getLastName() %></td>
            <td><%= u.getGender() %></td>
            <td><%= u.getSkills() %></td>
            <td>
                <a href="UserServlet?action=edit&id=<%= u.getId() %>">Edit</a>
                <a href="UserServlet?action=delete&id=<%= u.getId() %>" class="delete">Delete</a>
            </td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>
