<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.SQLException" %>
 <%@ page import = "kr.or.ksmart.driverdb.DriverDB" %>

<% request.setCharacterEncoding("euc-kr"); %>


<jsp:useBean id="m" class="kr.or.ksmart.dto.Member"/>
<jsp:setProperty name="m" property="*"/>

<%
		
	
	Connection conn = null;
	PreparedStatement pstmt = null;
//화면에서 입력자료 받아서 콘솔창에 출력 시작
	
	
	try{
//02단계 :DB연결(Connection)시작
	DriverDB db = new DriverDB();
	conn = db.driverDbcon();
	System.out.println(conn + "<--conn");
		
//02단계 :DB연결(Connection)끝
//03단계 :Query실행을 위한 statement 또는 prepareStatement객체생성 시작
		pstmt = conn.prepareStatement(
				"INSERT INTO tb_member VALUES (?, ?, ?, ?, ?)");
		System.out.println(pstmt + "<-- pstmt 1");
		System.out.println(pstmt.getClass() + "<-- pstmt.getClass() 1");
//insert into tb_member values('id001','pw001','관리자','홍01','email01');
		pstmt.setString(1, m.getM_id());
		pstmt.setString(2, m.getM_pw());
		pstmt.setString(3, m.getM_level());
		pstmt.setString(4, m.getM_name());
		pstmt.setString(5, m.getM_email());
		
		System.out.println(pstmt + "<-- pstmt 2");
		
//03단계 :Query실행을 위한 statement 또는 prepareStatement객체생성 끝
//04단계 :Query실행 시작
		pstmt.executeUpdate();
//response.sendRedirect(request.getContextPath()+"/mlist/m_list.jsp");
response.sendRedirect(request.getContextPath()+"/user/user_list.jsp");

//04단계 :Query실행 끝
//05단계 :Query실행결과 사용 (insert의 경우 생략 가능단계)

	}finally{
		//06단계 :statement 또는 prepareStatement객체 종료(close())
		if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		//07단계 :Connection 객체 종료(close())
		if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	}

%>