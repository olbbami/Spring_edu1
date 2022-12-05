<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>7장 JSP</h3>
	<p>태그의 몸체를 값으로 사용한다.</p>
	
	<c:set var="memberId">${member.userId }</c:set>
	<table border="1">
		<tr>
			<td>member.userId</td>
			<td>${memberId }</td>
		</tr>
	</table>
</body>
</html>