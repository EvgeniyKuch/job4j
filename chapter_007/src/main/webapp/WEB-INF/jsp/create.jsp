<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Create user</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/create">
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
    </table>
    <p><input type="submit" value="Create">
</form>
<p><a href="${pageContext.request.contextPath}/">User list</a></p>
</body>
</html>
