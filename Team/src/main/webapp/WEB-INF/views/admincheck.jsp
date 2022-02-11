<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직원용 예약</title>
</head>
<style>
table {
    margin-left: auto;
    margin-right: auto;
}
table.bound { /*bound라는 class는 table 안에서만 쓸 수 있다.*/
   border-collapse: collapse;
   border: 1px solid black;
/*    height: 450px; */
/*    width: 450px; */
/*    font-family: cursive; */
}

td.bound { /*이 bound는 td 안에서는 아래의 의미가 된다.*/
   border: 1px solid black;
   background-color: cornsilk;
}
#selRoom{
   width:380px;
   height:320px;
}


#selSales {
   width: 300px;
   height: 400px;
}

#btnMinus, #btnCancel, #btnReset {
   background-color: tomato;
}

#btnMenu {
   background-color: red;
}
}
h3{
   text-align:center;
    background-color:white; 
}
</style>
<body>
<input type=button id=btn1 value="객실관리">
   <input type=button id=btn2 value="객실타입관리">
<h3 align="center">휴먼 호텔관리</h3>
   <hr>
   <table>
   <tr><td><a href="/team/home" style="text-decoration:none">홈&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
      <td><a href="/team/notice" style="text-decoration:none">게시판&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
      <td><a href="xxx" style="text-decoration:none">Q&A</a></td></tr>
   </table>
   <hr>
   <table>
      <tr>
         <td valign=top>
            <table class='bound' id=tblMenu>
               <caption>예약선택</caption>
               <tr>
                  <td colspan=2>예약기간:<input type=date id=date1>&nbsp; 
                             ~&nbsp;<input type=date id=date2></td>
               </tr>
               <tr>
                  <td>객실종류:<select id=roomtype name=roomtype style='width:150px'></select></td>
               </tr>
               <tr align=left>
                  <td>숙박인원<input type=number id=count min=0 style='width: 30px' value=1>명
                     <input type=button id=find value='찾기'></td>
                  <tr><td><select id=selRoom size=10></select></td></tr>
               </tr>
            </table>
         </td>
         <td valign=top>
            <table class='bound' id=tbl2 align=right>
               <caption id=sel1>예약입력</caption>
               <tr>
                  <td>예약번호 &nbsp;<input type=text style='width:150px'>
                  </td>
               </tr>
               <tr>
                  <td>숙박기간&nbsp;&nbsp;<input type=text style='width: 100px' class='as' >&nbsp;~&nbsp;
                                            <input type=text style='width: 100px' class='as'></td>
               </tr>
               <tr>
                  <td>객실명&nbsp;&nbsp;&nbsp;<input type=text size=13 readonly></td>
               </tr>
               <tr>
                  <td>객실종류&nbsp;&nbsp;<input type=text size=13 readonly></td>
               </tr>
               <tr>
                  <td>숙박인원&nbsp;&nbsp;<input type=number size=13></td>
               </tr>
               <tr>
                  <td>대표자명&nbsp;&nbsp;<input type=text size=13></td>
               </tr>
               <tr>
                  <td>모바일&nbsp;&nbsp;&nbsp;<input type=phone value='전화번호 입력' size=13></td>
               </tr>
               <tr>
                  <td>숙박비&nbsp;&nbsp;<input type=number readonly></td>
               </tr>
               <tr>
                  <td colspan=2>&nbsp;</td>
               </tr>
               <tr>
                  <td><button id=btnCancel>주문취소</button><button id=btnDone>주문완료</button></td>
               </tr>
            </table>
         </td>
         <td valign=top>
            <table class='bound'>
               <caption id=sel>예약된 객실목록</caption>
               <tr>
                  <td colspan=2 align=right>
               <tr>
                  <td><select id=selSales size=20></select></td>
               </tr>
            </table>
         </td>
      </tr>
   </table>
     <div id=dlgMenu id=btnMenu style='display:none;' title="메뉴관리">
        <table>
            <tr>
                <td>
                   <input type=text id=text1 style='width:120px'>
                </td>
                <td>
                    <table>
                    <tr>
                        <td>코드</td><td><input type=text id=code style='width:120px'></td>
                    </tr>
                    <tr>
                        <td>메뉴명</td><td><input type=text id=name style='width:120px'></td>
                    </tr>
                    <tr>
                        <td>단가(가격)</td><td><input type=number id=price min=0 step=500 style='width:50px'>원</td>
                    </tr>
                    <tr>
                        <td colspan=2 align=center><button id=btnPlus>Complete</button>&nbsp;
                        </td>
                    </tr>
                    </table>
                </td>
            </tr>    
            </table>
    </div>
   <div id=dlgMenu2 style='display: none;' title="매출내역">
      <table>
         <tr>
            <td><select id=selMenu2 size=10 title="메뉴별 매출내역"></select></td>
            <td><select id=selMenu3 size=10 title="고객별 매출내역"></select></td>
            </tr>
      </table>
   </div>
</body>
<script src='https://code.jquery.com/jquery-3.5.0.js'></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script>
   $(document)
   .on('click','#find',function(){
      
   })
</script>
</html>

</body>
</html>