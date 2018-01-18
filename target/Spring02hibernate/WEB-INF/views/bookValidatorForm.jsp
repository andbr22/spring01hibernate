<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: niblack
  Date: 17.01.18
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Validation Form</title>
</head>
<body>
    <form:form method="post" modelAttribute="book">
        Tytuł:<form:input path="title"/>
        <form:errors path="title"/>
        <br>
        Authors:<form:select path="authors" items="${authors}" itemLabel="lastName" itemValue="id" multiple="true"/>
        <form:errors path="authors"/>
        <br>
        Publisher:<form:select path="publisher" items="${publishers}" itemLabel="name" itemValue="id"/>
        <form:errors path="publisher"/>
        <input type="submit"/>
    </form:form>
</body>
</html>
