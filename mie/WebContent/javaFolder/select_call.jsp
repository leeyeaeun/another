<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- geting�ؼ� ȭ�鿡 �ѷ��ֱ� -->
<%@ page import="kr.or.ksmart.dao.Mdao"%>
<%@ page import="kr.or.ksmart.dto.Member"%>


<%
	Mdao mdao = new Mdao();
	Member m = mdao.mSelectforUpdate("id003");

	String dbid = m.getM_id();
	String dbpw = m.getM_pw();
	String dblevel = m.getM_level();
	String dbname = m.getM_name();
	String dbemail = m.getM_email();
%>
���̵�
<%=dbid%>
<br>
���̵�
<%=dbpw%>
<br>
���̵�
<%=dblevel%>
<br>
���̵�
<%=dbname%>
<br>
���̵�
<%=dbemail%>
<br>