<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/msearch/m_search_form.jsp" %>
<%@ page import = " kr.or.ksmart.dao.Mdao" %>
<%@ page import = "kr.or.ksmart.dto.Member" %>
<%@ page import="java.util.ArrayList"%>
ȸ�� ����Ʈ <br>
<table width="100%" border="1">
<tr>
	<td>�̸�</td><td>���̵�</td><td>����</td><td>�̸�</td><td>�̸���</td><td>����</td><td>����</td>
</tr>
<%
request.setCharacterEncoding("euc-kr");

String sk = request.getParameter("sk");//�˻�����
String sv = request.getParameter("sv");//�˻���
System.out.println(sk + "<-- sk m_search_list.jsp");
System.out.println(sv + "<-- sv m_search_list.jsp");

Mdao mdao = new Mdao();
ArrayList<Member> get_alm = mdao.mSearch(sk, sv);
//mSearch�޼��� ȣ�� �� return���� get_alm�� �־���

for(int i = 0; i < get_alm.size(); i++){
	Member m = get_alm.get(i);
%>
	<tr>
		<td><%= m.getM_id()%></td>
		<td><%= m.getM_pw()%></td>
		<td><%= m.getM_level()%></td>
		<td><%= m.getM_name()%></td>
		<td><%= m.getM_email()%></td>
		<td>
<a href="<%= request.getContextPath() %>/mupdate/m_update_form.jsp?send_id=<%=m.getM_id()%>">����Ŭ��</a>			
		</td>
		<td>
<a href="<%= request.getContextPath() %>/mdelete/m_delete_pro.jsp?send_id=<%=m.getM_id()%>">����Ŭ��</a>			
		</td>
	</tr>
<%		
}
%>
</table>