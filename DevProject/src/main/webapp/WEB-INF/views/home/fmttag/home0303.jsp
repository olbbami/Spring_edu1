<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>8) type 속성을 both로 지정하여 날짜, 시간 둘 다 포맷팅을 한다.</p>
		<p>now : ${now }</p>
	<p>time : <fmt:formatDate value="${now }" type="both"/></p>
	<p>time default : <fmt:formatDate value="${now }" type="both" dateStyle="default" timeStyle="default"/></p>
	<p>time short : <fmt:formatDate value="${now }" type="both" dateStyle="short" timeStyle="short"/></p>
	<p>time medium : <fmt:formatDate value="${now }" type="both" dateStyle="medium" timeStyle="medium"/></p>
	<p>time long : <fmt:formatDate value="${now }" type="both" dateStyle="long" timeStyle="long"/></p>
	<p>time full : <fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/></p>
</body>
</html>