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
<style>
table { border-collapse:collapse;}
td,th {
	border:1px solid black;
	background-color:gold;
	font-family:함초롱바탕;
	color:red;
}
</style>
<body>
<table>
<thead>
	   <tr>
	   	   <th>아이디</th><th>코드</th><th>이름</th>
	   	   <th>국가</th>
   	   </tr>
</thead>
<c:forEach items="${alcnr }" var="cnr">
	<tr>
		<td>${cnr.country_id}</td><td>${cnr.country_iso_code}</td><td>${cnr.country_name}</td>
		<td>${cnr.country_region}</td>
	</tr>
</c:forEach>
</table>
</body>
</html>