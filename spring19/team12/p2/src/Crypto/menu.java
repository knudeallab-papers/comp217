package Crypto;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class menu {
	private static JTextField txtEcryptYourMessage;
	private static JTextField txtDecryption;
	private static JTextField txtChat;
	private static JTextArea txtrDecrypt;
	private static JTextArea txtr;
	private static Encryption en;
	private static Decryption d;
	public static void main(String[] args) {
		showWindow();
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void showWindow() {
		
		
		//create the frame
				JFrame frame = new JFrame("Menu");
				frame.setBounds(100,100,1060,525);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				//create the panel
				JPanel panel = new JPanel();
				frame.getContentPane().add(panel, BorderLayout.WEST);
				panel.setLayout(new BorderLayout(0, 0));
				
				//label encryption
				JLabel lblEncryption = new JLabel("Encryption");
				lblEncryption.setHorizontalAlignment(JLabel.CENTER);
				panel.add(lblEncryption, BorderLayout.NORTH);
				
				
				//textfield for encryption
				txtEcryptYourMessage = new JTextField();
				txtEcryptYourMessage.setAlignmentY(Component.BOTTOM_ALIGNMENT);
				txtEcryptYourMessage.setText("");
				panel.add(txtEcryptYourMessage, BorderLayout.SOUTH);
				txtEcryptYourMessage.setColumns(30);
				
				
				//textarea for encryption
				txtr = new JTextArea(15,20);
				txtr.setRows(1);
				txtr.setLineWrap(true);
				txtr.setEditable(true);
				JScrollPane scrolling = new JScrollPane(txtr);
				scrolling.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				//panel.add(scrolling, BorderLayout.CENTER);
				
				
				txtr.setAlignmentY(Component.BOTTOM_ALIGNMENT);
				txtr.setText("");
				panel.add(scrolling, BorderLayout.CENTER);
				
				
				
				JPanel panel_1 = new JPanel();
				frame.getContentPane().add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(new BorderLayout(0, 0));
				
				txtChat = new JTextField();
				txtChat.setText("Enter your message");
				panel_1.add(txtChat, BorderLayout.SOUTH);
				txtChat.setColumns(25);
				
				JLabel labelchat = new JLabel("Chat");
				labelchat.setHorizontalAlignment(JLabel.CENTER);
				panel_1.add(labelchat, BorderLayout.NORTH);
				
				JTextArea txtrWelcome = new JTextArea(15,20);
				txtrWelcome.setRows(1);
				txtrWelcome.setText("Welcome to the chatroom");
				txtrWelcome.setLineWrap(true);
				txtrWelcome.setEditable(true);
				JScrollPane scrolle = new JScrollPane(txtrWelcome);
				scrolle.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				panel_1.add(scrolle, BorderLayout.CENTER);
				
				JPanel panel_2 = new JPanel();
				frame.getContentPane().add(panel_2, BorderLayout.EAST);
				panel_2.setLayout(new BorderLayout(0, 0));
				
				txtDecryption = new JTextField();
				txtDecryption.setText("");
				panel_2.add(txtDecryption, BorderLayout.SOUTH);
				txtDecryption.setColumns(30);
				
				JLabel labeldecrypt = new JLabel("Decryption");
				labeldecrypt.setHorizontalAlignment(JLabel.CENTER);
				panel_2.add(labeldecrypt, BorderLayout.NORTH);
				
				txtrDecrypt = new JTextArea();
				txtrDecrypt.setTabSize(14);
				txtrDecrypt.setText("");
				txtrDecrypt.setLineWrap(true);
				txtrDecrypt.setEditable(true);
				JScrollPane scroller = new JScrollPane(txtrDecrypt);
				scrolling.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				panel_2.add(scroller, BorderLayout.CENTER);
				
				JPanel panel_3 = new JPanel();
				frame.getContentPane().add(panel_3, BorderLayout.NORTH);
				
				connexion con = new connexion();
				JLabel lblNewLabel = new JLabel("Welcome to Crypto'Code " + con.getUsername());
				panel_3.add(lblNewLabel);
				
				JPanel panel_4 = new JPanel();
				frame.getContentPane().add(panel_4, BorderLayout.SOUTH);
				
				JButton btnEncrypt = new JButton("Encrypt");
				panel_4.add(btnEncrypt);
				btnEncrypt.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						/*String stock = txtEcryptYourMessage.getText();
						txtr.setText(stock);
						txtEcryptYourMessage.setText("");*/
						String txt = txtEcryptYourMessage.getText();
						if(txt.equals(""))
						{
							en = new Encryption();
							txtr.setText("Enter like that : \n"+
									"N e message");
						}
						else
						{
							//System.out.println("caca");
							int i = 0;
							int j = 0;
							int count = 0 ;
							ArrayList<String> l = new ArrayList<String>();
							while(i < txt.length() && count < 2)
							{	
								if (txt.charAt(i) == ' ')
								{
									l.add(txt.substring(j,i));
									count += 1;
									j = i + 1;
								}
								i += 1;
							}
							l.add(txt.substring(i));
							//System.out.println(l.get(0));
						//	System.out.println(l.get(1));
							//System.out.println(l.get(2));
							long N = Long.parseLong(l.get(0));
							long exp = Long.parseLong(l.get(1));
							en.Encrypt(N,exp,l.get(2));
							txtr.setText(en.getEn());
						}
						
					}
			
				});
				
				JButton btnDisconnect = new JButton("Disconnect");
				panel_4.add(btnDisconnect);
				btnDisconnect.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(false);
						connexion con = new connexion();
						con.showWindow();
						
					}
			
				});
				
				
				
				
				JButton btnQuit = new JButton("Quit");
				panel_4.add(btnQuit);
				btnQuit.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
						
					}
			
				});
				
				
				
				
				
				JButton btnDecrypt = new JButton("Decrypt");
				panel_4.add(btnDecrypt);
				btnDecrypt.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						/*String stock = txtDecryption.getText();
						 * 
						txtrDecrypt.setText(stock);
						txtDecryption.setText("");*/
						txtrDecrypt.setText("");
						//txtDecryption.setText("");
						if(txtDecryption.getText().equals(""))
						{
							d = new Decryption();
							for(int i = 500; i<600; i++)
							{
								if(prime(i))
								{
									txtrDecrypt.setText(txtrDecrypt.getText()+i + "\n");
								}
							}
							txtrDecrypt.setText(txtrDecrypt.getText() + "Choose 2 prime numbers p and q such that pq > 10000\n");
							txtrDecrypt.setText(txtrDecrypt.getText()+"like that : p q primes \n");
							txtrDecrypt.setText(txtrDecrypt.getText()+"then your message\n");
						}
						
						/*long start=System.nanoTime(); 
						while((System.nanoTime()-start)/10<600000000);
					    /*start=System.nanoTime(); 
						while((System.nanoTime()-start)<600000000);*/
						
						/*try {
							System.out.print("looooool");
							Thread.sleep(10000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}*/

						else 
						{
							String N = txtDecryption.getText();
							//System.out.println("caca");
							int i = 0;
							int j = 0;
							ArrayList<String> l = new ArrayList<String>();
							while(i < N.length())
							{	
								if (N.charAt(i) == ' ')
								{
									l.add(N.substring(j,i));
									j = i + 1;
								}
								i += 1;
							}
							l.add(N.substring(j,i));
							//System.out.println(l.get(0));
							//System.out.println(l.get(1));
							//System.out.println(l.get(2));
							if (l.size() > 2 && l.get(2).equals("primes") )
							{
								long p = Long.parseLong(l.get(0));
								long q = Long.parseLong(l.get(1));
								d.setp(p);
								d.setq(q);
								System.out.println(d.getN());
								long exp = p+1;
								if(p<q)
									exp = q+1;
								while(gcd(exp,(p-1)*(q-1)) != 1)
								{
									exp += 1;
									//System.out.println(exp);
								}
								d.sete(exp);
								//System.out.println(d.getN());
								//System.out.println(d.getexp());
								txtrDecrypt.setText(txtrDecrypt.getText()+
										"Send N = "+d.getN()+ " and e = " + d.getexp()+ "\n"
										+"then your message\n");
							}
							else 
							{
								
								//System.out.println(N);
								d.decrypt(N);
								//System.out.print(d.getd());
								txtrDecrypt.setText(d.getDec());
								
							}
							
						}
						
					}
			
				});
				
				

				
				//frame.setVisible(true);
				
				
				Socket socket;
				final BufferedReader in;
				final PrintWriter out;
				//final Scanner sc = new Scanner(System.in); // lire les messages
				
				try {
					//System.out.println("lalal");
					//server = new ServerSocket(4780); // creation du server avec port
				//	server.setSoTimeout(9000);
					//System.out.println("1");
					socket = new Socket();
					socket = new Socket("192.168.0.11", 5003);
					//System.out.println("1");
					out = new PrintWriter(socket.getOutputStream());  // flux pour envoyer
					in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // flux pour recevoir
					//System.out.println("3");//"" + socket.getInetAddress().getHostAddress());
					JButton btnSend = new JButton("Send");
					panel_4.add(btnSend);
					//System.out.println("1");
					btnSend.addActionListener(new ActionListener()
					{

						@Override
						public void actionPerformed(ActionEvent e) {
							//String stock = txtrWelcome.getText() + "\n" + con.getUsername()+ ": " + txtChat.getText();
							/*Thread send = new Thread(new Runnable() {
								String msg ;
								public void run() {
									while(true) {*/
							String msg = txtChat.getText();
							txtrWelcome.setText(txtrWelcome.getText() + "\nKevin : "+msg);
							out.println("Kevin : " + msg);
							out.flush();
										
									/*}
								}
							});
							send.start();*/
							//txtrWelcome.setText(stock);
							txtChat.setText("");
						}
				
					});
					//send.run();
					Thread receive = new Thread(new Runnable() {
						String msg;
						public void run() {
							while(true) {
								try {
									msg = in.readLine();
								}catch(IOException e) {
									e.printStackTrace();
								}
								txtrWelcome.setText(txtrWelcome.getText() + "\n" + msg);
							}
						}
					});
					receive.start();
				}catch(IOException e) {
					e.printStackTrace();
				}
				
				//frame.getContentPane().add(panel_4, BorderLayout.SOUTH);
				
				frame.setVisible(true);
			}
			
			public void Decrypt()
			{
				Decryption d = new Decryption();
				for(int i = 100; i<1000; i++)
				{
					if(prime(i))
					{
						txtrDecrypt.setText(txtrDecrypt.getText()+i + "\n");
					}
				}
				txtrDecrypt.setText(txtrDecrypt.getText() + "Choose 2 prime numbers p and q such that pq > 10000\n");
				txtrDecrypt.setText(txtrDecrypt.getText()+"like that : p q\n");
				String N;
			}
			
			
			public static boolean prime(long a)
			{
				boolean test = true;
				long b = 2;
				while(b*b < a && test)
				{
					test = test && a%b!=0;
					b += 1;
				}
				return test;
			}
			public static long gcd(long a, long b)
			{
				/*if(a<b)
					return gcd(b,a);
				if (b == 1)
					return a;
				if(b == 0)
					return a;
				return gcd(b, a%b);*/
				
				long r;
				 while (b != 0)
				 {
					 r = a%b;
				 	a = b;
				 	b = r;
				 }
				return a;
					
			}
	
	
	}
	
	
	
	
	
	
	
	
	
	
