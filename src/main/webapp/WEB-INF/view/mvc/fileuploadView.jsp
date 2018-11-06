<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fileuploadView</title>
</head>
<body>
	<h2>fileuploadView</h2>
	<form action="/mvc/fileupload" method="post" enctype="multipart/form-data">
		<input type="file" name="uploadFile"><br>
		<input type="text" name="userId" value="brown"><br>
		<input type="submit" value="전송"><br>
	</form>
</body>
</html>