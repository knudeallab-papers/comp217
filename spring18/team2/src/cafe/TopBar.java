package cafe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import cafe.SmallBar.ExitAct;

import javax.swing.*;
import java.util.Calendar;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TopBar extends JPanel 
{
	public static final int WIDTH = 930;
	public static final int HEIGHT = 150;
	public static final int SWIDTH = 640;
	public static final int SHEIGHT = 110;
	
	public Color midGray = new Color(180, 180, 180);
	public Color darkGray = new Color(100, 100, 100);
	
	private DateData dd = new DateData();
	JTextField date = new JTextField();
	
	private MoneyData md;
	private JPanel small;
	
	private JTextField balance;
	private JTextField profit;
	
	private Font topFont = new Font("SanSerif", Font.BOLD, 14);
	
	public static final String srcPath = "src";
	public static final String packageName = "cafe";
	
	public TopBar()
	{
		super();
		setSize(WIDTH, HEIGHT);
		setBackground(midGray);

//		EtchedBorder eborder = new EtchedBorder(EtchedBorder.LOWERED);
//		this.setBorder(eborder);
		setSmallBar();
		setDefault();
//		setTopIcon();
		
	}
	public void setTopIcon()
	{
		String currentPath = "";
		try
		{
			currentPath = new File(".").getCanonicalPath();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		String CupFileName = "Cup.png";
		String CupFilePath = currentPath + "/" + srcPath + "/" + packageName + "/" + CupFileName;
		ImageIcon CupIcon = new ImageIcon(CupFilePath);
		
		JLabel CupLab = new JLabel();
		CupLab.setIcon(CupIcon);
		CupLab.setSize(110, 130);
		CupLab.setBackground(midGray);
//		CupLab.setOpaque(false);
		add(CupLab);
		CupLab.setLocation(10, 10);
		
		String LogoFileName = "Logo.png";
		String LogoFilePath = currentPath + "/" + srcPath + "/" + packageName + "/" + LogoFileName;
		ImageIcon LogoIcon = new ImageIcon(LogoFilePath);
		
		JLabel LogoLab = new JLabel();
		LogoLab.setIcon(LogoIcon);
		LogoLab.setSize(140, 120);
		LogoLab.setBackground(midGray);
//		CupLab.setOpaque(false);
		add(LogoLab);
		LogoLab.setLocation(110, 40);
		
	}
	public void setMoneyData(MoneyData m)
	{
		md = m;
		update();
	}
	private void setDefault()
	{
		JLabel dateLab = new JLabel("영업 일자:");
		dateLab.setForeground(Color.WHITE);
		dateLab.setFont(topFont);
		JLabel today = new JLabel("오늘 매출:");
		today.setForeground(Color.WHITE);
		today.setFont(topFont);
		JLabel total = new JLabel("전체 잔고:");
		total.setForeground(Color.WHITE);
		total.setFont(topFont);
		
		dateLab.setSize(60, 40);
		today.setSize(60, 40);
		total.setSize(60, 40);
		
		small.add(dateLab);
		small.add(today);
		small.add(total);
		
		today.setLocation(200, 30);
		total.setLocation(390, 30);
		dateLab.setLocation(10, 30);
		
		
		balance = new JTextField();
		balance.setSize(110, 40);
		small.add(balance);
		balance.setLocation(450, 30);
		balance.setEditable(false);
		
		profit = new JTextField();
		profit.setSize(110, 40);
		small.add(profit);
		profit.setLocation(260, 30);
		balance.setEditable(false);
		
	}
	public void update()
	{
		balance.setText(Double.toString(md.getMoneyData()));
		profit.setText(Double.toString(md.getDaySaleData()));
	}
	public void getMoneyFile()
	{
		md = new MoneyData();
		try
		{
			ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream("MoneyData.dat"));
			
			md = (MoneyData)inputStream.readObject();
			
			
			inputStream.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	public void setSmallBar()
	{
		small = new JPanel();
		small.setSize(640, 110);
		small.setBackground(darkGray);
		add(small);
		small.setLocation(260, 20);

//		//날짜 파일 만들기 
//		try
//		{
//			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("DateData.dat"));
//			
//			outputStream.writeObject(dd);
//			
//			outputStream.close();
//		}
//		catch(IOException e1)
//		{
//			e1.printStackTrace();
//		}
		
		//파일에 저장되어있는 자료넣기 
		try
		{
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("DateData.dat"));
											
			dd = (DateData)inputStream.readObject();
									
			inputStream.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		JButton nextDay = new JButton("마감");
		
		date.setSize(110, 40);
		small.add(date);
		date.setLocation(70, 30);
		date.setEditable(false);
		
		nextDay.setSize(50, 20);
		small.add(nextDay);
		nextDay.setLocation(580, 40);
		
		nextDay.addActionListener(new nextDayActionListener());

		date.setText(dd.getDate());
		
	}
	private boolean tableCheck()
	{
		for(int n = 0; n < 7; n++)
		{
			if(Actions.getisEmpty(n) == true)
			{
				return false;
			}
		}
		return true;
	}
	
	public class nextDayActionListener implements ActionListener
	{
		EmployeeData ed = new EmployeeData();
		MemberData memd = new MemberData();
		int day;
		
		public void actionPerformed(ActionEvent e)
		{
			
			if(tableCheck())
			{
			
				//날짜 바꾸기 
				dd.setNextDate();
				date.setText(dd.getDate());
				day = dd.getDay();
				
				if(day == 1) // 매월 1일 마일리지 초기화 
				{
					try
					{
						ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("MemberData.dat"));
												
						memd = (MemberData)inputStream.readObject();
										
						inputStream.close();
					}
					catch(FileNotFoundException e1)
					{
						e1.printStackTrace();
					}
					catch(IOException e1)
					{
						e1.printStackTrace();
					} 
					catch (ClassNotFoundException e1) 
					{
						e1.printStackTrace();
					}
					
					String list[][] = new String[5][100];
					list = memd.getTableElement();
					for(int i = 0; i < 100; i++)
					{
						if(list[i][0] == null)
						{
							break;
						}
						list[i][1] = "일반";
						list[i][3] = "0";
					}
					memd.setTableElement(list);
					
					try
					{
						ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("MemberData.dat"));
						
						//dd.set
						outputStream.writeObject(memd);
						outputStream.close();
					}
					catch(IOException e1)
					{
						e1.printStackTrace();
					}
				}
				
				//직원 급여
				try
				{
					ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("EmployeeData.dat"));
													
					ed = (EmployeeData)inputStream.readObject();
											
					inputStream.close();
				}
				catch(FileNotFoundException e1)
				{
					e1.printStackTrace();
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				} 
				catch (ClassNotFoundException e1) 
				{
					e1.printStackTrace();
				}
				
				String[][] arr = new String[100][6];
				arr = ed.getTableElement();
				for(int i = 0; i < 100; i++)
				{
					if(arr[i][0] == null)
					{
						break;
					}
					md.spendMoney(Double.parseDouble(arr[i][2]));
				}
				
				//날짜랑 돈 파일에 저장
				
				//날짜 파일쓰기 
				try
				{
					ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("DateData.dat"));
					
					//dd.set
					outputStream.writeObject(dd);
					outputStream.close();
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
				
				Actions.EndingDay();
				Actions.resetCurSale();
				md.end();
				update();

				
				//돈파일 쓰기
				try
				{
					ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("MoneyData.dat"));
				
					//dd.set
					outputStream.writeObject(md);
					outputStream.close();
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
			
			}
			else
			{
				JOptionPane.showMessageDialog(null, "ERROR : 테이블/포장을 비워주세요");
			}
		}
	 }
}
