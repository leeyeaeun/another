<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="kr.or.ksmart.dao.Mdao"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="kr.or.ksmart.dto.Member"%>
<%-- 
<%@ include file="/minsert/m_insert_form.jsp" %>
 --%>
ȸ�� ����Ʈ
<br>
<table width="100%" border="1">
	<tr>
		<td>�̸�</td>
		<td>���̵�</td>
		<td>����</td>
		<td>�̸�</td>
		<td>�̸���</td>
		<td>����</td>
		<td>����</td>
	</tr>
	<%
		Member member = new Member();//Member Ŭ���� ~��ü����
		Mdao dao = new Mdao();//jdbc�ڵ� �ִ� MdaoŬ���� ~��ü����
		ArrayList<Member> get_alm = dao.mAllSelect();
		//Mdao��ü���� �޼��� ȣ�� �ϸ� ArrayList��ü���� ������ ȸ������ ����ִ�
		//m��ü�ּҰ� get_alm ������ copy

		for (int i = 0; i < get_alm.size(); i++) {//get_alm���̸�ŭ �ݺ�
			Member m = get_alm.get(i);
		//�ε����� 0~���̸�ŭ ������ ȸ������ ��ü�ּҰ� m ������ copy��
	%>
	<tr>
		<td><%=m.getM_id()%></td>  <!-- getting�� ��� -->
		<td><%=m.getM_pw()%></td>
		<td><%=m.getM_level()%></td>
		<td><%=m.getM_name()%></td>
		<td><%=m.getM_email()%></td>
		<td><a
			href="<%=request.getContextPath()%>/mupdate/m_update_form.jsp?send_id=<%=m.getM_id()%>">����Ŭ��</a>
		</td>
		<td><a
			href="<%=request.getContextPath()%>/mdelete/m_delete_pro.jsp?send_id=<%=m.getM_id()%>">����Ŭ��</a>
		</td>
	</tr>
	<%
		}
	%>
</table>