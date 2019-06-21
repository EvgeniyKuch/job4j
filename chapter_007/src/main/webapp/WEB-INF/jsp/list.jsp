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
        <th>Edit</th>
        <th>Delete</th>
    </tr><c:forEach var="user" items="${list}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.login}</td>
            <td>${user.email}</td>
            <td>${user.date}</td>
            <td>
                <form method="GET" action="${pageContext.request.contextPath}/edit">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="Edit">
                </form>
            </td>
            <td>
                <form method="POST" action="${pageContext.request.contextPath}/">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr></c:forEach>
</table><p><a href="${pageContext.request.contextPath}/create">Create new user</a></p>
</body>
</html>
