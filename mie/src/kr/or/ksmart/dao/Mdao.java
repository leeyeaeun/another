package kr.or.ksmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import kr.or.ksmart.driverdb.DriverDB;
import kr.or.ksmart.dto.Member;

public class Mdao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Member m = null;
	ArrayList<Member> alm = new ArrayList<Member>();

	// 08 아이디를 입력받아 권한과 이름을 리턴하는 메서드
	public Member mGetForSession(String in_id) throws ClassNotFoundException, SQLException {
		System.out.println("03 회원 조회 mSelectforUpdate Mdao.java ");
		DriverDB db = new DriverDB();
		conn = db.driverDbcon();
		pstmt = conn.prepareStatement("select m_name,m_level from tb_member where m_id=?");
		pstmt.setString(1, in_id);

		rs = pstmt.executeQuery();
		if (rs.next()) {
			m = new Member();
			// Member클래스 객체 생성해서 select한 정보를 setting 해준다.
			m.setM_level(rs.getString("m_level"));
			m.setM_name(rs.getString("m_name"));
		}
		pstmt.close();
		conn.close();
		rs.close();
		return m;
	}

	// 07 아이디와 비번 입력받아 db아이디와 비번 확인
	// 리턴 : 01아이디불일치 02로그인성공 03비번불일치
	public String mLoginCheck(String in_id, String in_pw) throws ClassNotFoundException, SQLException {
		System.out.println("07 회원 검색 mLoginCheck Mdao.java ");
		String re = null;
		DriverDB db = new DriverDB();
		conn = db.driverDbcon();
		pstmt = conn.prepareStatement("select m_pw from tb_member where m_id=?");
		pstmt.setString(1, in_id);

		rs = pstmt.executeQuery();
		if (rs.next()) {
			if (in_pw.equals(rs.getString("m_pw"))) {
				re = "02로그인성공";
			} else {
				re = "03비번불일치";
			}
		} else {
			re = "01아이디불일치";
		}

		pstmt.close();
		conn.close();
		rs.close();
		return re;
	}

	// 06 검색처리
	public ArrayList<Member> mSearch(String sf, String sv) throws ClassNotFoundException, SQLException {
		System.out.println("06회원 검색 ArrayList<Member> mSearch Mdao.java ");
		DriverDB db = new DriverDB();
		conn = db.driverDbcon();
		// sf (검색조건) sv (검색어)
		if (sf == null & sv == null) {// 전체회원조회
			System.out.println("01 sk 널 sv 널인 조건");
			pstmt = conn.prepareStatement("select * from tb_member");
		} else if (sf != null & sv.equals("")) {// 전체회원조회
			System.out.println("02 sk 값있고 sv 공백 조건");
			pstmt = conn.prepareStatement("select * from tb_member");
		} else if (sf != null & sv != null) {// 검색조건으로 조회
			System.out.println("03 sk sv 둘다 있는 조건");
			pstmt = conn.prepareStatement("select * from tb_member where " + sf + "=?");
			pstmt.setString(1, sv);
		}
		System.out.println("<br>" + pstmt + "<--- Mdao pstmt");

		rs = pstmt.executeQuery();
		System.out.println(rs + "<-- rs m_list.jsp");

		// rs에서 member에 setting 하는거
		while (rs.next()) {
			m = new Member();// 반복시 새로운 객체 생성됨
			m.setM_id(rs.getString("m_id"));
			m.setM_pw(rs.getString("m_pw"));
			m.setM_level(rs.getString("m_level"));
			m.setM_name(rs.getString("m_name"));
			m.setM_email(rs.getString("m_email"));
			alm.add(m);
			System.out.println(alm + "<<<alm ArrayList<Member> mSearch Mdao.java ");
		}
		pstmt.close();
		conn.close();
		rs.close();
		return alm;
	}

	// 05 전체회원 조회 메서드 선언
	public ArrayList<Member> mAllSelect() throws ClassNotFoundException, SQLException {
		System.out.println("05 전체회원 조회 ArrayList<Member> Mdao.java ");
		DriverDB db = new DriverDB();
		conn = db.driverDbcon();
		pstmt = conn.prepareStatement("select * from tb_member");
		rs = pstmt.executeQuery();
		while (rs.next()) {
			m = new Member();// 반복시 새로운 객체 생성됨
			System.out.println(m + "<<<m");
			m.setM_id(rs.getString("m_id"));
			m.setM_pw(rs.getString("m_pw"));
			m.setM_level(rs.getString("m_level"));
			m.setM_name(rs.getString("m_name"));
			m.setM_email(rs.getString("m_email"));

			alm.add(m);
			System.out.println(alm + "<<<alm ArrayList<Member> Mdao.java ");
		}
		pstmt.close();
		conn.close();
		return alm;

	}

	// 04 한명 회원정보 조회(수정화면 또는 상세보기등)
	public Member mSelectforUpdate(String mid) throws ClassNotFoundException, SQLException {
		System.out.println("03 회원 조회 mSelectforUpdate Mdao.java ");
		DriverDB db = new DriverDB();
		conn = db.driverDbcon();
		pstmt = conn.prepareStatement("select * from tb_member where m_id=?");
		pstmt.setString(1, mid);

		rs = pstmt.executeQuery();
		if (rs.next()) {
			m = new Member();
			// Member클래스 객체 생성해서 select한 정보를 setting 해준다.
			m.setM_id(rs.getString("m_id"));
			m.setM_pw(rs.getString("m_pw"));
			m.setM_level(rs.getString("m_level"));
			m.setM_name(rs.getString("m_name"));
			m.setM_email(rs.getString("m_email"));
		}
		pstmt.close();
		conn.close();
		return m;
	}

	// 03 회원삭제를 한다
	public void mDelete(String send_id) throws ClassNotFoundException, SQLException {
		System.out.println("03 회원 삭제 mDelete Mdao.java ");

		try {
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();

			pstmt = conn.prepareStatement("DELETE FROM tb_member WHERE m_id=?");
			pstmt.setString(1, send_id);
			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
	}

	// 02 회원수정를 한다
	public void mUpdate(Member m) throws ClassNotFoundException, SQLException {
		System.out.println("02회원수정 Member Mdao.java ");
		ResultSet rs = null;

		try {

			DriverDB db = new DriverDB();
			conn = db.driverDbcon();

			pstmt = conn.prepareStatement("UPDATE tb_member SET m_pw=?,m_level=?,m_name=?,m_email=? WHERE m_id=?");
			System.out.println(pstmt + "<-- pstmt 1 Member Mdao.java");
			System.out.println(pstmt.getClass() + "<-- pstmt.getClass() 1");

			pstmt.setString(1, m.getM_pw());
			pstmt.setString(2, m.getM_level());
			pstmt.setString(3, m.getM_name());
			pstmt.setString(4, m.getM_email());
			pstmt.setString(5, m.getM_id());

			System.out.println(pstmt + "<-- pstmt 2 Member Mdao.java ");

			pstmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}

		return;

	}

	// 01_02 회원등록 메서드선언하고 내에서 드라이버로딩과 DB연결한다

	public void mInsert(Member m) throws ClassNotFoundException, SQLException {
		System.out.println("01 입력화면 Mdao.java");

		try {

			DriverDB db = new DriverDB();
			conn = db.driverDbcon();

			pstmt = conn.prepareStatement("INSERT INTO tb_member VALUES (?, ?, ?, ?, ?)");
			System.out.println(pstmt + "<-- pstmt 1");
			System.out.println(pstmt.getClass() + "<-- pstmt.getClass() 1");

			pstmt.setString(1, m.getM_id());
			pstmt.setString(2, m.getM_pw());
			pstmt.setString(3, m.getM_level());
			pstmt.setString(4, m.getM_name());
			pstmt.setString(5, m.getM_email());

			System.out.println(pstmt + "<-- pstmt 2");
			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
	}

	// 01_01 회원등록(드라이버로딩과 DB연결 후 )메서드선언
	public void mInsert(Member m, Connection conn) throws ClassNotFoundException, SQLException {
		System.out.println("01 회원등록 mInsert Mdao.java");

		try {

			pstmt = conn.prepareStatement("INSERT INTO tb_member VALUES (?, ?, ?, ?, ?)");
			System.out.println(pstmt + "<-- pstmt mInsert Mdao.java 1");
			System.out.println(pstmt.getClass() + "<-- pstmt.getClass() 1");

			pstmt.setString(1, m.getM_id());
			pstmt.setString(2, m.getM_pw());
			pstmt.setString(3, m.getM_level());
			pstmt.setString(4, m.getM_name());
			pstmt.setString(5, m.getM_email());
			System.out.println(pstmt + "<-- pstmt mInsert Mdao.java 2");
			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
	}
}
