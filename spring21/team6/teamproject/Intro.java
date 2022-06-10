package teamproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Intro extends JFrame implements ActionListener{
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 740;
	
	public static final String srcPath = "src";
	public static final String packageName = "teamproject";
	
	private int page;
	JFrame comp = new JFrame();
	JPanel intro = new JPanel();
	Pan panPanel = new Pan();
	mallGui mallPanel = new mallGui();
	JMenuItem backmenu;
	JMenuItem savemenu;
	Font font;
	private String[] save; 
	private String inFilename;
	JButton ok;
	JButton no;
	JFrame con1;
	JFrame con2;
	JFrame newNotice;
	
	String outfileName;
	
	JPanel savenotice = new JPanel();
	
	private Malll[] P1 = new Malll[4];
	private Malll[] P2 = new Malll[4];
	private boolean[] ch1 = new boolean[71];
	private boolean[] ch2 = new boolean[71];
	private boolean check = false;
	private int cnt = 0;
	private boolean player1;
	private boolean player2;
	
	JButton submit1;
	JButton submit2;
	
	JTextField input;
	
	JPanel panMain;
	ImageIcon backgroundIcon;
	JLabel background;
	public Intro() {
		comp.setSize(WIDTH, HEIGHT);
		comp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		comp.setBackground(Color.WHITE);
		comp.setLayout(null);
		
		font = new Font("Gothic", font.BOLD, 15);
		/***/
		
		/***/
		intro.setLayout(null);
		intro.setBounds(0,0,WIDTH,HEIGHT);
	//	intro.setBackground(Color.gray);
		
		backgroundIcon = new ImageIcon("./src/teamproject/firstback.png");
		Image img = backgroundIcon.getImage();
		Image changeImg = (img.getScaledInstance(1000,740,Image.SCALE_SMOOTH));
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		background = new JLabel(changeIcon);
		background.setSize(1000,740);
		
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(null);
		btnPanel.setBounds(400,430,200,320);
		btnPanel.setBackground(Color.gray);
		
		JButton nstart = new JButton("�����ϱ�");
		nstart.addActionListener(this);
		nstart.setBounds(0,0,200,40);
		nstart.setBackground(Color.WHITE);
		btnPanel.add(nstart);
		
		JButton con = new JButton("�̾��ϱ�");
		con.addActionListener(this);
		con.setBounds(0,50,200,40);
		con.setBackground(Color.WHITE);
		btnPanel.add(con);
		
		JButton explanation = new JButton("���Ӽ���");
		explanation.addActionListener(this);
		explanation.setBounds(0,100,200,40);
		explanation.setBackground(Color.WHITE);
		//btnPanel.add(explanation);
		
		JButton exit = new JButton("�����ϱ�");
		exit.addActionListener(this);
		exit.setBounds(0,100,200,40);
		exit.setBackground(Color.WHITE);
		btnPanel.add(exit);
		JPanel notice;
		
		JMenuBar mb = new JMenuBar();
		JMenu screenMenu = new JMenu("�޴�");
		
		JMenuItem newmenu = new JMenuItem("�����ϱ�");
		newmenu.addActionListener(this);
		JMenuItem callmenu = new JMenuItem("�̾��ϱ�");
		callmenu.addActionListener(this);
		backmenu = new JMenuItem("���ư���");
		backmenu.addActionListener(this);
		backmenu.setEnabled(false);
		savemenu = new JMenuItem("�����ϱ�");
		savemenu.addActionListener(this);
		savemenu.setEnabled(false);
		JMenuItem finmenu = new JMenuItem("�����ϱ�");
		finmenu.addActionListener(this);
		//setBackground(getForeground());
		
		
		
		screenMenu.add(newmenu);
		screenMenu.add(callmenu);
		screenMenu.add(backmenu);
		screenMenu.add(savemenu);
		screenMenu.addSeparator();
		screenMenu.add(finmenu);

		mb.add(screenMenu);

		comp.setJMenuBar(mb);
		
		panMain = panPanel.comp;
		panMain.setVisible(false);
		btnPanel.setOpaque(false);
		
		
		intro.add(btnPanel);
		intro.add(background);
		comp.add(intro);
		comp.add(panMain);
		
	}
	
	public int getPage() {
		return page;
	}
	
	public static void main(String[] args) {
		Intro panGui = new Intro();
		panGui.comp.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		// TODO Auto-generated method stub
		if(actionCommand.equals("�����ϱ�")) {
			intro.setVisible(false);
			panMain.setVisible(true);
			panPanel.setNewgame();
			backmenu.setEnabled(true);
			savemenu.setEnabled(true);
		}
		else if(actionCommand.equals("�̾��ϱ�")) {
			boolean check = false;
			backmenu.setEnabled(true);
			savemenu.setEnabled(true);
			/**************************����������*************************/
			con1 = new JFrame("�ҷ�����");
			int WIDTHc = 300;
			int HEIGHTc = 200;
			JPanel notice1 = new JPanel();
			con1.setSize(WIDTHc,HEIGHTc);
			con1.setBackground(Color.WHITE);
			con1.setLayout(new FlowLayout());
			notice1.setLayout(new GridLayout(5,1));
			
			JLabel label = new JLabel("�̸��� �Է��ϼ���.");
			
			input = new JTextField(20);
			
			submit2 = new JButton("Ȯ��");
			submit2.addActionListener(this);
			submit2.setBackground(Color.white);
			
			notice1.add(new JLabel());
			notice1.add(label);
			notice1.add(input);
			notice1.add(submit2);
			
			con1.add(notice1);
			con1.setVisible(true);
		}
		else if(actionCommand.equals("���ư���")) {
			intro.setVisible(true);
			panMain.setVisible(false);
			backmenu.setEnabled(false);
			savemenu.setEnabled(false);
		}
		else if(actionCommand.equals("�����ϱ�")) {
			P1 = panPanel.getP1();
			P2 = panPanel.getP2();
			ch1 = panPanel.getCh1();
			ch2 = panPanel.getCh2();
			player1 = panPanel.getPlayer1();
			player2 = panPanel.getPlayer2();
			int WIDTHc = 300;
			int HEIGHTc = 200;
			/**************************����������**************************/
			con2 = null;
			con2 = new JFrame("�����ϱ�");
			con2.setSize(WIDTHc,HEIGHTc);
			con2.setBackground(Color.WHITE);
			con2.setLayout(null);
			
			JLabel label = new JLabel("�̸��� �Է��ϼ���.");
			label.setBounds(90, 20, 300, 20);
			input = new JTextField(20);
			input.setBounds(5, 50, 270, 30);
			submit1 = new JButton("Ȯ��");
			submit1.setBounds(115, 100, 60, 40);
			submit1.addActionListener(this);
			submit1.setBackground(Color.white);
			
			con2.add(label);
			con2.add(input);
			con2.add(submit1);
			
			con2.setVisible(true);
		}
		else if(actionCommand.equals("�����ϱ�")) {
			comp.dispose();
		}
		else if(actionCommand.equals("���Ӽ���")) {
			page = 3;
		}
		
		if(e.getSource() == submit2) {
			try {
				fileInput();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			intro.setVisible(false);
			panMain.setVisible(true);
		}
		
		if(e.getSource() == submit1) {
			fileSave();
		}
		
		if(e.getSource() == ok) {
			cnt = 1;
			newNotice.dispose();
			con2.dispose();
			String currentProjPath = "";
			try {
				currentProjPath = new File(".").getCanonicalPath();
			}catch (IOException k) {
				k.printStackTrace();
			}
			
			ObjectOutputStream outputStream = null;
			
			try {
				outputStream = new ObjectOutputStream(new FileOutputStream(currentProjPath+ "/" + srcPath + "/" + packageName + "/" +outfileName));
				//outputStream.close();
				outputStream.writeObject(P1);
				outputStream.writeObject(P2);
				outputStream.writeObject(ch1);
				outputStream.writeObject(ch2);
				outputStream.writeObject(player1);
				outputStream.writeObject(player2);
				outputStream.close();
			}catch(IOException k) {
				k.printStackTrace();
			}
			
		}
		
		if(e.getSource() == no) {
			cnt = 2;
			newNotice.dispose();
			JOptionPane.showMessageDialog(null, "�ٸ� �̸��� �Է��� �ּ���.", "Notice", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void fileInput() throws ClassNotFoundException {
		Scanner keyboard = new Scanner(System.in);
		ObjectInputStream inputStream = null;
		String line = null;
		String fileName = null;
		//PrintWriter outputStream = null;
		
		String currentProjPath = "";
		try {
			currentProjPath = new File(".").getCanonicalPath();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
		inFilename = input.getText() + ".dat";
		
		File fileObj = null;
		
		boolean cnt = true;
		
			fileObj = new File(currentProjPath + "/" + srcPath + "/" + packageName + "/" + inFilename);
			if(fileObj.exists()) {
				try {
					cnt = false;
					check = true;
					con1.dispose();
					
					try {
						currentProjPath = new File(".").getCanonicalPath();
					}catch (IOException e) {
						e.printStackTrace();
					}
					
					inputStream = new ObjectInputStream(new FileInputStream(currentProjPath+ "/" + srcPath + "/" + packageName + "/" + inFilename));
					P1 = (Malll[])inputStream.readObject();
					P2 = (Malll[])inputStream.readObject();
					ch1 = (boolean[])inputStream.readObject();
					ch2 = (boolean[])inputStream.readObject();
					player1 = (boolean)inputStream.readObject();
					player2 = (boolean)inputStream.readObject();
					System.out.println(P1[0].getLocation());
					System.out.println(P2[0].getLocation());
					
					panPanel.setP1(P1.clone());
					panPanel.setP2(P2.clone());
					panPanel.setCh1(ch1.clone());
					panPanel.setCh2(ch2.clone());
					panPanel.setPlayer1(player1);
					panPanel.setPlayer2(player2);
					panPanel.setCongame();
					
				}catch(FileNotFoundException e) {
					e.printStackTrace();
					System.exit(0);
				}catch(IOException e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "������ �������� �ʽ��ϴ�. �ٸ� �̸��� �Է��� �ּ���.", "Notice", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void fileSave() {
		int WIDTHc = 300;
		int HEIGHTc = 150;
		Scanner keyboard = new Scanner(System.in);
		Scanner inputStream = null;
		String line = null;

		String currentProjPath = "";
		try {
			currentProjPath = new File(".").getCanonicalPath();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		newNotice = new JFrame();
		JPanel Nnotice = new JPanel();
		
		newNotice.setSize(WIDTHc,HEIGHTc);
		newNotice.setBackground(Color.WHITE);
		newNotice.setLayout(new FlowLayout());
		newNotice.setTitle("Notice");
		
		Nnotice.setLayout(new GridLayout(2,1));
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
		
		JLabel label = new JLabel("������ �����մϴ�. ����ڽ��ϱ�?");
		
		ok = new JButton("Ȯ��");
		ok.addActionListener(this);
		ok.setBackground(Color.white);
		btnPanel.add(ok);
		
		no = new JButton("���");
		no.addActionListener(this);
		no.setBackground(Color.white);
		btnPanel.add(no);
		
		Nnotice.add(label);
		Nnotice.add(btnPanel);
		
		newNotice.add(Nnotice);
		
		outfileName = input.getText() + ".dat";
		
		File fileObj = null;
		
			fileObj = new File(currentProjPath+ "/" + srcPath + "/" + packageName + "/" +outfileName);
			
			if(fileObj.exists()) {
				try {
					inputStream = new Scanner(new FileInputStream(currentProjPath+ "/" + srcPath + "/" + packageName + "/" +outfileName));
					newNotice.setVisible(true);
					if(cnt==1) {
						
					}

				}catch(FileNotFoundException e) {
					e.printStackTrace();
					System.exit(0);
				}catch(IOException e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
			else {
				con2.dispose();
				
				try {
					currentProjPath = new File(".").getCanonicalPath();
				}catch (IOException k) {
					k.printStackTrace();
				}
				
				ObjectOutputStream outputStream = null;
				
				try {
					outputStream = new ObjectOutputStream(new FileOutputStream(currentProjPath+ "/" + srcPath + "/" + packageName + "/" + outfileName));
					//outputStream.close();
					outputStream.writeObject(P1);
					outputStream.writeObject(P2);
					outputStream.writeObject(ch1);
					outputStream.writeObject(ch2);
					outputStream.writeObject(player1);
					outputStream.writeObject(player2);
					outputStream.close();
				}catch(IOException k) {
					k.printStackTrace();
				}
		}
	}
}