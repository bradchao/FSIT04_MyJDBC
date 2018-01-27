package tw.brad.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class MyJDBC04 {

	public static void main(String[] args) {
		try {
			Properties props = new Properties();
			props.setProperty("user", "brad");
			props.setProperty("password", "123456");
			Connection conn = 
				DriverManager.getConnection(
					"jdbc:mysql://10.21.100.72/iii", props);
			Statement stmt = conn.createStatement();
			ResultSet rs1 = stmt.executeQuery(
				"SELECT count(*) as num FROM gift");
			rs1.next();
			String count = rs1.getString("num");
			System.out.println(count);
			
			ResultSet rs = stmt.executeQuery(
				"SELECT id as myid, pname as pp FROM gift LIMIT 40,10");
			while (rs.next()) {
				String pname = rs.getString("pp");
				String id = rs.getString("myid");
				
				System.out.println(id + ":" + pname);
			}
			
			
		}catch(Exception e) {
			
		}

	}

}
