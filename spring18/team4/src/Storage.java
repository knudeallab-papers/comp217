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


public class Storage extends JPanel implements ActionListener{ 
	private JTextField number;
	private JTextField name;
	private JTextField value;
	private JTextField ord;
	private JTextField can;
	private TextArea infoprint;

	private JTable table;
	private JScrollPane sp;
	public static DefaultTableModel model;
	
	private class MyMouseListener extends MouseAdapter {
		 
	    @Override
	    public void mouseClicked(MouseEvent e) 
	    {
	    		
	    		if (e.getButton() == 1) 
	    		{ 
	    			infoprint.setText("");
	    			
	    			String[] get=new String[5];
	    			int row,col;
	    			row=table.getSelectedRow();
	    			col=table.getSelectedColumn();
	    			
	    			for(int i=0;i<5;i++)
	    			{
	    				get[i]=(String)table.getValueAt(row,i);
	    			}
	    			
	    			
	    			infoprint.setText("번호 : "+get[0]+" 이름 : "+get[1]+" 가격 : "+get[4]+"\n재고 : "+get[2]+" 주문량 : "+get[3]+"\nKNU 식품 - 품질 SSS");
	    		}
	    	} 
	   
	  
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public DefaultTableModel getModel() {
		return model;
	}
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	// 첫 번째 탭 
	public Storage()
	{
		
	    String title[] = {"번호","이름 ", "재고 ", "주문량 ","가격 "};
	    String info[][]= {};
		model = new DefaultTableModel(info,title){ public boolean isCellEditable(int i, int c){ return false; } };
	    // 테이블 생성
	    table = new JTable(model);
	    table.addMouseListener(new MyMouseListener());
	    sp = new JScrollPane(table); 
	    //데이터 입력 부분 +파일 저장,불러오기  + 메뉴 추가
	    JPanel inputpanel=new JPanel();
	    inputpanel.setLayout(new GridLayout(3,3));
	    JLabel num=new JLabel("번호 ");
	    JLabel nam=new JLabel("이름  ");
	    JLabel va=new JLabel("가격 ");
	    inputpanel.add(num);
	    inputpanel.add(nam);
	    inputpanel.add(va);
	    number=new JTextField(2);
	    name=new JTextField(5);
	    value=new JTextField(5);
	   
	    inputpanel.add(number);
	    inputpanel.add(name);
	    inputpanel.add(value);
	    
//	    JPanel inputpanel2=new JPanel();
//	    inputpanel2.setLayout(new FlowLayout());
	    ord=new JTextField(3);
	    can=new JTextField(3);
//	    inputpanel2.add(ord);
//	    inputpanel2.add(can);
	    
	 // 패널 추가버 삭제버튼 
        
        JButton addbutton=new JButton("추가 ");
        addbutton.setActionCommand("add");
        addbutton.addActionListener(this);
        inputpanel.add(addbutton);
        JButton delbutton=new JButton("삭제 ");
        delbutton.setActionCommand("del");
        delbutton.addActionListener(this);
        inputpanel.add(delbutton);
        // 상세정보창 패널 생성
        //JPanel infoPanel=new JPanel();
        //상세정보창 버튼 패널 및 버튼 생성 
        JPanel infoandinfo=new JPanel();
        infoandinfo.setLayout(new GridLayout(2,1));
        JPanel infobuttonPanel=new JPanel();
        infobuttonPanel.setLayout(new GridLayout(2,2));
        JButton orderbutton=new JButton("주문 ");
        orderbutton.setActionCommand("order");
        orderbutton.addActionListener(this);
        JButton cancelbutton=new JButton("주문 취소 ");
        cancelbutton.setActionCommand("cancel");
        cancelbutton.addActionListener(this);
        infobuttonPanel.add(orderbutton);
        infobuttonPanel.add(cancelbutton);
        infobuttonPanel.add(ord);
        infobuttonPanel.add(can);
        //상세정보창 텍스트에리어 생성
        infoprint=new TextArea(10,20);
        infoprint.setText("재료 정보 ");
        //infoPanel.add(infoprint);
        //infoPanel.add(infobuttonPanel);
        //inputpanel.setBounds(750, 700, 200, 50);
        infoandinfo.add(infoprint);
        infoandinfo.add(infobuttonPanel);

        
        add(inputpanel);
	    add(sp);  
	    add(infoandinfo);
	    
	    //this.setVisible(true);
	    
	    /* 파일에 저장된 정보 들 불러오기 */
	    callData();
	    
	  
	}
	public void callData()
	{
		File file =new File("storage.txt");
		if(file.exists()) {
			String line;
			String inputStr[] =new String[5];
			StringTokenizer st;
			Scanner inputStream= null;
			
			
			try {
				inputStream= new Scanner(new FileInputStream("storage.txt"));
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
				this.model.addRow(inputStr);
			}
		}
		
	}
	public void addData()
	{
		PrintWriter outputStream = null;
		String inputStr[] =new String[5];
	
		try {
			String fileName ="storage.txt";
			outputStream =new PrintWriter(new FileOutputStream(fileName,true));	
		}
				
		catch(FileNotFoundException er){
			System.out.println("Error opening storage.txt");
			er.printStackTrace();
		}
		
		//파일에도 한줄씩 저장해주고 있다.
		inputStr[0]= number.getText();
		outputStream.print(inputStr[0]+" ");
		inputStr[1]= name.getText();
		outputStream.print(inputStr[1]+" ");
		inputStr[2]="0"; //재고 
		outputStream.print(inputStr[2]+" ");
		inputStr[3]="0"; //주문량 
		outputStream.print(inputStr[3]+" ");
		inputStr[4]= value.getText();
		outputStream.println(inputStr[4]);
		outputStream.close();
		
		//프론트에 추가
		this.model.addRow(inputStr);
		number.setText("");
		name.setText("");
		value.setText("");
		Menu.makelist();

		
	}
	
	public void delData()
	{
		if(table.getSelectedRow() == -1)
			return ;
		else {
			Scanner inputStream= null;
			PrintWriter outputStream =null;
			
			try {
				inputStream= new Scanner(new FileInputStream("storage.txt"));
				outputStream =new PrintWriter(new FileOutputStream("temps.txt",true));	
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
			File file =new File("storage.txt");
			file.delete();
			file =new File("temps.txt");
			File dest =new File("storage.txt");
			file.renameTo(dest);                
			
			//프론트에서 삭제
			this.model.removeRow(table.getSelectedRow());
			Menu.makelist();
		}
		
	}
	
	public void ordData()
	{
		String inputStr[] = new String[5];
		String get;
		int row,col;
		row=table.getSelectedRow();
		col=table.getSelectedColumn();
		
		get=(String)table.getValueAt(row, 3);
		
		int remain=Integer.parseInt(get);
		int plus=Integer.parseInt(ord.getText());
		remain+=plus;
		get=remain+"";
		ord.setText("");
		
		table.setValueAt(get,row,3);
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
	        	//System.out.print(inputStr[i]+" ");
	        	outputStream.print(inputStr[i]+" ");
	        }
	        inputStr[4] =(String)v.elementAt(4);
	       outputStream.println(inputStr[4]);
	    }
		outputStream.close();
		File file =new File("storage.txt");
		file.delete();
		file =new File("temp.txt");
		File dest =new File("storage.txt");
		file.renameTo(dest); 
	}
	public void canData()
	{
		String inputStr[] = new String[5];
		String get;
		int row,col;
		row=table.getSelectedRow();
		col=table.getSelectedColumn();
		
		get=(String)table.getValueAt(row, 3);
		
		int remain=Integer.parseInt(get);
		int minus=Integer.parseInt(can.getText());
		remain-=minus;
		get=remain+"";
		can.setText("");
		
		table.setValueAt(get,row,3);
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
	        	//System.out.print(inputStr[i]+" ");
	        	outputStream.print(inputStr[i]+" ");
	        }
	        inputStr[4] =(String)v.elementAt(4);
	       outputStream.println(inputStr[4]);
	    }
		outputStream.close();
		File file =new File("storage.txt");
		file.delete();
		file =new File("temp.txt");
		File dest =new File("storage.txt");
		file.renameTo(dest); 
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
		else if(btnStr.equals("order"))
		{
			//System.out.println("1");
			ordData();
			
		}
		else if(btnStr.equals("cancel"))
		{
			//System.out.println("2");
			canData();
			
		}
	}
}
