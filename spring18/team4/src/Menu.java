import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
 
// 두 번째 탭
public class Menu extends JPanel implements ActionListener
{
	private int curvalue;
	private JTable table;
	private JTable table1;
	private DefaultTableModel model;
	static  DefaultTableModel kind;
	
	//재료 -> 메뉴단꼐에쓰이는 정보
	private JTextField mname;
	private JTextField mnumber;
	private JTextField mprice;
	
	//for fix
	private JTextField fname;
	private JTextField fprice;
	
	private JLabel curprice;
	
	private String[] deinfo;
	private TextArea infoprint;
	private TextArea kindprint;
	
	//click action for kind->menu;
	private class kindclick extends MouseAdapter {
		 
	    @Override
	    public void mouseClicked(MouseEvent e) 
	    {
	    		
	    		if (e.getButton() == 1)  //더블클
	    		{ 
	    			//System.out.println("1");
	    			String[] get=new String[3];
	    			int row,col;
	    			row=table1.getSelectedRow();
	    			col=table1.getSelectedColumn();
	    			
	    			for(int i=0;i<3;i++)
	    			{
	    				get[i]=(String)kind.getValueAt(row,i);
	    			}
	    			kindprint.append(get[1]+"("+get[2]+")"+"||");
	    			curvalue+=Integer.parseInt(get[2]);
	    			curprice.setText("총 재료 가격: "+curvalue);
	    			
	    		}
	    	} 
	}
	//click action for menu->detail
	private class menuclick extends MouseAdapter {
		 
	    @Override
	    public void mouseClicked(MouseEvent e) 
	    {
	    		
	    		if (e.getButton() == 1)  //더블클
	    		{ 
	    			int num;
	    			infoprint.setText("");
	    			
	    			String[] get=new String[4];
	    			int row,col;
	    			row=table.getSelectedRow();
	    			col=table.getSelectedColumn();
	    			
	    			for(int i=0;i<4;i++)
	    			{
	    				get[i]=(String)table.getValueAt(row,i);
	    			}
	    			
	    			num=Integer.parseInt(get[0]);
	    			infoprint.setText("번호 : "+get[0]+" 이름 : "+get[1]+"\n가격 : "+get[2]+" 생산단가 : "+get[3]+"\n사용된 재료 : "+deinfo[num]);
	    			
	    		}
	    	} 
	}
	
	
    public Menu(){
    		deinfo = new String[30];
		curvalue=0;
		JPanel formenu=new JPanel(new GridLayout(2,1));
    		String title[] = {"번호 ","음식 ","가격 ","생산단가 "};
		String info[][]= {};
		model = new DefaultTableModel(info,title){ public boolean isCellEditable(int i, int c){ return false; } };
         // 테이블 생성
         table=new JTable(model);
         table.addMouseListener(new menuclick());
         table.setPreferredSize(new Dimension(300,300));
         JScrollPane sp = new JScrollPane(table);
         sp.setPreferredSize(new Dimension(300,300));
         formenu.add(sp);
         JPanel forkind=new JPanel(new GridLayout(2,1));
        String title1[] = {"번호","재료","가격 "};
 		String info1[][]= {};
 		kind = new DefaultTableModel(info1,title1){ public boolean isCellEditable(int i, int c){ return false; } };
          // 테이블 생성
          table1=new JTable(kind);
          table1.addMouseListener(new kindclick());
          table1.setPreferredSize(new Dimension(200,300));
          JScrollPane sp1 = new JScrollPane(table1);
          sp1.setPreferredSize(new Dimension(200,300));
         forkind.add(sp1);
         
         // 추가버튼 패널생성 menu name menu price
         JPanel addPanel=new JPanel();
         addPanel.setLayout(new GridLayout(3,4));
         JButton resetbutton=new JButton("재료 리셋 ");
         resetbutton.setActionCommand("reset");
         resetbutton.addActionListener(this);
         JButton addbutton=new JButton("메뉴 추가 ");
         addbutton.setActionCommand("add");
         addbutton.addActionListener(this);
         
         //재료 -> 메뉴 수정
         JLabel nu=new JLabel("번호 ");
         JLabel na=new JLabel("음식 이름 ");
         JLabel pr=new JLabel("가격 ");
         curprice=new JLabel();
         mnumber=new JTextField(2);
         mname=new JTextField(5);
 	    	 mprice=new JTextField(5);
 	    	 
 	    	 addPanel.add(nu);
 	    	 addPanel.add(na);
 	    	 addPanel.add(pr);
 	    	 addPanel.add(mnumber);
 	    	 addPanel.add(mname);
 	    	 addPanel.add(mprice);
         addPanel.add(addbutton);
         addPanel.add(resetbutton);
         addPanel.add(curprice);
         // 상세정보창 패널 생성
         JPanel infoPanel=new JPanel();
         //상세정보창 버튼 패널 및 버튼 생성 
         JPanel infobuttonPanel=new JPanel();
         JButton fixbutton=new JButton("편집 ");
         fixbutton.setActionCommand("fix");
         fixbutton.addActionListener(this);
         JButton delbutton=new JButton("삭제 ");
         delbutton.setActionCommand("del");
         delbutton.addActionListener(this);
         //ㅈ편집용 텍스트필드
         fname=new JTextField(5);
         fprice=new JTextField(5);
         JLabel cn=new JLabel("이름 편집 ");
         JLabel cp=new JLabel("가격 편집 ");
         infobuttonPanel.setLayout(new GridLayout(3,2));
         infobuttonPanel.add(fixbutton);
         infobuttonPanel.add(delbutton);
         infobuttonPanel.add(cn);
         infobuttonPanel.add(cp);
         infobuttonPanel.add(fname);
         infobuttonPanel.add(fprice);
         //상세정보창 텍스트에리어 생성
         infoprint=new TextArea(10,10);
         infoprint.setText("");
         formenu.add(infoprint);
         //infoPanel.add(infoprint);
         infoPanel.add(infobuttonPanel);
        
         //재료정보창 텍스트에어리어생
         kindprint=new TextArea(10,30);
         forkind.add(kindprint);
         //좌표
//         kindprint.setBounds(500, 250, 100, 100);
//         addPanel.setBounds(600, 300, 100, 100);
         // 재료 추가용 입력 패널 및 입력 텍스트필드 생
         
         //addPanel.add(kindprint);
         add(formenu);  
         add(infoPanel);
         add(addPanel);
         add(forkind);
         
         makelist();
         callData();
     }
    
    public void callData()
   	{
   		File file =new File("menu.txt");
   		if(file.exists()) {
   			String line;
   			String inputStr[] =new String[4];
   			StringTokenizer st;
   			Scanner inputStream= null;
   			
   			try {
   				inputStream= new Scanner(new FileInputStream("menu.txt"));
   			}
   			catch(FileNotFoundException er) {
   				er.printStackTrace();
   			}
   			
   			while(inputStream.hasNextLine()) {
   				int i=0,num;
   				line = inputStream.nextLine();
   				st= new StringTokenizer(line, " ");
   				while(st.hasMoreTokens()) {
   					if(i==4) 
   					{
   						num=Integer.parseInt(inputStr[0]);
   						deinfo[num]=st.nextToken();
   					}
   					else inputStr[i++]=st.nextToken();
   				}
   				this.model.addRow(inputStr);
   			}
   			inputStream.close();
   		}
   		
   	}
    //재료 리스트 일단 만들
    public static void makelist()
    {
    		String line;
		String inputStr[] =new String[5];
		String getStr[]=new String[3];
		StringTokenizer st;
		Scanner inputStream= null;
		
		kind.setRowCount(0);
		
		File file =new File("storage.txt");
		if(file.exists())
		{
		try {
			
			inputStream= new Scanner(new FileInputStream("storage.txt"));
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
			getStr[2]=inputStr[4];
			kind.addRow(getStr);
		}
		inputStream.close();
		}
    }
    
    public void addData()
    {
    		int num;
    		PrintWriter outputStream = null;
		String inputStr[] =new String[4];
	
		try {
			String fileName ="menu.txt";
			outputStream =new PrintWriter(new FileOutputStream(fileName,true));	
		}
				
		catch(FileNotFoundException er){
			System.out.println("Error opening menu.txt");
			er.printStackTrace();
		}
		
		//파일에도 한줄씩 저장해주고 있다.
		inputStr[0]=mnumber.getText();
		outputStream.print(inputStr[0]+" ");
		inputStr[1]=mname.getText();
		outputStream.print(inputStr[1]+" ");
		inputStr[2]=mprice.getText();
		outputStream.print(inputStr[2]+" ");
		inputStr[3]=curvalue+"";
		outputStream.print(inputStr[3]+" ");
		num=Integer.parseInt(inputStr[0]);
		deinfo[num]=kindprint.getText();
		outputStream.println(deinfo[num]);
		kindprint.setText("");
		mnumber.setText("");
		mname.setText("");
		mprice.setText("");
		outputStream.close();
		
		//프론트에 추가
		this.model.addRow(inputStr);
		curvalue=0;
		Table.makemlist();
		
    }
   
    public void delData()
    {
    		if(table.getSelectedRow() == -1)
			return ;
		else {
			Scanner inputStream= null;
			PrintWriter outputStream =null;
			
			try {
				inputStream= new Scanner(new FileInputStream("menu.txt"));
				outputStream =new PrintWriter(new FileOutputStream("tempm.txt",true));	
			}
			catch(FileNotFoundException er){
				er.printStackTrace();
			}
			
			/* 파일에 저장된 데이터도 삭제*/
			int i=0;
			while(inputStream.hasNextLine() ) {
				if(i !=  table.getSelectedRow()) {
					String line = inputStream.nextLine();
					outputStream.println(line);
				}
				else
					inputStream.nextLine();
				i++;	
			}
			outputStream.close();
			
			// 파일 업데이트 되는 부분
			File file =new File("menu.txt");
			file.delete();
			file =new File("tempm.txt");
			File dest =new File("menu.txt");
			file.renameTo(dest);                
			
			//프론트에서 삭제
			this.model.removeRow(table.getSelectedRow());
		}
    	 Table.makemlist();
    }
    
    public void fixData()
    {
    	String inputStr[] = new String[4];
		String[] get=new String[3];
		int row,col;
		row=table.getSelectedRow();
		col=table.getSelectedColumn();
		
		
		get[1]=fname.getText();
		get[2]=fprice.getText();
		
		for(int i=1;i<3;i++)
		{
			if(get[i].equals("")==false)
			{
				table.setValueAt(get[i],row,i);
			}
		}
		fname.setText("");
		fprice.setText("");
		
		PrintWriter outputStream =null;
		try {
			outputStream =new PrintWriter(new FileOutputStream("temp.txt",true));	
		}
		catch(FileNotFoundException er){
			er.printStackTrace();
		}
		//파일에 적용
		for (Object o : model.getDataVector()) {
	        Vector v = (Vector) o;
	        //System.out.println(v);
	        for(int i=0;i<4;i++) {
	        	inputStr[i] = (String)v.elementAt(i);
	     
	        	outputStream.print(inputStr[i]+" ");
	        }
	        int num=Integer.parseInt(inputStr[0]);
//	        inputStr[4] =(String)v.elementAt(4);
	       outputStream.println(deinfo[num]);
	    }
		outputStream.close();
		File file =new File("Menu.txt");
		file.delete();
		file =new File("temp.txt");
		File dest =new File("Menu.txt");
		file.renameTo(dest); 
		 Table.makemlist();
    }
    public  void reData()
    {
    	mname.setText("");
    	mnumber.setText("");
    	mprice.setText("");
    	curprice.setText("");
    }
   
    
    public void actionPerformed(ActionEvent e) {
		String btnStr = e.getActionCommand();
		if(btnStr.equals("add"))
		{
			addData();
			
		}
		else if(btnStr.equals("del"))
		{
			delData();
			
		}
		else if(btnStr.equals("fix"))
		{
			fixData();
			
		}
		else if(btnStr.equals("reset"))
		{
			reData();
			
		}
			
		
	}
 
}

