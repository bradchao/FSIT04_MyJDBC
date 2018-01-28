package tw.brad.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class MyJDBC08 {

	public static void main(String[] args) {
		String keyword = "";
		int rpp = 8;	// record pre page
		int page = 7;
		
		Properties props = new Properties();
		props.setProperty("user", "root");
		props.setProperty("password", "root");
		
		try(Connection conn =
			DriverManager.getConnection(
				"jdbc:mysql://localhost/iii",props)){
			String sql = "SELECT * FROM gift "+ 
				"WHERE pname like ? OR " +
				"addr like ? OR feature like ? " + 
				"LIMIT " + (page-1)*rpp + ", " + rpp;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setString(3, "%" + keyword + "%");
			ResultSet rs  = pstmt.executeQuery();
			
			while (rs.next()) {
				String id = rs.getString("id");
				String pname = rs.getString("pname");
				String addr = rs.getString("addr");
				String feature = rs.getString("feature");
				System.out.println(id +":"+pname+":"+addr);
				//System.out.println(feature);
				System.out.println("-----");
			}
			
			
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
		

	}

}
