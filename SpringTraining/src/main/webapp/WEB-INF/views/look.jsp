<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${mobile}</h1><br>
<h2>${address }</h2><br>
<form method="GET" action="/exercise/view"> <!--  http://localhost:8088/exercise/view?nickname=xxxxx -->
	<input type=text name="nickname">
	<input type=submit value="Send">
	</form>
</body>
</html>