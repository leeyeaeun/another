package kr.or.ksmart.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.ksmart.driverdb.DriverDB;
import kr.or.ksmart.dto.Member;

public class Mdao02 {
	//07_02 로그인성공 후 이름과 권한을 리턴하는 메서드 선언
	public Member mGetForSession(String in_id) throws ClassNotFoundException{
		System.out.println("07_02 로그인성공 후 이름과 권한을 리턴하는 메서드 선언 Mdao.java");

		Member m = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//한명의 회원정보 DB에서 가져와 수정화면에 뿌려주기위한 변수 선언

		String dblevel = null;
		String dbname = null;

		try{
			DriverDB d = new DriverDB();
			conn = d.driverDbcon();
			System.out.println(conn + "<-- conn");
			
			pstmt = conn.prepareStatement("select m_name,m_level from tb_member where m_id=?");
			pstmt.setString(1, in_id);
		//3단계 : 쿼리실행 위한 준비	완료
			rs = pstmt.executeQuery();	//04단계 : 쿼리 실행
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
			// 6. 사용한 Statement 종료
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			
			// 7. 커넥션 종료
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		
		
		return m;		
	}
	//07_01 로그인 확인하는 메서드 선언
	public String mLoginCheck(String in_id,String in_pw) throws ClassNotFoundException{
		System.out.println("07_01 로그인 확인하는 메서드 선언 Mdao.java");
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
		//3단계 : 쿼리실행 위한 준비	완료
			rs = pstmt.executeQuery();	//04단계 : 쿼리 실행	
			if(rs.next()){	//01아이디 일치
				re = "01아이디일치";
				if(in_pw.equals(rs.getString("m_pw"))){
					re = "03로그인성공";
				}else{
					re = "04비번불일치";
				}
			}else{
				re = "02아이디불일치";
			}
			
			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} finally {
			// 6. 사용한 Statement 종료
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			
			// 7. 커넥션 종료
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		
		return re;
	}
	//06 검색 메서드 선언
	public ArrayList<Member> mSearch(String sk,String sv) throws ClassNotFoundException{
		System.out.println("06 검색 메서드 선언 Mdao.java");
		
		ArrayList<Member> alm = new ArrayList<Member>();
		
		Member m = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//한명의 회원정보 DB에서 가져와 수정화면에 뿌려주기위한 변수 선언
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
				System.out.println("01 둘다 없다");
				pstmt = conn.prepareStatement("select * from tb_member");
			}else if(sk != null && sv.equals("")){
				System.out.println("02 하나만 있다 ");
				pstmt = conn.prepareStatement("select * from tb_member");
			}else if(sk != null && sv != null){
				System.out.println("03 둘다 있다 ");
				String query = "select * from tb_member where "+sk+"=?";
				System.out.println(query + "<-- query m_search_list.jsp");
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, sv);
			}
			rs = pstmt.executeQuery();	//04단계 : 쿼리 실행
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
			// 6. 사용한 Statement 종료
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			
			// 7. 커넥션 종료
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}		
		
		
		return alm;
		
	}
	//05 전체회원 조회 메서드 선언
	public ArrayList<Member> mAllSelect() throws ClassNotFoundException{
		System.out.println("05 전체회원 조회 메서드 선언 Mdao.java");
		ArrayList<Member> alm = new ArrayList<Member>();
		
		Member m = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//한명의 회원정보 DB에서 가져와 수정화면에 뿌려주기위한 변수 선언
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
		//3단계 : 쿼리실행 위한 준비	완료
			rs = pstmt.executeQuery();	//04단계 : 쿼리 실행
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
			// 6. 사용한 Statement 종료
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			
			// 7. 커넥션 종료
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}		
		
		
		return alm;
	}
	
	
	
	
	
	//04 삭제처리 메서드 선언
	public void mDelete(String mid) throws ClassNotFoundException, SQLException{
		System.out.println("04 삭제처리 메서드 선언 Mdao.java");
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
	
	//03 수정처리 메서드 선언
	public void mUpdate(Member m) throws ClassNotFoundException, SQLException{
		System.out.println("03 수정처리 메서드 선언 Mdao.java");
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
			//06단계 :statement 또는 prepareStatement객체 종료(close())
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			//07단계 :Connection 객체 종료(close())
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}		
	}
	
	//02 한명의 회원정보 조회 후 수정화면 메서드 선언
	public Member mSelectforUpdate(String mid) throws ClassNotFoundException{
		System.out.println("02 한명의 회원정보 조회 후 수정화면 메서드 선언 Mdao.java");
		Member m = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//한명의 회원정보 DB에서 가져와 수정화면에 뿌려주기위한 변수 선언
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
		//3단계 : 쿼리실행 위한 준비	완료
			rs = pstmt.executeQuery();	//04단계 : 쿼리 실행
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
			// 6. 사용한 Statement 종료
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			
			// 7. 커넥션 종료
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		
		
		return m;
	}
	
	
	
	
	
	
	
	
	
	
	//01_02 입력처리 메서드 선언(매개변수 1개)
	public void mInsert(Member m) throws SQLException, ClassNotFoundException{
		System.out.println("01_02 입력처리 메서드 선언(매개변수 1개) Mdao.java");
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
			//06단계 :statement 또는 prepareStatement객체 종료(close())
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			//07단계 :Connection 객체 종료(close())
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}	
	//01_02 입력처리 메서드 선언(매개변수 1개) End
	
	//01_01 입력처리 메서드 선언(매개변수 2개)
	public void mInsert(Member m,Connection conn) throws SQLException{
		System.out.println("01_01 입력처리 메서드 선언(매개변수 2개) Mdao.java");
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
			//06단계 :statement 또는 prepareStatement객체 종료(close())
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			//07단계 :Connection 객체 종료(close())
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	//01_01 입력처리 메서드 End
	
	
}
