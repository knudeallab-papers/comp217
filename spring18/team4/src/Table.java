import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//tnumber무조권 1 부터 시작 curbut 1 start 
//table color 0 아무것도아닌놈글쎼 뭐하지  1 선택된놈노랭 2 확정된놈빨강 결제끝나면 0 으로가야 3확정된놈이 선택된상



public class Table extends JPanel implements ActionListener{
 
	private DefaultTableModel model;
	private static DefaultTableModel menulist;
	private JTable  table;
	private JTable  table1;
	private JButton[] tabelb;
	private mybutton[] pb;
	static tableinfo[] collect;
	private tableinfo pew;
	
	private JPanel tpanel;
	private String[] deinfo;
	private int[] disbut;
	static int curbut;
	
	//inner class for click and info
	private class kindclick extends MouseAdapter {
		 
	    @Override
	    public void mouseClicked(MouseEvent e) 
	    {
	    		
	    		if (e.getButton() == 1)  //더블클
	    		{ 
	    			
	    			String[] get=new String[3];
	    			int row,col;
	    			row=table1.getSelectedRow();
	    			col=table1.getSelectedColumn();
	    			
	    			for(int i=0;i<3;i++)
	    			{
	    				get[i]=(String)menulist.getValueAt(row,i);
	    			}
	    			
	    			//System.out.println("z"+get[0]+" "+get[1]+" "+get[2]+"z");
	    			pew.price+=Integer.parseInt(get[2]);
	    			pew.tnumber=curbut;
	    			pew.color=disbut[curbut-1];
	    			//System.out.println(pew.mnuber);
	    			int temp=pew.mnuber;
	    			pew.menu[temp][0]=get[0]; //번호 
	    			pew.menu[temp][1]=get[1]; //메뉴이름 
	    			pew.menu[temp][2]=get[2];  //메뉴 가
	    			pew.mnuber=temp+1;
	    			if(collect[curbut-1].color==2) showtableinfo();
	    			else showtemp();
	    		}
	    	} 
	}
	
	//main
	public Table()
	{
		curbut=999;
		pew=new tableinfo();
		disbut=new int[8];
		for(int i=0;i<8;i++)disbut[i]=0;
		collect=new tableinfo[8];
		for(int i=0;i<8;i++)collect[i]=new tableinfo(i);
		deinfo=new String[30];
		//메뉴 가격 표 생성 및 그에대한 패널
		JPanel chartpanel=new JPanel();
		String title[] = {"번호","메뉴 ","가격 "};
		String info[][] = {};
		//table info
		model = new DefaultTableModel(info,title){ public boolean isCellEditable(int i, int c){ return false; } };
		table = new JTable(model);
		table.setPreferredSize(new Dimension(300,500));
		JScrollPane sp = new JScrollPane(table); 
		sp.setPreferredSize(new Dimension(300, 500));
		chartpanel.add(sp);
		//menu list
		String title1[] = {"번호 ","메뉴 ","가격 "};
		String info1[][]= {};
		menulist = new DefaultTableModel(info1,title1){ public boolean isCellEditable(int i, int c){ return false; } };
         table1=new JTable(menulist);
         table1.addMouseListener(new kindclick());
         table1.setPreferredSize(new Dimension(300, 500));
         JScrollPane sp1 = new JScrollPane(table1);
         sp1.setPreferredSize(new Dimension(300, 500));
				
		//테이블 패널 생성 
		tpanel=new JPanel();
		tpanel.setLayout(new GridLayout(2,4));
		//테이블 버튼 및레이블 생성 
		tabelb=new JButton[8];
		pb=new mybutton[8];
		JLabel[] label=new JLabel[8];
		
		for(int i=0;i<8;i++)
		{
			int a=i+1;
			String tt="테이블"+a;
			String ts="t"+a;
			String tl=""+a;
			tabelb[i]=new JButton(tt);
			tabelb[i].setActionCommand(ts);
			tabelb[i].setPreferredSize(new Dimension(80, 200));
			tabelb[i].addActionListener(this);
			label[i]=new JLabel(tl);
			tabelb[i].add(label[i]);
			tpanel.add(tabelb[i]);
		}
		buttonrefresh();
		
		//추가 결제 버튼 생성 및 그에 대한 패널
		JPanel ordpanel=new JPanel();
		JButton add=new JButton("추가 ");
		add.setActionCommand("add");
		add.addActionListener(this);
		JButton pay= new JButton("결제 ");
		pay.setActionCommand("pay");
		pay.addActionListener(this);
		ordpanel.add(add);
		ordpanel.add(pay);
		
		callData();
		buttonrefresh();
		add(sp1);
		add(chartpanel);
		add(tpanel);
		add(ordpanel);
		
		makemlist();
	}
	
	public static void makemlist()
	{
		String line;
		String inputStr[] =new String[5];
		String getStr[]=new String[3];
		StringTokenizer st;
		Scanner inputStream= null;
		
		menulist.setRowCount(0);
		
		File file =new File("menu.txt");
		if(file.exists())
		{
		try {
			
			inputStream= new Scanner(new FileInputStream("menu.txt"));
		}
		catch(FileNotFoundException er) {
			er.printStackTrace();
		}
		while(inputStream.hasNextLine()) 
		{
			int i=0;
			line = inputStream.nextLine();
			st= new StringTokenizer(line, " ");
			while(st.hasMoreTokens()) {
				inputStr[i++]=st.nextToken();
			}
			getStr[0]=inputStr[0];
			getStr[1]=inputStr[1];
			getStr[2]=inputStr[2];
			menulist.addRow(getStr);
		}
		inputStream.close();
		}
	}
	
	//추가 버튼 누를시 이제 데이터에도 확정으로 들어가고 파일저장도 pew->collect->init pew colorchange
	public void addData()
	{
		int num;
		PrintWriter outputStream = null;
	String inputStr[] =new String[4];

	try {
		String fileName ="table.txt";
		outputStream =new PrintWriter(new FileOutputStream(fileName,true));	
	}
			
	catch(FileNotFoundException er){
		System.out.println("Error opening menu.txt");
		er.printStackTrace();
	}
	//pew->collect
	int go=pew.tnumber-1;
	collect[go].price=pew.price;
	collect[go].tnumber=pew.tnumber;
	collect[go].color=pew.color;
	int temp=pew.mnuber;
	for(int x=0;x<temp;x++)
	{
	collect[go].menu[x][0]=pew.menu[x][0]; //번호 
	collect[go].menu[x][1]=pew.menu[x][1]; //메뉴이름 
	collect[go].menu[x][2]=pew.menu[x][2];  //메뉴 가
	}
	collect[go].mnuber=pew.mnuber;
	collect[go].color=2; //확정 빨강색으로표
	//collect->menu.txt
	outputStream.print(collect[go].tnumber+" ");
	outputStream.print(collect[go].price+" ");
	outputStream.print(collect[go].color+" ");
	
	for(int x=0;x<temp;x++)
	{
		outputStream.print(collect[go].menu[x][0]+"|"+collect[go].menu[x][1]+"|"+collect[go].menu[x][2]+" ");
	}
	outputStream.print("\n");
	outputStream.close();
	
	//프론트에 갱신 및 컬러변경+확
	showtableinfo();
	buttonrefresh();  
	}
	//adddata 메소드와 잘 맞게 구
	public void callData()
   	{
   		File file =new File("table.txt");
   		if(file.exists()) {
   			String line;
   			String inputStr[] =new String[15];
   			String get[]=new String[3];
   			
   			StringTokenizer st;
   			Scanner inputStream= null;
   			
   			try {
   				inputStream= new Scanner(new FileInputStream("table.txt"));
   			}
   			catch(FileNotFoundException er) {
   				er.printStackTrace();
   			}
   			
   			while(inputStream.hasNextLine()) {
   				int i=0,num;
   				line = inputStream.nextLine();
   				st= new StringTokenizer(line, " ");
   				while(st.hasMoreTokens()) {
   					inputStr[i++]=st.nextToken();
   				}
   				try {
   					
   				
   				num=Integer.parseInt(inputStr[0]);
   				num-=1;
   				collect[num].tnumber=num;
   				collect[num].price=Integer.parseInt(inputStr[1]);
   				collect[num].color=Integer.parseInt(inputStr[2]);
   				
   				//tablenumber|name|price 3|ㅁㅋ|4000 17|qweqwe|1500 inputex
   				//parse menudata in inp
   				collect[num].mnuber=i-3;
   				for(int x=3,y=0;x<i;x++,y++)
   				{
   					int z=0;
   					line=inputStr[x];
   	   				st= new StringTokenizer(line, "|");
   	   				while(st.hasMoreTokens()) {
   	   				collect[num].menu[y][z++]=st.nextToken();
   	   				}
   				}
   				}
   				catch(NumberFormatException e)
   				{
   					System.out.println("oops");
   				}
   				
   			}
   			inputStream.close();
   		}
   		
   	}
	public void resetpew()
	{
		pew=new tableinfo();
	}
	public void buttonrefresh()
	{
		resetpew();//pew dataset 도 reset
		
		//System.out.println(curbut);
		//for(int i=0;i<8;i++)System.out.printf("%d ", collect[i].color);
		for(int i=0;i<8;i++)
		{
			
			if(collect[i].color==2)
			{
				tabelb[i].setBackground(Color.RED);
				tabelb[i].setOpaque(true);
				tabelb[i].setBorderPainted(false);
			}
			else if(collect[i].color==1||collect[i].color==3)
			{
				tabelb[i].setBackground(Color.YELLOW);
				tabelb[i].setOpaque(true);
				tabelb[i].setBorderPainted(false);
			}
			else if(collect[i].color==0)
			{
				tabelb[i].setBackground(Color.GREEN);
				tabelb[i].setOpaque(true);
				tabelb[i].setBorderPainted(false);
			}
			else
			{
				tabelb[i].setBackground(Color.BLUE);
				tabelb[i].setOpaque(true);
				tabelb[i].setBorderPainted(false);
			}
		}
	
		 tpanel.repaint();
	}
	//그 테이블의 정보를 보여줌 + 메뉴->테이블 단꼐에서도 사용
	public void showtableinfo()
	{
		
		int num=curbut-1;
		String getStr[]=new String[3];
		
		//System.out.println(collect[num].mnuber);
		
		model.setRowCount(0);
		// 추가있는곳만 제대로 보여주고 아직 확정 안난곳은 다른곳 갔다가 다시눌러도 정보 날라가버리
		for(int i=0;i<collect[num].mnuber;i++)
		{
			getStr[0]=collect[num].menu[i][0];
			getStr[1]=collect[num].menu[i][1];
			getStr[2]=collect[num].menu[i][2];
			model.addRow(getStr);
		}
		
	}
	
	public void showtemp()
	{
		
		int num=curbut-1;
		String getStr[]=new String[3];
		
		model.setRowCount(0);
		
		for(int i=0;i<pew.mnuber;i++)
		{
			getStr[0]=pew.menu[i][0];
			getStr[1]=pew.menu[i][1];
			getStr[2]=pew.menu[i][2];
			model.addRow(getStr);
		}
		
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		String btnStr = e.getActionCommand();
		
		if(btnStr.equals("t1"))
		{
			model.setRowCount(0);
			curbut=1;
			for(int i=0;i<8;i++) 
			{
				if(collect[i].color==3)collect[i].color=2;
				else if(collect[i].color==1)collect[i].color=0;
			}
			if(collect[0].color==2)collect[0].color=3;
			else collect[0].color=1;
			showtableinfo();
			buttonrefresh();
		}
		else if(btnStr.equals("t2"))
		{
			model.setRowCount(0);
			curbut=2;
			for(int i=0;i<8;i++) 
			{
				if(collect[i].color==3)collect[i].color=2;
				else if(collect[i].color==1)collect[i].color=0;
			}
			if(collect[1].color==2)collect[1].color=3;
			else collect[1].color=1;
			showtableinfo();
			buttonrefresh();
		}
		else if(btnStr.equals("t3"))
		{
			model.setRowCount(0);
			curbut=3;
			for(int i=0;i<8;i++) 
			{
				if(collect[i].color==3)collect[i].color=2;
				else if(collect[i].color==1)collect[i].color=0;
			}
			if(collect[2].color==2)collect[2].color=3;
			else collect[2].color=1;
			showtableinfo();
			buttonrefresh();
		}
		else if(btnStr.equals("t4"))
		{
			model.setRowCount(0);
			curbut=4;
			for(int i=0;i<8;i++) 
			{
				if(collect[i].color==3)collect[i].color=2;
				else if(collect[i].color==1)collect[i].color=0;
			}
			if(collect[3].color==2)collect[3].color=3;
			else collect[3].color=1;
			showtableinfo();
			buttonrefresh();
		}
		else if(btnStr.equals("t5"))
		{
			model.setRowCount(0);
			curbut=5;
			for(int i=0;i<8;i++) 
			{
				if(collect[i].color==3)collect[i].color=2;
				else if(collect[i].color==1)collect[i].color=0;
			}
			if(collect[4].color==2)collect[4].color=3;
			else collect[4].color=1;
			showtableinfo();
			buttonrefresh();
		}
		else if(btnStr.equals("t6"))
		{
			model.setRowCount(0);
			curbut=6;
			for(int i=0;i<8;i++) 
			{
				if(collect[i].color==3)collect[i].color=2;
				else if(collect[i].color==1)collect[i].color=0;
			}
			if(collect[5].color==2)collect[5].color=3;
			else collect[5].color=1;
			showtableinfo();
			buttonrefresh();
		}
		else if(btnStr.equals("t7"))
		{
			model.setRowCount(0);
			curbut=7;
			for(int i=0;i<8;i++) 
			{
				if(collect[i].color==3)collect[i].color=2;
				else if(collect[i].color==1)collect[i].color=0;
			}
			if(collect[6].color==2)collect[6].color=3;
			else collect[6].color=1;
			showtableinfo();
			buttonrefresh();
		}
		else if(btnStr.equals("t8"))
		{
			model.setRowCount(0);
			curbut=8;
			for(int i=0;i<8;i++) 
			{
				if(collect[i].color==3)collect[i].color=2;
				else if(collect[i].color==1)collect[i].color=0;
			}
			if(collect[7].color==2)collect[7].color=3;
			else collect[7].color=1;
			showtableinfo();
			buttonrefresh();
		}
		else if(btnStr.equals("add"))
		{
			addData();
		}
		else if(btnStr.equals("pay"))
		{
			//System.out.println("pay");
			paybutton pb=new paybutton();
			
			//프로늩갱
			showtableinfo();
			buttonrefresh();  
			
		}
		
	}
}
