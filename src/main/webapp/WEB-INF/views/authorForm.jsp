<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: niblack
  Date: 16.01.18
  Time: 09:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Author Form</title>
</head>
<body>
    <form method="post">
        Imię:<input name="firstName" value="${author.firstName}"/><br/>
        nazwisko:<input name="lastName" value="${author.lastName}"/><br/>
        email:<input name="email" value="${author.email}"/><br/>
        PESEL:<input name="pesel" value="${author.pesel}"/><br/>
        Year of Birth:<input name="yearOfBirth" value="${author.yearOfBirth}"/><br/>
        <input type="submit"/>
    </form>
    <div>
        <ul>
            <c:forEach items="${violations}" var="violation">
                <li>${violation.propertyPath} : ${violation.message}</li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>
