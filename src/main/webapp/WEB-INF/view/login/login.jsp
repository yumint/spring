<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
</head>
<body>
	<form action="/user/loginProcess" method="post">
		userId : <input type="text" name="userId" value="brown"/> <br>
		password : <input type="password" name="pass" value="brownpass"/>
		<input type="submit" value="전송">
	</form>
</body>
</html>