<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view.jsp</title>
</head>
<body>
	<h2>view.jsp - spring validator</h2>
	<form action="/validator/validate" method="post">
		userId :  <input type="text" name="userId" value="${param.userId}" /> <form:errors path="userVo.userId"/><br>
		pass :    <input type="password" name="pass" value="${param.pass}"/> <form:errors path="userVo.pass"/> <br>		
		name :    <input type="text" name="name" value="${param.name}" /> <form:errors path="userVo.name"/> <br>		
		<input type="submit" value="전송"/>
	</form>
	
	
	<h2>view.jsp-jsr303</h2>
	<form action="/validator/validateJsr" method="post">
		userId :  <input type="text" name="userId" value="${param.userId}" /> <form:errors path="userVoJsr303.userId"/><br>
		pass :    <input type="password" name="pass" value="${param.pass}"/> <form:errors path="userVoJsr303.pass"/> <br>		
		name :    <input type="text" name="name" value="${param.name}" /> <form:errors path="userVoJsr303.name"/> <br>		
		<input type="submit" value="전송"/>
	</form>
</body>
</html>





































