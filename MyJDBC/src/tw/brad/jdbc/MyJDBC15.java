package tw.brad.jdbc;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.sql.ResultSetMetaData;

public class MyJDBC15 extends JFrame {
	private JTable table;
	private MyTableModel model;
	private int dataCount;
	private String[] fields;
	
	public MyJDBC15() {
		super("伴手禮");
		setLayout(new BorderLayout());
		
		initDB();
		
		model = new MyTableModel();
		table = new JTable(model);	
		
		JScrollPane jsp = new JScrollPane(table);
		add(jsp, BorderLayout.CENTER);
		
		setSize(640, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}	
	
	private void initDB() {
		Properties props = new Properties();
		props.setProperty("user", "root");
		props.setProperty("password", "root");
		
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/iii",props)){
			PreparedStatement pstmt = conn.prepareStatement(
				"SELECT count(*) as count FROM gift");
			ResultSet rs = pstmt.executeQuery();
			rs.next(); dataCount = rs.getInt("count");
			
			PreparedStatement pstmt2 = conn.prepareStatement(
					"SELECT * FROM gift");
			ResultSet rs2 = pstmt2.executeQuery();
			
			ResultSetMetaData metadata = rs2.getMetaData();
			fields = new String[metadata.getColumnCount()];
			for (int i=0; i<fields.length; i++) {
				fields[i] = metadata.getColumnLabel(i+1);
			}
			
			
			
		}catch(Exception e) {
			
		}
	
	}
	
	private class MyTableModel extends DefaultTableModel {

		@Override
		public int getColumnCount() {
			return fields.length;
		}

		@Override
		public String getColumnName(int index) {
			return fields[index];
		}

		@Override
		public int getRowCount() {
			return dataCount;
		}

		@Override
		public Object getValueAt(int row, int col) {
			return "Brad";
		}

		@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		}

	}
	
	
	public static void main(String[] args) {
		new MyJDBC15();
	}

}
