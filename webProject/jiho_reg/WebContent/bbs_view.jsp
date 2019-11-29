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

<title>글쓰기</title>

</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<!--  Brand/Logo -->
	<a class="navbar-brand" href="index.jsp">Topic Fight</a>

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

<!-- 내용 -->
<section class="container-fluid col-sm-8">
	<HR>
	<H2 class="text-center">${bbs.bbs_topic1 } vs ${bbs.bbs_topic2 }</H2>
	<HR>
	<div class="container col-sm5">
		<div class="row">
			<p>${bbs.bbs_content }</p><BR>
		</div>
	
		<div class="row">
			<c:choose>
				<c:when test="${bbs.bbs_name eq userID}">
					<a class="btn btn-primary m-1" href="BoardServlet?key=read&id=${bbs.bbs_id }">수정</a>
					<a class="btn btn-warning m-1" href="BoardServlet?key=delete&id=${bbs.bbs_id }">삭제</a>	
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-danger m-1" id="reportBtn">신고</button>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

</section>
 

 <!-- 애니매이션 담당 JQUERY -->
 <script src="./js/jquery.min.js"></script> 
 
 <!-- 추가적 애니메이션 담당 popper -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script> 
  
 <!-- 부트스트랩 JS  -->
 <script src="./js/bootstrap.min.js"></script>


<script>


</script>

</body>
</html>