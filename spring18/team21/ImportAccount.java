package Restraunt;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ImportAccount extends JPanel implements ActionListener{

	public static JTextArea AccountBook;
	public static int todayImport = 0;
	
	public ImportAccount() {
//		setLayout(new BorderLayout());
		AccountBook = new JTextArea(28,30);
		AccountBook.setEditable(false);
		
		JScrollPane accountBar = new JScrollPane(AccountBook);
		
		accountBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		accountBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		add(accountBar);
		
		
		JButton clearButton = new JButton("clear");
		clearButton.addActionListener(this);
		
		add(clearButton, BorderLayout.WEST);
	}
	
	public void actionPerformed(ActionEvent e) {
		AccountBook.setText("");
	}
	
	public static void writeAccount(String msg) {
		AccountBook.setText(AccountBook.getText( ) + "\n" + msg);
	}
	
	public static void writeAccount(String msg, double price) {
		AccountBook.setText(AccountBook.getText( ) + "\n" + msg);
		todayImport += price;
	}
	
	public static void writeAccount(String msg, int price) {
		AccountBook.setText(AccountBook.getText( ) + "\n" + msg);
		todayImport += price;
	}
	
	public static void resetTodayImport() {
		todayImport = 0;
	}
	
	public void writeTextFile() {
		//OutputStream outStream = new OutputStream(new FileStream)
	}
}
