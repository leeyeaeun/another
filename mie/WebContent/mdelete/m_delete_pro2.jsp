<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.SQLException" %>
<%@ page import = "kr.or.ksmart.driverdb.DriverDB" %>
<%
String send_id = request.getParameter("send_id");
System.out.println(send_id + "<-- send_id m_delete_pro.jsp");
request.setCharacterEncoding("euc-kr");
Connection conn = null;
PreparedStatement pstmt = null;

try{
	DriverDB db = new DriverDB();
	conn = db.driverDbcon();
	System.out.println(conn + "<--conn");
	
	
	pstmt = conn.prepareStatement(
			"DELETE FROM tb_member WHERE m_id=?");
	pstmt.setString(1, send_id);
	pstmt.executeUpdate();
	
	//response.sendRedirect(request.getContextPath()+"/mlist/m_list.jsp");
	response.sendRedirect(request.getContextPath()+"/user/user_list.jsp");

}finally{
	if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	if (conn != null) try { conn.close(); } catch(SQLException ex) {}
}



%>