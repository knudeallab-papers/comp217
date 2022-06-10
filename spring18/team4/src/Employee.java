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
import java.io.IOException;
import java.io.OutputStream;
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


public class Employee extends JPanel implements ActionListener{
	
	public Employee() {
		String title[] = {"번호", "이름" ,"급여","직급", "입사일", "연락처"};
		String info[][]= {
		};
		DefaultTableModel model = new DefaultTableModel(info,title);
		JTable table= new JTable(model);
		JScrollPane sp= new JScrollPane(table);
		
		//getContentPane().setBackground(Color.YELLOW);
		JPanel totalPanel =new JPanel(new GridLayout(3, 1));
		//totalPanel.setBackground(Color.red);
		JPanel namePanel =new JPanel();
		namePanel.setLayout(new GridLayout(1,5));
		JLabel number= new JLabel("번호");
		JLabel name= new JLabel("이름");
		JLabel pay= new JLabel("급여");
		JLabel position= new JLabel("직급");
		JLabel phone= new JLabel("연락처");
		namePanel.add(number);
		namePanel.add(name);
		namePanel.add(pay);
		namePanel.add(position);
		namePanel.add(phone);
		//namePanel.setBackground(Color.RED);
		//namePanel.setLocation(namePanel.get, namePanel.getY());
		totalPanel.add(namePanel);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,5));
		
		JTextField numberfield =new JTextField(5);
		JTextField namefield = new JTextField(5);
		JTextField payfield = new JTextField(5);
		JTextField positionfield = new JTextField(5);
		//JTextField datefield = new JTextField(10);
		JTextField phonefield = new JTextField(10);
		
		panel.add(numberfield);
		panel.add(namefield);
		panel.add(payfield);
		panel.add(positionfield);
		//panel.add(datefield);
		panel.add(phonefield);
		
		
		/* 파일에 저장된 직원들 불러오기 */
		File file =new File("employee.txt");
		if(file.exists()) {
			String line;
			String inputStr[] =new String[6];
			StringTokenizer st;
			Scanner inputStream= null;
			
			try {
				inputStream= new Scanner(new FileInputStream("employee.txt"));
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
				model.addRow(inputStr);
			}
		}
		
		//추가기능, 파일에도 저장됨
		JButton addBtn =new JButton("추가");
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PrintWriter outputStream = null;
				String inputStr[] =new String[6];
			
				try {
					String fileName ="employee.txt";
					outputStream =new PrintWriter(new FileOutputStream(fileName,true));	
				}
						
				catch(FileNotFoundException er){
					System.out.println("Error opening employee.txt");
					er.printStackTrace();
				}
				
				//파일에도 한줄씩 저장해주고 있다.
				inputStr[0]= numberfield.getText();
				outputStream.print(inputStr[0]+" ");
				inputStr[1]= namefield.getText();
				outputStream.print(inputStr[1]+" ");
				inputStr[2]= payfield.getText();
				outputStream.print(inputStr[2]+" ");
				inputStr[3]= positionfield.getText();
				outputStream.print(inputStr[3]+" ");
				SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy.M.d", Locale.KOREA );
				Date currentTime = new Date ();
				String mTime = mSimpleDateFormat.format ( currentTime );
				inputStr[4]= mTime;
				outputStream.print(inputStr[4]+" ");
				inputStr[5]= phonefield.getText();
				outputStream.println(inputStr[5]);
				
				outputStream.close();
				
				//프론트에 추가
				model.addRow(inputStr);
		
				numberfield.setText("");
				namefield.setText("");
				payfield.setText("");
				positionfield.setText("");
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
						inputStream= new Scanner(new FileInputStream("employee.txt"));
						outputStream =new PrintWriter(new FileOutputStream("emptemp.txt",true));	
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
					File file =new File("employee.txt");
					file.delete();
					file =new File("emptemp.txt");
					File dest =new File("employee.txt");
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
				String inputStr[] = new String[6];
				PrintWriter outputStream =null;
				
				try {
					outputStream =new PrintWriter(new FileOutputStream("emptemp.txt",true));	
				}
				catch(FileNotFoundException er){
					er.printStackTrace();
				}
				
				//파일에 적용
				for (Object o : model.getDataVector()) {
			        Vector v = (Vector) o;
			        //System.out.println(v);
			        for(int i=0;i<5;i++) {
			        	inputStr[i] = (String)v.elementAt(i);
			        	//System.out.print(inputStr[i]+" ");
			        	outputStream.print(inputStr[i]+" ");
			        }
			        inputStr[5] =(String)v.elementAt(5);
			       // System.out.println(inputStr[5]);
			       outputStream.println(inputStr[5]);
			    }
				outputStream.close();
				File file =new File("employee.txt");
				file.delete();
				file =new File("emptemp.txt");
				File dest =new File("employee.txt");
				file.renameTo(dest); 
			}
		});
		
		JPanel btnPanel = new JPanel();
		btnPanel.add(addBtn);
		btnPanel.add(delBtn);
		btnPanel.add(editBtn);
		
	   /* panel.add(addBtn);
		panel.add(delBtn);
		panel.add(editBtn);*/
		
		totalPanel.add(panel);
		totalPanel.add(btnPanel);
		add(sp);
		add(totalPanel,BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
