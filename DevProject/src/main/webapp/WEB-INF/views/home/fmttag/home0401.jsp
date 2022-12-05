<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>10) dateStyle 속성을 지정하지 않으면 기본값은 default이다.</p>
	<p>dateValue : ${dateValue }</p>
	
	<!-- 
		2022.11.30 parseDate value에 출력되는 데이터 형태에 따른 에러 점검 
		- value 값으로 2010. 1. 2 와 같은 데이터 값으로만 parsing이 가능함
		- value 값으로 2010.10.20과 같은 데이터 parsing Error 발생
	-->
	<fmt:parseDate value="2010. 10. 20" type="date" var="date"/>
	<p>date : ${date }</p>
</body>
</html>