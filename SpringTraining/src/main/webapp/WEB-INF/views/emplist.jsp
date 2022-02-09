<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
table {border-collapse:collapse;}
td,th {border:1px solid blue;}
</style>
<body>
<input type=text id=txtKeyword>
<input type=button id=btnShow value='직원명단'><br>
<table id=tblEmp></table>
</body>
<script src='https://code.jquery.com/jquery-3.5.0.js'></script>
<script>
$(document)
.on('click','#btnShow',function(){
	
	$.ajax({url:"/exercise/EmpView",data:{kw:$('#txtKeyword').val()},datatype:"json",
		method:"post",
		beforeSend:function(){
			$('#tblEmp').empty();
			
		},
		    success:function(txt){
		// javascript code
		for(i=0; i<txt.length; i++){
			
			let str='<tr><td>'+txt[i]['emp_id']+'</td><td>'+txt[i]['emp_name']+'</td><td>'+txt[i]['mobile']+
						'</td><td>'+txt[i]['hire_date']+'</td><td>'+txt[i]['manager_id']+'</td></tr>'
			$('#tblEmp').append(str);
		}
		
		    }
	});
	//직원의 사번/이름/전화번호/매니저아이디.입사일자 onto the table "tblEmp"
})
</script>
</html>