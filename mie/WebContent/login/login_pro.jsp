<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "kr.or.ksmart.dao.Mdao" %>
<%@ page import = "kr.or.ksmart.dto.Member" %>
<%
String id = request.getParameter("id");
String pw = request.getParameter("pw");
System.out.println(id + "<-- id login_pro.jsp");
System.out.println(pw + "<-- pw login_pro.jsp");

Mdao mdao = new Mdao();
String result = mdao.mLoginCheck(id, pw);
System.out.println("결과 : "+result);

if(result.equals("02로그인성공")){
	//이름과 권한을 가져와 session영역에 세팅
	Member m = mdao.mGetForSession(id);
	session.setAttribute("S_NAME", m.getM_name());
	session.setAttribute("S_LEVEL",m.getM_level());
%>
	<script type="text/javascript">
		alert("로그인성공");
	</script>
<%
}else if(result.equals("01아이디불일치")){
%>
	<script type="text/javascript">
		alert("아이디 불일치");
	</script>
<%		
}else if(result.equals("03비번불일치")){
%>
	<script type="text/javascript">
		alert("비번불일치");		
	</script>
<%		
}
%>
<script type="text/javascript">
location.href = "<%= request.getContextPath() %>/user/index.jsp";	
</script>
