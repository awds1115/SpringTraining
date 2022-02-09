<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Input</title>
</head>
<body>
<form method="GET" action="/exercise/calc">
x1: <input type=text id=x1 name=x1><br>
x2: <input type=text id=x2 name=x2><br>
op: <input type=text id=op name=op><br>
	<input type=submit value="send">
<%-- 	result: ${result} --%>

<%-- <c:if test="${result==null}"> --%>
<!--   <h1>값없음</h1> -->
<%-- </c:if> --%>
<%-- <c:if test="${result!=null}"> --%>
<%-- 	<h1>result: ${result}</h1><br> --%>
<%-- </c:if> --%>
</form>
</body>
</html>