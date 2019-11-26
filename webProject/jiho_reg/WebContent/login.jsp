<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="./css/bootstrap.min.css">

<title>회원가입창</title>
</head>
<body>

<% 
String userID = null;

// session이 등록됬다면
if(session.getAttribute("userID") != null){
	userID = (String)session.getAttribute("userID");
	System.out.println("Session 이미 존재");
}
else{
	// 세션 없음
	System.out.println("Session 존재 안함.");
}

// 이미 로그인
if(userID != null){
	PrintWriter script = response.getWriter();
	
	script.println("<script>");
	script.println("alert('로그인이 된 상태입니다.');");
	script.println("location.href = 'index.jsp'");
	script.println("</script>");
	script.close();	
}

%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<!--  Brand/Logo -->
	<a class="navbar-brand" href="index.jsp">Topic Fight</a>

	<ul class="navbar-nav">
		<li class="nav-item">
			<a class="nav-link" href="bbs.jsp">게시판</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="#">FAQ</a>
		</li>
		<li  class="nav-item" id="manage" >
			<a class="nav-link" href="MemberServlet?key=management">관리자</a>
		</li>
		<!-- Dropdown -->
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
				Menu
			</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="MemberServlet?key=join">회원가입</a>
				<a class="dropdown-item" id="login" href="MemberServlet?key=login">로그인</a>
				<a class="dropdown-item" id="logout" href="#">로그아웃</a>
			</div>
		</li>
	</ul>
</nav>

<!-- 내용  -->
<div class = "container col-md-2">
	<HR>
	<H2>로그인</H2>
	<HR>
	<form action="MemberServlet" method="POST" class="was-validated">
		<input type="hidden" name="key" value="loginCheck"/> 
	<div class="form-group">
		<label for="id">아이디</label>
		<input type="text" class="form-control" id="id" name="id" required>
	</div>
	<div class="form-group">
		<label for="password">비밀번호</label>
		<input type="password" class="form-control" id="password" name="password" required>
	</div>
	<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</div>


	
	
<!-- 애니매이션 담당 JQUERY -->
<script src="./js/jquery.min.js"></script> 

<!-- 추가적 애니메이션 담당 popper -->
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
 
<!-- 부트스트랩 JS  -->
<script src="./js/bootstrap.min.js"></script>
 
</body>
</html>