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
	   	   <th>부서아이디</th><th>부서명</th><th>상위부서명</th>
	   	   <th>부서장이름</th>
   	   </tr>
</thead>
<c:forEach items="${aldpl }" var="dpl">
	<tr>
		<td>${dpl.department_id}</td><td>${dpl.department_name}</td><td>${dpl.dname}</td>
		<td>${dpl.manager_name}</td>
<%-- 		<td>${dpl.hire_Date}</td><td>${dpl.salary}</td> --%>
	</tr>
</c:forEach>
</table>
</body>
</html>