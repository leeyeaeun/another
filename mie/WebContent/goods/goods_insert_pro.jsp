<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.SQLException" %>
<%@ page import = "java.sql.ResultSet" %>
 <%@ page import = "kr.or.ksmart.driverdb.DriverDB" %>

<%
	request.setCharacterEncoding("euc-kr");
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt_select = null;
	ResultSet rs = null;
//화면에서 입력자료 받아서 콘솔창에 출력 시작
	//String g_code = request.getParameter("g_code");
	String g_name = request.getParameter("g_name");
	String g_cate = request.getParameter("g_cate");
	String g_price = request.getParameter("g_price");
	String g_desc = request.getParameter("g_desc");
	//System.out.println(g_code + "<-- g_code /goods/goods_insert_pro.jsp");
	System.out.println(g_name + "<-- g_name /goods/goods_insert_pro.jsp");
	System.out.println(g_cate + "<-- g_cate /goods/goods_insert_pro.jsp");
	System.out.println(g_price + "<-- g_price /goods/goods_insert_pro.jsp");
	System.out.println(g_desc + "<-- g_desc /goods/goods_insert_pro.jsp");
//화면에서 입력자료 받아서 콘솔창에 출력 끝	
	//JDBC 프로그램 순서
	//01단계 :드라이버 로딩 시작
	
	//01단계 :드라이버 로딩 끝
	
	String g_code = "gcode_1";
	
	try{
//02단계 :DB연결(Connection)시작
	DriverDB db = new DriverDB();
	conn = db.driverDbcon();
	System.out.println(conn + "<--conn");

		
		pstmt_select = conn.prepareStatement(
				"select MAX(substring(g_code,7)) from tb_goods");
			
		
		rs = pstmt_select.executeQuery();
		
		String temp = "gcode_";
		
		int result = 0;
		//System.out.println(rs.next() + "<-- rs.next()");
		if(rs.next()){
			result = rs.getInt(1);
			System.out.println(result + "<-- result goods_insert_pro.jsp");
			result = result + 1;
		}
	
		g_code = temp + result;
		
		System.out.println(g_code + "<-- g_code goods_insert_pro.jsp");

		pstmt = conn.prepareStatement(
				"insert into tb_goods values(?,?,?,?,?)");

		pstmt.setString(1, g_code);
		pstmt.setString(2, g_name);
		pstmt.setString(3, g_cate);
		pstmt.setString(4, g_price);
		pstmt.setString(5, g_desc);
//03단계 :Query실행을 위한 statement 또는 prepareStatement객체생성 끝
//04단계 :Query실행 시작
		pstmt.executeUpdate();

		//response.sendRedirect(request.getContextPath()+"/mlist/m_list.jsp");
%>		
	<script>
		alert('입력성공');
		location.href = "<%= request.getContextPath() %>/goods/goods_list.jsp";
	</script>	
<%		
		
		

//04단계 :Query실행 끝
//05단계 :Query실행결과 사용 (insert의 경우 생략 가능단계)

	}finally{
		//06단계 :statement 또는 prepareStatement객체 종료(close())
		if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		if (pstmt_select != null) try { pstmt_select.close(); } catch(SQLException ex) {}

		//07단계 :Connection 객체 종료(close())
		if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	}

%>