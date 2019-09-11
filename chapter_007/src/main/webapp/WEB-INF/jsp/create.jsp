<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Create user</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/create"
      enctype="multipart/form-data">
    <table>
        <tr>
            <td><b>Name:</b></td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td><b>Login:</b></td>
            <td><input type="text" name="login"></td>
        </tr>
        <tr>
            <td><b>E-mail:</b></td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td><b>Password:</b></td>
            <td><input type="text" name="password"></td>
        </tr>
        <tr>
            <td><b>Role:</b></td>
            <td>
                <select name="role">
                    <option value="root">Root</option>
                    <option value="user">User</option>
                </select>
            </td>
        </tr>
        <tr>
        <td><b>Photo:</b></td>
        <td><input type="file" name="photo" accept="image/jpeg,image/png"></td>
        </tr>
    </table>
    <p><input type="submit" value="Create">
</form>
<p><a href="${pageContext.request.contextPath}/">User list</a></p>
<p><a href="${pageContext.request.contextPath}/signout">Sign Out</a></p>
</body>
</html>
