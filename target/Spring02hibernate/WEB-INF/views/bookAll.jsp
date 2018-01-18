<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: niblack
  Date: 17.01.18
  Time: 09:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Books</title>
</head>
<body>
<table border="2">
    <tr>
        <td>Title</td>
        <td>Author</td>
        <td>Publisher</td>
        <td>Description</td>
        <td>Actions</td>
    </tr>

    <c:forEach items="${books}" var="book">
    <tr>
        <td>${book.title}</td>
        <td>
            <c:forEach items="${book.authors}" var="author">
                ${author.firstName} ${author.lastName} <br>
            </c:forEach>
        </td>
        <td>${book.publisher.name}</td>
        <td>${book.description}</td>
        <td><a href="/book/update/${book.id}">EDIT</a><a href="/book/delete/${book.id}">DELETE</a> </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
