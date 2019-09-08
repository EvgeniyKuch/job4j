<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authentication</title>
</head>
<body>
<c:if test = "${error}">
    <p color="red">Invalid login or password</p>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/signin">
    <table>
        <tr>
            <td><b>Login:</b></td>
            <td><input type="text" name="login"></td>
        </tr>
        <tr>
            <td><b>Password:</b></td>
            <td><input type="password" name="password"></td>
        </tr>
    </table>
    <p><input type="submit" value="Sign In">
</form>
</body>
</html>
