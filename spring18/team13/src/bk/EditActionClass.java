package bk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class EditActionClass implements ActionListener{

JTable table;
JTextField text1, text2, text3, text4, text5;

	public EditActionClass(JTable table, JTextField text1, JTextField text2, JTextField text3, JTextField text4, JTextField text5) {
		this.table = table;
		this.text1 = text1;
		this.text2 = text2;
		this.text3 = text3;
		this.text4 = text4;
		this.text5 = text5;
		
		
		text1.requestFocus();
	}
	
	public void actionPerformed(ActionEvent e) {
		String ar[] = new String[6];
		
		int row = table.getSelectedRow();
		
		if(row == -1)
			return;
		
		ar[0] = text1.getText();
		ar[1] = text2.getText();
		ar[2] = text3.getText();
		ar[3] = text4.getText();
		ar[4] = BurgerKing.str;
		ar[5] = text5.getText();
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
			
			
		model.setValueAt(ar[0], row, 0);
		model.setValueAt(ar[1], row, 1);
		model.setValueAt(ar[2], row, 2);
		model.setValueAt(ar[3], row, 3);
		model.setValueAt(ar[4], row, 4);
		model.setValueAt(ar[5], row, 5);

	
		
	}
}

