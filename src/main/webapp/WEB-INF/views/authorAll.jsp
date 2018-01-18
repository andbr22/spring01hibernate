<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: niblack
  Date: 17.01.18
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Authors</title>
</head>
<body>
    <table>
        <tr>
            <td>Name</td>
            <td>Action</td>
        </tr>
        <c:forEach items="${authors}" var="author">
            <tr>
                <td>${author.firstName} ${author.lastName}</td>
                <td><a href="/author/update/${author.id}">EDIT</a> <a href="/author/delete/${author.id}">DELETE</a></td>
            </tr>
        </c:forEach>
    </table>
<hr/>
<a href="/author/create">Add New</a>
</body>
</html>
