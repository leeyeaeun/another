<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="kr.or.ksmart.dao.Mdao" %>
<% request.setCharacterEncoding("euc-kr"); %>

<!--//01_02 ȸ����� �޼��弱���ϰ� ������ ����̹��ε��� DB�����Ѵ� -->


<jsp:useBean id="m" class="kr.or.ksmart.dto.Member"/>
<jsp:setProperty name="m" property="*"/>

<%

	Mdao mdao = new Mdao ();
	mdao.mInsert(m);

	response.sendRedirect(request.getContextPath()+"/user/user_list.jsp");

%>