package tw.brad.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
			
			
			
		}catch(Exception e) {
			
		}

	}

}
