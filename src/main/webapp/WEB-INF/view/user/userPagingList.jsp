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

<style type="text/css">
.userClick {
	cursor: pointer;
}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		
		// tr에 select(class="userClick")
		$("#userList").on("click",".userClick" ,function(){
			console.log("userClick");
			var userId = $(this).children()[1].innerHTML;
			
			$("#userId").val(userId);
			
			//  .summit();
			$("#frm").submit();
		});
		
		// 이부분을 입력하였기 때문에 페이지리스트를 클릭하였을때 리스트가 나오는것
		//getUserList(1);
		getUserListHtml(1);	// userList html로 리턴해주는 함수 
	
		
	});
	
	function getUserListHtml(page){
		var pageSize = 10;
		
		$.ajax({
			url : "/user/userPageListAjaxHtml",
			type : "get" ,
			data : "page="+page+"&pageSize="+pageSize,
			success : function(dt){
				$("#userList").html(dt);
				getUserPagenationHtml(1);	// 해당 페이지의 페이지 네이션 정보를 리턴해주는 함수 
			}
		});
		
	}
	
	function getUserPagenationHtml(page){
		var pageSize = 10;

		$.ajax({
			url : "/user/pageListAjaxHtml",
			type : "get" ,
			data : "page="+page+"&pageSize="+pageSize,
			success : function(dt){
				$("#paginationHtml").html(dt);
			
			}
		});
		
	}
	
	
	// page, pageSize인자를 받아서 해당 페이지에 속하는 사용자 리스트 정보를 가져온다
	function getUserList(page){
		var pageSize = 10;
		
		// ajax call
		// 사용자 리스트 데이터만 가져오기 
		// html(기존) --> json(현재 사용할것)데이터를 받는 형태로 변경 
		$.ajax({
			type : "GET",
			url : "/user/userPageListAjax",
			data : "page="+page+"&pageSize="+pageSize,
				// page=1&pageSize=10
			success : function(data){
				// data.userList = 10 data.pageCnt
				// data(사용자 json 데이터)를 바탕으로 사용자 리스트를 변경하는 작업 
				// 1. 기존 리스트를 삭제 
				// 2. data를 이용하여 table태그 (tr)를 작성
				// 3. 기존 리스트 위치에다가 붙여 넣기 
				
				// 데이터가 잘왔다는걸 확인하기 위해서 입력함 
				//console.log(data);
				
				var html = "";
				$.each(data.userList , function(idx , user){
					html += "<tr class='userClick'>";
					html += "<td>"+user.rnum+"</td>";
					html += "<td>"+user.userId+"</td>";
					html += "<td>"+user.name+"</td>";
					html += "<td>"+user.FormattedBierth+"</td>";
					html += "</tr>";
// 				<tr class="userClick">
// 					<td>${vo.rnum}</td>
// 					<td>${vo.userId}</td>
// 					<td>${vo.name}</td>
// 					<td><fmt:formatDate value="${vo.birth }" pattern="yyyy-MM-dd" />
// 				</tr>
				});
				
				// 지우는 작업
				$("#userList").html("");
				// 다시 입히는 방법 
				$("#userList").html(html);
				
				
				// 페이지 처리하기
				

				// 페이지 처리
				var paginationHtml="";
				
				// 첫번쨰 페이지
				paginationHtml += "<li><a href='javascript:getUserList(1);' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li>";
				
				// 현재 페이지 수
				for(var i =1; i <= data.pageCnt; i++){
					paginationHtml +="<li><a href='javascript:getUserList("+i+");'>"+i+"</a></li>";
				}
				
				// 마지막 페이지
				paginationHtml += "<li><a href='javascript:getUserList("+data.pageCnt+");'aria-label='Next'> <span aria-hidden='true'>&raquo;</span></a></li>";		
	

				// 지우는 작업
				$("#paginationHtml").html("");
				$("#paginationHtml").html(paginationHtml);
			}
			
		});
	}
	
</script>

<form id="frm" action="/user/userDetail" method="get">
	<input type="hidden" id="userId" name="userId" />
</form>
<div class="col-sm-8 blog-main">
	<h2 class="sub-header">사용자</h2>
	<div class="table-responsive">
		<table class="table table-striped table-hover">
			<thead>
			<tr>
				<th>번호</th>
				<th>사용자 아이디</th>
				<th>사용자 이름</th>
				<th>사용자 생일</th>
			</tr>
			</thead>
			<tbody id="userList">
			<%--             <% List<UserVo> userList = (List)request.getAttribute("pageList"); --%>
			<!-- //                SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일"); -->
			<!-- //                for(int i=0;i<userList.size();i++){  -->
			<%--                   UserVo userVo2 = userList.get(i); %> --%>
			<!--             <tr class="userClick"> -->
			<%--                <td><%=userVo2.getRnum() %></td> --%>
			<%--                <td><%=userVo2.getUserId() %></td> --%>
			<%--                <td><%=userVo2.getName() %></td> --%>
			<%--                <td><%=sdf.format(userVo2.getBirth()) %></td> --%>
			<!--             </tr> -->
			<%--             <%} %> --%>

			<!-- 아래 부분은 아작스로 작업했지만 남겨놔야 하는것은 처음에는 화면에 뿌려져야 하기 때문에 -->
			<!-- userList loop 이용하여 출력  -->
			<!-- 
				<c:forEach items="${userList }" var="vo">
					<tr class="userClick">
						<td>${vo.rnum}</td>
						<td>${vo.userId}</td>
						<td>${vo.name}</td>
						<td><fmt:formatDate value="${vo.birth }" pattern="yyyy-MM-dd" />
					</tr>
				</c:forEach>
		 	-->
			 	
			</tbody>
			
			
		</table>
	</div>

	<a class="btn btn-default pull-right" href="/user/userForm">사용자 등록</a>

	<div class="text-center">
		<ul class="pagination" id="paginationHtml">

			<!-- 							   <li> -->
			<!-- 							      <a href="/userPageList?page=1&pageSize=10" aria-label="Previous"> -->
			<!-- 							        <span aria-hidden="true">&laquo;</span> -->
			<!-- 							      </a> -->
			<!-- 							    </li> -->
			<%-- 						<% int pageCnt = (Integer)request.getAttribute("pageCnt");  --%>
			<!-- //					for(int p = 1; p <= pageCnt; p++){ -->
			<%-- 						%> --%>
			<%-- 							<li><a href="/userPageList?page=<%=p %>&pageSize=10"><%=p %></a></li> --%>

			<%-- 						<%} %> --%>
			<!-- 						    <li> -->
			<%-- 						      <a href="/userPageList?page=<%=pageCnt %>&pageSize=10" aria-label="Next"> --%>
			<!-- 						        <span aria-hidden="true">&raquo;</span> -->
			<!-- 						      </a> -->
			<!-- 						    </li> -->

<!-- 첫번쨰 페이지 가는부분 -->	
<!-- 
	페이징 처리 방법 1: 
			<li><a href="/user/userPageList?page=1&pageSize=10"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
 -->

<!-- 페이징 처리방법 2: -->	
<!--  
			<li><a href="javascript:getUserList(1);"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
-->


<!-- 페이지수가 나오는 부분 -->
<%-- 			<c:set var="pageCnt" value="${pageCnt }"></c:set> --%>

<%-- 			<c:forEach begin="1" end="${pageCnt }" var="page"> --%>
<!-- 				<tr> -->
<!--  방법1			<li><a href="/user/userPageList?page=${page}&pageSize=10">${page}</a></li>-->	
<!-- 방법2 -->
<%-- 					<li><a href="javascript:getUserList(${page});">${page}</a></li> --%>
<!-- 				</tr> -->
<%-- 			</c:forEach> --%>



<!-- 다음페이지 가는 부분 -->
<!-- 방법1 
			<li><a href="/user/userPageList?page=${pageCnt}&pageSize=10"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
-->

<!-- 방법2 -->
<!--  
			<li><a href="javascript:getUserList(${pageCnt});"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
-->

		</ul>

	</div>
</div>
</body>
</html>