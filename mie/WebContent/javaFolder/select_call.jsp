<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- geting해서 화면에 뿌려주기 -->
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
아이디
<%=dbid%>
<br>
아이디
<%=dbpw%>
<br>
아이디
<%=dblevel%>
<br>
아이디
<%=dbname%>
<br>
아이디
<%=dbemail%>
<br>