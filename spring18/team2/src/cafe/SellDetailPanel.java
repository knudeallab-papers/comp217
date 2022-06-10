package cafe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import cafe.InvenDetailPanel.func;

public class SellDetailPanel extends JPanel 
{
	private static final int WIDTH = 450;
	private static final int HEIGHT = 370;
	private static final int TableNum = 7;
	
	private SellDetail Detail[] = new SellDetail[TableNum];
	private SellDetail PackingDetail[];
	
	public Color darkGray = new Color(100, 100, 100);
	//회원정보 
	// 합계 라벨 
	private SellDetail cur;
	private int curNum;
	private TableDataSet data;
	
	private JButton AllCancle;
	private JButton ClickedCancle;
	private JButton minusBtn;
	private JButton plusBtn;
	private JButton payBtn;
	
	
	public SellDetailPanel()
	{
		super();
		setSize(WIDTH, HEIGHT);
		
//		setBackground(darkGray);
		
		getMenuFile();
		
		
		setBtn();

		setSellDefault();
		
		
	}
	public void getMenuFile()
	{
		data = new TableDataSet();
		try
		{
			ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream("TableData.dat"));
			
			data = (TableDataSet)inputStream.readObject();
			
			
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
	public void setMenuFile()
	{
		for(int c1 = 0; c1 < TableNum; c1++)
		{
			data.getData(c1).totalNum = Detail[c1].tNum;
			data.getData(c1).totalCost = Detail[c1].tCost;
			data.getData(c1).discountRate = Detail[c1].dis;
			data.getData(c1).finalCost = Detail[c1].finalC;
			data.getData(c1).member = Detail[c1].mem; //되나?
			
//			for(int c2 = 0; c2 < 5; c2++)
//			{
//				data.getData(c1).member[c2] = Detail[c1].mem[c2];
//			}
			
			int row = Detail[c1].model.getRowCount();
			data.getData(c1).rowNum = row;
			
			for(int c2 = 0; c2 < row; c2++)
			{
				for(int c3 = 0; c3 < 4; c3++)
				{
					data.getData(c1).contents[c2][c3] = (String)Detail[c1].model.getValueAt(c2, c3);
				}
			}
			
			
			//Detail 안 테이블 정보랑 rowNum을 넘겨야함.
			
			
			
		}
		try
		{
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("TableData.dat"));
			outputStream.writeObject(data);
			outputStream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public SellDetail curDetail()
	{
		return cur;
	}
	public boolean isEmpty(int n)
	{
		if(Detail[n].tNum == 0)
		{
			return false;
		}
		else
		{
			return true;
			
		}
	
	}
	private void setBtn()
	{
		AllCancle = new JButton("전체취소");
		AllCancle.setSize(78, 30);
		add(AllCancle);
		AllCancle.setLocation(10, 330);
		AllCancle.addActionListener(new func());
		
		ClickedCancle = new JButton("선택취소");
		ClickedCancle.setSize(78, 30);
		add(ClickedCancle);
		ClickedCancle.setLocation(98, 330);
		ClickedCancle.addActionListener(new func());
		
		minusBtn = new JButton("-");
		minusBtn.setSize(78, 30);
		add(minusBtn);
		minusBtn.setLocation(186, 330);
		minusBtn.addActionListener(new func());
		
		plusBtn = new JButton("+");
		plusBtn.setSize(78, 30);
		add(plusBtn);
		plusBtn.setLocation(274, 330);
		plusBtn.addActionListener(new func());
		
		payBtn = new JButton("결제");
		payBtn.setSize(78, 30);
		add(payBtn);
		payBtn.setLocation(362, 330);
		payBtn.addActionListener(new func());
	}
	private void setDummy()
	{
		String t[][] = null;
		cur = new SellDetail(t, 0);
		add(cur);
		cur.setLocation(0, 0);
	}
	private void setSellDefault()
	{
		setDummy();
		for(int c1 = 0; c1 < TableNum; c1++)
		{
			TableData t = data.getData(c1);
			
			
			Detail[c1] = new SellDetail(t.contents, t.rowNum);
			Detail[c1].tNum = t.totalNum;
			Detail[c1].tCost = t.totalCost;
			Detail[c1].dis = t.discountRate;
			Detail[c1].finalC = t.finalCost;
			Detail[c1].mem = t.member;
			
			Detail[c1].totalNum.setText(Integer.toString(t.totalNum));
			Detail[c1].totalCost.setText(Double.toString(t.totalCost));
			Detail[c1].discount.setText(Double.toString(t.discountRate));
			Detail[c1].finalCost.setText(Double.toString(t.finalCost));
			Detail[c1].member.setText(t.member[2] + " " + t.member[1]); // 몰라 
			
		}
	}
	public void setDetail(int n)
	{
		remove(cur);
		repaint();
		cur = Detail[n];
		add(cur);
		cur.setLocation(0, 0);
		curNum = n;
		
//		curKind = "Coffee";
	}
//	public void setPackingDetail()
//	{
//		remove(cur);
//		repaint();
////		cur = PackingDetail;
//		add(cur);
//		cur.setLocation(0, 0);
//	}
	class func implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String act = e.getActionCommand();
			
			if(act.equals("전체취소"))
			{
				
				cur.Reset();
			}
			else if(act.equals("선택취소"))
			{
				int choose = cur.table.getSelectedRow();
				if(choose != -1)
				{
					cur.optionReset(choose);
				}
			}
			else if(act.equals("-"))
			{
				int choose = cur.table.getSelectedRow();
				if(choose != -1)
				{
					cur.selecMinus(choose);
				}
			}
			else if(act.equals("+"))
			{
				int choose = cur.table.getSelectedRow();
				if(choose != -1)
				{
					cur.selecPlus(choose);
				}
			}
			else if(act.equals("결제"))
			{
				
				MemberData md = new MemberData();
				
				Actions.addToMoney(cur.finalC);
				Actions.addToDaySale(cur.finalC);
				
				//결제
				
				//넣기 
				//회원고려  
				try
				{
					ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("MemberData.dat"));
											
					md = (MemberData)inputStream.readObject();
									
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
				String list[][] = new String[100][5];
				list = md.getTableElement();
				
				//마일리지 적립 후 등급변경 
				double plusMile = cur.tCost * 0.02;
				Double presentMile =	Double.parseDouble(list[cur.searchIndex][3]);
				presentMile += plusMile;
				
				list[cur.searchIndex][3] = presentMile.toString();
				if(presentMile <= 1000 && presentMile > 500)
				{
					list[cur.searchIndex][1] = "골드";
				}
				else if(presentMile > 1000)
				{
					list[cur.searchIndex][1] = "플래티넘";
				}
				md.setTableElement(list);
				try
				{
					ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("MemberData.dat"));
					
					//dd.set
					outputStream.writeObject(md);
					outputStream.close();
				}
				
				catch(IOException e1)
				{
					e1.printStackTrace();
				}

				
				
				
				cur.makeFood();
				// 재고줄이기 
				cur.Reset();
				Actions.update();
			}
		}
	}
}
