<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import  = "kr.or.ksmart.dao.Test"%>
<%@ page import  = "java.util.ArrayList"%>
<%@ page import  = "kr.or.ksmart.dto.Member"%>
<%
Test test = new Test();

ArrayList<Member> get_al = test.bbb();//bbb메서드 호출
out.print(get_al);
Member m1 = get_al.get(0);

System.out.println(m1.getM_id()+"<<<<<m1.getM_id()");
System.out.println(m1.getM_pw()+"<<<<<m1.getM_pw()");
System.out.println(m1.getM_level()+"<<<<<m1.getM_level()");
System.out.println(m1.getM_name()+"<<<<<m1.getM_name()");
System.out.println(m1.getM_email()+"<<<<<m1.getM_email()");

Member m2 = get_al.get(1);
System.out.println(m2.getM_id()+"<<<<<m2.getM_id()");
System.out.println(m2.getM_pw()+"<<<<<m2.getM_pw()");
System.out.println(m2.getM_level()+"<<<<<2.getM_level()");
System.out.println(m2.getM_name()+"<<<<<m2.getM_name()");
System.out.println(m2.getM_email()+"<<<<<m2.getM_email()");
%>