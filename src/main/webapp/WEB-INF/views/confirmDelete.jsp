<%--
  Created by IntelliJ IDEA.
  User: niblack
  Date: 17.01.18
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Usunięcie książki</title>
</head>
<body>
    <p>${book}</p>
    Are you sure you want to delete book?
    <form method="post"><input type="submit" value="DELETE"></form>
    <form method="get" action="/book/read"><input type="submit" value="NO"></form>
</body>
</html>
