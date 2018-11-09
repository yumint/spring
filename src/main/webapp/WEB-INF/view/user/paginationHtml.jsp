<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<li><a href="javascript:getUserListHtml(1);" aria-label="Previous">
		<span aria-hidden="true">&laquo;</span>
</a></li>


<c:set var="pageCnt" value="${pageCnt }"></c:set>

<c:forEach begin="1" end="${pageCnt }" var="page">
		<li><a href="javascript:getUserListHtml(${page });">${page}</a></li>
</c:forEach>


<li><a href="javascript:getUserListHtml(${pageCnt});" aria-label="Next">
		<span aria-hidden="true">&raquo;</span>
</a></li>
