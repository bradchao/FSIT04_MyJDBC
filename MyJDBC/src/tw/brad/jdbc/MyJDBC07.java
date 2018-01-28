package tw.brad.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class MyJDBC07 {

	public static void main(String[] args) {
		String account = "eric", passwd = "123456";
		
		Properties props = new Properties();
		props.setProperty("user", "root");
		props.setProperty("password", "root");
		
		try(Connection conn =
			DriverManager.getConnection(
				"jdbc:mysql://localhost/iii",props)){
		
			Member member = null;
			if ( (member = checkMember(account,passwd,conn)) != null) {
				System.out.println(
					"Welcome, " + member.realname);
			}else {
				System.out.println("Login Failure");
			}
			
		}catch (Exception e) {
			System.out.println(e.toString());
		}

	}
	
	static Member checkMember (
		String account, String passwd, Connection conn) 
				throws SQLException {

		String sql = "SELECT * FROM member WHERE account=? AND passwd=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, account);
		pstmt.setString(2, passwd);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return new Member(rs.getString("account"),
					rs.getString("passwd"),
					rs.getString("realname"));
		}else {
			return null;	
		}
		
		
	}

}
class Member {
	String id, account, realname;
	Member(String id, String account, String realname){
		this.id = id;
		this.account = account;
		this.realname = realname;
	}
}
