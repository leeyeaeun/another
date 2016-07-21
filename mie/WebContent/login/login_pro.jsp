<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.SQLException" %>
<%@ page import = "kr.or.ksmart.driverdb.DriverDB" %>
<%
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;



String id = request.getParameter("id");
String pw = request.getParameter("pw");
System.out.println(id + "<-- id login_pro.jsp");
System.out.println(pw + "<-- pw login_pro.jsp");
// 가입된 회원정보 가정 Start
String dbid = null;
String dbpw = null;
String dbname = null;
String dblevel = null; 
// 가입된 회원정보 가정 End


try{
	DriverDB db = new DriverDB();
	conn = db.driverDbcon();
	System.out.println(conn + "<--conn");
	String sql = "select m_pw,m_level,m_name from tb_member where m_id=?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, id);
	
	rs = pstmt.executeQuery();
	if(rs.next()){
		System.out.println("01아이디일치");
		
		dbpw = rs.getString("m_pw");
		dbname = rs.getString("m_name");
		dblevel = rs.getString("m_level"); 
		
		if(pw.equals(dbpw)){
			System.out.println("03로그인성공");
			//session영역에 이름과 권한을 셋팅
			session.setAttribute("S_NAME", dbname);
			session.setAttribute("S_LEVEL", dblevel);
			//session영역에 이름과 권한을 셋팅
			//index.jsp로 이동
//response.sendRedirect(request.getContextPath()+"/user/index.jsp");
%>
<script type="text/javascript">
	alert("로그인성공");
	location.href = "<%= request.getContextPath() %>/user/index.jsp";
</script>
<%		
		}else{
			System.out.println("04비번불일치");
%>
<script type="text/javascript">
	alert("비번불일치");
	location.href = "<%= request.getContextPath() %>/user/index.jsp";
</script>
<%			
		}
	}else{
		System.out.println("02아이디불일치");
%>
<script type="text/javascript">
	alert("아이디 불일치");
	location.href = "<%= request.getContextPath() %>/user/index.jsp";
</script>
<%		
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







