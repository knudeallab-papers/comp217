import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

 class CheckOnExit implements WindowListener{
	public CheckOnExit() {
		// TODO Auto-generated constructor stub
	}
	
	public void windowOpened(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		ConfirmWindow checkers =new ConfirmWindow();
		checkers.setVisible(true);
	}
	
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
}

class ConfirmWindow extends JFrame implements ActionListener{
	public ConfirmWindow() {
		setSize(300,100);

		setLayout(new FlowLayout());
		
        JLabel message =new JLabel("종료하시겠습니까? ");
        JButton yesButton= new JButton("예 ");
        yesButton.setActionCommand("exit_yes");
        yesButton.addActionListener(this);
        JButton noButton= new JButton("아니요 ");
        noButton.setActionCommand("exit_no");
        noButton.addActionListener(this);
		
		add(message);
		add(yesButton);
		add(noButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCmd =e.getActionCommand();
		if(actionCmd.equals("exit_yes")) {
			PrintWriter outputStream = null;
			PrintWriter dateStream =null;
			String inputStr[] =new String[6];
		
			try {
				String fileName ="jango.txt";
				String dateFile = "date.txt";
				outputStream =new PrintWriter(new FileOutputStream(fileName));	
				dateStream = new PrintWriter(new FileOutputStream(dateFile));
			}
			catch(FileNotFoundException er){
				System.out.println("Error opening employee.txt");
				er.printStackTrace();
			} 
			outputStream.print(String.valueOf(StartRestaurant.jango));
			outputStream.close();
			dateStream.print(StartRestaurant.mTime);
			dateStream.close();
			System.exit(0);
		}
		else if(actionCmd.equals("exit_no"))
			dispose();
	}
}

class MagamError extends JFrame implements ActionListener{
	public MagamError() {
		setSize(300,100);
		setLayout(new FlowLayout());
		
        JLabel message =new JLabel("결제가 끝나지 않았습니다.");
        
		add(message);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
class Bye extends JFrame implements ActionListener{
	public Bye() {
		setSize(300,100);
		setLayout(new FlowLayout());
		
        JLabel message =new JLabel("종료하시겠습니까? ");
        JButton yesButton= new JButton("예 ");
        yesButton.setActionCommand("exit_yes");
        yesButton.addActionListener(this);
        JButton noButton= new JButton("아니요 ");
        noButton.setActionCommand("exit_no");
        noButton.addActionListener(this);
		
		add(message);
		add(yesButton);
		add(noButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCmd =e.getActionCommand();
		if(actionCmd.equals("exit_yes")) {
			PrintWriter outputStream = null;
			PrintWriter dateStream =null;
			String inputStr[] =new String[6];
		
			try {
				String fileName ="jango.txt";
				String dateFile = "date.txt";
				outputStream =new PrintWriter(new FileOutputStream(fileName));	
				dateStream = new PrintWriter(new FileOutputStream(dateFile));
			}
			catch(FileNotFoundException er){
				System.out.println("Error opening employee.txt");
				er.printStackTrace();
			} 
			outputStream.print(String.valueOf(StartRestaurant.jango));
			outputStream.close();
			dateStream.print(StartRestaurant.mTime);
			dateStream.close();
			System.exit(0);
		}
		else if(actionCmd.equals("exit_no"))
			dispose();
	}
}

public class StartRestaurant extends JFrame implements ActionListener{
	public static final int WIDTH =1100;
	public static final int HEIGHT =600;
	
	static JLabel todayIncome;
	static JLabel totalMoney;
	
	static int mechul;
	static int jango;
	
	JPanel employeePanel,vipPanel,tablePanel,storagePanel,menusPanel,datePanel;
	static String mTime;
	JLabel date;
	public StartRestaurant()  {
		
		super("Restaurant Program");
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new CheckOnExit());
		
		File dateFile =new File("date.txt");
		if(dateFile.exists()) {
			Scanner sc=null;
			try {
				 sc= new Scanner(new FileInputStream("date.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			mTime = sc.nextLine();
		}
		
		else {
	    // 날짜, 마감	버튼 수정 
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy.M.d", Locale.KOREA );
		Date currentTime = new Date ();
		mTime = mSimpleDateFormat.format ( currentTime );
		System.out.println ( mTime );
		}

		date=new JLabel(mTime);
		//마감
		JButton endButton= new JButton("마감 ");
		endButton.setSize(50, 30);
		endButton.setActionCommand("finish");
		endButton.addActionListener(this);
		datePanel =new JPanel();
		datePanel.setLayout(new FlowLayout());
		datePanel.add(date);
		datePanel.add(endButton);
		
		//매출 , 잔고, 종료 버튼
		mechul=0;
		todayIncome = new JLabel();
		todayIncome.setText("오늘 매출 :"+mechul);
		totalMoney= new JLabel();
	
		//잔고 파일로부터 얻어오기 
		File jangoFile = new File("jango.txt");
		if(jangoFile.exists()) {
			Scanner jangoinputStream= null;
			try {
				jangoinputStream= new Scanner(new FileInputStream("jango.txt"));
			}
			catch(FileNotFoundException er) {
				er.printStackTrace();
			}
			
			String money =jangoinputStream.nextLine();
			jango = Integer.valueOf(money);
			totalMoney.setText("전체 잔고 :"+jango);
		}
		else {
			jango =0;
			totalMoney.setText("전체 잔고 :0");
		}
		
		
		//종료
		JButton exitButton =new JButton("종료 ");
		exitButton.setActionCommand("exit");
		exitButton.setSize(50,50);
		exitButton.addActionListener(this);
		JPanel moneyPanel= new JPanel();
		moneyPanel.setLayout(new GridLayout(3,1));
		moneyPanel.add(todayIncome);
		moneyPanel.add(totalMoney);
		moneyPanel.add(exitButton);
		moneyPanel.setSize(100,50);
		
		//메뉴 탭 //다른 클래스의 내용을 추가시켜줄 경우 그 쪽의세부요소를 직접 넣어주자.
		JPanel tabPanel=new JPanel();
		TapMenu menu=new TapMenu();
		JTabbedPane tab=menu.getTab();
		
		tabPanel.setLayout(new BorderLayout());
		tabPanel.add(tab);
		tabPanel.setSize(500, 500);
		add(tabPanel,BorderLayout.CENTER);
		
		JPanel topPanel= new JPanel();
		topPanel.setLayout(new GridLayout(1, 2));
		topPanel.add(datePanel);
		topPanel.add(moneyPanel);
		add(topPanel,BorderLayout.NORTH);  
        this.pack();
         this.setResizable(false);
        
		/*JPanel menuPanel=new JPanel();
		menuPanel.setLayout(new GridLayout(1, 5));
		menuPanel.setBackground(Color.LIGHT_GRAY);*/
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String btnStr = e.getActionCommand();
		
		if(btnStr.equalsIgnoreCase("exit")) {
			Bye exBye=new Bye();
			exBye.setVisible(true);
		}
		
		//마감눌렀을때  
		else if(btnStr.equalsIgnoreCase("finish")) {
			int kk=0;
			for( kk=0;kk<8;kk++) {
				if(Table.collect[kk].color==2) 
					break;
			}
			if(kk !=8) {
				MagamError me = new MagamError();
				me.setVisible(true);
				return;
			}
			
			//날짜 넘기
			StringTokenizer st = new StringTokenizer(mTime, ".");
			String year= st.nextToken();
		    String month= st.nextToken();
		    String day =st.nextToken();
	
		    if(month.equals("12")) {
		    	if(day.equals("31")) {
			    	int y = Integer.valueOf(year);
			    	mTime = String.valueOf(y+1)+".1.1";
		    	}
		    	
		    	else {
		    		int d =Integer.valueOf(day)+1;
		    		mTime = year+"."+month+"."+d;
		    	}
		    }
		    else if(month.equalsIgnoreCase("2") ) {
		    	if(day.equals("28"))
		    		mTime= year+".3.1";
		    	else {
		    		int d= Integer.valueOf(day);
		    		mTime = year+".2."+String.valueOf(d+1);
		    	}
		    }
		    else if(month.equalsIgnoreCase("4")||month.equalsIgnoreCase("6")||month.equals("9")||month.equals("11")) {
		    	if(day.equals("30")) {
		    		int mon =Integer.valueOf(month)+1;
		    		mTime=year+"."+String.valueOf(mon)+".1";
		    	}
		    	
		    	else {
		    		int d = Integer.valueOf(day)+1;
		    		//day =String.valueOf(d);
		    		mTime=year+"."+month+"."+d;
		    	}
		    }
		    else {
		    	if(day.equals("31")) {
		    		int mon =Integer.valueOf(month)+1;
		    		mTime=year+"."+String.valueOf(mon)+".1";
		    	}
		    	else {
		    		int d= Integer.valueOf(day)+1;
		    		mTime =year+"."+month+"."+String.valueOf(d);
		    	}
		    }
		    date.setText(mTime);
		    
		    
		    //매출을 잔고에 더하기
		   StartRestaurant.jango += StartRestaurant.mechul;
		   StartRestaurant.mechul= 0;
		   totalMoney.setText("전체 잔고 :"+jango);
		   todayIncome.setText("오늘 매출: 0");
		   
		   // 직원 급여 잔고에서 뺴기  
		   if(month.equals("4") || month.equals("6")||month.equals("9")||month.equals("11")) {
			   if(day.equals("30")) {
				   StringTokenizer st2;
				   File file =new File("employee.txt");
				   
					if(file.exists() ) {
						String line;
						Scanner inputStream= null;
						
						try {
							inputStream= new Scanner(new FileInputStream("employee.txt"));
						}
						catch(FileNotFoundException er) {
							er.printStackTrace();
						}
						
						while(inputStream.hasNextLine()) {
							line = inputStream.nextLine();
							
							st2= new StringTokenizer(line, " ");
							st2.nextToken();
							st2.nextToken();
							String pay= st2.nextToken();
							jango-= Integer.valueOf(pay);
						}
					}
					totalMoney.setText("전체 잔고:"+jango);
			   }
		   }
		   
		   else if(month.equals("2")) {
			   if(day.equals("28")) {
				   StringTokenizer st2;
				   File file =new File("employee.txt");
				   
					if(file.exists() ) {
						String line;
						Scanner inputStream= null;
						
						try {
							inputStream= new Scanner(new FileInputStream("employee.txt"));
						}
						catch(FileNotFoundException er) {
							er.printStackTrace();
						}
						
						while(inputStream.hasNextLine()) {
							line = inputStream.nextLine();
							st2= new StringTokenizer(line, " ");
							st2.nextToken();
							st2.nextToken();
							String pay= st2.nextToken();
							jango-= Integer.valueOf(pay);
						}
					}
					totalMoney.setText("전체 잔고:"+jango);
			   }
		   }
		   else {
			   if(day.equals("31")) {
				   StringTokenizer st2;
				   File file =new File("employee.txt");
				   
					if(file.exists() ) {
						String line;
						Scanner inputStream= null;
						
						try {
							inputStream= new Scanner(new FileInputStream("employee.txt"));
						}
						catch(FileNotFoundException er) {
							er.printStackTrace();
						}
						
						while(inputStream.hasNextLine()) {
							line = inputStream.nextLine();
							
							st2= new StringTokenizer(line, " ");
							st2.nextToken();
							st2.nextToken();
							String pay= st2.nextToken();
							jango-= Integer.valueOf(pay);
						}
					}
					totalMoney.setText("전체 잔고:"+jango);
			   }
		   }
		   
		 //주문하기 
			File stoFile =new File("storage.txt");
			if(stoFile.exists()) {
				Scanner inputStream=null;
				String line;
				StringTokenizer st2;
				try{
					 inputStream = new Scanner(new FileInputStream("storage.txt"));
				}
				catch(FileNotFoundException er) {
					er.printStackTrace();
				}
				
				PrintWriter outputStream = null;
				
				while(inputStream.hasNextLine()) {
					line = inputStream.nextLine();
					st2= new StringTokenizer(line, " ");
					String num =st2.nextToken(); 
					String name= st2.nextToken();
					int rest= Integer.valueOf(st2.nextToken());
					int amount = Integer.valueOf(st2.nextToken());
					int price = Integer.valueOf(st2.nextToken());
					
					jango -= amount*price;
					outputStream = null;
					
					try {
						String fileName ="storage2.txt";
						outputStream =new PrintWriter(new FileOutputStream(fileName,true));	
					}
							
					catch(FileNotFoundException er){
						System.out.println("Error opening storage.txt");
						er.printStackTrace();
					}
				    
				    outputStream.print(num+" ");
				    outputStream.print(name+" ");
				    outputStream.print((rest+amount)+" ");
				    outputStream.print("0 ");
				    outputStream.println(price);
				    outputStream.close();
				}
				
				outputStream.close();
				File file =new File("storage.txt");
				file.delete();
				file =new File("storage2.txt");
				File dest =new File("storage.txt");
				file.renameTo(dest);   
				totalMoney.setText("전체잔고: "+jango);
				
				
				File file3 =new File("storage.txt");
				if(file3.exists()) {
					String line3;
					String inputStr3[] =new String[5];
					StringTokenizer st3;
					Scanner inputStream3= null;
					
					try {
						inputStream3= new Scanner(new FileInputStream("storage.txt"));
					}
					catch(FileNotFoundException er) {
						er.printStackTrace();
					}
					
					
					Storage.model.setRowCount(0);
					while(inputStream3.hasNextLine()) {
						int i=0;
						line3 = inputStream3.nextLine();
						st3= new StringTokenizer(line3, " ");
						while(st3.hasMoreTokens()) {
							inputStr3[i++]=st3.nextToken();
						}
						Storage.model.addRow(inputStr3);
					}
				
				}
	
			}
		}
	}
}
		/*if(btnStr.equalsIgnoreCase("Employee")) {
			add(employeePanel,BorderLayout.SOUTH);
		}
		else if(btnStr.equalsIgnoreCase("VIPs"))
			vipPanel.setVisible(true);
		//if(btsStr.equals("TableButton"))*/
		
	