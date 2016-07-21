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


</table>
	          		 
		 
<%@ include file="/module/hadan.jsp" %>