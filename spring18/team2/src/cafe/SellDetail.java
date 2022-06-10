package cafe;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class SellDetail extends JPanel
{
	private static final int WIDTH = 450;
	private static final int HEIGHT = 325;
	
	private String header[] = {"메뉴명", "단가", "수량", "금액"};
	private String contents[][];
	
	public DefaultTableModel model;
	public JTable table;
	public JTextField totalNum;
	public JTextField totalCost;
	public JTextField discount;
	public JTextField finalCost;
	public JTextField member;
	
	public int tNum;
	public double tCost;
	public double dis;
	public double finalC;
	
	public int searchIndex;
	public String mem[] = new String[5];
	
	private JScrollPane scrollPane;
	private Font tableFont = new Font("SanSerif", Font.PLAIN, 13);
	private Font memFont = new Font("SanSerif", Font.PLAIN, 17);
	private Color unClicked = new Color(240, 240, 240);
	public Color darkGray = new Color(100, 100, 100);
	public Color briGray = new Color(215, 215, 215);
	
	public SellDetail(String[][] input, int rowNum)
	{
		super();
		setSize(WIDTH, HEIGHT);
		
		setBackground(briGray);
		
		contents = new String[rowNum][4];
		for(int c1 = 0; c1 < rowNum; c1++)
		{
			for(int c2 = 0; c2 < 4; c2++)
			{
				contents[c1][c2] = input[c1][c2];
			}
		}
		resetMem();
		setTableUI();
	}
	public void resetMem()
	{
		for(int i = 0; i < 5; i++)
		{
			mem[i] = "";
		}
	}
	public void makeFood()
	{
		InvenDataSet data = new InvenDataSet();
		
		try
		{
			ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream("InvenData.dat"));
			
			data = (InvenDataSet)inputStream.readObject();
			
			
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
		
		MenuDataSet data1 = new MenuDataSet();
		try
		{
			ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream("MenuData.dat"));
			
			data1 = (MenuDataSet)inputStream.readObject();
			
			
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
		
		
		int bean = 0;
		int milk = 0;
		int water = 0;
		int syrup = 0;
		int vanilaSyrup = 0;
		int cream = 0;
		int peachPouder = 0;
		int lemonPouder = 0;
		int greenTeaPouder = 0;
		int earlGrey = 0;
		int berry = 0;
		int butter = 0;
		int bread = 0;
		int waffleMix = 0;
		int sugar = 0;
		int iceCup = 0;
		int hotCup = 0;
		int iceLid = 0;
		int hotLid = 0;
		int straw = 0;
		int holder = 0; 
		
		for(int c1 = 0; c1 < model.getRowCount(); c1++)
		{
			String s = (String)model.getValueAt(c1, 0);
			int n = Integer.parseInt((String)model.getValueAt(c1, 2));
			
			MenuData t = data1.findGetData(s);
			
			Actions.addToCurSale(s, t.getPrice(), n, t.getPrice() * n);
			
			bean += t.getBean() * n;
			milk += t.getMilk() * n;
			water += t.getWater() * n;
			syrup += t.getSyrup() * n;
			vanilaSyrup += t.getVanilaSyrup() * n;
			cream += t.getCream() * n;
			peachPouder += t.getPeachPouder() * n;
			lemonPouder += t.getLemonPouder() * n;
			greenTeaPouder += t.getGreenTeaPouder() * n;
			earlGrey += t.getEarlGrey() * n;
			butter += t.getButter() * n;
			berry += t.getBerry() * n;
			bread += t.getBread() * n;
			waffleMix += t.getWaffleMix() * n;
			sugar += t.getSugar() * n;
			iceCup += t.getIceCup() * n;
			hotCup += t.getHotCup() * n;
			iceLid += t.getIceLid() * n;
			straw += t.getStraw() * n;
			hotLid += t.getHotLid() * n;
			holder += t.getHolder() * n;
			
			
		}
		
		
		data.getData(data.findData("원두")).stock -= bean;
		data.getData(data.findData("우유")).stock -= milk;
		data.getData(data.findData("물")).stock -= water;
		data.getData(data.findData("슈가시럽")).stock -= syrup;
		data.getData(data.findData("바닐라시럽")).stock -= vanilaSyrup;
		data.getData(data.findData("휘핑크림")).stock -= cream;
		data.getData(data.findData("복숭아파우더")).stock -= peachPouder;
		data.getData(data.findData("레몬파우더")).stock -= lemonPouder;
		data.getData(data.findData("그린티파우더")).stock -= greenTeaPouder;
		data.getData(data.findData("얼그레이")).stock -= earlGrey;
		data.getData(data.findData("트리플베리")).stock -= berry;
		data.getData(data.findData("버터")).stock -= butter;
		data.getData(data.findData("식빵")).stock -= bread;
		data.getData(data.findData("와플믹스")).stock -= waffleMix;
		data.getData(data.findData("설탕")).stock -= sugar;
		data.getData(data.findData("아이스컵")).stock -= iceCup;
		data.getData(data.findData("아이스컵리드")).stock -= iceLid;
		data.getData(data.findData("핫컵")).stock -= hotCup;
		data.getData(data.findData("핫컵리드")).stock -= hotLid;
		data.getData(data.findData("빨대")).stock -= straw;
		data.getData(data.findData("컵홀더")).stock -= holder;
		
		
		try
		{
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("InvenData.dat"));
			outputStream.writeObject(data);
			outputStream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	public void setTexts()
	{
		
		
		totalNum.setText(Integer.toString(tNum));
		totalCost.setText(Double.toString(tCost));
		finalCost.setText(Double.toString(finalC));
		discount.setText(Double.toString(dis));
	}
	public void Reset()
	{
		model.setRowCount(0);
		totalNum.setText("0");
		totalCost.setText("0.0");
		finalCost.setText("0.0");
		discount.setText("0.0");
		tNum = 0;
		tCost = 0;
		finalC = 0;
		dis = 0;
		
		resetMem();
		member.setText("");
	}
	public void optionReset(int n)
	{
		tNum -= Integer.parseInt((String) model.getValueAt(n, 2));
		tCost -= Double.parseDouble((String) model.getValueAt(n, 3));
		finalC = tCost * (1 - dis);
		model.removeRow(n);
		setTexts();
	}
	public void selecPlus(int n)
	{
		int num = Integer.parseInt((String) model.getValueAt(n, 2));
		double price = Double.parseDouble((String) model.getValueAt(n, 1));
		double tPrice = Double.parseDouble((String) model.getValueAt(n, 3));
		tNum += 1;
		tCost += price;
		finalC = tCost * (1 - dis);
		model.setValueAt(Double.toString(tPrice + price) , n, 3);
		model.setValueAt(Integer.toString(num + 1) , n, 2);
		
		setTexts();
	}
	public void selecMinus(int n)
	{
		int num = Integer.parseInt((String) model.getValueAt(n, 2));
		double price = Double.parseDouble((String) model.getValueAt(n, 1));
		double tPrice = Double.parseDouble((String) model.getValueAt(n, 3));
		
		tNum -= 1;
		tCost -= price;
		finalC = tCost * (1 - dis);
		
		if(num == 1)
		{
			model.removeRow(n);
		}
		else
		{
			model.setValueAt(Double.toString(tPrice - price) , n, 3);
			model.setValueAt(Integer.toString(num - 1) , n, 2);
		}
		
		setTexts();
	}
	public void addDummy()
	{
		String input[] = {"", "", "", ""};
		
		model.addRow(input);
		int k = model.getRowCount() - 1;
		model.removeRow(k);
	}
	public void addToTable(String category, int n)
	{
		InvenDataSet data = new InvenDataSet();
		
		try
		{
			ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream("InvenData.dat"));
			
			data = (InvenDataSet)inputStream.readObject();
			
			
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
		
		MenuDataSet data1 = new MenuDataSet();
		try
		{
			ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream("MenuData.dat"));
			
			data1 = (MenuDataSet)inputStream.readObject();
			
			
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
		
		MenuData t = new MenuData();
		if(category.equals("Coffee"))
		{
			t = data1.getCoffeeData(n);
		}
		else if(category.equals("NonCoffee"))
		{
			t = data1.getNonCoffeeData(n);
		}
		else if(category.equals("Dessert"))
		{
			t = data1.getDessertData(n);
		}
		
		String kind = t.getName();
		double price = t.getPrice();
		
	/////////	
		
		
			if(data.getData(data.findData("원두")).stock  < t.getBean())
			{
				JOptionPane.showMessageDialog(null, "원두 재고 부족");
				return;
			}
			if(data.getData(data.findData("우유")).stock <  t.getMilk())
			{
				JOptionPane.showMessageDialog(null, "우유 재고 부족");
				return;
			}
			if(data.getData(data.findData("물")).stock < t.getWater())
			{
				JOptionPane.showMessageDialog(null, "물 재고 부족");
				return;
			}
			if(data.getData(data.findData("슈가시럽")).stock <  t.getSyrup())
			{
				JOptionPane.showMessageDialog(null, "슈가시럽 재고 부족");
				return;
			}
			if(data.getData(data.findData("바닐라시럽")).stock < t.getVanilaSyrup()) 
				{
				JOptionPane.showMessageDialog(null, "바닐라시럽 재고 부족");
				return;
				}
			if(data.getData(data.findData("휘핑크림")).stock < t.getCream()) 
				{
				JOptionPane.showMessageDialog(null, "휘핑크림 재고 부족");
				return;
				}
			if(data.getData(data.findData("복숭아파우더")).stock < t.getPeachPouder()) 
				{
				JOptionPane.showMessageDialog(null, "복숭아파우더 재고 부족");
				return;
				}
			if(data.getData(data.findData("레몬파우더")).stock < t.getLemonPouder())
				{
				JOptionPane.showMessageDialog(null, "레몬파우더 재고 부족");
				return;
				}
			if(data.getData(data.findData("그린티파우더")).stock < t.getGreenTeaPouder()) 
				{
				JOptionPane.showMessageDialog(null, "그린티파우더 재고 부족");
				return;
				}
			if(data.getData(data.findData("얼그레이")).stock < t.getEarlGrey()) 
				{
				JOptionPane.showMessageDialog(null, "얼그레이 재고 부족");
				return;
				}
			if(data.getData(data.findData("트리플베리")).stock < t.getBerry()) 
				{
				JOptionPane.showMessageDialog(null, "트리플베리 재고 부족");
				return;
				}
			if(data.getData(data.findData("버터")).stock < t.getButter())
				{
				JOptionPane.showMessageDialog(null, "버터 재고 부족");
				return;
				}
			if(data.getData(data.findData("식빵")).stock < t.getBread()) 
				{
				JOptionPane.showMessageDialog(null, "식빵 재고 부족");
				return;
				}
			if(data.getData(data.findData("와플믹스")).stock < t.getWaffleMix())
				{
				JOptionPane.showMessageDialog(null, "와플믹스 재고 부족");
				return;
				}
			if(data.getData(data.findData("설탕")).stock < t.getSugar()) 
				{
				JOptionPane.showMessageDialog(null, "설탕 재고 부족");
				return;
				}
			if(data.getData(data.findData("아이스컵")).stock < t.getIceCup()) 
				{
				JOptionPane.showMessageDialog(null, "아이스컵 재고 부족");
				return;
				}
			if(data.getData(data.findData("아이스컵리드")).stock < t.getIceLid())
				{
				JOptionPane.showMessageDialog(null, "아이스컵리드 재고 부족");
				return;
				}
			if(data.getData(data.findData("핫컵")).stock < t.getHotCup()) 
				{
				JOptionPane.showMessageDialog(null, "핫컵 재고 부족");
				return;
				}
			if(data.getData(data.findData("핫컵리드")).stock < t.getHotLid()) 
				{
				JOptionPane.showMessageDialog(null, "핫컵리드 재고 부족");
				return;
				}
			if(data.getData(data.findData("빨대")).stock < t.getStraw()) 
				{
				JOptionPane.showMessageDialog(null, "빨대 재고 부족");
				return;
				}
			if(data.getData(data.findData("컵홀더")).stock < t.getHolder())
				{
				JOptionPane.showMessageDialog(null, "컵홀더 재고 부족");
				return;
				}
			
			
		//////////
		int sys = existInTable(kind);
		if(sys < 0)
		{
			String input[] = new String[4];
			
			input[0] = kind;
			input[1] = Double.toString(price);
			input[2] = "1";
			input[3] = Double.toString(price);
			model.addRow(input);
			
			
		}
		else
		{
			model.setValueAt(Integer.toString((Integer.parseInt((String) model.getValueAt(sys, 2)) + 1)), sys, 2);
			model.setValueAt(Double.toString(Double.parseDouble((String)model.getValueAt(sys, 3)) + price), sys, 3);
		}
		
		tNum += 1;
		tCost += price;
		finalC = tCost * (1 - dis);
//		
		totalNum.setText(Integer.toString(tNum));
		totalCost.setText(Double.toString(tCost));
		finalCost.setText(Double.toString(finalC));
	}
	private int existInTable(String kind)
	{
		int result = -1;
		for(int c1 = 0; c1 < table.getRowCount(); c1++)
		{
			if(table.getValueAt(c1, 0).equals(kind))
			{
				return c1;
			}
		}
		return result;
	}
	private void setTableUI()
	{
		model = new DefaultTableModel(contents, header);
		table = new JTable(model);
		table.setFont(tableFont);
		scrollPane = new JScrollPane(table);
		scrollPane.setSize(450, 230);
		add(scrollPane);
		scrollPane.setLocation(0, 0);
		
		JLabel total = new JLabel();
		JLabel discountLab = new JLabel();
		JLabel finalCostLab = new JLabel();
		JLabel memberLab = new JLabel();
		
		total.setSize(225, 25);
		total.setText("합계");
		total.setHorizontalAlignment(SwingConstants.LEFT);
		total.setFont(tableFont);
		total.setBackground(unClicked);
		total.setOpaque(true);
		add(total);
		total.setLocation(0, 230);
		
		totalNum = new JTextField();
		totalNum.setSize(112, 25);
		totalNum.setFont(tableFont);
		totalNum.setHorizontalAlignment(SwingConstants.LEFT);
		totalNum.setBackground(unClicked);
		totalNum.setOpaque(true);
		add(totalNum);
		totalNum.setLocation(225, 230);
		totalNum.setEditable(false);
//		totalNum.setText("3");
		
		totalCost = new JTextField();
		totalCost.setSize(113, 25);
		totalCost.setFont(tableFont);
		totalCost.setHorizontalAlignment(SwingConstants.LEFT);
		totalCost.setBackground(unClicked);
		totalCost.setOpaque(true);
		add(totalCost);
		totalCost.setLocation(337, 230);
		totalCost.setEditable(false);
//		totalCost.setText("10000");
		
		discountLab.setSize(112, 25);
		discountLab.setText("할인율");
		discountLab.setHorizontalAlignment(SwingConstants.LEFT);
		discountLab.setFont(tableFont);
//		discountLab.setBackground(unClicked);
		discountLab.setBackground(Color.WHITE);
		discountLab.setOpaque(true);
		add(discountLab);
		discountLab.setLocation(0, 255);
		
		discount = new JTextField();
		discount.setSize(113, 25);
		discount.setFont(tableFont);
		discount.setHorizontalAlignment(SwingConstants.LEFT);
		discount.setBackground(Color.WHITE);
		discount.setOpaque(true);
		add(discount);
		discount.setLocation(112, 255);
		discount.setEditable(false);
//		discount.setText("3");
		
		finalCostLab.setSize(112, 25);
		finalCostLab.setText("받을 금액");
		finalCostLab.setHorizontalAlignment(SwingConstants.LEFT);
		finalCostLab.setFont(tableFont);
//		finalCostLab.setBackground(unClicked);
		finalCostLab.setBackground(Color.WHITE);
		finalCostLab.setOpaque(true);
		add(finalCostLab);
		finalCostLab.setLocation(225, 255);
		
		finalCost = new JTextField();
		finalCost.setSize(113, 25);
		finalCost.setFont(tableFont);
		finalCost.setHorizontalAlignment(SwingConstants.LEFT);
		finalCost.setBackground(Color.white);
		finalCost.setOpaque(true);
		add(finalCost);
		finalCost.setLocation(337, 255);
		finalCost.setEditable(false);
//		finalCost.setText("3");
		
		memberLab = new JLabel();
		memberLab.setSize(150, 45);
		memberLab.setFont(memFont);
		memberLab.setHorizontalAlignment(SwingConstants.LEFT);
		memberLab.setBackground(briGray);
		memberLab.setOpaque(true);
		add(memberLab);
		memberLab.setLocation(0, 280);
		memberLab.setText("*회원 정보");
//		totalNum.setText("3");
		
		member = new JTextField();
		member.setSize(150, 45);
		member.setFont(memFont);
		member.setHorizontalAlignment(SwingConstants.LEFT);
		member.setBackground(briGray);
		member.setOpaque(true);
		add(member);
		member.setLocation(150, 280);
		member.setEditable(false);
//		member.setText("홍정훈/VIP");
		
		JButton resetBtn = new JButton("재설정");
		resetBtn.setSize(110, 35);
		add(resetBtn);
		resetBtn.setLocation(315, 285);
		resetBtn.addActionListener(new func());
	}
	class func implements ActionListener
	{
		MemberData md = new MemberData();
		int arr[] = new int[100];
		String[] st = new String[5];
		String[][] list = new String[100][5]; //동명이인들 정보 
		Double plusMile;
		@Override
		public void actionPerformed(ActionEvent e) 
		{	
			int c = 0, k;
			String msg = JOptionPane.showInputDialog("회원 이름을 입력해주세요.");
			String name = null, rank = null, combine;
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
			
//			k = md.getMemberIndex(msg);
//			if(k == -1)
//			{
//				JOptionPane.showMessageDialog(null, "해당 회원이 존재하지 않습니다.");
//			}
//			else
//			{
//				st = md.getMember(k);
//			}
			//중복검색 가능하게
			arr = md.getMemberIndexArr(msg);
			if(arr[0] == -1)
			{
				JOptionPane.showMessageDialog(null, "해당 회원이 존재하지 않습니다.");
			}
			
			else
			{
				for(int i = 0; i < 100; i++)
				{
					if(arr[i] == -1)
					{
						break;
					}
					list[i] = md.getMember(arr[i]);
				}
				for(int i = 0; i < 100; i++)
				{
					if(list[i][4] != null)
					{
						c++;
					}
				}
				String[] phoneArr = new String[c]; //그들의 폰 번호 
				
				for(int i = 0; i < c; i++)
				{
					phoneArr[i] = list[i][4];
				}
				
				Object select = JOptionPane.showInputDialog(null, "전화번호", "회원을 선택해주세요", JOptionPane.INFORMATION_MESSAGE, null, phoneArr, phoneArr[0]);
				
				for(k = 0; k < 100; k++)
				{
					if(select.equals(list[k][4]))
					{
						name = list[k][2];
						rank = list[k][1];
						break;
					}
				}
				
				combine = name + " " + rank;
				member.setText(combine);
				searchIndex = k;
				
				for(int c1 = 0; c1 < 5; c1++)
				{
					mem[c1] = list[k][c1];
				}
				
				if(rank.equals("일반"))
				{
					dis = 0.02;
				}
				else if(rank.equals("골드"))
				{
					dis = 0.05;
				}
				else if(rank.equals("플래티넘"))
				{
					dis = 0.1;
				}
				else
				{
					dis = 0;
				}
				finalC = tCost * (1 - dis);
				setTexts();
			}
		}
	}


}
