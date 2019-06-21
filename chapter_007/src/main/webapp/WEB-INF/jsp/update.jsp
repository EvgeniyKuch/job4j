<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editing user</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/edit">
    <input type="hidden" name="id" value="${user.id}">
    <table>
        <tr>
            <td><b>Name:</b></td>
            <td><input type="text" name="name" value="${user.name}"></td>
        </tr>
        <tr>
            <td><b>Login:</b></td>
            <td><input type="text" name="login" value="${user.login}"></td>
        </tr>
        <tr>
            <td><b>E-mail:</b></td>
            <td><input type="text" name="email" value="${user.email}"></td>
        </tr>
    </table>
    <p><input type="submit" value="Update">
</form>
<p><a href="${pageContext.request.contextPath}/">User list</a></p>
</body>
</html>
