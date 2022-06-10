import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.sound.sampled.AudioInputStream;


public class TableDemo extends JFrame implements ActionListener, MouseListener{
	
	public static final int MEMBERS_NUM = 10;
	public static final int MENU_NUM = 10;
	public static final int ASSET_NUM = 10;
	public static final int ORDER_NUM = 10;
	public static final int WORKER_NUM = 10;
	
	public static JOptionPane errorPane;
	
	public static Members[] MemberAry = new Members[MEMBERS_NUM];
	public static Table[] TableAry = new Table[8];
	public static Menu[] MenuAry = new Menu[MENU_NUM];
	public static Asset[] AssetAry = new Asset[ASSET_NUM];
	public static Worker[] WorkerAry = new Worker[WORKER_NUM];
	private Font fontObj = new Font("SansSerif", Font.PLAIN, 22);
	
	public static int EntireMoney = 0; //총 수익 
	public static int TodayMoney = 0; //&&오늘의수익 
	public static int TableNow = 0;//테이블 수 
	
	public static File Clap;
    Clip clip;
	public static int PlayingMusic = 0;//노래 재생
	
	public int WIDTH = 1040;
	public int HEIGHT = 860;
	
	public static final String tapBtn1 = "테이블";
	public static final String tapBtn2 = "회원";
	public static final String tapBtn3 = "메뉴";
	public static final String tapBtn4 = "창고";
	public static final String tapBtn5 = "직원";
	
	//0.
	public static JPanel NorthPanel;
	public static JPanel Timetable;
	public static JPanel tapPanel;
	public static JPanel contentPanel;
	
	//1. table UI
	public static JPanel tablePanel;
	public static JPanel tableInfoPanel;
	public static JPanel tableInfoPanel_1;
	public static JPanel tableInfoPanel_2;
	
	public static JButton[] tableSection = new JButton[8];
	public static JButton tableAdd;
	public static JButton tableEnd;
	public static JButton tableconfirm;
	
	public static JTable tableTable;
	public static String[] tableTable_head = {"메뉴", "가격"};
	public static String[][] tableTable_content = new String[MENU_NUM][2];

	public static JList<String> tableList;
	//2. member UI
	public static JPanel memberPanel;
	public static JPanel southMemberPanel;
	
	public static JButton membRevise;
	public static JButton membAdd;
	public static JButton membDelete;
	
	public static JTable membTable;
	
	public static String[] membTable_head = {"회원번호", "등급", "이름", "마일리지", "연락처"};
	public static String[][] membTable_content = new String[MEMBERS_NUM][5];

	
	//3. Menu UI
	public static JPanel menuPanel;
	public static JPanel menuPanel_N;
	public static JPanel menuPanel_S;
	public static JPanel menuInfoPanel;
	public static JPanel assetAsk;
	
	public static int selectedA;
	public static JButton[] AddAsset;
	public static JButton[] menuSection = new JButton[10];
	public static JButton menuAdd;
	public static JButton menuRevise;
	public static JButton menuDelete;
	public static JTextArea whichChoice;
	public static Asset[] assetInmenu;
	public static int assetIn;
	
	//4. Asset UI
	public static JPanel assetPanel;
	public static JPanel assetPanel_S;
	public static JPanel assetInfoPanel;
	
	public static JButton assetAdd;
	public static JButton assetDelete;
	public static JButton assetOrder;
	public static JButton assetNotOrder;
	
	public static JList<String> assetList;
	
	public static JTable assetTable;
	
	public static String[] assetTable_head = {"이름", "재고", "주문", "가격"};
	public static String[][] assetTable_content = new String[ASSET_NUM][4];

	//5. Worker UI
	public static JPanel workerPanel;
	public static JPanel southWorkerPanel;
	
	public static JButton workRevise;
	public static JButton workAdd;
	public static JButton workDelete;
	
	public static JTable workTable;
	
	public static String[] workTable_head = {"번호", "이름", "급여", "직급", "입사일", "연락처"};
	public static String[][] workTable_content = new String[MEMBERS_NUM][6];
	
	public static JTextField textfield1;
	public static CDate now;
	public static void main(String[] args) throws FileNotFoundException {
		String path = TableDemo.class.getResource(".").getPath().toString()+"Money"+".txt";
		File money = new File(path);
		
		Scanner fin = new Scanner(money);
		EntireMoney = fin.nextInt();
		fin.close();
		
		Calendar c = Calendar.getInstance();
		now = new CDate(c.get(c.YEAR), c.get(c.MONTH)+1, c.get(c.DATE) );
		
		TableDemo gui = new TableDemo();
		Asset.readfile();
		Members.readfile();
		Menu.readfile();
		Worker.readfile();
		gui.setVisible(true);
	}

	public void tapGUI() {
		NorthPanel.removeAll();
		
		// 왼쪽
		Timetable = new JPanel();
		Timetable.setLayout(new FlowLayout());
		JLabel today = new JLabel();
		today.setText(now.toString());
		Timetable.add(today);
		
		NorthPanel.add(Timetable, BorderLayout.WEST);
		
		// 오른쪽
		JPanel End = new JPanel();
		End.setLayout(new FlowLayout());
		JLabel profit = new JLabel();
		profit.setText("오늘 매출: " + TodayMoney + "      전체 잔고: " + EntireMoney);
		End.add(profit);
		
		JButton end = new JButton("마감");
		end.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ending();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		end.setPreferredSize(new Dimension(120, 45));
		End.add(end);
		NorthPanel.add(End, BorderLayout.EAST);
		
		//&&
		// 가운데
		JPanel TitlePanel = new JPanel();
		TitlePanel.setLayout(new FlowLayout());
		
		Font Subjectfont = new Font("SansSerif", Font.PLAIN, 30);
		JLabel NamePanel = new JLabel();
		NamePanel.setText("                      ઊ Burito Express ઊ   ");
		NamePanel.setFont(Subjectfont);
		TitlePanel.add(NamePanel);
		
		JButton PlayMusic = new JButton("▶");
		PlayMusic.setPreferredSize(new Dimension(30, 30));
		PlayMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				if(PlayingMusic == 0)
				{
					Clap =new File("test.wav");

				try {
						AudioInputStream stream = AudioSystem.getAudioInputStream(Clap);
						clip = AudioSystem.getClip();
						clip.open(stream);
				         clip.start();
				         PlayingMusic = 1;
				         PlayMusic.setBackground(Color.blue);
				         PlayMusic.setText("▥");
				         UpdatecontentPanel();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else
				{
			         clip.stop();
			         PlayMusic.setBackground(Color.red);
			         PlayingMusic = 0;
			         PlayMusic.setText("▶");
			         UpdatecontentPanel();

				}
			}
		});
		TitlePanel.add(PlayMusic);
		
		NorthPanel.add(TitlePanel, BorderLayout.CENTER);

		// TAPs panel 아래
		tapPanel = new JPanel();
		tapPanel.setLayout(new FlowLayout());
		
		JButton tapTable = new JButton(tapBtn1);
		tapTable.addActionListener(this);
		tapTable.setLocation(10, 10);
		tapTable.setPreferredSize(new Dimension(120, 45));
		tapPanel.add(tapTable);
		
		JButton tapMember = new JButton(tapBtn2);
		tapMember.addActionListener(this);
		tapMember.setPreferredSize(new Dimension(120, 45));
		tapPanel.add(tapMember);
		
		JButton tapMenu = new JButton(tapBtn3);
		tapMenu.addActionListener(this);
		tapMenu.setPreferredSize(new Dimension(120, 45));
		tapPanel.add(tapMenu);
		
		JButton tapAsset = new JButton(tapBtn4);
		tapAsset.addActionListener(this);
		tapAsset.setPreferredSize(new Dimension(120, 45));
		tapPanel.add(tapAsset);
		
		JButton tapWorker = new JButton(tapBtn5);
		tapWorker.addActionListener(this);
		tapWorker.setPreferredSize(new Dimension(120, 45));
		tapPanel.add(tapWorker);
				
		tapPanel.setSize(WIDTH, HEIGHT/5);
		
		NorthPanel.add(tapPanel, BorderLayout.SOUTH);
	}
	public TableDemo() 
	{
		// TODO
		super("Table Demo");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		NorthPanel = new JPanel();
		NorthPanel.setLayout(new BorderLayout());
		add(NorthPanel, BorderLayout.NORTH);
		
		// TAP GUI
		tapGUI();
		
		//Set Content Section
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBackground(Color.white);
		
		tapPanel.setSize(WIDTH, HEIGHT*4/5);
		add(contentPanel, BorderLayout.CENTER);
	}
	public void ending() throws IOException {
		System.out.println("Ending Clicked");
		// 날짜 넘기
		now.nextDay();
		// 돈 빼기
		EntireMoney += TodayMoney;
		TodayMoney = 0;
		
		String path = TableDemo.class.getResource(".").getPath().toString()+"Money"+".txt";
		try(FileWriter fout= new FileWriter(path)) {
			PrintWriter out = new PrintWriter(fout);
			out.println(EntireMoney+"");
		}catch(Exception e) {
			System.out.println("saveFile error: "+e.getMessage());
		}
		
		// 월급
		for(Worker elem : WorkerAry ) {
			if(elem == null)
				break;
			if(elem.getStartday().getDay() == now.getDay())
				EntireMoney -= elem.getSalary();
		}
		
		// 창고
		for(Asset elem : AssetAry) {
			if(elem == null)
				break;
			if(elem.getOrder() > 0) {
				EntireMoney -= elem.getOrderprice();
				elem.setLeft(elem.getLeft()+elem.getOrder());
				elem.setOrder(0);
			}
		}
		
		Asset.savefile();
		Members.savefile();
		Menu.savefile();
		Worker.savefile();
		
		tapGUI();
		setVisible(true);
		repaint();
	}
	
	public void UpdatecontentPanel()
	{
		contentPanel.removeAll();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		System.out.println("recieved ActionEvent " + action);
		
		if(action.equals(tapBtn1))
		{
			TableUI();
			repaint();
		}
		else if(action.equals(tapBtn2))
		{
			MemberUI();
			repaint();
		}
		else if(action.equals(tapBtn3))
		{
			MenuUI();
			repaint();
		}
		else if(action.equals(tapBtn4))
		{
			AssetUI();
			repaint();
		}
		else if(action.equals(tapBtn5))
		{
			WorkerUI();
			repaint();
		}
		else if(action.equals("테이블1"))
		{
			tableInfo(0);
		}
		else if(action.equals("테이블2"))
		{
			tableInfo(1);
		}
		else if(action.equals("테이블3"))
		{
			tableInfo(2);
		}
		else if(action.equals("테이블4"))
		{
			tableInfo(3);
		}
		else if(action.equals("테이블5"))
		{
			tableInfo(4);
		}
		else if(action.equals("테이블6"))
		{
			tableInfo(5);
		}
		else if(action.equals("테이블7"))
		{
			tableInfo(6);
		}
		else if(action.equals("테이블8"))
		{
			tableInfo(7);
		}
	}
	
	//Main UI
	public void TableUI() {
		UpdatecontentPanel();

		//set table section
		tablePanel = new JPanel();
		tablePanel.setLayout(new GridLayout(2, 4));
		tablePanel.setBackground(Color.lightGray);
		System.out.println("tablePanel works well!");
				
		for(int i = 0; i<8;i++)
		{
			if(TableAry[i] != null)
			{
				drawTable(TableAry[i], i);
			}
			else
			{
				drawTable(i);
			}
		}
		tablePanel.setSize(WIDTH*2/3, HEIGHT*4/5);
		contentPanel.add(tablePanel, BorderLayout.CENTER);
		
		
		tableInfoPanel = new JPanel();
		tableInfoPanel.setLayout(new BorderLayout());
		tableInfoPanel.setBackground(Color.lightGray);
		
		tableInfoPanel.setSize(WIDTH/3, HEIGHT*4/5);
		contentPanel.add(tableInfoPanel, BorderLayout.EAST);
	
		JLabel ldemo = new JLabel();
		ldemo.setText("                                                                                                                         ");
		tableInfoPanel.add(ldemo, BorderLayout.NORTH);
		
		setVisible(true);
	}
	
	public void MemberUI() {
		UpdatecontentPanel();
		
		memberPanel = new JPanel();
		memberPanel.setLayout(new BorderLayout());
		memberPanel.setBackground(Color.pink);
		contentPanel.add(memberPanel, BorderLayout.CENTER);

		System.out.println("memberPanel works well!");
		
		if(Members.membernum >=0)
		{
			for(int i = 0 ; i<Members.membernum;i++)
			{
				membTable_content[i][0] = (i+1)+"";
				membTable_content[i][1] = MemberAry[i].getLevel().toString();
				membTable_content[i][2] = MemberAry[i].getName();
				membTable_content[i][3] = MemberAry[i].getMile() +"";
				membTable_content[i][4] = MemberAry[i].getPhone();
			}
			for(int i = Members.membernum;i<MEMBERS_NUM;i++)
			{
				membTable_content[i][0] = " ";
				membTable_content[i][1] = " ";
				membTable_content[i][2] = " ";
				membTable_content[i][3] = " ";
				membTable_content[i][4] = " ";
			}
			
		}
		membTable = new JTable(membTable_content, membTable_head);
		membTable.setBackground(Color.white);
		membTable.setSelectionBackground(Color.gray);
		membTable.setGridColor(Color.black);
		membTable.setRowHeight(55);
		
        JScrollPane scrollPane = new JScrollPane(membTable);
        scrollPane.setFont(fontObj);
        
        membTable.setModel(new DefaultTableModel(membTable_content, membTable_head) {
    		public boolean isCellEditable(int row, int column) {
    			return false;
    		}
    });
        
		memberPanel.add(membTable.getTableHeader());
		memberPanel.add(scrollPane, BorderLayout.CENTER);

		
		southMemberPanel = new JPanel();
		southMemberPanel.setLayout(new FlowLayout());
		memberPanel.add(southMemberPanel, BorderLayout.SOUTH);

		
		membRevise = new JButton("편집");
		membRevise.setPreferredSize(new Dimension(120, 45));
		membRevise.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("get membRevise action!");
				int selec = membTable.getSelectedRow();
				if(selec != -1 && !membTable_content[selec][3].equals(" "))
				{
					MembReviseUI(selec);
					repaint();
				}
			}
		});
		southMemberPanel.add(membRevise);
		
		membAdd = new JButton("추가");
		membAdd.setPreferredSize(new Dimension(120, 45));
		membAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("get membAdd action!");
					MembAddUI();
					repaint();
			}
		});
		southMemberPanel.add(membAdd);

		
		membDelete = new JButton("삭제");
		membDelete.setPreferredSize(new Dimension(120, 45));
		membDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int selec = membTable.getSelectedRow();
				if(selec != -1 && !membTable_content[selec][3].equals(" "))
				{
					System.out.println("get membDelete action!");
					for(int i = selec;i<Members.membernum;i++)
					{
						MemberAry[i] = MemberAry[i+1];
					}
					MemberAry[Members.membernum] = null;
					Members.membernum--;
				}
				MemberUI();
			}
		});
		southMemberPanel.add(membDelete);

		
		setVisible(true);
	}
	
	public void MenuUI() {
		UpdatecontentPanel();
		//main panel
		menuPanel = new JPanel();
		menuPanel.setLayout(new BorderLayout());
		contentPanel.add(menuPanel, BorderLayout.WEST);
		
		//menu selection UI
		menuPanel_N = new JPanel();
		menuPanel_N.setLayout(new GridLayout(MENU_NUM, 1));
		menuPanel_N.setBackground(Color.white);
		menuPanel.add(menuPanel_N, BorderLayout.CENTER);
		
		JLabel text0 = new JLabel();
		text0.setText("메뉴                                                       ");
		text0.setFont(fontObj);
		menuPanel_N.add(text0);
		
		for(int i =0;i<Menu.menunum;i++)
		{
			drawMenu(i);
		}
		
		menuPanel_S = new JPanel();
		menuPanel_S.setLayout(new BorderLayout());
		menuPanel.add(menuPanel_S, BorderLayout.SOUTH);

		menuAdd = new JButton("추가");
		menuAdd.setPreferredSize(new Dimension(120, 45));
		menuAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("get menuAdd action!");
					MenuAddUI();
					repaint();
			}
		});
		menuPanel_S.add(menuAdd);
		
		//menu information UI
		menuInfoPanel = new JPanel();
		menuInfoPanel.setLayout(new GridLayout(6, 2));
		contentPanel.add(menuInfoPanel, BorderLayout.CENTER);
		
		JLabel text1 = new JLabel();
		text1.setText("                     ");
		menuInfoPanel.add(text1);
		
		setVisible(true);

	}
	public String ingredientName;

	public void AssetUI() {
		UpdatecontentPanel();
		
		assetPanel = new JPanel();
		assetPanel.setLayout(new BorderLayout());
		contentPanel.add(assetPanel, BorderLayout.CENTER);

		System.out.println("assetPanel works well!");
		
		if(Asset.assetNum >= 0)
		{
			for(int i = 0 ; i<Asset.assetNum; i++)
			{
				assetTable_content[i][0] = AssetAry[i].getName();
				assetTable_content[i][1] = AssetAry[i].getLeft()+"";
				assetTable_content[i][2] = AssetAry[i].getOrder()+"";
				assetTable_content[i][3] = AssetAry[i].getPrice()+"";
			}
			for(int i = Asset.assetNum; i<ASSET_NUM; i++)
			{
				assetTable_content[i][0] = " ";
				assetTable_content[i][1] = " ";
				assetTable_content[i][2] = " ";
				assetTable_content[i][3] = " ";
			}
		}
		
		assetTable = new JTable(assetTable_content, assetTable_head);
		assetTable.setBackground(Color.white);
		assetTable.setSelectionBackground(Color.gray);
		assetTable.setGridColor(Color.black);
		assetTable.setRowHeight(55);
		assetTable.addMouseListener(this);

        JScrollPane scrollPane = new JScrollPane(assetTable);
        scrollPane.setFont(fontObj);
        assetTable.setModel(new DefaultTableModel(assetTable_content, assetTable_head) {
    		public boolean isCellEditable(int row, int column) {
    			return false;
    		}
    });
        
		assetPanel.add(assetTable.getTableHeader());
		assetPanel.add(scrollPane, BorderLayout.CENTER);
		
		assetPanel_S = new JPanel();
		assetPanel_S.setLayout(new FlowLayout());
		assetPanel.add(assetPanel_S, BorderLayout.SOUTH);
		
		assetAdd = new JButton("추가");
		assetAdd.setPreferredSize(new Dimension(120, 45));
		assetAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("get assetAdd action!");
				AssetAddUI();
			}
		});
		assetPanel_S.add(assetAdd);
		
		assetDelete = new JButton("삭제"); 
		assetDelete.setPreferredSize(new Dimension(120, 45));
		assetDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int selec = assetTable.getSelectedRow();
				if(selec != -1)
				{
					System.out.println("get assetDelete action!");
					for(int i = selec;i<Members.membernum;i++)
					{
						AssetAry[i] = AssetAry[i+1];
					}
					AssetAry[Members.membernum] = null;
					Asset.assetNum--;
				}
				AssetUI();
			}
			
		});
		assetPanel_S.add(assetDelete);
		
		assetInfoPanel = new JPanel();
		assetInfoPanel.setLayout(new FlowLayout());
		contentPanel.add(assetInfoPanel, BorderLayout.EAST);
		
		
		setVisible(true);
	}
	
	public void WorkerUI() {
		UpdatecontentPanel();
		
		workerPanel = new JPanel();
		workerPanel.setLayout(new BorderLayout());
		workerPanel.setBackground(Color.pink);
		contentPanel.add(workerPanel, BorderLayout.CENTER);

		System.out.println("workerPanel works well!");
		
		if(Worker.workerNum >= 0)
		{
			for(int i = 0 ; i<Worker.workerNum;i++)
			{
				workTable_content[i][0] = i+1 + "";
				workTable_content[i][1] = WorkerAry[i].getName();
				workTable_content[i][2] = WorkerAry[i].getSalary()+"";
				workTable_content[i][3] = WorkerAry[i].getPosition();
				workTable_content[i][4] = WorkerAry[i].getStartday().toString();
				workTable_content[i][5] = WorkerAry[i].getPhoneNumber();

			}
			for(int i = Worker.workerNum;i<WORKER_NUM;i++)
			{
				workTable_content[i][0] = " ";
				workTable_content[i][1] = " ";
				workTable_content[i][2] = " ";
				workTable_content[i][3] = " ";
				workTable_content[i][4] = " ";
				workTable_content[i][5] = " ";
			}
			
		}
		workTable = new JTable(workTable_content, workTable_head);
		workTable.setBackground(Color.white);
		workTable.setSelectionBackground(Color.gray);
		workTable.setGridColor(Color.black);
		workTable.setRowHeight(55);
		
        JScrollPane scrollPane = new JScrollPane(workTable);
        scrollPane.setFont(fontObj);
        
        workTable.setModel(new DefaultTableModel(workTable_content, workTable_head) {
	    		public boolean isCellEditable(int row, int column) {
	    			return false;
	    		}
        });
        
		workerPanel.add(workTable.getTableHeader());
		workerPanel.add(scrollPane, BorderLayout.CENTER);

		
		southWorkerPanel = new JPanel();
		southWorkerPanel.setLayout(new FlowLayout());
		workerPanel.add(southWorkerPanel, BorderLayout.SOUTH);

		
		workRevise = new JButton("편집");
		workRevise.setPreferredSize(new Dimension(120, 45));
		workRevise.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("get workRevise action!");
				int selec = workTable.getSelectedRow();
				if(selec != -1 && !workTable_content[selec][1].equals(" "))
				{
					WorkReviseUI(selec);
					repaint();
				}
			}
		});
		southWorkerPanel.add(workRevise);
		
		workAdd = new JButton("추가");
		workAdd.setPreferredSize(new Dimension(120, 45));
		workAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("get workAdd action!");
					WorkAddUI();
					repaint();
			}
		});
		southWorkerPanel.add(workAdd);

		
		workDelete = new JButton("삭제");
		workDelete.setPreferredSize(new Dimension(120, 45));
		workDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int selec = workTable.getSelectedRow();
				if(selec != -1 && !workTable_content[selec][1].equals(" "))
				{
					System.out.println("get workerDelete action!");
					for(int i = selec;i<Worker.workerNum;i++)
					{
						WorkerAry[i] = WorkerAry[i+1];
					}
					WorkerAry[Worker.workerNum] = null;
					Worker.workerNum--;
				}
				WorkerUI();
			}
		});
		southWorkerPanel.add(workDelete);

		setVisible(true);
	}
	//Sub function
	//0.
	//Play music
	static void PlaySound(File Sound)
	{

		try{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength()/1000);
		}
		catch(Exception e)
		{	
		}
	}
	//1. Table Tap
	public void drawTable(Table item, int i)
	{
		String str = "table" + (i+1);
		tableSection[i] = new JButton(str);
		tableSection[i].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				tableInfo(i);
			}
		});
		tableSection[i].setPreferredSize(new Dimension(120, 120));
		tableSection[i].setText("테이블"+(i+1) + "\n" +"총액: " + TableAry[i].getPrice());
		tableSection[i].setBackground(Color.cyan);
		tableSection[i].setOpaque(true);
		tableSection[i].setContentAreaFilled(true);
		tableSection[i].setBorderPainted(true);

		
		tablePanel.add(tableSection[i]);
	}
	public void drawTable(int i)
	{
		tableSection[i] = new JButton();
		tableSection[i].addActionListener(this);
		tableSection[i].setPreferredSize(new Dimension(120, 120));
		tableSection[i].setText("테이블"+(i+1));
		tableSection[i].setBackground(Color.white);
		tableSection[i].setOpaque(true);
		tableSection[i].setContentAreaFilled(true);
		tableSection[i].setBorderPainted(true);


		tablePanel.add(tableSection[i]);
	}

	public void tableInfo(int tablenum)
	{
		System.out.println("Do tableInfo. argument is " + tablenum);
		TableNow = tablenum;
		tableInfoPanel.removeAll();
		contentPanel.add(tableInfoPanel, BorderLayout.EAST);
		
		for(int i = 0; i<8;i++)
		{
			if(i == tablenum)
			{
				tableSection[tablenum].setBackground(Color.BLUE);

			}
			else if(TableAry[i] == null)
			{
				tableSection[i].setBackground(Color.white);
			}
			else
			{
				tableSection[i].setBackground(Color.CYAN);
			}
		}

		tableInfoPanel_1 = new JPanel();
		tableInfoPanel_1.setLayout(new FlowLayout());
		tableInfoPanel_1.setBackground(Color.white);
		tableInfoPanel.add(tableInfoPanel_1, BorderLayout.CENTER);

		
		if(TableAry[tablenum] != null)
		{
			for(int i = 0; i<TableAry[tablenum].ordered;i++)
			{
				tableTable_content[i][0] = TableAry[tablenum].getMenu()[i].getName();
				tableTable_content[i][1] = "" + TableAry[tablenum].getMenu()[i].getPrice();
			}
			for(int i = TableAry[tablenum].ordered; i<MENU_NUM;i++)
			{
				tableTable_content[i][0] = " ";
				tableTable_content[i][1] = " ";
			}

		}
		else
		{
			for(int i = 0; i<MENU_NUM;i++)
			{
				tableTable_content[i][0] = " ";
				tableTable_content[i][1] = " ";
			}
		}
		
		tableTable = new JTable(tableTable_content, tableTable_head);
		tableTable.setBackground(Color.white);
		tableTable.setSelectionBackground(Color.white);
		tableTable.setGridColor(Color.black);
		tableTable.setRowHeight(55);
		
        JScrollPane scrollPane = new JScrollPane(tableTable);
        scrollPane.setFont(fontObj);
        
        tableTable.setModel(new DefaultTableModel(tableTable_content, tableTable_head) {
        		public boolean isCellEditable(int row, int column) {
        			return false;
        		}
        });
        
		tableInfoPanel_1.add(tableTable.getTableHeader());
		tableInfoPanel_1.add(scrollPane, BorderLayout.CENTER);
			
		
		tableInfoPanel_2 = new JPanel();
		tableInfoPanel_2.setLayout(new FlowLayout());
		tableInfoPanel_2.setBackground(Color.yellow);
		
		tableAdd = new JButton();
		tableAdd.setText("추가");
		tableAdd.setPreferredSize(new Dimension(120, 45));
		tableAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("recieved AddTable action");

				tableInfoPanel_2.removeAll();
				tableInfoPanel.add(tableInfoPanel_2, BorderLayout.SOUTH);
				
				JTextField selectedList = new JTextField(15);
				try {
					tableList = new JList<String>();
					String[] array = Menu.MenuAryToStringAry(MenuAry);
					if(MenuAry[0] == null)
					{
						JOptionPane.showMessageDialog(null, "추가할 메뉴가 존재하지 않습니다!");
						TableUI();
					}
					tableList.setListData(array);
					tableList.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

					JScrollPane scroll = new JScrollPane(tableList);
					scroll.setSize(new Dimension(250, 45));
					scroll.setViewportView(tableList);
			        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //가로바정책

					tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					setVisible(true);

					tableList.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent e)
						{
							String name = (String)tableList.getSelectedValue();
							selectedList.setText(name);
						}
					});
					tableInfoPanel_2.add(tableList);
					
					JLabel textA = new JLabel();
					textA.setText("메뉴 선택 : ");
					selectedList.setEditable(false);
					tableInfoPanel_2.add(textA);
					tableInfoPanel_2.add(selectedList);
					
					JButton okay = new JButton("확인");
					okay.setPreferredSize(new Dimension(120, 45));
					okay.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(TableAry[tablenum] == null)
							{
								TableAry[tablenum] = new Table(); 
								TableAry[tablenum].addOrder(selectedList.getText());
							}
							else
							{
								TableAry[tablenum].addOrder(selectedList.getText());
							}
							
							TableUI();
						}});
					tableInfoPanel_2.add(okay);
					setVisible(true);
				}catch(NullPointerException e1)
				{
					JOptionPane.showMessageDialog(null, "추가할 메뉴가 존재하지 않습니다!");
					TableUI();
				}
				
				
			}
		});
	
		tableInfoPanel_2.add(tableAdd);
		
		tableEnd = new JButton();
		tableEnd.setText("결제");
		tableEnd.setPreferredSize(new Dimension(120, 45));
		try {
			tableEnd.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					tableInfoPanel_2.removeAll();
					tableInfoPanel.add(tableInfoPanel_2, BorderLayout.SOUTH);
					
					textfield1 = new JTextField(10);
					tableInfoPanel_2.add(textfield1);
					
					JLabel showprice = new JLabel();
					showprice.setText("총액 : " + TableAry[TableNow].getPrice());
					tableInfoPanel_2.add(showprice);
									
					JButton textconfirm = new JButton();
					textconfirm.setText("회원 할인 적용");
					textconfirm.setPreferredSize(new Dimension(120, 45));
					textconfirm.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							int membnum = -1;
							for(int i = 0; i<Members.membernum;i++)
							{
								if(MemberAry[i].getName().equals(textfield1.getText()))
								{
									membnum = i;
								}
							}
							TableAry[TableNow].ApplyMile(textfield1.getText());
							if(membnum > -1)
							{
								TableAry[TableNow].ApplyMile(membnum);
							}
							showprice.setText("총액 : " + TableAry[TableNow].getPrice() + " 현재 마일리지: " + MemberAry[membnum].getMile());
							setVisible(true);
						}
					});
					tableInfoPanel_2.add(textconfirm);

									
					tableconfirm = new JButton();
					tableconfirm.setText("확인");
					tableconfirm.setPreferredSize(new Dimension(120, 45));
					tableconfirm.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							int money = TableAry[TableNow].getPrice();
							TodayMoney += money;//&&
							TableAry[TableNow].TableEnd();
							TableAry[TableNow] = null;
							tapGUI();
							TableUI();
						}
					});
					tableInfoPanel_2.add(tableconfirm);
					
					setVisible(true);

				}
			});
		}catch(NullPointerException e)
		{
			System.err.println("You confirm order in empty table!!");
		}
		
		tableInfoPanel_2.add(tableEnd);

		
		tableInfoPanel.add(tableInfoPanel_2, BorderLayout.SOUTH);
		
		
		setVisible(true);
	}
	
	//2. Member Tap
	public void MembReviseUI(int selected)
	{
		southMemberPanel.removeAll();
		memberPanel.add(southMemberPanel, BorderLayout.SOUTH);

		
		JLabel asknameL = new JLabel();
		asknameL.setText("이름");
		JTextField asknameT = new JTextField(10);
		
		southMemberPanel.add(asknameL);
		southMemberPanel.add(asknameT);

		
		JLabel askphoneL = new JLabel();
		JTextField askphoneT = new JTextField(20);
		askphoneL.setText("연락처");
		
		southMemberPanel.add(askphoneL);
		southMemberPanel.add(askphoneT);
		
		JButton askbutton = new JButton("수정하기");
		askbutton.setPreferredSize(new Dimension(120, 45));
		
		askbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(asknameT.getText() != null)
				{
					MemberAry[selected].setName(asknameT.getText());
				}
				if(askphoneT.getText()!= null)
				{
					MemberAry[selected].setPhone(askphoneT.getText());
				}
				MemberUI();
			}
		});
		southMemberPanel.add(askbutton);
		
		setVisible(true);
	}
	
	public void MembAddUI()
	{
		southMemberPanel.removeAll();
		memberPanel.add(southMemberPanel, BorderLayout.SOUTH);
		
		JLabel asknameL = new JLabel();
		asknameL.setText("이름");
		JTextField asknameT = new JTextField(10);
		
		southMemberPanel.add(asknameL);
		southMemberPanel.add(asknameT);

		
		JLabel askphoneL = new JLabel();
		JTextField askphoneT = new JTextField(20);
		askphoneL.setText("연락처");
		
		southMemberPanel.add(askphoneL);
		southMemberPanel.add(askphoneT);
		
		JButton askbutton = new JButton("추가하기");
		askbutton.setPreferredSize(new Dimension(120, 45));
		askbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("get askbutton action!");
				MemberAry[Members.membernum] = new Members(asknameT.getText(), askphoneT.getText());
				System.out.println(Members.membernum);
				MemberUI();
			}
		});
		southMemberPanel.add(askbutton);
		
		setVisible(true);
	}

	//3. Menu Tap
	public void drawMenu(int i)
	{
		menuSection[i] = new JButton();
		menuSection[i].setText(MenuAry[i].getName());
		menuSection[i].setBackground(Color.white);
		menuSection[i].setOpaque(true);
		menuSection[i].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("get menuSection action!");
				for(int j = 0; j<Menu.menunum;j++)
				{
					if(j == i)
					{
						menuSection[j].setBackground(Color.blue);
					}
					else
					{
						menuSection[j].setBackground(Color.white);
					}
				}
				menuInfo(i);
			}
		});
		menuPanel_N.add(menuSection[i]);
	}
	
	public void menuInfo(int i)
	{
		menuInfoPanel.removeAll();
		contentPanel.add(menuInfoPanel, BorderLayout.CENTER);
		
		
		JLabel menuName = new JLabel();
		menuName.setText("이름: "+MenuAry[i].getName());
		menuName.setFont(fontObj);
		menuInfoPanel.add(menuName);
		
		JLabel menuPrice = new JLabel();
		menuPrice.setText("가격: "+MenuAry[i].getPrice());
		menuPrice.setFont(fontObj);
		menuInfoPanel.add(menuPrice);
		
		if(MenuAry[i].asset[0] != null)
		{
			JLabel menuAsset = new JLabel();
			menuAsset.setText("재료: "+MenuAry[i].getAsset());
			menuAsset.setFont(fontObj);
			menuInfoPanel.add(menuAsset);
		}
		selectedA = i;
		JButton deleteMenu = new JButton("삭제하기");
		deleteMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuAry[selectedA].assetnum = 0;
				MenuAry[selectedA] = null;
				for(int i = selectedA;i<Menu.menunum-1;i++)
				{
					MenuAry[i] = MenuAry[i+1];
				}
				Menu.menunum--;
				MenuUI();
			}
		});
		menuInfoPanel.add(deleteMenu);

		selectedA = i;
		JButton reviceMenu = new JButton("수정하기");
		reviceMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuReviseUI(selectedA);
			}
		});
		menuInfoPanel.add(reviceMenu);

		
		setVisible(true);
	}

	public void MenuAddUI()
	{
		menuInfoPanel.removeAll();
		contentPanel.add(menuInfoPanel, BorderLayout.CENTER);
		
		
		JLabel asknameL = new JLabel();
		asknameL.setText("이름");
		JTextField asknameT = new JTextField(10);
		
		menuInfoPanel.add(asknameL);
		menuInfoPanel.add(asknameT);

		
		JLabel askpriceL = new JLabel();
		JTextField askpriceT = new JTextField(20);
		askpriceL.setText("가격");
		
		menuInfoPanel.add(askpriceL);
		menuInfoPanel.add(askpriceT);
		
		if(Asset.assetNum >0)
		{
			JLabel askAsset = new JLabel();
			askAsset.setText("재료 추가하기");
			menuInfoPanel.add(askAsset);
			
			assetAsk = new JPanel();
			assetAsk.setLayout(new FlowLayout());
			menuInfoPanel.add(assetAsk);
			
			assetIn = 0;
			assetInmenu = new Asset[ASSET_NUM];
			AddAsset = new JButton[ASSET_NUM]; 
			for(int i = 0; i< Asset.assetNum;i++)
			{
				AddAsset[i] = new JButton(AssetAry[i].getName());
				AddAsset[i].setPreferredSize(new Dimension(100,30));
				AddAsset[i].setBackground(Color.white);
				AddAsset[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for(int i =0;i<Asset.assetNum;i++)
						{
							if(AssetAry[i].getName().equals(e.getActionCommand()))
							{
								System.out.println("find "+ AssetAry[i].getName());
								AddAsset[i].setBackground(Color.blue);
								assetInmenu[assetIn] = AssetAry[i];
								assetIn++;
								setVisible(true);
								break;
							}
						}
						
					}
				});
				assetAsk.add(AddAsset[i]);
			}
			
			setVisible(true);
		}
		
		
		JButton askbutton = new JButton("추가하기");
		askbutton.setPreferredSize(new Dimension(120, 45));
		askbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("get askbutton action!");
				
				MenuAry[Menu.menunum] = new Menu(asknameT.getText(), askpriceT.getText());
				MenuAry[Menu.menunum-1].assetnum = assetIn;
				for(int i = 0; i<assetIn;i++)
				{
					MenuAry[Menu.menunum-1].asset[i] = assetInmenu[i];
				}
				MenuUI();
			}
		});
		menuInfoPanel.add(askbutton);
		
		setVisible(true);
	}
	public void MenuReviseUI(int selected)
	{
		menuInfoPanel.removeAll();
		contentPanel.add(menuInfoPanel, BorderLayout.CENTER);
		
		
		JLabel asknameL = new JLabel();
		asknameL.setText("이름");
		JTextField asknameT = new JTextField(10);
		
		menuInfoPanel.add(asknameL);
		menuInfoPanel.add(asknameT);

		
		JLabel askpriceL = new JLabel();
		JTextField askpriceT = new JTextField(20);
		askpriceL.setText("가격");
		
		menuInfoPanel.add(askpriceL);
		menuInfoPanel.add(askpriceT);
		
		if(Asset.assetNum >0)
		{
			JLabel askAsset = new JLabel();
			askAsset.setText("재료 추가하기");
			menuInfoPanel.add(askAsset);
			
			assetIn = 0;

			assetAsk = new JPanel();
			assetAsk.setLayout(new FlowLayout());
			menuInfoPanel.add(assetAsk);
			
			assetInmenu = new Asset[ASSET_NUM];
			AddAsset = new JButton[ASSET_NUM]; 
			for(int i = 0; i< Asset.assetNum;i++)
			{
				AddAsset[i] = new JButton(AssetAry[i].getName());
				AddAsset[i].setPreferredSize(new Dimension(100,30));
				AddAsset[i].setBackground(Color.white);
				AddAsset[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for(int i =0;i<Asset.assetNum;i++)
						{
							if(AssetAry[i].getName().equals(e.getActionCommand()))
							{
								System.out.println("find "+ AssetAry[i].getName());
								AddAsset[i].setBackground(Color.blue);
								assetInmenu[assetIn] = AssetAry[i];
								assetIn++;
								setVisible(true);
								break;
							}
						}
						
					}
				});
				assetAsk.add(AddAsset[i]);
			}
			
			setVisible(true);
		}
		
		
		JButton askbutton = new JButton("수정하기");
		askbutton.setPreferredSize(new Dimension(120, 45));
		askbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("get askbutton action!");
				if(asknameT.getText().length()>0)
				{
					MenuAry[selected].setKindOfMenu(asknameT.getText());
				}
				if(askpriceT.getText().length() >0)
				{
					MenuAry[selected].setPrice(Integer.parseInt(askpriceT.getText()));
				}
				if(assetIn>0)
				{
					MenuAry[Menu.menunum-1].assetnum = assetIn;
					for(int i = 0; i<assetIn;i++)
					{
						MenuAry[Menu.menunum-1].asset[i] = assetInmenu[i];
					}
				}
				
				MenuUI();
			}
		});
		menuInfoPanel.add(askbutton);
		
		setVisible(true);
	}
	
	//4. Asset tap
	public void AssetAddUI()
	{
		assetPanel_S.removeAll();
		contentPanel.add(assetInfoPanel, BorderLayout.EAST);
		
		JLabel nameL = new JLabel("이름: ");
		JTextField nameF = new JTextField(10);

		JLabel priceL = new JLabel("가격: ");
		JTextField priceF = new JTextField(10);

		JLabel sellerL = new JLabel("판매처: ");
		JTextField sellerF = new JTextField(10);

		JLabel contactL = new JLabel("연락처: ");
		JTextField contactF = new JTextField(10);

		assetPanel_S.add(nameL);
		assetPanel_S.add(nameF);
		assetPanel_S.add(priceL);
		assetPanel_S.add(priceF);
		assetPanel_S.add(sellerL);
		assetPanel_S.add(sellerF);
		assetPanel_S.add(contactL);
		assetPanel_S.add(contactF);
		
		JButton addB = new JButton("추가하기");
		assetPanel_S.add(addB);
		addB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("get addAsset action!");
				AssetAry[Asset.assetNum] = new Asset(nameF.getText(), Integer.parseInt(priceF.getText()), sellerF.getText(), contactF.getText());
				System.out.println(AssetAry[Asset.assetNum-1]);
				AssetUI();
			}
		});
		
		setVisible(true);
	}
	
	//5. Worker tap
	public void WorkAddUI() {
		southWorkerPanel.removeAll();
		workerPanel.add(southWorkerPanel, BorderLayout.SOUTH);
		
		JLabel asknameL = new JLabel();
		asknameL.setText("이름");
		JTextField asknameT = new JTextField(10);
		
		southWorkerPanel.add(asknameL);
		southWorkerPanel.add(asknameT);

		JLabel askphoneL = new JLabel();
		JTextField askphoneT = new JTextField(20);
		askphoneL.setText("연락처");
		
		southWorkerPanel.add(askphoneL);
		southWorkerPanel.add(askphoneT);
		
		JLabel askpositionL = new JLabel();
		JTextField askpositionT = new JTextField(20);
		askpositionL.setText("직급");
		
		southWorkerPanel.add(askpositionL);
		southWorkerPanel.add(askpositionT);
		
		JLabel askwageL = new JLabel();
		JTextField askwageT = new JTextField(20);
		askwageL.setText("급여");
		
		southWorkerPanel.add(askwageL);
		southWorkerPanel.add(askwageT);
		
		JButton askbutton = new JButton("추가하기");
		askbutton.setPreferredSize(new Dimension(120, 45));
		askbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("get worker add action!");
				WorkerAry[Worker.workerNum] = new Worker(asknameT.getText(), askpositionT.getText(), askphoneT.getText(), Integer.parseInt(askwageT.getText()),now);
				System.out.println(WorkerAry[Worker.workerNum-1]);
				WorkerUI();
			}
		});
		southWorkerPanel.add(askbutton);
		
		JLabel junk = new JLabel();
		junk.setText("                                                                                                                      ");
		southWorkerPanel.add(junk);
		
		setVisible(true);
	}
	
	public void WorkReviseUI(int selected)
	{
		southWorkerPanel.removeAll();
		workerPanel.add(southWorkerPanel, BorderLayout.SOUTH);
		
		JLabel asknameL = new JLabel();
		asknameL.setText("이름");
		JTextField asknameT = new JTextField(10);
		
		southWorkerPanel.add(asknameL);
		southWorkerPanel.add(asknameT);

		
		JLabel askphoneL = new JLabel();
		JTextField askphoneT = new JTextField(20);
		askphoneL.setText("연락처");
		
		southWorkerPanel.add(askphoneL);
		southWorkerPanel.add(askphoneT);
		
		JLabel askpositionL = new JLabel();
		JTextField askpositionT = new JTextField(20);
		askpositionL.setText("직급");
		
		southWorkerPanel.add(askpositionL);
		southWorkerPanel.add(askpositionT);
		
		JLabel askwageL = new JLabel();
		JTextField askwageT = new JTextField(20);
		askwageL.setText("급여");
		
		southWorkerPanel.add(askwageL);
		southWorkerPanel.add(askwageT);
		
		JButton askbutton = new JButton("편집하기");
		askbutton.setPreferredSize(new Dimension(120, 45));
		askbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("get askbutton action!");
				if(asknameT.getText() != null)
				{
					WorkerAry[selected].setName(asknameT.getText());
				}
				if(askphoneT.getText()!= null)
				{
					WorkerAry[selected].setPhoneNumber(askphoneT.getText());
				}
				if(askpositionT.getText()!= null)
				{
					WorkerAry[selected].setPosition(askpositionT.getText());
				}
				if(askwageT.getText()!= null)
				{
					WorkerAry[selected].setSalary(Integer.parseInt(askwageT.getText()));
				}
				WorkerUI();
			}
		});
		southWorkerPanel.add(askbutton);
		
		setVisible(true);
	}
	
	Asset selected;
	@Override
	public void mouseClicked(MouseEvent e) {		
		
		assetInfoPanel.removeAll();
		contentPanel.add(assetInfoPanel, BorderLayout.EAST);
		
		int row = assetTable.getSelectedRow();
		ingredientName = assetTable.getValueAt(row, 0).toString();
		
		for(Asset elem : AssetAry) {
			if(elem.getName().equals(ingredientName)) {
				selected = elem;
				break;
			}
		}
		if(selected == null) {
			System.out.println("ingredient name not match");
			System.exit(-1);
		}
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(5,1));
		
		JLabel nameL = new JLabel("이름: "+ selected.getName());
		JLabel priceL = new JLabel("가격: "+ selected.getPrice());
		JLabel sellerL = new JLabel("판매처: "+ selected.getSeller());
		JLabel contactL = new JLabel("연락처: "+ selected.getContact());
		JLabel orderL = new JLabel("주문: ");
		JTextField orderN = new JTextField(10);
		
		panel1.add(nameL);
		panel1.add(priceL);
		panel1.add(sellerL);
		panel1.add(contactL);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2,2));
		panel2.add(orderL);
		panel2.add(orderN);
				
		assetOrder = new JButton("주문");
		assetOrder.setPreferredSize(new Dimension(120, 45));
		panel2.add(assetOrder);
		assetOrder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int n = Integer.parseInt(orderN.getText());
				if(n <= 0) 
					System.exit(-1);

				if(selected.getOrder() == 0) {
					selected.setOrder(n);
					selected.setOrderprice(selected.getPrice() * n);
				}else {
					selected.setOrder(selected.getOrder() + n);
					selected.setOrderprice(selected.getPrice() * selected.getOrder());
				}
				AssetUI();
				System.out.println(selected.toString());
			}
		});
		

		assetNotOrder = new JButton("주문 취소");
		panel2.add(assetNotOrder);
		assetNotOrder.setPreferredSize(new Dimension(120, 45));
		assetNotOrder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int n = Integer.parseInt(orderN.getText());
				if(n <= 0) 
					System.exit(-1);
					
				if(selected.getOrder() == 0) {
					System.out.println("없는주문을왜취소해!");
					System.exit(-1);
				}else {
					selected.setOrder(selected.getOrder() - n);
					selected.setOrderprice(selected.getPrice() * selected.getOrder());
				}
				AssetUI();
			}
		});
		
		panel1.add(panel2);
		assetInfoPanel.add(panel1);
		setVisible(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {		
	}

	@Override
	public void mouseReleased(MouseEvent e) {		
	}

	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
