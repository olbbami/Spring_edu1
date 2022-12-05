<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>유저 등록 화면</h2>
	<form:form action="/validation/result2" method="post" modelAttribute="member">
		<table>
			<tr>
				<td>유저 ID</td>
				<td>
					<form:input path="userId"/>
					<font>
						<form:errors path="userId"/>
					</font>
				</td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td>
					<form:input path="password"/>
					<font>
						<form:errors path="password"/>
					</font>
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<form:input path="userName"/>
					<font>
						<form:errors path="userName"/>
					</font>
				</td>
			</tr>
			<tr>
				<td>E-Mail</td>
				<td>
					<form:input path="email"/>
					<font>
						<form:errors path="email"/>
					</font>
				</td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td>
					<form:input path="dateOfBirth" type="date"/>
					<font>
						<form:errors path="dateOfBirth"/>
					</font>
				</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<form:radiobutton path="gender" value="male" label="Male"/>
					<form:radiobutton path="gender" value="female" label="Female"/>
					<form:radiobutton path="gender" value="other" label="Other"/>
				</td>
			</tr>
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>