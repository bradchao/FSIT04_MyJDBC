package tw.brad.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MyJDBC02 {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = 
//				DriverManager.getConnection(
//					"jdbc:mysql://10.21.100.72/iii",
//					"brad","123456");
			
			Properties prop = new Properties();
			prop.setProperty("user", "brad");
			prop.setProperty("password", "123456");
			prop.setProperty("connectTimeout", "15000");
			
			Connection conn = 
					DriverManager.getConnection(
						"jdbc:mysql://10.21.100.72/iii",
						prop);
			
			Statement stmt = conn.createStatement();
			//stmt.execute("INSERT INTO cust (cname,tel,birthday) VALUES ('brad','123','1999-01-02')");
			//int i = stmt.executeUpdate("DELETE FROM cust WHERE id<5");
			//System.out.println(i);
			int i = stmt.executeUpdate(
				"UPDATE cust SET cname='Kevin',tel='321' WHERE id=5");
			
			
			System.out.println("OK");
		} catch (SQLException e) {
			System.out.println(e.toString());
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}
	}

}
