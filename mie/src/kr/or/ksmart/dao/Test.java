package kr.or.ksmart.dao;

import java.util.ArrayList;

import kr.or.ksmart.dto.Member;

public class Test {
	public ArrayList<Member> bbb(){
		System.out.println("01 bbb test.java");
		
		ArrayList<Member> al = new ArrayList<Member>();//arr��ü ����
		System.out.println(al);
//1�� member���� - 0 �� index�� add		
		Member m1 = new Member();// m1��ü ����
		System.out.println(m1);
		m1.setM_id("id001");//seting
		m1.setM_pw("pw001");
		m1.setM_level("������");
		m1.setM_name("name001");
		m1.setM_email("email001");
		al.add(m1);
//2�� member���� - 1 �� index�� add		
		Member m2 = new Member();
		System.out.println(m2);
		m2.setM_id("id002");
		m2.setM_pw("pw002");
		m2.setM_level("�Ǹ���");
		m2.setM_name("name002");
		m2.setM_email("email002");
		al.add(m2);
		
		return al;
	}
}
