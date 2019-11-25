<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과화면</title>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="./css/bootstrap.min.css">

</head>
<body>
<%
if(request.getAttribute("userID")!= null){
	session.setAttribute("userID",request.getAttribute("userID"));
	session.setMaxInactiveInterval(6000);
	System.out.print("Session 생성 \n");
}else{
	System.out.print("Session 생성 실패 \n");
}

%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<!--  Brand/Logo -->
	<a class="navbar-brand" href="index.jsp">Topic Fight</a>

	<ul class="navbar-nav">
		<li class="nav-item">
			<a class="nav-link" href="#">게시판</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="#">FAQ</a>
		</li>
			
		<!-- Dropdown -->
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
				Menu
			</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="MemberServlet?key=join">회원가입</a>
				<a class="dropdown-item" href="MemberServlet?key=login">로그인</a>
			</div>
		</li>
	</ul>
</nav>

	

<!-- 내용  -->
<div class = "container col-md-2">
	<HR>
	<H2>로그인 결과</H2>
	<HR>
	<p>${message}</p>
</div>


<!-- 애니매이션 담당 JQUERY -->
<script src="./js/jquery.min.js"></script> 

<!-- 추가적 애니메이션 담당 popper -->
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
 
<!-- 부트스트랩 JS  -->
<script src="./js/bootstrap.min.js"></script>

</body>
</html>