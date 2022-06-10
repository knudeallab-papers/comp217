package GH;

import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.*;
import java.util.TimerTask;
import java.util.Timer;

import java.awt.*;
import java.util.Scanner;

public class Main_HOBAN extends JFrame implements ActionListener, Serializable{
    JScrollPane scrollPane;
    ImageIcon icon;
    ImageIcon happy_icon;
	static int happy_ = 50;
	int diningcnt = 0;
	
	static String user_name = "test name";
	static JTextField input_name;
	
    public Main_HOBAN() {
    	super("호반우를 키워라");
    	setSize(740, 430);
    	setLocationRelativeTo(null);
    	setResizable(false);
    	
        icon = new ImageIcon("main.jpeg");//배경 경로

        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, null);

                setOpaque(false);
                super.paintComponent(g);
            }
        };
        
    	panel.setLayout(null);

		happy_image();
		JButton happiness = new JButton(happy_icon);
		happiness.setBorderPainted(false);
		happiness.setContentAreaFilled(false);
		happiness.setPreferredSize(new Dimension(200, 200));
		happiness.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Character_status();
				happy_image();
				happiness.setIcon(happy_icon);
			}
		});

		happiness.setBounds(200, 150, 200, 200);
		panel.add(happiness);

    	JButton library_btn = new JButton("도서관");
    	library_btn.setFocusPainted(false);
    	library_btn.setBounds(400, 150, 80, 30);
    	library_btn.addActionListener(this);
    	
    	JButton classroom_btn = new JButton("강의실");
    	classroom_btn.setFocusPainted(false);
    	classroom_btn.setBounds(200, 100, 80, 30);
    	classroom_btn.addActionListener(this);
    	
    	JButton club_btn = new JButton("동아리");
    	club_btn.setFocusPainted(false);
    	club_btn.setBounds(420, 300, 80, 30);
    	club_btn.addActionListener(this);
    	
    	JButton gym_btn = new JButton("체육관");
    	gym_btn.setFocusPainted(false);
    	gym_btn.setBounds(100, 300, 80, 30);
    	gym_btn.addActionListener(this);
    	
    	JButton restaurant_btn = new JButton("식당");
    	restaurant_btn.setFocusPainted(false);
    	restaurant_btn.setBounds(600, 330, 80, 30);
    	restaurant_btn.addActionListener(this);
    	
    	panel.add(gym_btn);
    	panel.add(library_btn);
    	panel.add(club_btn);
    	panel.add(classroom_btn);
    	panel.add(restaurant_btn);
    	
        scrollPane = new JScrollPane(panel);
        setContentPane(scrollPane);
       
        setVisible(true);
        
    }
	public static void input_user(){
	}
	public void happy_image(){
		if(happy_>=90){
			happy_icon = new ImageIcon("04-사랑_A.png");
		}
		else if(happy_<90 && happy_>=70){
			happy_icon = new ImageIcon("04-사랑_B.png");
		}
		else if(happy_<70 && happy_>=50){
			happy_icon = new ImageIcon("03-자신감.png");
		}
		else{
			happy_icon = new ImageIcon("basic.png");

		}
		happy_icon=imageSetSize(happy_icon, 200, 200);
	}
	ImageIcon imageSetSize(ImageIcon icon, int i, int j) {
		Image ximg = icon.getImage(); 
		Image yimg = ximg.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg);
		return xyimg;
	}
    public void actionPerformed(ActionEvent e) {
		try{
			assumingAction(e);
			
		}catch(NumberFormatException e2) {
			
		}
	}
    private void assumingAction(ActionEvent e) {
		
    	String actionCommand = e.getActionCommand();
    	int a = 1;
		if(actionCommand.equals("도서관")) {
			Library_page lib = new Library_page();
		}
		else if(actionCommand.equals("강의실")) {
			Classroom_page clas = new Classroom_page();
			
		}
		else if(actionCommand.equals("동아리")) {
			Club_page club = new Club_page();
		}
		else if(actionCommand.equals("체육관")) {
			Gym_page gym = new Gym_page();
		}
		else if(actionCommand.equals("식당")) {
			Dining_page dini = new Dining_page();
			diningcnt++;
			if(diningcnt == 10) {
				Ending_Cow ce = new Ending_Cow();
				ce.setVisible(true);
				dini.setVisible(false);
				setVisible(false);
				}
			else
				dini.setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null, "error",
	                "Fatal Error", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
		
	}
	public static void main(String[] args) {
		input_user();
		JFrame user = new JFrame();
		user.setBounds(100,100,420,100);
		user.setTitle("이름을 입력해주세요.");
		user.setLayout(null);
		user.setLocationRelativeTo(null);
		user.setDefaultCloseOperation(EXIT_ON_CLOSE);

		Scanner keyboard = new Scanner(System.in);

		JPanel user_panel = new JPanel(null);
		user_panel.setBounds(50,10,300, 40);
		JLabel user_name_lable = new JLabel("User Name : ");
		user_name_lable.setBounds(0,0,80, 50);
		user_panel.add(user_name_lable);

		input_name = new JTextField(10);
		input_name.setBounds(73, 10, 200, 31);
		user_panel.add(input_name);

		TimerTask t_task = new TimerTask() {
			@Override
			public void run() {
				;
			}
		};
		Timer t_timer=new Timer();

		JButton name_assign = new JButton("결정!");
		name_assign.setBounds(324, 20, 70, 31);
		name_assign.setFocusPainted(false);
		name_assign.addActionListener(event->{
			user_name = input_name.getText();
			try {
				
				ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("username.dat"));
				outputStream.writeUTF(user_name);
				outputStream.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			t_timer.schedule(t_task, 1000);
			user.setVisible(false);
			
			Regis_page rp = new Regis_page();
			rp.setVisible(true);
			
		});
		
		

		user.add(name_assign);

		user.add(user_panel);
		
		Main_HOBAN frame = new Main_HOBAN();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		user.setVisible(true);
    }
}
