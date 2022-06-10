import javax.swing.*;




import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.IOException;
public class Reinforce extends JFrame implements ActionListener{
	
	public int money;
	public int chance;
	public int level;
	public String name;
	
	ImageIcon shield;
	JLabel moneycheck;
	JLabel levelcheck;
	JLabel chancecheck;
	
	JLabel pay;
	JLabel sell_pay;
	
	JButton rein;
	JButton sell;
	
	public static final int WIDTH=800;
	public static final int HEIGHT=500;
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
	public Reinforce() {
		
		super("방패 강화하기");
		money=10;
		chance=100;
		level=1;
		name="낡은 방패";
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel north=new JPanel();
		north.setLayout(new GridLayout(1,3,100,100));
		readFile();
		
		moneycheck=new JLabel("소지금 : "+money);
		north.add(moneycheck);
		
		JButton save=new JButton("저장하기");
		save.addActionListener(this);
		north.add(save);
		
		JButton backtoMenu=new JButton("메뉴로 돌아가기");
		backtoMenu.addActionListener(this);
		north.add(backtoMenu);
		
		JPanel center=new JPanel();
		center.setBackground(Color.WHITE);
		center.setLayout(new BorderLayout());
		levelcheck=new JLabel("+"+level+"  "+get_name(),JLabel.CENTER);
		levelcheck.setFont(new Font("Serif",Font.BOLD,40));
		center.add(levelcheck,BorderLayout.NORTH);
		shield=new ImageIcon(get_image(level));
		chancecheck=new JLabel("확률: "+chance+"%");
		chancecheck.setIcon(shield);
		chancecheck.setHorizontalAlignment(JLabel.CENTER);
		center.add(chancecheck,BorderLayout.CENTER);
		
		JPanel south=new JPanel();
		south.setLayout(new GridLayout(2,2,100,100));
		rein=new JButton("강화하기");
		rein.addActionListener(this);
		south.add(rein);
		
		sell=new JButton("판매하기");
		sell.addActionListener(this);
		south.add(sell);
		
		pay=new JLabel("강화 비용 :"+get_pay());
		south.add(pay);
		
		sell_pay=new JLabel("판매 비용 :"+get_sell_pay());
		south.add(sell_pay);
		
		add(north,BorderLayout.NORTH);
		add(center,BorderLayout.CENTER);
		add(south,BorderLayout.SOUTH);
	}
	class StarForce extends JFrame{
		public static final int WIDTH=400;
		public static final int HEIGHT=300;
		public int k;
		public StarForce() {
			this.setTitle("Star Force");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(WIDTH,HEIGHT);
			setLayout(new BorderLayout());
			
			JLabel title=new JLabel("별을 정확한 곳에 멈추세요.성공시 확률 +5%");
			title.setFont(new Font("Serif",Font.BOLD,15));
			add(title,BorderLayout.NORTH);
			k=0;
			GamePanel p =new GamePanel();
			add(p,BorderLayout.CENTER);
			
			JPanel btnPanel=new JPanel();
			JButton stop=new JButton("Stop");
			stop.addActionListener(p);
			btnPanel.add(stop);
			add(btnPanel,BorderLayout.SOUTH);
			
			
			
			this.setVisible(true);
			this.setResizable(true);
		}
		
		class GamePanel extends JPanel implements ActionListener{
			TargetThread targetThread;
			JLabel target;
			JLabel standard;
			
			GamePanel(){
				setLayout(null);
				this.setBackground(Color.WHITE);
				ImageIcon img=new ImageIcon("star.jpeg");
				Image temp=img.getImage();
				temp=temp.getScaledInstance(50, 50,Image.SCALE_SMOOTH);
				img=new ImageIcon(temp);
				target=new JLabel(img);
				target.setSize(img.getIconWidth(), img.getIconHeight());
				ImageIcon img2=new ImageIcon("standard.png");
				standard=new JLabel(img2);
				add(target);
				add(standard);
				standard.setBounds(0, 105, 400, 50);
				
				
				
				target.setBounds(0,HEIGHT/3,50,50);
				targetThread=new TargetThread(target);
				targetThread.start();
				
			}
			class inner1 implements WindowListener{

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
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String cmd=e.getActionCommand();
				if(cmd.equals("Stop")) {
					targetThread.interrupt();
					if(hit()) {
						JFrame hi=new JFrame();
						hi.setTitle("성공");
					
						JLabel text=new JLabel("5% 확률 상승");
						hi.addWindowListener(new inner1());
						hi.setSize(200, 100);
						hi.setLocationRelativeTo(null);
						hi.add(text);
						hi.setVisible(true);
						k=5;
					}else {
						k=0;
						JFrame hi=new JFrame();
						hi.setTitle("실패");
						
						JLabel text=new JLabel("확률 상승 실패");
						hi.setSize(200, 100);
						hi.setLocationRelativeTo(null);
						hi.addWindowListener(new inner1());
						hi.add(text);
						hi.setVisible(true);
					}
					dispose();
				}
			}
			public boolean hit() {
				int x=target.getX();
				int y=target.getY();
				System.out.println(x);
				if(x>=100&&x<=250)return true;
				else return false;
			}
			
		}
		class TargetThread extends Thread{
			JLabel target;
			TargetThread(JLabel target){
				this.target=target;
				target.setLocation(0,HEIGHT/3);
			}
			public void run() {
				while(true) {
					int x=target.getX()+5;
					int y=target.getY();
					
					if(x>WIDTH)target.setLocation(0,HEIGHT/3);
					else
						target.setLocation(x, y);
					try {
						sleep(20);
					}
					catch(Exception e) {
						target.setLocation(0, HEIGHT/3);
						try {
							sleep(500);
						}catch(Exception e2) {}
					}
				}
			}
		}
	}
	public boolean random() {
		StarForce a=new StarForce();
		a.setVisible(true);
		a.setLocationRelativeTo(null);
		Random r=new Random();
		
		
		int temp=r.nextInt(100);
		if(temp>=5) {
			temp-=a.k;
		}
		
		
		switch(level) {
		case 1:
			if(temp<100)return true;
			break;
		case 2:
			if(temp<88)return true;
			break;
		case 3:
			if(temp<73)return true;
			break;
		case 4:
			if(temp<50)return true;
			break;
		case 5:
			if(temp<25)return true;
			break;
		case 6:
			if(temp<18)return true;
			break;
		default:
			return false;
		}
		return false;
	}
	public int get_sell_pay() {
		int pay=0;
		switch(level) {
		case 1:pay=0;break;
		case 2:pay=45;break;
		case 3:pay=180;break;
		case 4:pay=525;break;
		case 5:pay=50960;break;
		case 6:pay=297010;break;
		default:
			pay=-1;
		}
		return pay;
	}
	public int get_pay() {
		int pay=0;
		switch(level) {
		case 1:pay=1;break;
		case 2:pay=5;break;
		case 3:pay=20;break;
		case 4:pay=45;break;
		case 5:pay=1136;break;
		case 6:pay=1328;break;
		default:
			pay=-1;
		}
		return pay;
	}
	public static String get_image(int level) {
		String image="woodshield.png";
		switch(level) {
		case 1:image="woodshield.png";break;
		case 2:image="2.jpeg";break;
		case 3:image="3.jpeg";break;
		case 4:image="4.jpeg";break;
		case 5:image="5.jpeg";break;
		case 6:image="6.jpeg";break;
		case 7:image="Done.jpeg";break;
		default:
			image="woodshield.png";
		}
		return image;
	}
	public int get_chance() {
		int temp;
		switch(level) {
		case 1:temp=100;break;
		case 2:temp=88;break;
		case 3:temp=73;break;
		case 4:temp=50;break;
		case 5:temp=25;break;
		case 6:temp=18;break;
		default:
			temp=-1;
		}
		return temp;
	}
	public String get_name() {
		String image="낡은 방패";
		switch(level) {
		case 1:image="낡은 방패";break;
		case 2:image="방패";break;
		case 3:image="조금 좋은 방패";break;
		case 4:image="좋은 방패";break;
		case 5:image="엄청 좋은 방패";break;
		case 6:image="티타늄 방패";break;
		case 7:image="Done";break;
		default:
			image="낡은 방패";
		}
		return image;
	}
	public void Fail() {
		
		shield=new ImageIcon("fail.jpeg");
		chancecheck.setIcon(shield);
		levelcheck.setText("재도전 하시겠습니까?");
		chancecheck.setText("재도전 비용: "+get_retry());
		sell_pay.setText("");
		rein.setText("재도전하기");
		sell.setText("처음부터다시하기");
	}
	public int get_retry() {
		int temp=0;
		temp=get_sell_pay();
		temp*=2;
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
		String btn=e.getActionCommand();
		if(btn.equals("메뉴로 돌아가기")) {
			information a=new information();
			a.save2(money);
			Menu menu=new Menu();
			menu.setLocationRelativeTo(null);
			menu.setVisible(true);
			dispose();
			
		}
		else if(btn.equals("저장하기")) {
			information a=new information();
			a.save(money);
		}
		else if(btn.equals("판매하기")) {
			money+=get_sell_pay();
			level=1;
			shield=new ImageIcon(get_image(level));
			chancecheck.setIcon(shield);
			moneycheck.setText("소지금 : "+money);
			levelcheck.setText("+"+level+"  "+get_name());
			chancecheck.setText("확률: "+chance+"%");
			pay.setText("강화 비용 :"+get_pay());
			sell_pay.setText("판매 비용 :"+get_sell_pay());
			
		}
		else if(btn.equals("재도전하기")) {
			if(money>=get_retry()) {
				money-=get_retry();
				shield=new ImageIcon(get_image(level));
				moneycheck.setText("소지금 : "+money);
				levelcheck.setText("+"+level+"  "+get_name());
				chancecheck.setIcon(shield);
				chancecheck.setText("확률: "+get_chance()+"%");
				pay.setText("강화 비용 :"+get_pay());
				sell_pay.setText("판매 비용 :"+get_sell_pay());
				sell.setText("판매하기");
				rein.setText("강화하기");
			}else {
				JFrame Alert=new JFrame("경고");
				JLabel alert=new JLabel("돈이 부족합니다.");
				Alert.add(alert);
				Alert.setLocationRelativeTo(null);
				Alert.setVisible(true);
				
				Alert.setSize(200,100);
				
				Alert.addWindowListener(new inner());
			}
				
		}
		else if(btn.equals("처음부터다시하기")) {
			level=1;
			shield=new ImageIcon(get_image(level));
			moneycheck.setText("소지금 : "+money);
			levelcheck.setText("+"+level+"  "+get_name());
			chancecheck.setIcon(shield);
			chancecheck.setText("확률: "+get_chance()+"%");
			pay.setText("강화 비용 :"+get_pay());
			sell_pay.setText("판매 비용 :"+get_sell_pay());
			sell.setText("판매하기");
			rein.setText("강화하기");
		}
		else if(btn.equals("강화하기")) {
			if(money>=get_pay()) {
				
				
				money-=get_pay();
				
				if(random()) {
					
					level++;
					shield=new ImageIcon(get_image(level));
					moneycheck.setText("소지금 : "+money);
					levelcheck.setText("+"+level+"  "+get_name());
					if(level>=7) {
						chancecheck.setText("");
						pay.setText("");
						sell_pay.setText("");
					}else {
						chancecheck.setIcon(shield);
						chancecheck.setText("확률: "+get_chance()+"%");
						pay.setText("강화 비용 :"+get_pay());
						sell_pay.setText("판매 비용 :"+get_sell_pay());
					}
					
				}else {
					
					moneycheck.setText("소지금 : "+money);
					Fail();
				}
				
				
			}
			
		}
	}
	
}

