package tw.brad.jdbc;

import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class MyJDBC12 {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("user", "root");
		props.setProperty("password", "root");
		
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/iii",props)){
			
			Student st1 = new Student(90, 80, 40);
			PreparedStatement pstmt = conn.prepareStatement(
				"UPDATE member set student = ? WHERE id = ?");
			pstmt.setObject(1, st1);
			pstmt.setInt(2, 3);
			pstmt.executeUpdate();
			System.out.println("OK");
		}catch(Exception e) {
			System.out.println(e.toString());
		}

	}

}

class Student implements Serializable {
	int ch, eng, math;
	Student(int ch, int eng, int math){
		this.ch = ch;
		this.eng = eng;
		this.math = math;
	}
	int calScore() {
		return ch + eng + math;
	}
}
