package kr.or.ksmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import kr.or.ksmart.driverdb.DriverDB;
import kr.or.ksmart.dto.Member;


public class Mdao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	//04�˻�
	
	
	//03 ȸ�������� �Ѵ�
	public void mDelete(String send_id) throws ClassNotFoundException, SQLException{
		System.out.println("03 ȸ�� ���� mDelete Mdao.java ");
		
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
		
			pstmt = conn.prepareStatement(
					"DELETE FROM tb_member WHERE m_id=?");
			pstmt.setString(1, send_id);
			pstmt.executeUpdate();
			
			
		
		}finally{
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	
	//02 ȸ�������� �Ѵ�	
	public  void mUpdate(Member m) throws ClassNotFoundException, SQLException{
		System.out.println("02ȸ������ Member Mdao.java ");
		ResultSet rs =null;
		
		try{	
			
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			
			
			pstmt = conn.prepareStatement(
					"UPDATE tb_member SET m_pw=?,m_level=?,m_name=?,m_email=? WHERE m_id=?");
			System.out.println(pstmt + "<-- pstmt 1 Member Mdao.java");
			System.out.println(pstmt.getClass() + "<-- pstmt.getClass() 1");


			pstmt.setString(1, m.getM_pw());
			pstmt.setString(2, m.getM_level());
			pstmt.setString(3, m.getM_name());
			pstmt.setString(4, m.getM_email());
			pstmt.setString(5, m.getM_id());
			
			System.out.println(pstmt + "<-- pstmt 2 Member Mdao.java ");
			
			pstmt.executeUpdate();	
			
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
				
		return;
		
	}
		
	//01_02 ȸ����� �޼��弱���ϰ� ������ ����̹��ε��� DB�����Ѵ�
	
	public void mInsert(Member m) throws ClassNotFoundException, SQLException{
		System.out.println("01 �Է�ȭ�� Mdao.java");
			
	try{	
		
		DriverDB db = new DriverDB();
		conn = db.driverDbcon();
		
		pstmt = conn.prepareStatement(
				"INSERT INTO tb_member VALUES (?, ?, ?, ?, ?)");
		System.out.println(pstmt + "<-- pstmt 1");
		System.out.println(pstmt.getClass() + "<-- pstmt.getClass() 1");

		pstmt.setString(1, m.getM_id());
		pstmt.setString(2, m.getM_pw());
		pstmt.setString(3, m.getM_level());
		pstmt.setString(4, m.getM_name());
		pstmt.setString(5, m.getM_email());
		
		System.out.println(pstmt + "<-- pstmt 2");
		pstmt.executeUpdate();
		
		
	}finally{
		if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	}
	} 
	
	
//01_01 ȸ�����(����̹��ε��� DB���� �� )�޼��弱��
		public void mInsert(Member m, Connection conn ) throws ClassNotFoundException, SQLException{
			System.out.println("01 ȸ����� mInsert Mdao.java");
					
		try{	
			
			
			pstmt = conn.prepareStatement(
					"INSERT INTO tb_member VALUES (?, ?, ?, ?, ?)");
			System.out.println(pstmt + "<-- pstmt mInsert Mdao.java 1");
			System.out.println(pstmt.getClass() + "<-- pstmt.getClass() 1");

			pstmt.setString(1, m.getM_id());
			pstmt.setString(2, m.getM_pw());
			pstmt.setString(3, m.getM_level());
			pstmt.setString(4, m.getM_name());
			pstmt.setString(5, m.getM_email());			
			System.out.println(pstmt + "<-- pstmt mInsert Mdao.java 2");
			pstmt.executeUpdate();
			
			
		}finally{			
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}		
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		} 
}





