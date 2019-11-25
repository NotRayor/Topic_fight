<%@page import="kpu.club.domain.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/student.css">
<title>회원 정보 수정</title>
</head>
<body>
<%
StudentVO student = (StudentVO)request.getAttribute("student");
%>
<div align="center">
<header>Member Update</header>
<HR>
	<form action="MemberServlet?key=update" method="post">
		<fieldset>
			<legend>개인정보 수정</legend>
			<ul>
				<li>계정 : <input type="text" name="id" value= <%= student.getId() %> readonly></li>
				<li>비밀번호 : <input type="password" name="passwd" value= <%= student.getPw()%> autofocus> </li>
				<li>이름 :<input type="text" name="username" value= <%= student.getUsername()%> > </li>
				<li>학번 : <input type="text" name="snum" value= <%= student.getSnum() %>></li>
				<li>학부 : <input type="text" name="depart" value= <%= student.getDepart() %>></li>
				<li>전화번호 : <input type="text" name="mobile" value= <%= student.getMobile() %>></li>
				<li>이메일 : <input type="text" name="email" value= <%= student.getEmail() %>></li>
			</ul>
		</fieldset>
		<br>
		<fieldset>
			<input type="submit" name="submit" value="최종수정">
			<input type="reset" name="reset" value="다시작성">
		</fieldset>
	</form>
</div>

</body>
</html>