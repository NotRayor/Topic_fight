<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!--  Meterial icon  -->
 <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!--  Meterial icon 사용을 위한 css -->
<link rel="stylesheet" href="./css/mycss.css">

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="./css/bootstrap.min.css">


<title>게시판 읽기</title>

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

<!-- 내용 -->
<section class="container-fluid col-sm-8">
	<HR>
	<H2 class="text-center">${bbs.bbsTopic1 } vs ${bbs.bbsTopic2 }</H2>
	<HR>
	<div class="float-right">
		<c:choose>
			<c:when test="${userID != bbs.bbsPostId }">
				<a class="text-primary" href="BoardServlet?key=recommand&userID=${userID }&bbs_id=${bbs.bbsId }"
					data-toggle="tooltip" data-placement="top" title="이미 추천한 경우에 누르면, 추천을 취소 합니다.">
					<i class="material-icons">thumb_up</i>
					${bbs.bbsRecommend }
				</a>
			</c:when>
			<c:otherwise>
				<a class="text-primary">
					<i class="material-icons">thumb_up</i>
					${bbs.bbsRecommend }
				</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="container col-sm5">
		<div class="row">
			<p>${bbs.bbsContent }</p><BR>
		</div>
	
		<div class="row">
			<c:choose>
				<c:when test="${bbs.bbsPostId eq userID}">
					<a class="btn btn-primary m-1" href="BoardServlet?key=read&id=${bbs.bbsId }">수정</a>
					<a class="btn btn-warning m-1" href="BoardServlet?key=delete&id=${bbs.bbsId }">삭제</a>	
				</c:when>
				<c:when test="${userID eq 'admin'}">
					<a class="btn btn-primary m-1" href="BoardServlet?key=read&id=${bbs.bbsId }">수정</a>
					<a class="btn btn-warning m-1" href="BoardServlet?key=delete&id=${bbs.bbsId }">삭제</a>	
					<a class="btn btn-danger m-1" id="reportBtn" href="BoardServlet?key=report&userID=${userID }&bbs_id=${bbs.bbsId }">신고</a>
				</c:when>
				<c:otherwise>
					<a class="btn btn-danger m-1" id="reportBtn" href="BoardServlet?key=report&userID=${userID }&bbs_id=${bbs.bbsId }">신고</a>
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

<%
if(request.getAttribute("result") != null){
	
	int result = (Integer)request.getAttribute("result");
	
	if (result == 1) { // 1인경우 디비에서 해당 게시물 신고 완료
	
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('신고가 완료되었습니다.');");
		script.println("</script>");
		script.close();
	
	
	} else { // 이미 PK, NN으로 설정되어 중복되면 1 반환이 되지 않음
	
		PrintWriter script = response.getWriter();
	
		script.println("<script>");
		script.println("alert('이미 신고한 글입니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
	}
}

%>

</body>
</html>