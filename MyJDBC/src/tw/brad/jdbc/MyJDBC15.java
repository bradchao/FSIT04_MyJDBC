package tw.brad.jdbc;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MyJDBC15 extends JFrame {
	public MyJDBC15() {
		super("伴手禮");
		setLayout(new BorderLayout());
		
		
		setSize(640, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}	
	
	private class MyTableModel extends DefaultTableModel {

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return super.getColumnCount();
		}

		@Override
		public String getColumnName(int arg0) {
			// TODO Auto-generated method stub
			return super.getColumnName(arg0);
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return super.getRowCount();
		}

		@Override
		public Object getValueAt(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return super.getValueAt(arg0, arg1);
		}

		@Override
		public boolean isCellEditable(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return super.isCellEditable(arg0, arg1);
		}

		@Override
		public void setValueAt(Object arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			super.setValueAt(arg0, arg1, arg2);
		}
		
	}
	
	
	public static void main(String[] args) {
		new MyJDBC15();
	}

}
