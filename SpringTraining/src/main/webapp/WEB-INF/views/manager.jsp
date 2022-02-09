<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매니저별 직원명단 조회</title>
</head>
<style>
table {border-collapse:collapse;}
td,th {border:1px solid blue;}
</style>
<body>
<h1>직위별 직위명단 조회</h1>
매니저명: <select id=selJob>
<option>-</option>
<c:forEach items="${jobs }" var="job">
	<option value="${job.employee_id }">${job.emp_name }</option>
</c:forEach>
</select><br><br>
<table id=tbljob></table>
</body>
<script src='https://code.jquery.com/jquery-3.5.0.js'></script>
<script>
$(document)
.on('change','#selJob',function(){
	let jobid=$('#selJob').val();
	$.ajax({
		url:"/exercise/ManagerList",
		data:{mcode:jobid},
		method:"GET",
		datatype:'json',
		beforeSend:function(){
			$('#tbljob').empty();
		},
		success:function(data){
			for(i=0; i<data.length; i++){
				let str='<tr><td>'+data[i]['emp_id']+'</td><td>'+
						data[i]['emp_name']+'</td></tr>';
				$('#tbljob').append(str);
			}
		}
	})
	return false;
})
</script>
</html>