<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.SQLException" %>
 <%@ page import = "kr.or.ksmart.driverdb.DriverDB" %>
 <%@ page import="kr.or.ksmart.dao.Mdao" %>
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


<%
String send_id = request.getParameter("send_id");
System.out.println(send_id + "<-- send_id m_update_form.jsp");

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

String dbid = null;
String dbpw = null;
String dblevel = null;
String dbname = null;
String dbemail = null;

try{

	DriverDB db = new DriverDB();
	conn = db.driverDbcon();
	System.out.println(conn + "<--conn");
	
	
	pstmt = conn.prepareStatement("select * from tb_member where m_id=?");
	pstmt.setString(1, send_id);
	
	rs = pstmt.executeQuery();
	if(rs.next()){
		dbid = rs.getString("m_id");
		dbpw = rs.getString("m_pw");
		dblevel = rs.getString("m_level");
		dbname = rs.getString("m_name");
		dbemail = rs.getString("m_email");
		//콘솔창에 출력한다
	}
	
	
} catch(SQLException ex) {
	out.println(ex.getMessage());
	ex.printStackTrace();
} finally {
	// 6. 사용한 Statement 종료
	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	
	// 7. 커넥션 종료
	if (conn != null) try { conn.close(); } catch(SQLException ex) {}
}
%>
<form action="<%= request.getContextPath() %>/mupdate/m_update_pro.jsp" method="post">
<table border="1">
<tr>
	<td>아이디</td>
	<td>
<input type="hidden" name="m_id" size="20" value="<%= dbid %>">	
		<%= dbid %>
		
<%-- 
<input type="text" name="m_id" size="20" value="<%= dbid %>" readonly> 
--%>
	
	</td>
<tr> 
<tr>
	<td>암호</td>
	<td><input type="text" name="m_pw" size="20" value="<%= dbpw %>"></td>
<tr>
<tr>
	<td>권한</td>
	<td><input type="text" name="m_level" size="20" value="<%= dblevel %>"></td>
<tr>
<tr>
	<td>이름</td>
	<td><input type="text" name="m_name" size="20" value="<%= dbname %>"></td>
<tr>
<tr>
	<td>이메일</td>
	<td><input type="text" name="m_email" size="20" value="<%= dbemail %>"></td>
<tr>
<tr>
	<td colspan="4"><input type="submit" value="회원수정버튼"></td>
</tr>
</table>
</form>



<%@ include file="/module/hadan.jsp" %>

</body>
</html>






