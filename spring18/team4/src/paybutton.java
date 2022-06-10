import java.awt.Dimension;
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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import java.awt.FlowLayout;

public class paybutton extends JFrame implements ActionListener{
	
	static  DefaultTableModel viplist;
	
	private JTable table;
	private JButton nvbut;
	private JButton yvbut;
	private JPanel butpanel;
	private JPanel chartpanel;
	private String[] get;
	int r,c;
	
	
	private class vipclick extends MouseAdapter {
		 
	    @Override
	    public void mouseClicked(MouseEvent e) 
	    {
	    		
	    		if (e.getButton() == 1)  //더블클
	    		{ 
	    			
	    			
	    			int row,col;
	    			row=table.getSelectedRow();
	    			r=row;
	    			col=table.getSelectedColumn();
	    			c=col;
	    			
	    			for(int i=0;i<5;i++)
	    			{
	    				get[i]=(String)viplist.getValueAt(row,i);
	    				
	    			}
	    			
	    		}
	    	} 
	}
	
	public paybutton()
	{
		get=new String[5];
		String title[] = {"번호", "등급" ,"이름","마일리지",  "연락처"};
		String info[][]= {
		};
		this.setSize(800, 600);
		this.setLayout(new FlowLayout());
		viplist= new DefaultTableModel(info,title){ public boolean isCellEditable(int i, int c){ return false; } };
		table= new JTable(viplist);
		 table.addMouseListener(new vipclick());
		JScrollPane sp= new JScrollPane(table);
		chartpanel=new JPanel();
		chartpanel.add(sp);
		
		nvbut=new JButton("비회원용 ");
		nvbut.setActionCommand("nv");
		nvbut.addActionListener(this);
		nvbut.setPreferredSize(new Dimension(110, 110));
		yvbut=new JButton("회원용 ");
		yvbut.setActionCommand("yv");
		yvbut.addActionListener(this);
		yvbut.setPreferredSize(new Dimension(110, 110));
		butpanel=new JPanel();
		butpanel.add(nvbut);
		butpanel.add(yvbut);
		
		add(chartpanel);
		add(butpanel);
		callData();
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void callData() // get vip list from vip.txt
	{
		File file =new File("vip.txt");
		if(file.exists()) {
			String line;
			String inputStr[] =new String[5];
			StringTokenizer st;
			Scanner inputStream= null;
			
			try {
				inputStream= new Scanner(new FileInputStream("vip.txt"));
			}
			catch(FileNotFoundException er) {
				er.printStackTrace();
			}
			
			while(inputStream.hasNextLine()) {
				int i=0;
				line = inputStream.nextLine();
				st= new StringTokenizer(line, " ");
				while(st.hasMoreTokens()) {
					inputStr[i++]=st.nextToken();
				}
				
				/*String levelStr = inputStr[4];
				for(int j=0;j<3;j++)
					inputStr[j+2]= inputStr[j+1];
				inputStr[1] =levelStr;*/
				viplist.addRow(inputStr);
			}
		}
	}
	public void fixvipData() ////원본은 테이블에서 눌러서 변경하는거
	{
		String inputStr[] = new String[5];
		PrintWriter outputStream =null;
					
		try {
			outputStream =new PrintWriter(new FileOutputStream("viptemp.txt",true));	
		}
		catch(FileNotFoundException er){
			er.printStackTrace();
		}
		
		//데이터 갱신작업 viplist 자체를 수정
		System.out.println(get[0]+get[1]+get[2]);
		for(int i=0;i<5;i++) table.setValueAt(get[i], r, i);
		
		//데이터만 갱신되있다면 알아서 다시 바꿔
		int cnt=0;
		for (Object o : viplist.getDataVector()) {
	        Vector v = (Vector) o;
	       
	        for(int i=0;i<5;i++) 
	        	inputStr[i] = (String)v.elementAt(i);
	       
	        double mileage =Double.parseDouble(inputStr[3]);
        	if(mileage<=500)
        		inputStr[1] ="일반";
        	else if(mileage>500 && mileage<=1000)
        		inputStr[1]= "골드";
        	else
        		inputStr[1]="플래티넘";
            
        	for(int i=0;i<4;i++)
        		outputStream.print(inputStr[i]+" ");
        	outputStream.println(inputStr[4]);
        	
        	
        	viplist.setValueAt(inputStr[1], cnt, 1);
        	cnt++;
	    }

		outputStream.close();
		File file =new File("vip.txt");
		file.delete();
		file =new File("viptemp.txt");
		File dest =new File("vip.txt");
		file.renameTo(dest); 
	
	}
	

	public void fixtableData()
    {
		int num;
		PrintWriter outputStream = null;
		String inputStr[] =new String[4];
		Scanner inputStream= null;
		StringTokenizer st;
		String plz=new String();	
		String tn=new String();
		
		try {
			inputStream= new Scanner(new FileInputStream("table.txt"));
			outputStream =new PrintWriter(new FileOutputStream("tempt.txt",true));	
		}
		catch(FileNotFoundException er){
			er.printStackTrace();
		}
		
		while(inputStream.hasNextLine() ) 
		{
			tn=inputStream.nextLine();
			st= new StringTokenizer(tn, " ");
  			while(st.hasMoreTokens()) 
  			{
  				plz=st.nextToken();
  				break;
  			}
			int comp=0;
			try
			{
			comp=Integer.parseInt(plz);
			
			if(Table.curbut != comp) 
			{
				outputStream.println(tn);
			}
			}catch(NumberFormatException e)
			{
				System.out.println("oops");
			}
			
		}
	inputStream.close();
	outputStream.close();
	
	File file =new File("table.txt");
	file.delete();
	file =new File("tempt.txt");
	File dest =new File("table.txt");
	file.renameTo(dest);      
	
	
    }
	
	public void vipupdate()
	{
		Vip.model.setRowCount(0);
		File file =new File("vip.txt");
		if(file.exists()) {
			String line;
			String inputStr[] =new String[5];
			StringTokenizer st;
			Scanner inputStream= null;
			
			try {
				inputStream= new Scanner(new FileInputStream("vip.txt"));
			}
			catch(FileNotFoundException er) {
				er.printStackTrace();
			}
			
			while(inputStream.hasNextLine()) {
				int i=0;
				line = inputStream.nextLine();
				st= new StringTokenizer(line, " ");
				while(st.hasMoreTokens()) {
					inputStr[i++]=st.nextToken();
				}
				
				/*String levelStr = inputStr[4];
				for(int j=0;j<3;j++)
					inputStr[j+2]= inputStr[j+1];
				inputStr[1] =levelStr;*/
				Vip.model.addRow(inputStr);
			}
		}
	}
	
	
	public void actionPerformed(ActionEvent e) 
	{
		String btnStr = e.getActionCommand();
		
		int cur,getmoney;
		// 현재 결제할려는 테이ㅇ블의 정보를 가져온다.
		//그 테이블을 이제 리셋셋팅한다 정보도 마찬기지 파일정보도 마찬가지 그리고 매출에 돈 업데이트 그리고버튼을 누르면결제프레임은 닫힌
		if(btnStr.equals("nv"))
		{
			cur=Table.curbut-1;
			getmoney=Table.collect[cur].price;
			
			//매출에 더해준다
			 StartRestaurant.mechul+=getmoney;		
			 StartRestaurant.todayIncome.setText("오늘 매출 "+StartRestaurant.mechul);
			 //테이블을 리셋해준다 그리고 파일에도 갱신한 여기는 테이블 파일만 갱신하면된다.
			Table.collect[cur]=new tableinfo(cur);
			fixtableData();
			this.dispose();
		}
		// 회원이므로 마일리지가 증가한다 회원 정보를 갱신해야한다.
		else if(btnStr.equals("yv"))
		{	
			double bonus=0;
			double temp=0,mile=0;
			cur=Table.curbut-1;
			getmoney=Table.collect[cur].price;
			
			
			//회원정보 얻어옴 + 마일리지작업 + 회원정보에 갱신 get배열로 이미 얻어
			// 할인 및 마일리지
			mile=getmoney*0.02;
			 if(get[1].equals("일반"))
			 {
				 temp=getmoney*(0.98);
			 }
			 else if(get[1].equals("골드"))
			 {
				 temp=getmoney*(0.95);
			 }
			 else if(get[1].equals("플래티넘"))
			 {
				 temp=getmoney*(0.9);
			 }
			 getmoney=(int)temp;
			 bonus=Double.parseDouble(get[3]);
			 bonus+=mile;
			 get[3]=""+bonus;
			 // 갱신 회원정보 파일
			 
			 fixvipData();
			 vipupdate(); // 회원탭도 갱신해줘야
			 StartRestaurant.mechul+=getmoney;		
			 StartRestaurant.todayIncome.setText("오늘 매출 "+StartRestaurant.mechul);
			 
			 
			//테이블을 리셋해준다 그리고 파일에도 갱신한 여기는 테이블 파일 
			Table.collect[cur]=new tableinfo(cur);
			fixtableData();
			this.dispose();
		}
	}
	
	
	
	
}
