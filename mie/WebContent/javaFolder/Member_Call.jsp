<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="kr.or.ksmart.dto.Member"%>
<%
	Member m = new Member();
	m.setM_id("id001");
	m.setM_pw("pw001");
	m.setM_level("������");
	m.setM_name("ȫ�浿");
	m.setM_email("email01");
	
/* 	String m_id = m.getM_id();
	out.println(m_id + " : m_id");
	
	String m_pw = m.getM_pw();
	out.println(m_pw + " : m_pw");
	
	String m_level = m.getM_level();
	out.println(m_level + " : m_level");
	
	String m_name = m.getM_name();
	out.println(m_name + " : m_name");
	
	String m_email = m.getM_email();
	out.println(m_email + " : m_email"); */
	
%>
���̵�: <%=m.getM_id() %> <br>
��й�ȣ: <%=m.getM_pw() %> <br>
����: <%=m.getM_level() %> <br>
�̸�: <%=m.getM_name() %> <br>
�̸���: <%=m.getM_email() %> <br>

