<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: niblack
  Date: 17.01.18
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Validate Errors</title>
</head>
<body>
    <div>
        <ul>
        <c:forEach items="${violations}" var="violation">
            <li>${violation.propertyPath} : ${violation.message}</li>
        </c:forEach>
        </ul>
    </div>
</body>
</html>
