<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>네비게이션</h1>
<ol>
	<li><a href="<%=request.getContextPath()%>/mlist/m_list.jsp">리스트</a></li>
	
	<li><a href="<%=request.getContextPath()%>/minsert/m_insert_form.jsp">입력</a></li>
	
	<li><a href="<%=request.getContextPath()%>/mupdate/m_update_pro.jsp">수정</a></li>
	
	<li><a href="<%=request.getContextPath()%>/msearch/m_search_list.jsp">조회</a></li>
	
	
	


</ol>
</body>
</html>