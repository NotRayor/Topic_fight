<%@page import="kpu.club.domain.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과화면</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel= "stylesheet" href="css/memdent.css">

</head>
<body>
<header>Member List</header>
<p id=sect><%=request.getAttribute("message") %>	
<div>
	<%
		MemberVO mem = (MemberVO)request.getAttribute("member");
	%>
	<a href="welcome.html">메인 페이지로 이동하기</a>
	<h2>입력 내용</h2>
	<table>
		<tr>
			<td>계정</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>닉네임</td>
		</tr>
		<tr>
			<td><%=mem.getId()%></td>
			<td><%=mem.getPassword()%></td>
			<td><%=mem.getUsername()%></td>
			<td><%=mem.getEmail()%></td>
			<td><%=mem.getNickname()%></td>
		</tr>
	</table>
</div>
	 <!-- 부트스트랩 JS  -->
	 <script src="js/bootstrap.js"></script>
	 <!-- 애니매이션 담당 JQUERY -->
	 <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script> 

</body>
</html>