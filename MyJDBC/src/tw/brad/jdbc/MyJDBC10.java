package tw.brad.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class MyJDBC10 {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("user", "root");
		props.setProperty("password", "root");
		
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/iii",props)){
			
			FileInputStream fin = new FileInputStream("dir1/ball.png");
			PreparedStatement pstmt = conn.prepareStatement(
				"UPDATE member set img = ? WHERE id = ?");
			pstmt.setBinaryStream(1, fin);
			pstmt.setInt(2, 3);
			pstmt.executeUpdate();
			System.out.println("OK");
		}catch(Exception e) {
			System.out.println(e.toString());
		}

	}

}
