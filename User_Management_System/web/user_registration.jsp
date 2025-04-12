<%-- 
    Document   : user_registration
    Created on : 4 Apr, 2025, 8:29:17 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Registration</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="UserServlet?action=register" method="post">
    Full Name: <input type="text" name="full_name" required><br><br>
    Email: <input type="email" name="email" required><br><br>
    Password: <input type="password" name="password" required><br><br>
    Phone: <input type="text" name="phone"><br><br>
    Role:
    <select name="role">
        <option value="user">User</option>
        <option value="admin">Admin</option>
    </select><br><br>

    <input type="submit" value="Register">
</form>

    </body>
</html>
