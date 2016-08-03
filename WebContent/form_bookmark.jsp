<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="DTO.BookmarkDTO" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="css/style.css">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="bookmark" items="${requestScope.bookmarks }">
	<tr>
		<td width="73"
			onclick="javascript:joinchatroom('<%=session.getAttribute("nickName")%>')">${bookmark.title }</td>
		<td width="73"></td>
		<td width="73">${bookmark.rating }</td>
	</tr>
</c:forEach>

</body>
</html>