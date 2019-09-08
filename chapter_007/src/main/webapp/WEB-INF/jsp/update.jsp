<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editing user</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/edit">
    <input type="hidden" name="id" value="${editingUser.id}">
    <table>
        <tr>
            <td><b>Name:</b></td>
            <td><input type="text" name="name" value="${editingUser.name}"></td>
        </tr>
        <tr>
            <td><b>Login:</b></td>
            <td><input type="text" name="login" value="${editingUser.login}"></td>
        </tr>
        <tr>
            <td><b>E-mail:</b></td>
            <td><input type="text" name="email" value="${editingUser.email}"></td>
        </tr>
        <tr>
            <td><b>Password:</b></td>
            <td><input type="text" name="password" value="${editingUser.password}"></td>
        </tr>
        <c:if test="${sessionScope.user.role.rule == 'root'}">
            <tr>
                <td><b>Role:</b></td>
                <td>
                    <select name="role">
                        <option <c:if test="${editingUser.role.rule == 'root'}">selected</c:if> value="root">Root</option>
                        <option <c:if test="${editingUser.role.rule == 'user'}">selected</c:if> value="user">User</option>
                    </select>
                </td>
            </tr>
        </c:if>
    </table>
    <p><input type="submit" value="Update">
</form>
<c:if test="${userAlreadyExists}"><p>User Already Exists</p>></c:if>
<p><a href="${pageContext.request.contextPath}/">User list</a></p>
<p><a href="${pageContext.request.contextPath}/signout">Sign Out</a></p>
</body>
</html>
