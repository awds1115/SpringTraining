<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>호텔홈</title>
</head>
<style>
table {
    margin-left: auto;
    margin-right: auto;
}
</style>
<body>
<%String userid=(String) session.getAttribute("userid"); 
String type=(String) session.getAttribute("type");
System.out.println(type);%>
<table id=home align=right>
	<tr>
		<td>
		<%	
				if(userid==null){ %>
					<a href='/team/login'>로그인</a>&nbsp;<a href='/team/register'>회원가입</a><%
				} else {
					%><%=userid%>&nbsp; <a href='/team/logout_update' id=logout>로그아웃</a><%
				}	
	 %> 
		</td>
	</tr>
</table>
<br>
<h1 align="center">휴먼 호텔</h1>
   <hr>
   <table>
   <tr align=center><td><%
   							if(type.equals("1")){%>
   							<a href="/team/bookcheck" style="text-decoration:none">고객예약&nbsp;&nbsp;&nbsp;&nbsp;</a><%
   							} else if(type.equals("0")){%>
   								<a href="/team/admincheck" style="text-decoration:none">직원예약&nbsp;&nbsp;&nbsp;&nbsp;</a>
   							<%} else{
   								%><a href="/team/bookcheck" id=close style="text-decoration:none">고객예약&nbsp;&nbsp;&nbsp;&nbsp;</a>
   						<%	}
   								%></td>
      <td><a href="/team/" style="text-decoration:none">게시판&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
      <td><a href="/team/" style="text-decoration:none">Q&A</a></td></tr>
   </table>
   <hr>
</body>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script>
$(document)
.on('click','#close',function(){
	alert('로그인 후 이용이 가능 합니다.);
})
</script>
</html>
