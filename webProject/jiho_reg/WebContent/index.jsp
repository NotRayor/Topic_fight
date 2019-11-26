<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="./css/bootstrap.min.css">

<title>index</title>

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
<div class = "container-fluid">
	<button type="button" class="btn btn-primary" id="registerBtn">등록하기</button>
</div>

<!-- The Modal -->
<div class="modal fade" id="registerModal" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
		
			<!--  Modal Header -->
			<div class="modal-header">
				<h5 class="modal-title" id="modal">등록하기</h5>
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			
			<!--  Modal body -->
			<div class="modal-body">
				<form action="./registerAction.jsp" method="post">
					<div class="form-row">
						<div class="form-group col-sm-5">
							<label>주제1</label>
							<input type="text" name="topic1" class="form-control">
						</div>
						<div class="col-sm-2">
							VS
						</div>
						<div class="form-group col-sm-5">
							<label>주제2</label>
							<input type="text" name="topic2" class="form-control">
						</div>
					</div>
				</form>
			</div>
			
			<!--  Modal footer -->
			<div class="modal-footer">
				<button type="button" class="btn btn-primary">register</button>				
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<section class="container-fluid col-sm-4">
<HR>
<H2>토픽 TOP3</H2>
<HR>
<table class="table">
  <thead>
    <tr>
      <th scope="col">순위</th>
      <th scope="col">주제1</th>
      <th scope="col">주제2</th>
      <th scope="col">작성자</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>Larry</td>
      <td>the Bird</td>
      <td>@twitter</td>
    </tr>
  </tbody>
</table>
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
		$('#login').hide();
		$('#logout').click(function(){
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
		$('#logout').hide();
	</script>
	<%
}
%>


</body>
</html>