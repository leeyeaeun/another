<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.SQLException" %>
 <%@ page import = "kr.or.ksmart.driverdb.DriverDB" %>
 <%@ page import="kr.or.ksmart.dto.Member"%>
<%
request.setCharacterEncoding("euc-kr");
Connection conn = null;
PreparedStatement pstmt = null;
//화면에서 입력자료 받아서 콘솔창에 출력 시작
String m_id = request.getParameter("m_id");
String m_pw = request.getParameter("m_pw");
String m_level = request.getParameter("m_level");
String m_name = request.getParameter("m_name");
String m_email = request.getParameter("m_email");
System.out.println(m_id + "<-- m_id /mupdate/m_update_pro.jsp");
System.out.println(m_pw + "<-- m_pw /mupdate/m_update_pro.jsp");
System.out.println(m_level + "<-- m_level /mupdate/m_update_pro.jsp");
System.out.println(m_name + "<-- m_name /mupdate/m_update_pro.jsp");
System.out.println(m_email + "<-- m_email /mupdate/m_update_pro.jsp");
Member m = new Member();
m.setM_id(m_id);
m.setM_pw(m_pw);
m.setM_level(m_level);
m.setM_name(m_name);
m.setM_email(m_email);


//01단계 :드라이버 로딩 끝

try{
//02단계 :DB연결(Connection)시작
DriverDB db = new DriverDB();
	conn = db.driverDbcon();
	System.out.println(conn + "<--conn");
	pstmt = conn.prepareStatement(
			"UPDATE tb_member SET m_pw=?,m_level=?,m_name=?,m_email=? WHERE m_id=?");
	System.out.println(pstmt + "<-- pstmt 1");
	System.out.println(pstmt.getClass() + "<-- pstmt.getClass() 1");


	pstmt.setString(1, m.getM_pw());
	pstmt.setString(2, m.getM_level());
	pstmt.setString(3, m.getM_name());
	pstmt.setString(4, m.getM_email());
	pstmt.setString(5, m.getM_id());
	
	System.out.println(pstmt + "<-- pstmt 2");
	
	pstmt.executeUpdate();
	//response.sendRedirect(request.getContextPath()+"/mlist/m_list.jsp");
	response.sendRedirect(request.getContextPath()+"/user/user_list.jsp");

	
}finally{
	if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	if (conn != null) try { conn.close(); } catch(SQLException ex) {}
}
%>