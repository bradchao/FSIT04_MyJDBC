package tw.brad.jdbc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class MyJDBC11 {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("user", "root");
		props.setProperty("password", "root");
		
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/iii",props)){
			PreparedStatement pstmt = conn.prepareStatement(
				"SELECT * FROM member WHERE id = ?");
			pstmt.setInt(1, 3);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			InputStream in = rs.getBinaryStream("img");
			FileOutputStream fout = 
				new FileOutputStream("dir2/brad.png");
			fout.write(in.readAllBytes());
			fout.flush();
			fout.close();
			System.out.println("OK");
		}catch(Exception e) {
			System.out.println(e.toString());
		}

	}

}
