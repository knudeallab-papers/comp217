/**
 * JAVA Term Project : Pos Program
 * Student : Yeji Ahn, Seunghye Jung
 * Prof. : Young-gyoon, Suh
 * Main Class
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class StartPos {
	private String myId = "1";
	private String myPassword = "1";
	Pos pos;
	
	public StartPos() {
		
		//ID and Password
		JDialog Lock = new JDialog();
		JLabel name = new JLabel("YS POS");
		JLabel id = new JLabel("ID : ");
		JLabel password = new JLabel("Password : ");
		JLabel notice = new JLabel("초기 아이디 : 1, 비밀번호 : 1 입니다");
		JButton login = new JButton("로그인");
		JTextField idField = new JTextField();
		JPasswordField passwordField = new JPasswordField();
		Lock.getContentPane().setLayout(null);
		Lock.setSize(300,180);
		
		name.setFont(new Font("나눔바른고딕", Font.ITALIC, 23));
		name.setBounds(100,10,100,30);
		Lock.add(name);
		login.setBackground(Color.WHITE);
		login.setBounds(220,50,60,60);
		login.setFont(new Font("나눔바른고딕", Font.PLAIN, 9));
		login.setHorizontalAlignment(SwingConstants.LEFT);
		Lock.add(login);
		id.setBounds(20, 40, 120, 40);
		Lock.add(id);
		idField.setBounds(40,50,120,20);
		Lock.add(idField);
		password.setBounds(20,70,120,40);
		Lock.add(password);
		passwordField.setBounds(90,80,120,20);
		Lock.add(passwordField);
		notice.setBounds(20,110,200,20);
		Lock.add(notice);
		Lock.setVisible(true);
		
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( idField.getText().equals(myId) ) {
					if ( passwordField.getText().equals(myPassword) == true) {
					Lock.setVisible(false);
					pos = new Pos();
					pos.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Warning : 비밀번호를 확인하세요 ");
						System.exit(0);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Warning : 권한이 없습니다 ");
					System.exit(0);
				}
			}
		});
	}
	
	public static void main(String args[]) {
		StartPos YS = new StartPos();
	}
}
