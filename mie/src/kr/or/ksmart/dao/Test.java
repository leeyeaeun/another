package kr.or.ksmart.dao;

import java.util.ArrayList;

import kr.or.ksmart.dto.Member;

public class Test {
	public ArrayList<Member> bbb(){
		System.out.println("01 bbb test.java");
		
		ArrayList<Member> al = new ArrayList<Member>();//arr按眉 积己
		System.out.println(al);
//1锅 member积己 - 0 锅 index俊 add		
		Member m1 = new Member();// m1按眉 积己
		System.out.println(m1);
		m1.setM_id("id001");//seting
		m1.setM_pw("pw001");
		m1.setM_level("包府磊");
		m1.setM_name("name001");
		m1.setM_email("email001");
		al.add(m1);
//2锅 member积己 - 1 锅 index俊 add		
		Member m2 = new Member();
		System.out.println(m2);
		m2.setM_id("id002");
		m2.setM_pw("pw002");
		m2.setM_level("魄府磊");
		m2.setM_name("name002");
		m2.setM_email("email002");
		al.add(m2);
		
		return al;
	}
}
