<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.SQLException" %>
<%@ page import = "kr.or.ksmart.driverdb.DriverDB" %>

<%@ include file="/msearch/m_search_form.jsp" %>
회원 리스트 <br>
<table width="100%" border="1">
<tr>
	<td>이름</td><td>아이디</td><td>권한</td><td>이름</td><td>이메일</td><td>수정</td><td>삭제</td>
</tr>

<%
request.setCharacterEncoding("euc-kr");
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
//JDBC 프로그램 순서
//01단계 :드라이버 로딩 시작

String sk = request.getParameter("sk");
String sv = request.getParameter("sv");
System.out.println(sk + "<-- sk m_search_list.jsp");
System.out.println(sv + "<-- sv m_search_list.jsp");


try{
	DriverDB db = new DriverDB();
	conn = db.driverDbcon();
	System.out.println(conn + "<--conn");
	if(sk == null & sv == null){
		out.println("01 sk 널 sv 널인 조건");
		pstmt = conn.prepareStatement("select * from tb_member");
		//select * from tb_member; 
	}else if(sk != null & sv.equals("")){
		out.println("02 sk 값있고 sv 공백 조건");
		pstmt = conn.prepareStatement("select * from tb_member");
		//select * from tb_member; 
	}else if(sk != null & sv != null){
		out.println("03 sk sv 둘다 있는 조건");
		if(sk.equals("m_id")){
			pstmt = conn.prepareStatement("select * from tb_member where m_id=?");
		}else if(sk.equals("m_level")){
			pstmt = conn.prepareStatement("select * from tb_member where m_level=?");
		}else if(sk.equals("m_name")){
			pstmt = conn.prepareStatement("select * from tb_member where m_name=?");
		}else if(sk.equals("m_email")){
			pstmt = conn.prepareStatement("select * from tb_member where m_email=?");
		}
		pstmt.setString(1, sv);
		//select * from tb_member where m_id='id001'; 
		//select * from tb_member where m_level='관리자'; 
		//select * from tb_member where m_name='홍02'; 
		//select * from tb_member where m_email='email03';  
	}
	out.println("<br>"+pstmt + "<--- pstmt");
	rs = pstmt.executeQuery();
	System.out.println(rs + "<-- rs m_list.jsp");
	while(rs.next()){
%>
	<tr>
		<td><%= rs.getString("m_id")%></td>
		<td><%= rs.getString("m_pw")%></td>
		<td><%= rs.getString("m_level")%></td>
		<td><%= rs.getString("m_name")%></td>
		<td><%= rs.getString("m_email")%></td>
		<td>
<a href="<%= request.getContextPath() %>/mupdate/m_update_form.jsp?send_id=<%= rs.getString("m_id")%>">수정클릭</a>			
		</td>
		<td>
<a href="<%= request.getContextPath() %>/mdelete/m_delete_pro.jsp?send_id=<%= rs.getString("m_id")%>">삭제클릭</a>			
		</td>
	</tr>
<%		
	}	
	
	
}catch(SQLException ex) {
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