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
<table>
<tr>
	<td>
		<select id=selRoom style="width:260px;" size=10>
<%-- 		<c:forEach items="${alRml}" var="rml"> --%>
<%-- 		<option value=${rml.roomcode }>${rml.name },${rml.typename },${rml.howmany },${rml.howmuch }</option> --%>
<%-- 		</c:forEach> --%>
		</select>
	</td>
	<td>
		<form id=frmRoom action="/exercise/addRoom">
		<table>
		<tr><td align=right>객실명:</td>
			<td><input type=text name=roomname><input type=hidden id=code name=code></td>
		</tr>
		<tr><td align=right>타입:</td>
			<td><select id=roomtype name=roomtype>
			<option value=""></option>
<%-- 			<c:forEach items="${types }" var="roomtype"> --%>
<%-- 			<option value=${roomtype.typecode}>${roomtype.name}</option> --%>
<%-- 			</c:forEach> --%>
			</select></td>
		</tr>
		<tr><td align=right>숙박가능인원:</td>
			<td><input type=number name=howmany></td>
		</tr>
		<tr><td align=right>숙박비:</td>
			<td><input type=number name=howmuch></td>
		</tr>
		<tr><td colspan=2 align=center>
			<input type=submit value='추가'>
			<input type=button value='삭제' id=btnDelete>
			<input type=reset value='비우기' id=resetbutton></td>
		</tr>
		</table>
		</form>
	</td>
</tr>
</table>
</body>
<script src='https://code.jquery.com/jquery-3.5.0.js'></script>
<script>
$(document)
.ready(function(){   //  웹페이지가 화면에 나타난 직후에 실행되는 곳
	$.ajax({url:"/exercise/RoomList",data:{},datatype:"json",
		method:"GET",
		beforeSend:function(){
			alert('ajax called');
		},
		    success:function(txt){
		// javascript code
		for(i=0; i<txt.length; i++){
			let str='<option value='+txt[i]['roomcode']+'>'+txt[i]['roomname']+','+txt[i]['name']+','+
									 txt[i]['howmany']+','+txt[i]['howmuch']+'</option>';
			$('#selRoom').append(str);
		}
		
		    }
	});
	$.ajax({url:"/exercise/TypeList",data:{},datatype:"json",
		method:"GET",
		    success:function(txt){
		// javascript code
		for(i=0; i<txt.length; i++){
			let str='<option value='+txt[i]['typecode']+'>'+txt[i]['typename']+'</option>';
			$('#roomtype').append(str);
		}
		
		    }
	});
	
})
.on('click','#selRoom option',function(){
	$('#code').val($(this).val());
	let str=$(this).text();
	let ar=str.split(',');
	console.log("["+ar[0]+"]"+"["+ar[1]+"]");
	$('input[name=roomname]').val(ar[0]);
	$('input[name=howmany]').val(ar[2]);
	$('input[name=howmuch]').val(ar[3]);
	let roomtype=$.trim(ar[1]);
	$('#roomtype').val('');
	$('#roomtype option').each(function(){  // select로 만든 typecode 를 클릭했을때 클릭한 code가 나오게 하는 법
		if($(this).text()==roomtype){
			$(this).prop('selected','selected');
			return false;
		}
	})
	return false;
})
.on('click','#btnDelete',function(){
	let url="/exercise/deleteRoom?code="+$('#code').val();
	console.log(url);
	document.location=url;
})
.on('click','#resetbutton',function(){
	$('#code').val('');
})
</script>
</html>