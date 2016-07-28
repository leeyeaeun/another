<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="kr.or.ksmart.dao.Mdao"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="kr.or.ksmart.dto.Member"%>
<%-- 
<%@ include file="/minsert/m_insert_form.jsp" %>
 --%>
회원 리스트
<br>
<table width="100%" border="1">
	<tr>
		<td>이름</td>
		<td>아이디</td>
		<td>권한</td>
		<td>이름</td>
		<td>이메일</td>
		<td>수정</td>
		<td>삭제</td>
	</tr>
	<%
		Member member = new Member();//Member 클래스 ~객체생성
		Mdao dao = new Mdao();//jdbc코드 있는 Mdao클래스 ~객체생성
		ArrayList<Member> get_alm = dao.mAllSelect();
		//Mdao객체내부 메서드 호출 하면 ArrayList객체내부 각각의 회원정보 담고있는
		//m객체주소가 get_alm 변수에 copy

		for (int i = 0; i < get_alm.size(); i++) {//get_alm길이만큼 반복
			Member m = get_alm.get(i);
		//인덱스의 0~길이만큼 각각의 회원정보 객체주소가 m 변수에 copy됨
	%>
	<tr>
		<td><%=m.getM_id()%></td>  <!-- getting후 출력 -->
		<td><%=m.getM_pw()%></td>
		<td><%=m.getM_level()%></td>
		<td><%=m.getM_name()%></td>
		<td><%=m.getM_email()%></td>
		<td><a
			href="<%=request.getContextPath()%>/mupdate/m_update_form.jsp?send_id=<%=m.getM_id()%>">수정클릭</a>
		</td>
		<td><a
			href="<%=request.getContextPath()%>/mdelete/m_delete_pro.jsp?send_id=<%=m.getM_id()%>">삭제클릭</a>
		</td>
	</tr>
	<%
		}
	%>
</table>