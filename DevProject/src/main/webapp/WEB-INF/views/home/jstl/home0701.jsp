<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home0601</title>
</head>
<body>
	<h3>7장 JSP</h3>
	<p>배열값을 순차적으로 처리한다.</p>
	<c:forEach items="${member.hobbyArray }" var="hobby" varStatus="stat">
		${stat.count } - ${hobby }
	</c:forEach>
</body>
</html>