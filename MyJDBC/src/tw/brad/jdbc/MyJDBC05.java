package tw.brad.jdbc;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.json.JSONStringer;
import org.json.JSONWriter;

public class MyJDBC05 {

	public static void main(String[] args) {
		
		try {
			Properties props = new Properties();
			props.setProperty("user", "root");
			props.setProperty("password", "root");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/iii",props);
			String sql = "SELECT * FROM gift";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			JSONStringer js = new JSONStringer();
			// [...]
			JSONWriter jw = js.array();
			while (rs.next()) {
				String pname = rs.getString("pname");
				String feature = rs.getString("feature");
				// {"key1":"value1","key2":"value2"}
				jw.object();
				jw.key("PName").value(pname);
				jw.key("Feature").value(feature);
				jw.endObject();
			}
			jw.endArray();
			//System.out.println(jw);
			
			FileWriter fw = new FileWriter("dir1/mydata.json");
			fw.write(jw.toString());
			fw.flush();
			fw.close();
			
			System.out.println("OK");
			
		}catch(Exception e) {
			
		}
	}

}
