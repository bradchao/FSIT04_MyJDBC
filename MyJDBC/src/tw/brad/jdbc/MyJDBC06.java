package tw.brad.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class MyJDBC06 {

	public static void main(String[] args) {

		String account = "eric", passwd = "123456", realname = "Eric";
		
		Properties props = new Properties();
		props.setProperty("user", "root");
		props.setProperty("password", "root");
		
		try(Connection conn =
			DriverManager.getConnection(
				"jdbc:mysql://localhost/iii",props)){
			
			if (!isDataRepeat(account, conn)) {
				String sql = "INSERT INTO member " + 
							"(account,passwd,realname)"+
							" VALUE (?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, account);
				pstmt.setString(2, passwd);
				pstmt.setString(3, realname);
				pstmt.executeUpdate();
				System.out.println("OK");
			}else {
				System.out.println("資料重複");
			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}

	static boolean isDataRepeat(String account, Connection conn)
		throws SQLException {
		String sql = "SELECT count(*) as count FROM member WHERE account=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, account);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int count = rs.getInt("count");
		
		return count != 0;
	}
	
	
}
