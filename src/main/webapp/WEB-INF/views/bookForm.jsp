<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
	Author:<select name="author" multiple>
		<c:forEach items="${authors}" var="aut">
			<option value="${aut.id}">${aut.firstName} ${aut.lastName}</option>
		</c:forEach>
	</select></br>



	Publisher:<select name="publisher">
		<c:forEach items="${publishers}" var="pub">
			<option value="${pub.id}">${pub.name}</option>
		</c:forEach>
	</select><br/>
	<input type="submit"/>
</form>
</body>
</html>