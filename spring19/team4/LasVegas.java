import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.IOException;

public class LasVegas extends JFrame implements ActionListener{

	public int money;
	public int chance1;
	public int chance2;
	public int chance3;
	
	ImageIcon box1;
	ImageIcon box2;
	ImageIcon box3;
	
	JLabel moneycheck;
	JLabel chancecheck;
	JLabel boxcheck1;
	JLabel boxcheck2;
	JLabel boxcheck3;
	JLabel pay1;
	JLabel pay2;
	JLabel pay3;
	
	JButton btn1;
	JButton btn2;
	JButton btn3;
	
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	class inner implements WindowListener{
		
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			dispose();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public LasVegas() {
		super("도박소");
		chance1 = 70;
		chance2 = 50;
		chance3 = 30;
		
		box1 = new ImageIcon("box1.jpg");
		box2 = new ImageIcon("box2.jpg");
		box3 = new ImageIcon("box3.jpg");
		
		
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel north = new JPanel();
		north.setLayout(new GridLayout(1,3,100,100));
		readFile();
		
		moneycheck = new JLabel("소지금 : " + money);
		north.add(moneycheck);
		
		JButton save = new JButton("저장하기");
		save.addActionListener(this);
		north.add(save);
		
		JButton backtoMenu=new JButton("메뉴로 돌아가기");
		backtoMenu.addActionListener(this);
		north.add(backtoMenu);
		
		JPanel center = new JPanel();
		center.setBackground(Color.WHITE);
		center.setLayout(new GridLayout(1,3,100,100));
		
		boxcheck1 = new JLabel("확률 : "+chance1+"% "+"0 ~ 500");
		boxcheck1.setIcon(box1);
		center.add(boxcheck1);
		
		boxcheck2 = new JLabel("확률 : "+chance2+"% "+"1000 ~ 5000");
		boxcheck2.setIcon(box2);
		center.add(boxcheck2);
		
		boxcheck3 = new JLabel("확률 : "+chance3+"% "+"10000 ~ 50000");
		boxcheck3.setIcon(box3);
		center.add(boxcheck3);
		
		JPanel south = new JPanel();
		south.setLayout(new GridLayout(2,3,100,100));
		
		btn1 = new JButton("초급도박");
		btn1.addActionListener(this);
		south.add(btn1);
		
		btn2 = new JButton("중급도박");
		btn2.addActionListener(this);
		south.add(btn2);
		
		btn3 = new JButton("고급도박");
		btn3.addActionListener(this);
		south.add(btn3);
		
		pay1 = new JLabel("초급도박료 : 100");
		pay2 = new JLabel("중급도박료 : 1000");
		pay3 = new JLabel("고급도박료 : 10000");
		
		south.add(pay1);
		south.add(pay2);
		south.add(pay3);
		
		add(north,BorderLayout.NORTH);
		add(center,BorderLayout.CENTER);
		add(south,BorderLayout.SOUTH);
	}
	
	public int randomchance() {
		Random r = new Random();
		int temp = r.nextInt(100);
		return temp;
	}
	
	public int randombox1() {
		Random r = new Random();
		int temp = r.nextInt(500);
		return temp;
	}
	
	public int randombox2() {
		Random r = new Random();
		int temp;
		while(true) {
			temp = r.nextInt(5000);
			if(temp >= 1000)
				break;
		}
		
		return temp;
	}
	
	public int randombox3() {
		Random r = new Random();
		int temp;
		while(true) {
			temp = r.nextInt(50000);
			if(temp >= 10000)
				break;
		}
		
		return temp;
	}
	
	public void readFile() {
		//dat파일에 게임정보 불러오기
		try {
			ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream("information.dat"));
			information a=new information();
			a=(information)inputStream.readObject();
			if(a!=null) {
				money=a.money;
			}
			
		}catch(FileNotFoundException e) {
			
		}catch(ClassNotFoundException e) {
			
		}catch(IOException e) {
			
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		String btn = e.getActionCommand();
		if(btn.equals("메뉴로 돌아가기")) {
			information a = new information();
			a.save2(money);
			Menu menu = new Menu();
			menu.setLocationRelativeTo(null);
			menu.setVisible(true);
			dispose();
		}
		else if(btn.equals("저장하기")) {
			information a = new information();
			a.save(money);
		}
		else if(btn.equals("초급도박")){
			if(money >= 100) {
				if(randomchance()>chance1) {
					money = money - 100;
					moneycheck.setText("소지금 : "+money);
					
					JFrame Alert = new JFrame("실패");
					JLabel alert = new JLabel("   실패 ㅠㅠ");
					Alert.add(alert);
					Alert.setLocationRelativeTo(null);
					Alert.setVisible(true);
					
					Alert.setSize(200,100);
					
					Alert.addWindowListener(new inner());
					
				}
				else {
					int temp = randombox1();
					money = money - 100 + temp;
					moneycheck.setText("소지금 : "+money);
					
					JFrame Alert = new JFrame("성공");
					JLabel alert = new JLabel("성공 !!   획득 금액 : "+ temp);
					Alert.add(alert);
					Alert.setLocationRelativeTo(null);
					Alert.setVisible(true);
					
					Alert.setSize(200,100);
					
					Alert.addWindowListener(new inner());
				}
			}
			else {
				JFrame Alert = new JFrame("경고");
				JLabel alert = new JLabel("돈이 부족합니다.");
				Alert.add(alert);
				Alert.setLocationRelativeTo(null);
				Alert.setVisible(true);
				
				Alert.setSize(200,100);
				
				Alert.addWindowListener(new inner());
			}
		}
		else if(btn.equals("중급도박")){
			if(money >= 1000) {
				if(randomchance()>chance2) {
					money = money - 1000;
					moneycheck.setText("소지금 : "+money);
					
					JFrame Alert = new JFrame("실패");
					JLabel alert = new JLabel("   실패 ㅠㅠ");
					Alert.add(alert);
					Alert.setLocationRelativeTo(null);
					Alert.setVisible(true);
					
					Alert.setSize(200,100);
					
					Alert.addWindowListener(new inner());
					
				}
				else {
					int temp = randombox2();
					money = money - 1000 + temp;
					moneycheck.setText("소지금 : "+money);
					
					JFrame Alert = new JFrame("성공");
					JLabel alert = new JLabel("성공 !!   획득 금액 : "+ temp);
					Alert.add(alert);
					Alert.setLocationRelativeTo(null);
					Alert.setVisible(true);
					
					Alert.setSize(200,100);
					
					Alert.addWindowListener(new inner());
				}
			}
			else {
				JFrame Alert = new JFrame("경고");
				JLabel alert = new JLabel("돈이 부족합니다.");
				Alert.add(alert);
				Alert.setLocationRelativeTo(null);
				Alert.setVisible(true);
				
				Alert.setSize(200,100);
				
				Alert.addWindowListener(new inner());
			}
		}
		else if(btn.equals("고급도박")){
			if(money >= 10000) {
				if(randomchance()>chance3) {
					money = money - 10000;
					moneycheck.setText("소지금 : "+money);
					
					JFrame Alert = new JFrame("실패");
					JLabel alert = new JLabel("   실패 ㅠㅠ");
					Alert.add(alert);
					Alert.setLocationRelativeTo(null);
					Alert.setVisible(true);
					
					Alert.setSize(200,100);
					
					Alert.addWindowListener(new inner());
				}
				else {
					int temp = randombox3();
					money = money - 10000 + temp;
					moneycheck.setText("소지금 : "+money);
					
					JFrame Alert = new JFrame("성공");
					JLabel alert = new JLabel("성공 !!   획득 금액 : "+ temp);
					Alert.add(alert);
					Alert.setLocationRelativeTo(null);
					Alert.setVisible(true);
					
					Alert.setSize(200,100);
					
					Alert.addWindowListener(new inner());
				}
			}
			else {
				JFrame Alert = new JFrame("경고");
				JLabel alert = new JLabel("돈이 부족합니다.");
				Alert.add(alert);
				Alert.setLocationRelativeTo(null);
				Alert.setVisible(true);
				
				Alert.setSize(200,100);
				
				Alert.addWindowListener(new inner());
			}
		}
	}
}
