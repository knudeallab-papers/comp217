package bk;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class UserAddActionClass implements ActionListener{

	JTable table;
	JTextField text1, text2, text3, text4, text5;
	
	public UserAddActionClass(JTable table, JTextField text1, JTextField text2, JTextField text3, JTextField text4, JTextField text5) {
		this.table = table;
		this.text1 = text1;
		this.text2 = text2;
		this.text3 = text3;
		this.text4 = text4;
		this.text5 = text5;
		
		this.text1.setText(null);
		this.text2.setText(null);
		this.text3.setText(null);
		this.text4.setText(null);
		this.text5.setText(null);
		text1.requestFocus();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		String ar[] = new String[5];
		
				/*
		MainWindow.arr[MainWindow.count][0] = text1.getText();
		MainWindow.arr[MainWindow.count][1] = text2.getText();
		MainWindow.arr[MainWindow.count][2] = text3.getText();
		MainWindow.arr[MainWindow.count][3] = text4.getText();
		MainWindow.arr[MainWindow.count][4] = text5.getText();
		MainWindow.arr[MainWindow.count][5] = text6.getText();
		*/
		ar[0] = text1.getText();
		ar[1] = text2.getText();
		ar[2] = text3.getText();
		ar[3] = text4.getText();
		ar[4] = text5.getText();
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();

		model.addRow(ar);
		
		//MainWindow.count++;
	}

}