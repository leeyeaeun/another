<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	System.out.println(id + "<-- id login_pro.jsp");
	System.out.println(pw + "<-- pw login_pro.jsp");
// 가입된 회원정보 가정 Start
	String dbid = "id001";
	String dbpw = "pw001";
	String dbname = "홍길동";
	String dblevel = "판매자";
// 가입된 회원정보 가정 End
	if(id.equals(dbid)){
		System.out.println("01아이디일치");
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
%>







