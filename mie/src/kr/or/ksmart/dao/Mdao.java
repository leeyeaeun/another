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

	// 08 ���̵� �Է¹޾� ���Ѱ� �̸��� �����ϴ� �޼���
	public Member mGetForSession(String in_id) throws ClassNotFoundException, SQLException {
		System.out.println("03 ȸ�� ��ȸ mSelectforUpdate Mdao.java ");
		DriverDB db = new DriverDB();
		conn = db.driverDbcon();
		pstmt = conn.prepareStatement("select m_name,m_level from tb_member where m_id=?");
		pstmt.setString(1, in_id);

		rs = pstmt.executeQuery();
		if (rs.next()) {
			m = new Member();
			// MemberŬ���� ��ü �����ؼ� select�� ������ setting ���ش�.
			m.setM_level(rs.getString("m_level"));
			m.setM_name(rs.getString("m_name"));
		}
		pstmt.close();
		conn.close();
		rs.close();
		return m;
	}

	// 07 ���̵�� ��� �Է¹޾� db���̵�� ��� Ȯ��
	// ���� : 01���̵����ġ 02�α��μ��� 03�������ġ
	public String mLoginCheck(String in_id, String in_pw) throws ClassNotFoundException, SQLException {
		System.out.println("07 ȸ�� �˻� mLoginCheck Mdao.java ");
		String re = null;
		DriverDB db = new DriverDB();
		conn = db.driverDbcon();
		pstmt = conn.prepareStatement("select m_pw from tb_member where m_id=?");
		pstmt.setString(1, in_id);

		rs = pstmt.executeQuery();
		if (rs.next()) {
			if (in_pw.equals(rs.getString("m_pw"))) {
				re = "02�α��μ���";
			} else {
				re = "03�������ġ";
			}
		} else {
			re = "01���̵����ġ";
		}

		pstmt.close();
		conn.close();
		rs.close();
		return re;
	}

	// 06 �˻�ó��
	public ArrayList<Member> mSearch(String sf, String sv) throws ClassNotFoundException, SQLException {
		System.out.println("06ȸ�� �˻� ArrayList<Member> mSearch Mdao.java ");
		DriverDB db = new DriverDB();
		conn = db.driverDbcon();
		// sf (�˻�����) sv (�˻���)
		if (sf == null & sv == null) {// ��üȸ����ȸ
			System.out.println("01 sk �� sv ���� ����");
			pstmt = conn.prepareStatement("select * from tb_member");
		} else if (sf != null & sv.equals("")) {// ��üȸ����ȸ
			System.out.println("02 sk ���ְ� sv ���� ����");
			pstmt = conn.prepareStatement("select * from tb_member");
		} else if (sf != null & sv != null) {// �˻��������� ��ȸ
			System.out.println("03 sk sv �Ѵ� �ִ� ����");
			pstmt = conn.prepareStatement("select * from tb_member where " + sf + "=?");
			pstmt.setString(1, sv);
		}
		System.out.println("<br>" + pstmt + "<--- Mdao pstmt");

		rs = pstmt.executeQuery();
		System.out.println(rs + "<-- rs m_list.jsp");

		// rs���� member�� setting �ϴ°�
		while (rs.next()) {
			m = new Member();// �ݺ��� ���ο� ��ü ������
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

	// 05 ��üȸ�� ��ȸ �޼��� ����
	public ArrayList<Member> mAllSelect() throws ClassNotFoundException, SQLException {
		System.out.println("05 ��üȸ�� ��ȸ ArrayList<Member> Mdao.java ");
		DriverDB db = new DriverDB();
		conn = db.driverDbcon();
		pstmt = conn.prepareStatement("select * from tb_member");
		rs = pstmt.executeQuery();
		while (rs.next()) {
			m = new Member();// �ݺ��� ���ο� ��ü ������
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

	// 04 �Ѹ� ȸ������ ��ȸ(����ȭ�� �Ǵ� �󼼺����)
	public Member mSelectforUpdate(String mid) throws ClassNotFoundException, SQLException {
		System.out.println("03 ȸ�� ��ȸ mSelectforUpdate Mdao.java ");
		DriverDB db = new DriverDB();
		conn = db.driverDbcon();
		pstmt = conn.prepareStatement("select * from tb_member where m_id=?");
		pstmt.setString(1, mid);

		rs = pstmt.executeQuery();
		if (rs.next()) {
			m = new Member();
			// MemberŬ���� ��ü �����ؼ� select�� ������ setting ���ش�.
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

	// 03 ȸ�������� �Ѵ�
	public void mDelete(String send_id) throws ClassNotFoundException, SQLException {
		System.out.println("03 ȸ�� ���� mDelete Mdao.java ");

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

	// 02 ȸ�������� �Ѵ�
	public void mUpdate(Member m) throws ClassNotFoundException, SQLException {
		System.out.println("02ȸ������ Member Mdao.java ");
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

	// 01_02 ȸ����� �޼��弱���ϰ� ������ ����̹��ε��� DB�����Ѵ�

	public void mInsert(Member m) throws ClassNotFoundException, SQLException {
		System.out.println("01 �Է�ȭ�� Mdao.java");

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

	// 01_01 ȸ�����(����̹��ε��� DB���� �� )�޼��弱��
	public void mInsert(Member m, Connection conn) throws ClassNotFoundException, SQLException {
		System.out.println("01 ȸ����� mInsert Mdao.java");

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
