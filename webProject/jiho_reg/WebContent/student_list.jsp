<%@page import="kpu.club.domain.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="kpu.club.domain.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>리스트</title>
<link rel="stylesheet" href="css/student.css">

</head>
<body>
<header>2019 KPU Study Club</header>
<HR/>
<div>
	<a href="welcome.html">메인페이지 이동</a>
	<table>
		<tr>
			<td>계정</td><td>비밀번호</td><td>이름</td><td>학번</td><td>학부</td><td>전화번호</td><td>이메일</td>
		</tr>
		<%
		List<MemberVO> list = (List<MemberVO>)request.getAttribute("memberList");
		for(MemberVO stu : list){
		%>
		<tr>
			<td><a href="MemberServlet?key=update&id=<%=stu.getId()%>" target= "self"><%=stu.getId()%></a></td>
			<td><%=stu.getPassword()%></td>
			<td><%=stu.getUsername()%></td>
			<td><%=stu.getEmail()%></td>
			<td><%=stu.getNickname()%></td>
		</tr>
		<%
		}
		%>
	</table>
</div>
</body>
</html>