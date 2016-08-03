<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="DTO.ScheduleReservationDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>게시판</title>
<style>
 a:active{text-decoration: none;}          
 a:hover{text-decoration: none;}
 a:link{text-decoration: none;}
 a:visited{text-decoration: none;}
 @import url
 (http://fonts.googleapis.com/earlyacccess/nanumgothic.css);
 td {
 font-family : 'Nanum Gothic';
 }
 div_input{
 width:33px;
 background:url(images/search.png) no-repeat;}

left{
float:left;
}


board{

float:right;
border-radius: 8px; 
-moz-border-radius: 8px; 
-webkit-border-radius: 8px; 
border: 1px solid #6C24F3;}
</style>

</head>

<Script>

window.onload = function(){
	
	$(".menubar").click(function(){
		var no = document.getElementsByClassName("menubar")[$(".menubar").index(this)].textContent;
		if(no=='전체')
			window.location="Down.jsp";
		$.ajax({
			type:"post",
			url:"genre",
			data:{ generNo: no },
			success : function(data) {
				$("#realtimearea").html(data);
			}
		});
	});
		
	
	function realtimearea(data){
		$("#realtimearea").html(data);
	}
	$("#login_input").keydown(function (e){
	    if(e.keyCode == 13){
	        $.ajax({
	        	type:"post",
	        	url:"join",
	        	data: {nickName :$("#login_input").val() },
	        	success: function(data) {
	        		alert(data);
	        		window.location="Down.jsp";
	        	}
	        });
	    }
	});
	$("#search_input").keydown(function(e){
		if(e.keyCode==13){
			$.ajax({
				type:"post",
				url:"search",
				data:{keyword: $("#search_input").val()},
				success : function(data) {
					$("#current_text").html("검색 결과");
					$("#realtimearea").html(data);
				}
			});
		}
	});
	$.ajax({
		type:"post",
		url:"realtime",
		success: function(data) {
			
			$("#realtimearea").html(data);
		}
	});
	$.ajax({
		type:"get",
		url:"bookmark",
		success: function(data) {
			
		}
	});
	//genre

};
function joinchatroom(name,room,chanel){
	if(name==null) {
		alert("로그인 후 서비스를 이용해 주세요.");
		return;
	}
	$("#myname").val(name);
	$("#roomnm").val(room);
	$("#chanel").val(chanel);
	$("#subm").focus().click();
}

</Script>
<body style="margin:0">
<font face="Nanum Godic" size="3px">
<div style="padding:20px">
<div><left>
<a href="Down.jsp">
<img src="images/logo.png">
</a>
</left>
</div>
<div style="line-height:0;" ><board>
<input type="text" id="search_input"  style="border: none; background: transparent; width:90px; height:9.5px;">
<input type="image" src="images/search_btn.png" height="8px" style="padding-right: 4px;">
</board></div>
<div style="float:right;">&nbsp;&nbsp;&nbsp;</div>
<div style="float:right;">
<input type="text" value="ID:" id="login_input" style="font-size:10px; color:#6C24F3; border: 0; 
    outline: 0;
    background: transparent;
    border-bottom: 1px solid #6C24F3; width:90px; height:9.5px; margin-top:1px;"></div>
</div>
</div>
<table border="0" width="100%" align="center" style="padding-top:25px; font-size:13px;">
   <tr align="center">
   <style>
   .menubar:hover{
   	cursor:pointer;
   }
   </style>
      <td><span value="a" class="menubar"><font color="#6C24F3">전체</font></span></td>
      <td><font color=gray>|</font></td>
      <td><span value="" class="menubar"><font color="black">예능</font></span></td>
      <td><font color=gray>|</font></td>
      <td> <span value="c" class="menubar"> <font color="black">드라마</font></span></td>
      <td><font color=gray>|</font></td>
      <td> <span value="d" class="menubar"><font color="black">시사</font></span></td>
      <td><font color=gray>|</font></td>
      <td><span value="f" class="menubar"><font color="black">교양</font></span></td>
      <td><font color=gray>|</font></td>
      <td><span value="g" class="menubar"><font color="black">스포츠</font></span></td>   
   </tr>
</table> 

<div style="margin-bottom:10px; width:100%;height:15px; background-color:#6C24F3"></div>
	<div>
		
		
		<Subject style="padding-left:1.5%;">
      <td>즐겨찾기</td>
      </Subject>
		
		
		<br>
		<table width="100%" cellpadding="0" cellspacing="5" border=solid 0.5px #e7e7e7 align="center" height: 9px;
			rules="rows">
			
			<% List<ScheduleReservationDTO> list = (List<ScheduleReservationDTO>)request.getAttribute("list"); %>
			<font size="2px">

			</font>
			<tr height="25" align="center">
			</tr> 
		</table>

	
		
		<Subject style="padding-left:1.5%;">
      
      <td id="current_text">실시간 참여율</td>
      
      </Subject>
		
		

		<br>
		
         <!-- 
		<table width="100%" cellpadding="5" cellspacing="5" border: solid 0.5px #e7e7e7 align="center" rules="rows" id="realtimearea">
 -->
 <table style="font-size:12px; border: solid 0.5px #e7e7e7; padding:10px; margin-top:5px;" width="97%" cellpadding="5" cellspacing="5" align="center"
         rules="rows" id="realtimearea">
		</table>
	</div>
	<!--  
		<div>
		 <table style="font-size:12px; border: solid 0.5px #e7e7e7; padding:10px; margin-top:5px;" width="97%" cellpadding="5" cellspacing="5" align="center"
         rules="rows">
		<!--
		 
		<table border=0 bgcolor="#6C24F3"  width="100%"  height: 25.5px border align="center">
		 -->
		<!--  
			<tr align="left">
				<td >
					<font font-family: 'Nanum Gothic', sans-serif; color="#ffffff" size="1px" font-weight: 400;>
						문의 : jbk666@naver.com</td></font>
			</tr>

			<tr align="left">
				<td  style='padding-left: 0px;'>
					<font font-family: 'Nanum Gothic', sans-serif; color="#ffffff" size="1px" font-weight: 400;>
						서비스 장애 신고 :070-3384-2911</td></font>
					
			</tr>
			
			
	
			<tr>

				<td rowspan="4" align="right"  width="100%" style="">
					<font font-family: 'Nanum Gothic', sans-serif; color="#ffffff"
					size="11px" letter-spacing: 0.6px;>TvT</font> <font color="#ffffff"
					size="9px" font-family:ArchitectsDaughter;   font-weight: 400; letter-spacing: 0.6px;>alk</font>
				</td>

			</tr>

		</table>
		</div>-->
		
		<form action="http://52.78.32.50:3000/chat/gochat" method="post" accept-charset="utf-8">
				<input type="hidden" id="myname" name ="name" value="" placeholder="이름">
				<input type="hidden" id="roomnm" name ="room" value="" placeholder="방이름">
				<input type="hidden" id="chanel" name ="chanel" name="chanel" placeholder="채널" value=""/>
				<button id="subm" style="background-color:transparent; border:0px; border-color:none;" ></button>
			</form>
		<div>
      <table cellspacing=15 border=0 bgcolor="#6C24F3"  width="100%"  height: 25.5px border align="center">
      
         <tr align="left">
            <td >
               <font font-family: 'Nanum Gothic', sans-serif; color="#ffffff" size="1px" font-weight: 400;>
                  문의 : jbk666@naver.com</td></font>
         </tr>

         <tr align="left">
            <td  style='padding-left: 0px;'>
               <font font-family: 'Nanum Gothic', sans-serif; color="#ffffff" size="1px" font-weight: 400;>
                  서비스 장애 신고 :070-3384-2911</td></font>
               
         </tr>
      
         <tr>

            <td rowspan="4" align="right"  width="100%">
            <img src="images/logo2.png" width="75px">
            
            </td>

         </tr>

      </table>
      </div>
		
		
		
		</font>
</body>

</html>
