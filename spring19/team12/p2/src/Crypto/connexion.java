package Crypto;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;

public class connexion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		showWindow();
		
	}
	
	private static String username;
	private static String password;
	private static JPasswordField passwordField;
	private static char[] pass1;
	private String p1;
	
	
	public static void showWindow() {
		JFrame frame = new JFrame("CRYPTO'CODE ");
		frame.setBounds(100,100,470,405);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//create the label
		JLabel lblWelcomeToCryptocode = new JLabel("WELCOME TO CRYPTO'CODE");
		lblWelcomeToCryptocode.setFont(new Font("Zapfino", Font.BOLD, 13));
		lblWelcomeToCryptocode.setBounds(46, 29, 418, 35);
		frame.getContentPane().add(lblWelcomeToCryptocode);
		
		JTextArea txtrEnterYourUsername = new JTextArea();
		txtrEnterYourUsername.setText("");
		txtrEnterYourUsername.setBounds(60, 133, 353, 16);
		frame.getContentPane().add(txtrEnterYourUsername);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(180, 114, 117, 16);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(180, 181, 74, 16);
		frame.getContentPane().add(lblPassword);
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(60, 206, 353, 26);
		frame.getContentPane().add(passwordField);
		

		//register.setP1(p1);
		
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(180, 292, 117, 29);
		frame.getContentPane().add(btnLogin);
		btnLogin.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				utilisateur util = new utilisateur();
				frame.setVisible(false);
				pass1 = passwordField.getPassword();
				String p1 = new String(pass1);
				System.out.println("pass = " + p1);
				
				String user = txtrEnterYourUsername.getText();
				
				//if username empty
				if (user.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Your username/password is empty or does not exist");
					frame.setVisible(false);
					connexion con = new connexion();
					con.showWindow();
					
				}
				//if user doesnot exist
				else if(!util.checkpresence(util.getUser(), user) )
				{
					JOptionPane.showMessageDialog(frame, "Your username/password is empty or does not exist");
					frame.setVisible(false);
					connexion con = new connexion();
					con.showWindow();
				}
				
				//if password and username  does not match
				else if(!util.checkmatch(util.getListeu(), user, p1))
				{
					System.out.println("pass mauvais = " + p1);

					JOptionPane.showMessageDialog(frame, "Your username and your password does not match");
					frame.setVisible(false);
					connexion con = new connexion();
					con.showWindow();
				}
				
				//if everything good
				else
				{
					//set username for print
					setUsername(user);
					frame.setVisible(false);
					
					menu men = new menu();
					men.showWindow();
				}
				


			}
			
		});
		
		
		//register button
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(180, 333, 117, 29);
		frame.getContentPane().add(btnRegister);
		btnRegister.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//open a new register window
				frame.setVisible(false);
				register regis = new register();
				regis.showWindow();


			}
			
		});
		

		
		frame.setVisible(true);
	}
	
	//getter setter
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		connexion.username = username;
	}
}