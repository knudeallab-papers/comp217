package teamp2;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;


public class 회원성별체형 {

	private JFrame frame;
	private static String gender;
	private static String shape;
	
	public 회원성별체형(String ID_value,String PW_value){
		
			
		frame = new JFrame();
		frame.setBounds(100, 100, 655, 474);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String[] genderlist= {"남자", "여자"};
		String[] shapelist= {"마름", "평범","통통"};
		
		JComboBox comboBox = new JComboBox(genderlist);
		comboBox.setBounds(128, 125, 142, 67);
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox(shapelist);
		comboBox_1.setBounds(335, 125, 142, 67);
		frame.getContentPane().add(comboBox_1);
		
		JButton 결정 = new JButton("결정 완료");
		결정.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				어플홈화면 win= new 어플홈화면(ID_value);
			}
		});
		결정.setBounds(249, 356, 97, 23);
		frame.getContentPane().add(결정);
		
		
		try(FileWriter fw = new FileWriter("./src/teamp2/program/login.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
				String gender2=comboBox.getSelectedItem().toString();
				String shape2=comboBox_1.getSelectedItem().toString();
			    out.printf(ID_value+" "+PW_value+" "+gender2+" "+shape2+"\n");
			    
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		


		frame.setVisible(true);
		
		
		
	}

	public String getGender() {
		return gender;
	}

	public String getShape() {
		return shape;
	}
}
