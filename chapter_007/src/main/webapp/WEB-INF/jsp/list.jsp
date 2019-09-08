<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Users list</title>
</head>
<body>
<table border="2" cellspacing="0">
    <caption>Users list</caption>
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Login</th>
        <th>E-mail</th>
        <th>Create date</th>
        <th>Password</th>
        <th>Role</th>
        <th>Edit</th>
        <c:if test="${sessionScope.user.role.rule == 'root'}">
            <th>Delete</th>
        </c:if>
    </tr><c:forEach var="user" items="${list}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.login}</td>
            <td>${user.email}</td>
            <td>${user.date}</td>
            <td>${user.password}</td>
            <td>${user.role.rule}</td>
            <td>
                <form method="GET" action="${pageContext.request.contextPath}/edit">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="Edit">
                </form>
            </td>
            <c:if test="${sessionScope.user.role.rule == 'root'}">
                <td>
                    <form method="POST" action="${pageContext.request.contextPath}/">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </c:if>
        </tr></c:forEach>
</table>
<c:if test="${sessionScope.user.role.rule == 'root'}">
<p><a href="${pageContext.request.contextPath}/create">Create new user</a></p>
</c:if>
<p><a href="${pageContext.request.contextPath}/signout">Sign Out</a></p>
</body>
</html>
