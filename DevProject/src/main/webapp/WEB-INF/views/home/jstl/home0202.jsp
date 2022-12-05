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
	<p>JSP에서 사용될 변수 memberId를 설정해서 사용한다.</p>
	
	<c:set value="${member.userId }" var="memberId"/>
	<table border="1">
		<tr>
			<td>member.userId</td>
			<td>${memberId }</td>
		</tr>
	</table>
</body>
</html>