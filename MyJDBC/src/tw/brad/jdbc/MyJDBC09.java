package tw.brad.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class MyJDBC09 {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("user", "root");
		props.setProperty("password", "root");
		
		try(Connection conn =
			DriverManager.getConnection(
				"jdbc:mysql://localhost/iii",props)){
			DatabaseMetaData metadata = conn.getMetaData();
			boolean isOK = metadata.supportsResultSetConcurrency(
				ResultSet.TYPE_FORWARD_ONLY, 
				ResultSet.CONCUR_UPDATABLE);
			System.out.println(isOK);
			
			String sql = "SELECT * FROM member where id = 1";
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY, 
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			System.out.println(rs.getString("realname"));
			
			rs.updateString("realname", "Eric OK");
			rs.updateString("passwd", "654321");
			rs.updateRow();
			
			
			PreparedStatement pstmt =
				conn.prepareStatement("SELECT * FROM member", 
					ResultSet.TYPE_FORWARD_ONLY, 
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs2 = pstmt.executeQuery();
			while (rs2.next()) {
				rs2.updateString("passwd", "111111");
				rs2.updateRow();
			}
			
			rs2.moveToInsertRow();
			rs2.updateString("account", "mary");
			rs2.updateString("passwd", "121212");
			rs2.updateString("realname", "Mary");
			rs2.insertRow();
			
			System.out.println("OK");
			
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}

	}

}
