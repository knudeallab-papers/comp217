import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Vip extends JPanel implements ActionListener{
	private int id;
	private String name;
	private double pay;
	private String position;
	private String date_in;
	private String phone;
	static DefaultTableModel model;
	static JTable table;
	
	public Vip() {
		String title[] = {"번호", "등급" ,"이름","마일리지",  "연락처"};
		String info[][]= {
		};
		model = new DefaultTableModel(info,title);
		table= new JTable(model);
		JScrollPane sp= new JScrollPane(table);
		JPanel vi=new JPanel(new GridLayout(2,1));
		vi.add(sp);
		JLabel help=new JLabel("위의 테이블 항목을 더블클릭하시면 내용이 수정 가능. 그후 편집 버튼을 눌러주세요. ");
		//vi.add(help);
		
		JPanel totalPanel = new JPanel(new GridLayout(3, 1));
		JPanel namePanel = new JPanel(new GridLayout(1, 5));
		JLabel lnum =new JLabel("번호");
		JLabel llevel =new JLabel("등급");
		JLabel lname =new JLabel("이름");
		JLabel lmileage =new JLabel("마일리지");
		JLabel lphone =new JLabel("연락처");
		namePanel.add(lnum);
		namePanel.add(llevel);
		namePanel.add(lname);
		namePanel.add(lmileage);
		namePanel.add(lphone);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 5));
		
		JTextField numberfield =new JTextField(5);
		JTextField levelfield = new JTextField(5);
		JTextField namefield = new JTextField(5);
		JTextField mileagefield = new JTextField(5);
		JTextField phonefield = new JTextField(10);
		
		panel.add(numberfield);
		panel.add(levelfield);
		panel.add(namefield);
		panel.add(mileagefield);
		panel.add(phonefield);
		
		//파일로부터 불러오기 기능
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
				model.addRow(inputStr);
			}
		}
		
		
		//추가 기능
		JButton addBtn =new JButton("추가");
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PrintWriter outputStream = null;
				String inputStr[] =new String[5];
			
				try {
					String fileName ="vip.txt";
					outputStream =new PrintWriter(new FileOutputStream(fileName,true));	
				}
						
				catch(FileNotFoundException er){
					System.out.println("Error opening vip.txt");
					er.printStackTrace();
				}
				
				//파일에도 한줄씩 저장해주고 있다.
				inputStr[0]= numberfield.getText();
				//outputStream.print(inputStr[0]+" ");
				inputStr[2]= namefield.getText();
				//outputStream.print(inputStr[2]+" ");
				inputStr[3]= mileagefield.getText();
				//outputStream.print(inputStr[3]+" ");
				inputStr[4]= phonefield.getText();
				//outputStream.print(inputStr[4]+" ");
				inputStr[1] = levelfield.getText();
				
				int mileage = Integer.valueOf(inputStr[3]);
				if(mileage<=500)
					inputStr[1] ="일반";
				else if(mileage>500 && mileage<=1000)
					inputStr[1]= "골드";
				else
					inputStr[1] = "플래티넘";
				//outputStream.println(inputStr[1]);
				
				for(int i=0;i<4;i++)
					outputStream.print(inputStr[i]+" ");
				outputStream.println(inputStr[4]);
				
				outputStream.close();
				
				//프론트에 추가
				model.addRow(inputStr);
		
				numberfield.setText("");
				namefield.setText("");
				mileagefield.setText("");
				levelfield.setText("");
				//datefield.setText("");
				phonefield.setText("");
			}
		});
		
		//삭제기능 , 파일에서도 삭제됨
		JButton delBtn = new JButton("삭제");
		delBtn.addActionListener(new ActionListener() {
					
		@Override
		public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
						
			if(table.getSelectedRow() == -1)
					return;
			else {
				Scanner inputStream= null;
				PrintWriter outputStream =null;
							
				try {
					inputStream= new Scanner(new FileInputStream("vip.txt"));
					outputStream =new PrintWriter(new FileOutputStream("viptemp.txt",true));	
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
				File file =new File("vip.txt");
				file.delete();
				file =new File("viptemp.txt");
				File dest =new File("vip.txt");
				file.renameTo(dest);                
							
				//프론트에서 삭제
				model.removeRow(table.getSelectedRow());
			}
		}
		});
		
		//편집기능, 파일에도 적용
		JButton editBtn =new JButton("편집");
		editBtn.addActionListener(new ActionListener() {
					
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String inputStr[] = new String[5];
			PrintWriter outputStream =null;
						
			try {
				outputStream =new PrintWriter(new FileOutputStream("viptemp.txt",true));	
			}
			catch(FileNotFoundException er){
				er.printStackTrace();
			}
						
			int cnt=0;
			for (Object o : model.getDataVector()) {
		        Vector v = (Vector) o;
		        //System.out.println(v);
		        for(int i=0;i<5;i++) 
		        	inputStr[i] = (String)v.elementAt(i);
		       
		        int mileage = Integer.valueOf(inputStr[3]);
	        	if(mileage<=500)
	        		inputStr[1] ="일반";
	        	else if(mileage>500 && mileage<=1000)
	        		inputStr[1]= "골드";
	        	else
	        		inputStr[1]="플래티넘";
	            
	        	for(int i=0;i<4;i++)
	        		outputStream.print(inputStr[i]+" ");
	        	outputStream.println(inputStr[4]);
	        	
	        	
	        	model.setValueAt(inputStr[1], cnt, 1);
	        	cnt++;
		    }
	
			outputStream.close();
			File file =new File("vip.txt");
			file.delete();
			file =new File("viptemp.txt");
			File dest =new File("vip.txt");
			file.renameTo(dest); 
		}
		});
		
		JPanel btnPanel = new JPanel();
		btnPanel.add(addBtn);
		btnPanel.add(delBtn);
		btnPanel.add(editBtn);
		//btnPanel.add(help);
		
		totalPanel.add(namePanel);
		totalPanel.add(panel);
		totalPanel.add(btnPanel);
		
		add(sp,BorderLayout.CENTER);
		add(totalPanel,BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}