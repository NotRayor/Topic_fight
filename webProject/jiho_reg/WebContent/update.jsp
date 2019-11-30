
<%@page import="java.util.List"%>
<%@page import="kpu.club.domain.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="./css/bootstrap.min.css">

<title>회원 정보 수정</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<!--  Brand/Logo -->
	<a class="navbar-brand" href="BoardServlet?key=index">Topic Fight</a>

	<ul class="navbar-nav">
		<li class="nav-item">
			<a class="nav-link" href="BoardServlet?key=board">게시판</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="BoardServlet?key=FAQ">FAQ</a>
		</li>
		<c:if test="${userID eq 'admin' }">
			<li  class="nav-item" id="manage" >
				<a class="nav-link" href="MemberServlet?key=management">관리자</a>
			</li>
		</c:if>
		
		<!-- Dropdown -->
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
				Menu
			</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="MemberServlet?key=join">회원가입</a>
				<c:choose>
					<c:when test="${userID eq null }">
						<a class="dropdown-item" id="login" href="MemberServlet?key=login">로그인</a>
					</c:when>
					<c:otherwise>
						<a class="dropdown-item" id="logout" href="MemberServlet?key=logout">로그아웃</a>
					</c:otherwise>
				</c:choose>
			</div>
		</li>
	</ul>
</nav>


<%
MemberVO member = (MemberVO)request.getAttribute("member");
%>


<!-- 내용  -->
<div class = "container-fluid">
	<button type="button" class="btn btn-primary" id="registerBtn">등록하기</button>
</div>

<section class="container-fluid col-sm-8">
<HR>
<H2>개인정보 수정</H2>
<HR>
<form action="MemberServlet?key=update" method="POST">
	<div class="form-group">
		<label>아이디 : </label>
		<input type="text" class="form-control" name="id" value=${member.id } readonly>
	</div>
	<div class="form-group">
		<label>비밀번호 : </label>
		<input type="text" class="form-control" name="passwd" value=${member.password }>
	</div>
	<div class="form-group">
		<label>이름 : </label>
		<input type="text" class="form-control" name="username" value=${member.username }>
	</div>
	<div class="form-group">
		<label>이메일 : </label>
		<input type="email" class="form-control" name="email" value=${member.email }>
	</div>
	<div class="form-group">
		<label>닉네임 : </label>
		<input type="text" class="form-control" name="nickname" value=${member.nickname }>
	</div>
	<button type="submit" class="btn btn-primary">Submit</button>
	<c:if test="${member.id != 'admin' }">
		<a class="btn btn-danger active" id="deleteCheck" >Delete</a>
	</c:if>
</form>
<!-- href="MemberServlet?key=delete&id=${member.id } -->

</section>
 
<!-- The Modal -->
<div class="modal fade" id="deleteCheckModal" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">

			<!--  Modal Header -->
			<div class="modal-header text-center">
				<h5 class="modal-title" id="modal">회원삭제</h5>
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">x</span>
				</button>
			</div>

			<!--  Modal body -->
			<div class="modal-body">
				<form action="./registerAction.jsp" method="post">
					<div class="form-row">
						<div class="form-group">
							<label>${member.id } 님의 계정을 삭제합니까?</label>
						</div>
					</div>
				</form>
			</div>

			<!--  Modal footer -->
			<div class="modal-footer text-center sm-6" >
				<a  class="btn btn-danger" href="MemberServlet?key=delete&id=${member.id }" >Yes</a>				
				<button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
			</div>
		</div>
	</div>
</div>

 <!-- 애니매이션 담당 JQUERY -->
 <script src="./js/jquery.min.js"></script> 
 
 <!-- 추가적 애니메이션 담당 popper -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script> 
  
 <!-- 부트스트랩 JS  -->
 <script src="./js/bootstrap.min.js"></script>


<script>

//Modal창을 띄운다.
$(document).ready(function() {
	$("#deleteCheck").click(function() {
		$("#deleteCheckModal").modal();
	});
});

</script>


</body>
</html>