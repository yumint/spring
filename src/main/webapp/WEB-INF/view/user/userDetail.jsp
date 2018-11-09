<%@page import="java.util.Set"%>
<%@page import="kr.or.ddit.util.model.PageVo"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.service.UserService"%>
<%@page import="kr.or.ddit.user.service.UserServiceInf"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
		$(document).ready(function(){
			var click = "click";
			$("#update").on(click, function(){
				var userId = $("#userId2").text();
				
				$("#userId").val(userId);
				$("#frm").submit();
			});
		});
	
</script>

<style type="text/css">
#updateForm {
	padding-left: 200px;
}
</style>


<% SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
%>

<div class="row">



	<form id="frm" class="form-horizontal" role="form" method="get"
		action="/user/userFormUpdate?userId=${userInfo.userId}">


		<div class="form-group">
			<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
			<div class="col-sm-10">

				<!-- //									String profilePath = user.getProfile(); -->
				<!--// 									profilePath = -->
				<!--// 									profilePath == null ? "/profile/noimage.png" : profilePath; -->

				<%-- 									<img src="<%= profilePath%>"> --%>

				<c:choose>
					<c:when test="${userInfo.profile == null}">
						<c:set var="profile" value="/profile/noimage.png"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="profile" value="${userInfo.profile }"></c:set>
					</c:otherwise>
				</c:choose>
				<img src="${userInfo.profile}">
				<%-- 									<img src="/fileDownload?userId=${userInfo.userId}"> --%>

			</div>
		</div>

		<div class="form-group">
			<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
			<div class="col-sm-10">
				<input type="hidden" id="userId" name="userId"> <label
					id="userId2" class="control-label">${userInfo.userId }</label>
			</div>
		</div>

		<div class="form-group">
			<label for="userNm" class="col-sm-2 control-label">이름</label>
			<div class="col-sm-10">
				<label class="control-label">${userInfo.name }</label>
			</div>
		</div>
		<div class="form-group">
			<label for="userNm" class="col-sm-2 control-label">주소</label>
			<div class="col-sm-10">
				<label class="control-label">${userInfo.addr1 }</label>
			</div>
		</div>
		<div class="form-group">
			<label for="pass" class="col-sm-2 control-label">상세주소</label>
			<div class="col-sm-10">
				<label class="control-label">${userInfo.addr2 }</label>
			</div>
		</div>

		<div class="form-group">
			<label for="pass" class="col-sm-2 control-label">우편번호</label>
			<div class="col-sm-10">
				<label class="control-label">${userInfo.zipcd }</label>
			</div>
		</div>

		<div class="form-group">
			<label for="pass" class="col-sm-2 control-label">생년월일</label>
			<div class="col-sm-10">
				<label class="control-label"> <fmt:formatDate
						value='${userInfo.birth }' pattern='yyyy-MM-dd' /></label>
			</div>
		</div>

		<div class="form-group">
			<label for="pass" class="col-sm-2 control-label">이메일</label>
			<div class="col-sm-10">
				<label class="control-label">${userInfo.email }</label>
			</div>
		</div>

		<div class="form-group">
			<label for="pass" class="col-sm-2 control-label">연락처</label>
			<div class="col-sm-10">
				<label class="control-label">${userInfo.tel }</label>
			</div>
		</div>

		<div id="updateForm" class="form-group">
			<button id="update">사용자 수정</button>
		</div>

	</form>

</div>

</body>
</html>