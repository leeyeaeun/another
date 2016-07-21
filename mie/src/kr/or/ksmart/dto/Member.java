package kr.or.ksmart.dto;

public class Member {
//전역변수 = 멤버 = 필드 = 멤버필드 = 프로퍼티
	private String m_id = null;
	private String  m_pw = null;
	private String  m_level = null;
	private String  m_name = null;
	private String  m_email = null;
	
	
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		System.out.println(m_id + "<--m_id setM_id Member.java");
		this.m_id = m_id;
	}
	public String getM_pw() {
		return m_pw;
	}
	public void setM_pw(String m_pw) {
		System.out.println(m_pw + "<--m_pw setM_pw Member.java");
		this.m_pw = m_pw;
	}
	public String getM_level() {
		return m_level;
	}
	public void setM_level(String m_level) {
		System.out.println(m_level + "<--m_level setM_level Member.java");
		this.m_level = m_level;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		System.out.println(m_name + "<--m_name setM_name Member.java");
		this.m_name = m_name;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		System.out.println(m_email + "<--m_email setM_email Member.java");
		this.m_email = m_email;
	}
	
}
