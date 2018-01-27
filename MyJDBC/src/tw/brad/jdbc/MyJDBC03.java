package tw.brad.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

public class MyJDBC03 {

	public static void main(String[] args) {
		
		String source = fatchOpendata();
		if (source != null) {
			toMyDB(source);
		}else {
			System.out.println("no data");
		}
		
	}
	
	static String fatchOpendata() {
		String ret = null;
		try {
			URL url = 
				new URL(
					"http://data.coa.gov.tw/Service/OpenData/ODwsv/ODwsvAgriculturalProduce.aspx");
			HttpURLConnection conn = 
				(HttpURLConnection)url.openConnection();
			conn.connect();
			String line; StringBuffer sb = new StringBuffer();
			BufferedReader reader = 
				new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			System.out.println("reading");
			while ((line = reader.readLine()) != null) {
				//System.out.println(line);
				sb.append(line);
			}
			reader.close();
			ret = sb.toString();
			//System.out.println(sb.toString().length());
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return ret;
	}
	
	static void toMyDB(String json) {
		try {
			Properties props = new Properties();
			props.setProperty("user", "brad");
			props.setProperty("password", "123456");
			Connection conn = 
				DriverManager.getConnection(
					"jdbc:mysql://10.21.100.72/iii", props);
			PreparedStatement pstmt = conn.prepareStatement(
				"INSERT INTO gift (pname,addr,feature,imgurl)" + 
				" VALUES (?,?,?,?)");

			
			JSONArray root = new JSONArray(json);
			//System.out.println(root.length());
			for (int i=0; i<root.length(); i++) {
				JSONObject row = root.getJSONObject(i);
				String pname = row.getString("Name");
				String addr = row.getString("SalePlace");
				String feature = row.getString("Feature");
				String imgurl = row.getString("Column1");
				
				pstmt.setString(1, pname);
				pstmt.setString(2, addr);
				pstmt.setString(3, feature);
				pstmt.setString(4, imgurl);
				
				pstmt.execute();
				
			}
			
			
			System.out.println("OK");
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		
		/*
		JSONArray root = new JSONArray(json);
		//System.out.println(root.length());
		for (int i=0; i<root.length(); i++) {
			JSONObject row = root.getJSONObject(i);
			String name = row.getString("Name");
			String addr = row.getString("SalePlace");
			System.out.println(name + ":"+addr);
		}
		*/
		
		
	}
	

}
