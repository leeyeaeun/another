<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
											<!-- /layout19/css/main.css -->
</head>
<body>
<%@ include file="/module/top.jsp" %>
<%@ include file="/module/left.jsp" %>
		 <!-- Begin Right Column -->
		 <div id="rightcolumn">

<form action="<%= request.getContextPath() %>/goods/goods_insert_pro.jsp" method="post">
<table border="1">
<!-- <tr>
	<td>상품코드</td>
	<td><input type="text" name="g_code" size="20"></td>
<tr> -->
<tr>
	<td>상품명</td>
	<td><input type="text" name="g_name" size="20"></td>
<tr>
<tr>
	<td>카테고리</td>
	<td><input type="text" name="g_cate" size="20"></td>
<tr>
<tr>
	<td>가격</td>
	<td><input type="text" name="g_price" size="20"></td>
<tr>
<tr>
	<td>상세설명</td>
	<td><input type="text" name="g_desc" size="20"></td>
<tr>
<tr>
	<td colspan="4"><input type="submit" value="상품등록버튼"></td>
</tr>
</table>
</form>

		 </div>
		 <!-- End Right Column -->
<%@ include file="/module/hadan.jsp" %>
	
	
		 

</body>
</html>