package Crypto;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class register {
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static String username;
	private static char[] pass1;
	private static char[] pass2;
	private static JPasswordField passwordField;
	private static JPasswordField passwordField2;
	private static String p1;
	private static String p2;
	
	private static boolean okpass = false;
	private static boolean okuser = false;

	//getter setter
	public static String getP1() {
		return p1;
	}

	public static void setP1(String p1) {
		register.p1 = p1;
	}

	public static String getP2() {
		return p2;
	}

	public static void setP2(String p2) {
		register.p2 = p2;
	}
	
	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		register.username = username;
	}

	//end
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		showWindow();

	}

	public static void showWindow() {
		JFrame frame = new JFrame("Register");
		frame.setBounds(100,100,470,405);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRegisterYourselfTo = new JLabel("Register Yourself to Crypto'Code");
		lblRegisterYourselfTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterYourselfTo.setFont(new Font("Zapfino", Font.PLAIN, 13));
		lblRegisterYourselfTo.setBounds(6, 6, 444, 37);
		frame.getContentPane().add(lblRegisterYourselfTo);
		
		textField = new JTextField();
		textField.setBounds(6, 131, 458, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(6, 114, 238, 16);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(6, 173, 444, 16);
		frame.getContentPane().add(lblPassword);

		
		passwordField = new JPasswordField();
		passwordField.setBounds(6, 192, 458, 26);
		frame.getContentPane().add(passwordField);
		
		
		
		
		JLabel lblConfirmYourPassword = new JLabel("Confirm your password");
		lblConfirmYourPassword.setBounds(6, 225, 444, 16);
		frame.getContentPane().add(lblConfirmYourPassword);
		

		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(6, 250, 458, 26);
		frame.getContentPane().add(passwordField2);
		pass2=passwordField2.getPassword();
		String p2 = new String(pass2);
		
		JButton btnregister = new JButton("Register");

		btnregister.setBounds(167, 322, 117, 29);
		frame.getContentPane().add(btnregister);
		btnregister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get pass1
				pass1 = passwordField.getPassword();
				String p1 = new String(pass1);
				register.setP1(p1);
				
				//getpass2
				pass2 = passwordField2.getPassword();
				String p2 = new String(pass2);
				register.setP2(p2);
				
				//get username
				username = textField.getText();
				setUsername(username);
				System.out.println(register.getUsername());
				
				//check if password is equivalent or not
				if(p1.equals(p2)){
					
					System.out.println(register.getP1());
					System.out.println(register.getP2());
					okpass = true;
				}
				
				//check if username already exist on db or not
				utilisateur util = new utilisateur();
				System.out.println(username + " check presence:" + util.checkpresence(util.getUser(), username));
				if(!util.checkpresence(util.getUser(), username))
				{
					okuser = true;
					
				}
				else
				{
					okuser = false;
					JOptionPane.showMessageDialog(frame, "Your password is not equivalent or the username already exist");
					frame.setVisible(false);
					register reg = new register();
					reg.showWindow();
				}
			
		
				if(okpass && okuser) {
					util.addList(util.getUser(), username);
					util.addlisteu(util.getListeu(), username, p1);
					JOptionPane.showMessageDialog(frame, "You have successfully been registered");

					frame.setVisible(false);
					connexion con = new connexion();
					con.showWindow();
				}
				else
				{
					//put error window;
					JOptionPane.showMessageDialog(frame, "Your password is not equivalent or the username already exist");
					frame.setVisible(false);
					register reg = new register();
					reg.showWindow();
				}
				
				
				
			}
		});
		
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(167, 348, 117, 29);
		frame.getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				connexion con = new connexion();
				con.showWindow();
			}
		});
		
		
		
		frame.setVisible(true);

	}

	
	
}