<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴추가</title>
</head>
<body>
<table>
<tr>
	<td>
		<select id=selMenu style="width:260px;" size=10>
<%-- 		<c:forEach items="${alMl}" var="rml"> --%>
<%-- 		<option value=${rml.code }>${rml.name },${rml.price }</option> --%>
<%-- 		</c:forEach> --%>
		</select>
	</td>
	<td>
		<input type=hidden id=code name=code>
		<table>
		<tr><td align=right>메뉴명:</td>
			<td><input type=text id=name name=name></td>
		</tr>
		<tr><td align=right>가격:</td>
			<td><input type=number id=price name=price></td>
		</tr>
		<tr><td colspan=2 align=center><input type=button id=btnAdd  value='추가'>
									   <input type=button value="삭제" id=btnDelete>
									   <input type=button id=reset value="비우기"></td>
		</tr>
		</table>
	</td>
</tr>
</table>
<!-- 	<form method="get" action="/exercise/addMenu" id="frmAddMenu"> -->
<!-- 		메뉴명: <input type=text id=name name=name><br>  -->
<!-- 		가격: <input type=number id=price name=price><br>  -->
<!-- 			<input type=submit value=보내기> -->
<!-- 	</form> -->
</body>
<script src='https://code.jquery.com/jquery-3.5.0.js'></script>
<script>
$(document)
.ready(function(){   //  웹페이지가 화면에 나타난 직후에 실행되는 곳
loadMenu();
	
})
.on('click','#btnAdd',function(){
	$.ajax({
		url:"/exercise/addMenu",
		data:{code:$('#code').val(),name:$('#name').val(),price:$('#price').val()},
		method:'get',
		datatype:'json',
		beforeSend:function(){},
		success:function(data){
			$('#selMenu').empty();
			loadMenu();
		}
	})
	return false;
})
// .on('submit','#frmAddMenu',function() {
// 				if ($('input[name=name]').val() == ''
// 				 || $('input[name=price]').val() == '') {
// 					alert('두 값이 입력되어야 합니다.');
// 					return false;
// 				}
// 				return true;
// 			})
.on('click','#selMenu option',function(){
// 	console.log($(this).val()+$(this).text());
	$('#code').val($(this).val());
	console.log($('#code').val());
	let str=$(this).text();
	let ar=str.split(',');
	console.log("["+ar[0]+"]"+"["+ar[1]+"]");
	$('input[name=name]').val(ar[0]);
	$('input[name=price]').val(ar[1]);
	return false;
})
.on('click','#btnDelete',function(){
	let url="/exercise/deleteMenu?code="+$('#code').val();
	console.log(url);
	document.location=url;
})
.on('click','#reset',function(){
	$('#code,#name,#price').val('');
})
function loadMenu(){
	$.ajax({url:"/exercise/MenuList",data:{},datatype:"json",
		method:"GET",
		    success:function(txt){
		// javascript code
		for(i=0; i<txt.length; i++){
			let str='<option value='+txt[i]['code']+'>'+txt[i]['name']+','+txt[i]['price']+'</option>';
			$('#selMenu').append(str);
		}
		
		    }
	});
}
</script>
</html>