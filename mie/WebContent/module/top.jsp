<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String S_NAME = (String)session.getAttribute("S_NAME");
	String S_LEVEL = (String)session.getAttribute("S_LEVEL");
	System.out.println(S_NAME + "<-- S_NAME");
	System.out.println(S_LEVEL + "<-- S_LEVEL");
%>

   <!-- Begin Wrapper -->
   <div id="wrapper">
   
         <!-- Begin Header -->
         <div id="header">
		 
		       상단메뉴 <br><br>		 




<%
if(S_LEVEL == null){
%>
<a href="<%= request.getContextPath() %>/goods/goods_list.jsp"> 04상품리스트 </a>
<br><br>
<form action="<%= request.getContextPath() %>/login/login_pro.jsp" method="post">
	아이디 : <input type="text" name="id">
	비번 : <input type="password" name="pw">

	<input type="submit" value="로그인">
</form>

<%}else{ %>
<%	if(S_LEVEL.equals("구매자")){ 		%>
<a href="<%= request.getContextPath() %>/user/user_insert_form.jsp"> 01회원등록 </a>
<a href="<%= request.getContextPath() %>/goods/goods_list.jsp"> 04상품리스트 </a>
<%	}else if(S_LEVEL.equals("판매자")){ %>
<a href="<%= request.getContextPath() %>/user/user_insert_form.jsp"> 01회원등록 </a>
<a href="<%= request.getContextPath() %>/goods/goods_insert_form.jsp"> 03상품등록 </a>
<a href="<%= request.getContextPath() %>/goods/goods_list.jsp"> 04상품리스트 </a>	
<%	}else if(S_LEVEL.equals("관리자")){ %>
<a href="<%= request.getContextPath() %>/user/user_insert_form.jsp"> 01회원등록 </a>
<a href="<%= request.getContextPath() %>/user/user_list.jsp"> 02회원리스트 </a>
<a href="<%= request.getContextPath() %>/goods/goods_insert_form.jsp"> 03상품등록 </a>
<a href="<%= request.getContextPath() %>/goods/goods_list.jsp"> 04상품리스트 </a>
<%	} %>	
<br><br>
<%= S_NAME %> 님 <%= S_LEVEL %> 권한 로그인 
<a href="<%= request.getContextPath() %>/login/logout.jsp">로그아웃</a>
<%} %>






































		 </div>
		 <!-- End Header -->
		 
