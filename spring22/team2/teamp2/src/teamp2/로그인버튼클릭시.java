package teamp2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class 로그인버튼클릭시 {

	private JFrame frame;

	
	public 로그인버튼클릭시(String ID_value,String PW_value) {
		
		////////////////////////////////////////////////////////////////////////////////////
		frame = new JFrame();
		frame.setBounds(100, 100, 420, 130);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("TODAY FIT");
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 404, 91);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		
	}

}
