<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: niblack
  Date: 16.01.18
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Person Form</title>
</head>
<body>
    <form:form method="post" modelAttribute="person">
        Login:<form:input path="login"/><br/>
        Password:<form:password path="password"/><br/>
        Email:<form:input path="email"/><br/>

        First name:<form:input path="personDetails.firstName"/><br/>
        Last name:<form:input path="personDetails.lastName"/><br/>

        Country: <form:select path="personDetails.country" items="${countries}"/><br/>

        Male: <form:radiobutton path="personDetails.gender" value="Male"/><br>
        Female: <form:radiobutton path="personDetails.gender" value="Femal"/><br>
        Neither: <form:radiobutton path="personDetails.gender" value="Neither"/><br>

        Mailing: <form:checkbox path="personDetails.mailing"/>

        <input type="submit"/>
    </form:form>
</body>
</html>
