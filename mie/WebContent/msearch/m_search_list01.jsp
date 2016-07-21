<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.sql.*" %>
<%@ page import = "kr.or.ksmart.driverdb.DriverDB" %>
<%@ include file ="/msearch/m_search_form.jsp" %>
회원 리스트 <br>
<table width="100%" border="1">
<tr>
	<td>아이디</td><td>권한</td><td>이름</td><td>이메일</td>
</tr>
<%
	request.setCharacterEncoding("euc-kr");
	String sk = request.getParameter("sk");
	String sv = request.getParameter("sv");
	System.out.println(sk + "<-- sk /minsert/m_search_list.jsp");
	System.out.println(sv + "<-- sv /minsert/m_search_list.jsp");
	
	Connection conn = null;
	PreparedStatement pstmt = null;	
	ResultSet rs = null;
	

		try{
	
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + "<--conn");
			
			if(sk == null & sv == null){
				out.println("01 sk 널 sv 널인조건");
				pstmt = conn.prepareStatement("select * from tb_member");
			}else if(sk != null & sv.equals("")){
				out.println("02 sk 값있고 sv 공백조건");
				pstmt = conn.prepareStatement("select * from tb_member");
			}else if (sk != null & sv != null){
				out.println("03 sk sv 둘다 있는 조건");
				pstmt = conn.prepareStatement("select * from tb_member where "+sk+" = ?");
				pstmt.setString(1,sv);
			}
			out.println(pstmt);
			rs = pstmt.executeQuery();
		
			while(rs.next()){
%>
		<tr>
			<td><%= rs.getString("m_id")%></td>
			<td><%= rs.getString("m_level")%></td>
			<td><%= rs.getString("m_name")%></td>
			<td><%= rs.getString("m_email")%></td>
		</tr>	
<%			
			
		}
		
		} catch(SQLException ex) {
			out.println(ex.getMessage());
			ex.printStackTrace();
		} finally {
			//사용한 Statement 종료
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			
			// 커넥션 종료
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	
%>
</table>