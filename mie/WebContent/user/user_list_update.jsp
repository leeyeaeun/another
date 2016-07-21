<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="main.css" />

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
}
</style>


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
<table style="width:100%">
  <tr>
    <th>아이디</th>
    <th>비번</th>
    <th>이름</th>
    <th>전화번호</th>
    <th>주소</th>
    <th>수정버튼</th>
    <th>삭제버튼</th>
  </tr>
  <tr>
    <td><%= u_id %></td>
    <td><%= u_pw %></td>
    <td><%= u_name %></td>
    <td><%= u_phone %></td>
    <td><%= u_addr %></td>
    <td>
    	<a href="user_update_form.jsp?u_id=<%= u_id %>&u_pw=<%= u_pw %>&u_name=<%= u_name %>&u_phone=<%= u_phone %>&u_addr=<%= u_addr %>">수정</a>
    </td>
	<td>
    	<a href="user_list_delete.jsp">삭제</a>
    </td>
  </tr>

</table>
	          		 
		 
<%@ include file="/module/hadan.jsp" %>