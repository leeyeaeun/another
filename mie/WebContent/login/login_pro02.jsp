<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	System.out.println(id + "<-- id login_pro.jsp");
	System.out.println(pw + "<-- pw login_pro.jsp");
// ���Ե� ȸ������ ���� Start
	String dbid = "id001";
	String dbpw = "pw001";
	String dbname = "ȫ�浿";
	String dblevel = "�Ǹ���";
// ���Ե� ȸ������ ���� End
	if(id.equals(dbid)){
		System.out.println("01���̵���ġ");
		if(pw.equals(dbpw)){
			System.out.println("03�α��μ���");
			//session������ �̸��� ������ ����
			session.setAttribute("S_NAME", dbname);
			session.setAttribute("S_LEVEL", dblevel);
			//session������ �̸��� ������ ����
			//index.jsp�� �̵�
//response.sendRedirect(request.getContextPath()+"/user/index.jsp");
%>
<script type="text/javascript">
	alert("�α��μ���");
	location.href = "<%= request.getContextPath() %>/user/index.jsp";
</script>
<%		
		}else{
			System.out.println("04�������ġ");
%>
<script type="text/javascript">
	alert("�������ġ");
	location.href = "<%= request.getContextPath() %>/user/index.jsp";
</script>
<%			
		}
	}else{
		System.out.println("02���̵����ġ");
%>
<script type="text/javascript">
	alert("���̵� ����ġ");
	location.href = "<%= request.getContextPath() %>/user/index.jsp";
</script>
<%		
	}
%>







