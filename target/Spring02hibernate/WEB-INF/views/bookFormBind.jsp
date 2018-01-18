<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bookForm</title>
</head>
<body>
<form method="post">
	Title:<input name="title" value="${book.title}"/><br>
	Description:<input name="description" value="${book.description}"/><br>
	Author:
	<form:select path="book.authors" items="${authors}" itemLabel="lastName" itemValue="id" multiple="true"/><br>


	Publisher:
	<form:select path="book.publisher" items="${publishers}" itemLabel="name" itemValue="id"/><br/>
	<input type="submit"/>
</form>
</body>
</html>