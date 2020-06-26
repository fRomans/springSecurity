<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список пользователей</title>
</head>
<body>
<table border="2">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>password</td>
        <td>money</td>
    </tr>
    <c:forEach items="${users}" var = "user">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getFirstName()}</td>
            <td>${user.getLastName()}</td>
            <td>${user.getEmail()}</td>
            <td>
                <form action = "/update" method="get">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="submit" value="Изменить" style="float:left">
                </form>
                <form action="/delete" method="get">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="submit" value="Удалить" style="float:left">
                </form></td>
        </tr>
    </c:forEach>
</table>

<form action = "/add" method="get">
    <input type="submit" value="Добавить нового пользователя">
</form>

<p> ${SQLException}</p>

</body>
</html>