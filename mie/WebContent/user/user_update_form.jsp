<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="main.css" />
</head>

<body>

 <%@ include file="/module/top.jsp" %>

<%@ include file="/module/left.jsp" %>
<%
	String u_id = request.getParameter("u_id");
	String u_pw = request.getParameter("u_pw");
	String u_name = request.getParameter("u_name");
	String u_phone = request.getParameter("u_phone");
	String u_addr = request.getParameter("u_addr");
	System.out.println(u_id + "<-- u_id");
	System.out.println(u_pw + "<-- u_pw");
	System.out.println(u_name + "<-- u_name");
	System.out.println(u_phone + "<-- u_phone");
	System.out.println(u_addr + "<-- u_addr");
%>			       
		       
		       
		       회원수정 화면 <br><br>
<form action="user_list_update.jsp" method="post">
	아이디 : <input type="text" name="u_id" value="<%= u_id %>" readonly><br>
	비번 : <input type="text" name="u_pw" value="<%= u_pw %>"><br>
	이름 : <input type="text" name="u_name" value="<%= u_name %>"><br>
	전화번호 : <input type="text" name="u_phone" value="<%= u_phone %>"><br>
	주소 : <input type="text" name="u_addr" value="<%= u_addr %>"><br><br>

	<input type="submit" value="수정버튼">
</form>		       
		       
<%@ include file="/module/hadan.jsp" %>