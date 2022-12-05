<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>7장 JSP</h3>
	<p>EL식 내부에 발생한 예외는 EL식 내부에서 처리하므로 catch 변수 'ex'에 저장되지 않는다.</p>
	<c:catch var="ex">
		${member.hobbyArray[3] }
	</c:catch>
	<p>
		<c:if test="${ex != null }">
			${ex }
		</c:if>
	</p>
</body>
</html>