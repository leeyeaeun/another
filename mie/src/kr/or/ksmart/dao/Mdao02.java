package kr.or.ksmart.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.ksmart.driverdb.DriverDB;
import kr.or.ksmart.dto.Member;

public class Mdao02 {
	//07_02 �α��μ��� �� �̸��� ������ �����ϴ� �޼��� ����
	public Member mGetForSession(String in_id) throws ClassNotFoundException{
		System.out.println("07_02 �α��μ��� �� �̸��� ������ �����ϴ� �޼��� ���� Mdao.java");

		Member m = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//�Ѹ��� ȸ������ DB���� ������ ����ȭ�鿡 �ѷ��ֱ����� ���� ����

		String dblevel = null;
		String dbname = null;

		try{
			DriverDB d = new DriverDB();
			conn = d.driverDbcon();
			System.out.println(conn + "<-- conn");
			
			pstmt = conn.prepareStatement("select m_name,m_level from tb_member where m_id=?");
			pstmt.setString(1, in_id);
		//3�ܰ� : �������� ���� �غ�	�Ϸ�
			rs = pstmt.executeQuery();	//04�ܰ� : ���� ����
		//System.out.println(rs.next() + "<-- rs.next() m_update_form.jsp");
			if(rs.next()){
				dblevel = rs.getString("m_level");
				dbname = rs.getString("m_name");
				m = new Member();
				m.setM_level(dblevel);
				m.setM_name(dbname);
			}			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} finally {
			// 6. ����� Statement ����
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			
			// 7. Ŀ�ؼ� ����
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		
		
		return m;		
	}
	//07_01 �α��� Ȯ���ϴ� �޼��� ����
	public String mLoginCheck(String in_id,String in_pw) throws ClassNotFoundException{
		System.out.println("07_01 �α��� Ȯ���ϴ� �޼��� ���� Mdao.java");
		String re = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			DriverDB d = new DriverDB();
			conn = d.driverDbcon();
			System.out.println(conn + "<-- conn");
			pstmt = conn.prepareStatement("select m_pw from tb_member where m_id=?");
			pstmt.setString(1, in_id);
		//3�ܰ� : �������� ���� �غ�	�Ϸ�
			rs = pstmt.executeQuery();	//04�ܰ� : ���� ����	
			if(rs.next()){	//01���̵� ��ġ
				re = "01���̵���ġ";
				if(in_pw.equals(rs.getString("m_pw"))){
					re = "03�α��μ���";
				}else{
					re = "04�������ġ";
				}
			}else{
				re = "02���̵����ġ";
			}
			
			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} finally {
			// 6. ����� Statement ����
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			
			// 7. Ŀ�ؼ� ����
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		
		return re;
	}
	//06 �˻� �޼��� ����
	public ArrayList<Member> mSearch(String sk,String sv) throws ClassNotFoundException{
		System.out.println("06 �˻� �޼��� ���� Mdao.java");
		
		ArrayList<Member> alm = new ArrayList<Member>();
		
		Member m = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//�Ѹ��� ȸ������ DB���� ������ ����ȭ�鿡 �ѷ��ֱ����� ���� ����
		String dbid = null;
		String dbpw = null;
		String dblevel = null;
		String dbname = null;
		String dbemail = null;
		try{
			DriverDB d = new DriverDB();
			conn = d.driverDbcon();
			System.out.println(conn + "<-- conn");
			
			if(sk == null && sv ==null){
				System.out.println("01 �Ѵ� ����");
				pstmt = conn.prepareStatement("select * from tb_member");
			}else if(sk != null && sv.equals("")){
				System.out.println("02 �ϳ��� �ִ� ");
				pstmt = conn.prepareStatement("select * from tb_member");
			}else if(sk != null && sv != null){
				System.out.println("03 �Ѵ� �ִ� ");
				String query = "select * from tb_member where "+sk+"=?";
				System.out.println(query + "<-- query m_search_list.jsp");
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, sv);
			}
			rs = pstmt.executeQuery();	//04�ܰ� : ���� ����
		//System.out.println(rs.next() + "<-- rs.next() m_update_form.jsp");
			while(rs.next()){
				dbid = rs.getString("m_id");
				dbpw = rs.getString("m_pw");
				dblevel = rs.getString("m_level");
				dbname = rs.getString("m_name");
				dbemail = rs.getString("m_email");
				m = new Member();
				m.setM_id(dbid);
				m.setM_pw(dbpw);
				m.setM_level(dblevel);
				m.setM_name(dbname);
				m.setM_email(dbemail);
				alm.add(m);
			}			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} finally {
			// 6. ����� Statement ����
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			
			// 7. Ŀ�ؼ� ����
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}		
		
		
		return alm;
		
	}
	//05 ��üȸ�� ��ȸ �޼��� ����
	public ArrayList<Member> mAllSelect() throws ClassNotFoundException{
		System.out.println("05 ��üȸ�� ��ȸ �޼��� ���� Mdao.java");
		ArrayList<Member> alm = new ArrayList<Member>();
		
		Member m = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//�Ѹ��� ȸ������ DB���� ������ ����ȭ�鿡 �ѷ��ֱ����� ���� ����
		String dbid = null;
		String dbpw = null;
		String dblevel = null;
		String dbname = null;
		String dbemail = null;
		try{
			DriverDB d = new DriverDB();
			conn = d.driverDbcon();
			System.out.println(conn + "<-- conn");
			
			pstmt = conn.prepareStatement("select * from tb_member");
		//3�ܰ� : �������� ���� �غ�	�Ϸ�
			rs = pstmt.executeQuery();	//04�ܰ� : ���� ����
		//System.out.println(rs.next() + "<-- rs.next() m_update_form.jsp");
			while(rs.next()){
				dbid = rs.getString("m_id");
				dbpw = rs.getString("m_pw");
				dblevel = rs.getString("m_level");
				dbname = rs.getString("m_name");
				dbemail = rs.getString("m_email");
				m = new Member();
				m.setM_id(dbid);
				m.setM_pw(dbpw);
				m.setM_level(dblevel);
				m.setM_name(dbname);
				m.setM_email(dbemail);
				alm.add(m);
			}			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} finally {
			// 6. ����� Statement ����
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			
			// 7. Ŀ�ؼ� ����
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}		
		
		
		return alm;
	}
	
	
	
	
	
	//04 ����ó�� �޼��� ����
	public void mDelete(String mid) throws ClassNotFoundException, SQLException{
		System.out.println("04 ����ó�� �޼��� ���� Mdao.java");
		PreparedStatement pstmt = null;
		Connection conn = null;
		try{
			DriverDB d = new DriverDB();
			conn = d.driverDbcon();
			System.out.println(conn + "<-- conn");
			
			pstmt = conn.prepareStatement("DELETE FROM tb_member WHERE m_id=?");
			pstmt.setString(1, mid);
			pstmt.executeUpdate();
			
		}finally{
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	//03 ����ó�� �޼��� ����
	public void mUpdate(Member m) throws ClassNotFoundException, SQLException{
		System.out.println("03 ����ó�� �޼��� ���� Mdao.java");
		PreparedStatement pstmt = null;
		Connection conn = null;
		try{
			
			DriverDB d = new DriverDB();
			conn = d.driverDbcon();
			System.out.println(conn + "<-- conn");
			
			pstmt = conn.prepareStatement(
					"UPDATE tb_member SET m_pw=?,m_level=?,m_name=?,m_email=? WHERE m_id = ?");
			System.out.println(pstmt + "<-- pstmt 1");
			
			pstmt.setString(1, m.getM_pw());
			pstmt.setString(2, m.getM_level());
			pstmt.setString(3, m.getM_name());
			pstmt.setString(4, m.getM_email());
			pstmt.setString(5, m.getM_id());
			
			System.out.println(pstmt + "<-- pstmt 2");
			pstmt.executeUpdate();			
		}finally{
			//06�ܰ� :statement �Ǵ� prepareStatement��ü ����(close())
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			//07�ܰ� :Connection ��ü ����(close())
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}		
	}
	
	//02 �Ѹ��� ȸ������ ��ȸ �� ����ȭ�� �޼��� ����
	public Member mSelectforUpdate(String mid) throws ClassNotFoundException{
		System.out.println("02 �Ѹ��� ȸ������ ��ȸ �� ����ȭ�� �޼��� ���� Mdao.java");
		Member m = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//�Ѹ��� ȸ������ DB���� ������ ����ȭ�鿡 �ѷ��ֱ����� ���� ����
		String dbid = null;
		String dbpw = null;
		String dblevel = null;
		String dbname = null;
		String dbemail = null;
		try{
			DriverDB d = new DriverDB();
			conn = d.driverDbcon();
			System.out.println(conn + "<-- conn");
			
			pstmt = conn.prepareStatement("select * from tb_member where m_id=?");
			pstmt.setString(1, mid);
		//3�ܰ� : �������� ���� �غ�	�Ϸ�
			rs = pstmt.executeQuery();	//04�ܰ� : ���� ����
		//System.out.println(rs.next() + "<-- rs.next() m_update_form.jsp");
			if(rs.next()){
				dbid = rs.getString("m_id");
				dbpw = rs.getString("m_pw");
				dblevel = rs.getString("m_level");
				dbname = rs.getString("m_name");
				dbemail = rs.getString("m_email");
				m = new Member();
				m.setM_id(dbid);
				m.setM_pw(dbpw);
				m.setM_level(dblevel);
				m.setM_name(dbname);
				m.setM_email(dbemail);
			}			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} finally {
			// 6. ����� Statement ����
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			
			// 7. Ŀ�ؼ� ����
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		
		
		return m;
	}
	
	
	
	
	
	
	
	
	
	
	//01_02 �Է�ó�� �޼��� ����(�Ű����� 1��)
	public void mInsert(Member m) throws SQLException, ClassNotFoundException{
		System.out.println("01_02 �Է�ó�� �޼��� ����(�Ű����� 1��) Mdao.java");
		PreparedStatement pstmt = null;
		Connection conn = null;
		try{
			
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + "<-- conn");
			
			pstmt = conn.prepareStatement(
					"INSERT INTO tb_member VALUES (?, ?, ?, ?, ?)");
			System.out.println(pstmt + "<-- pstmt 1");
			pstmt.setString(1, m.getM_id());
			pstmt.setString(2, m.getM_pw());
			pstmt.setString(3, m.getM_level());
			pstmt.setString(4, m.getM_name());
			pstmt.setString(5, m.getM_email());
			
			System.out.println(pstmt + "<-- pstmt 2");
			pstmt.executeUpdate();			
		}finally{
			//06�ܰ� :statement �Ǵ� prepareStatement��ü ����(close())
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			//07�ܰ� :Connection ��ü ����(close())
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}	
	//01_02 �Է�ó�� �޼��� ����(�Ű����� 1��) End
	
	//01_01 �Է�ó�� �޼��� ����(�Ű����� 2��)
	public void mInsert(Member m,Connection conn) throws SQLException{
		System.out.println("01_01 �Է�ó�� �޼��� ����(�Ű����� 2��) Mdao.java");
		PreparedStatement pstmt = null;

		try{
			pstmt = conn.prepareStatement(
					"INSERT INTO tb_member VALUES (?, ?, ?, ?, ?)");
			System.out.println(pstmt + "<-- pstmt 1");
			pstmt.setString(1, m.getM_id());
			pstmt.setString(2, m.getM_pw());
			pstmt.setString(3, m.getM_level());
			pstmt.setString(4, m.getM_name());
			pstmt.setString(5, m.getM_email());
			
			System.out.println(pstmt + "<-- pstmt 2");
			pstmt.executeUpdate();			
		}finally{
			//06�ܰ� :statement �Ǵ� prepareStatement��ü ����(close())
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			//07�ܰ� :Connection ��ü ����(close())
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	//01_01 �Է�ó�� �޼��� End
	
	
}
