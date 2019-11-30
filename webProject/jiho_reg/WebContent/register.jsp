<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입창</title>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="./css/bootstrap.min.css">


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


<section class="container-fluid col-sm-8">
	<HR>
	<H2>회원가입 </H2>
	<HR>
	<form action="MemberServlet?key=joinRequest" method="POST">
		<div class="form-group">
			<input type="text" class="form-control" name="id" placeholder="아이디">
		</div>
		<div class="form-group">
			<input type="text" class="form-control" name="passwd"  placeholder="비밀번호">
		</div>
		<div class="form-group">
			<input type="text" class="form-control" name="username" placeholder="이름">
		</div>
		<div class="form-group">
			<input type="email" class="form-control" name="email" placeholder="이메일">
		</div>
		<div class="form-group">
			<input type="text" class="form-control" name="nickname" placeholder="닉네임">
		</div>
		<button type="submit" class="btn btn-primary">회원가입</button>
	</form>
</section>
 
	
 <!-- 애니매이션 담당 JQUERY -->
 <script src="./js/jquery.min.js"></script> 
 
 <!-- 추가적 애니메이션 담당 popper -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script> 
  
 <!-- 부트스트랩 JS  -->
 <script src="./js/bootstrap.min.js"></script>

</html>