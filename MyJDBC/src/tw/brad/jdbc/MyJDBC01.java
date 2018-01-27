package tw.brad.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyJDBC01 {
	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = 
				DriverManager.getConnection(
					"jdbc:mysql://10.21.100.72/iii?user=brad&password=123456");
			Statement stmt = conn.createStatement();
			
			stmt.execute("INSERT INTO cust (cname,tel,birthday) VALUES ('brad','123','1999-01-02')");
			
			System.out.println("OK");
		} catch (SQLException e) {
			System.out.println(e.toString());
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}
	}
}
