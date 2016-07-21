package kr.or.ksmart.driverdb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DriverDB {
	
	public Connection driverDbcon() throws ClassNotFoundException, SQLException{
		System.out.println("01 driverDbcon DriverDB");
		Class.forName("com.mysql.jdbc.Driver");
		
		String jdbcDriver = "jdbc:mysql://localhost:3306/dev21db?" +
				"useUnicode=true&characterEncoding=euckr";
		String dbUser = "dev21id";
		String dbPass = "dev21pw";
		
		Connection reconn =null;
		
		reconn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		
		
		return reconn;
	}
	
}
