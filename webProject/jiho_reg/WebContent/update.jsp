
<%@page import="java.util.List"%>
<%@page import="kpu.club.domain.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<a class="navbar-brand" href="index.jsp">Topic Fight</a>

	<ul class="navbar-nav">
		<li class="nav-item">
			<a class="nav-link" href="bbs.jsp">게시판</a>
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
				<a class="dropdown-item" id="login" href="MemberServlet?key=login">로그인</a>
				<a class="dropdown-item" id="logout" href="#">로그아웃</a>
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
</form>


</section>
 

 <!-- 애니매이션 담당 JQUERY -->
 <script src="./js/jquery.min.js"></script> 
 
 <!-- 추가적 애니메이션 담당 popper -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script> 
  
 <!-- 부트스트랩 JS  -->
 <script src="./js/bootstrap.min.js"></script>


<script>

//Modal창을 띄운다.
$(document).ready(function() {
	$("#registerBtn").click(function() {
		$("#registerModal").modal();
	});
});

</script>
<%
if(session.getAttribute("userID") != null){
	%>
	<script>
	$(document).ready(function(){
		$('#login').hide();
		$('#logout').click(function(){
	});
		<%
			session.invalidate();
		%>
		history.go(0);
		});
	</script>
	<%
}
else{
	%>
	<script>
	$(doucment).ready(function(){
		$('#logout').hide();
	});
	</script>
	<%
}
%>

</body>
</html>