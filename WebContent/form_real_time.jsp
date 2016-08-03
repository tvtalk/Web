<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="DTO.ScheduleReservationDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<tr style="text-align: center height: 9px;">
	<td width="73">제목</td>
	<td width="73">참여자</td>
	<td width="73">시작시간</td>
</tr>
<c:forEach var="realtime" items="${requestScope.realtimes }">
	<tr style="text-align: left;">
		<td width="73" onclick="javascript:joinchatroom('<%=session.getAttribute("nickName") %>','${realtime.title}','${realtime.boradcast_brand }')">${realtime.title }</td>
		<td width="73"></td>
		<td width="73">${realtime.broadcastingTime }</td>
	</tr>
</c:forEach>
<tr height="25" align="center">
</tr>