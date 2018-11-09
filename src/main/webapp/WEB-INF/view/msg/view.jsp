<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view.jsp</title>
</head>
<body>
hello : <spring:message code="hello"/><br>
visitor :  <spring:message code="visitor" arguments="brown"/><br>

<c:set var = "lang" value="${param.lang==null?'ko':param.lang }"/>

<script src ="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">

   <%--	방법1 : 변경버튼을 클릭하지 않고 select박스만 클릭해도 해당 언어로 변경하는 방법--%>
   	$(document).ready(function(){
      // select box value set
      $("#lang").val("${lang}");
      
      $("#lang").on("change",function(){
         $("#frm").submit();
      });
   });

   
   <%-- 방법2 : 셀렉트박스에서 원하는 언어를 클릭한후 변경버튼을 클릭해야 변경되는 방법 --%>
   <%--document.getElementsByTagName("select")[0].value="${lang}"; --%>
   
</script>

<form id="frm" action="/messageView" method="Post">

	<select id="lang" name="lang">
		<option value="ko">한국어</option>
		<option value="en">English</option>
		<option value="ja">日本語</option>
		
	</select>
	<input type="submit" value="변경">
</form>
</body>
</html>