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
<c:if test="${op2!=null}">
<h1>연산자 불명</h1>
</c:if>
<c:if test="${num!=null}">
<h1>값이 없습니다.</h1>
</c:if>
<a href='/exercise/input'>돌아가기</a>
</body>
</html>