<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록</title>
</head>
<style>
table {
    margin-left: auto;
    margin-right: auto;
    border-collapse:collapse;
}
th,td{
	 border:1px solid black;
}
</style>
<body>
<table id=home align=right>
	<tr>
		<td><c:if test="${userid =='null' }"><a href='/team/login'>로그인</a>&nbsp;<a href='/team/register'>회원가입</a></c:if>
			<c:if test="${userid !='null' }">${userid}&nbsp; <a href='/team/logout_update' id=logout>로그아웃</a></c:if>
		</td>
	</tr>
</table>
<br>
<h1 align="center">휴먼 호텔</h1>
   <hr>
   <table>
   <tr align=center id=check><td><c:if test="${type ==0}"><a href="/team/admincheck" style="text-decoration:none">직원예약&nbsp;&nbsp;&nbsp;&nbsp;</a> </c:if>
   								 <c:if test="${type ==1}"><a href="/team/bookcheck" style="text-decoration:none">예약하기&nbsp;&nbsp;&nbsp;&nbsp;</a></c:if>
   								 <c:if test="${type ==2}"><a href="/team/bookcheck" id=close style="text-decoration:none">예약하기&nbsp;&nbsp;&nbsp;&nbsp;</a></c:if></td>
      <td><a href="/team/notice" style="text-decoration:none">게시판&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
      <td><a href="/team/" style="text-decoration:none">Q&A</a></td></tr>
   </table>
   <hr>
<table style='border-collapse:collapse;'>
<thead>
	<tr><th>게시물번호</th><th>제목</th><th>작성자</th><th>작성시각</th><th>조회수</th></tr>
</thead>
<tbody>
<c:forEach var="notice" items="${alNotice}">
	<tr onclick='document.location="/team/view?id=${notice.id}"'>
		<td>${notice.id}</td>
		<td>${notice.title}</td>
		<td>${notice.name}</td>
		<td>${notice.created}</td>
		<td>${notice.viewCnt}</td>
	</tr>
</c:forEach>
	<tr><td><input type=button value='글쓰기' onclick='document.location="/team/compose"'></td></tr>
</tbody>
</table>

</body>
<script>
</script>
</html>