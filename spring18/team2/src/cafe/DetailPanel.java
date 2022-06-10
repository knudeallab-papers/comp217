package cafe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DetailPanel extends JPanel
{
	private static final int WIDTH = 450;
	private static final int HEIGHT = 610;
	private static final int menuNum = 15;
	
	private MenuDetail cur;
	private int curNum;
	private String curKind;
	
	private MenuDetail CoffeeDetail[] = new MenuDetail[menuNum];
	private MenuDetail NonCoffeeDetail[] = new MenuDetail[menuNum];
	private MenuDetail DessertDetail[] = new MenuDetail[menuNum];
	
	private JButton EditBtn;
	private JButton SubmitBtn;
	private JButton ResetBtn;
	
	private MenuDataSet data;
	
	public DetailPanel()
	{
		super();
		setSize(WIDTH, HEIGHT);
		
		getMenuFile();
		
		setBtn();

		setDetailDefault();
		
	}
	public void getMenuFile()
	{
		data = new MenuDataSet();
		try
		{
			ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream("MenuData.dat"));
			
			data = (MenuDataSet)inputStream.readObject();
			
			
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
	private void setBtn()
	{
		EditBtn = new JButton("편집");
		EditBtn.setSize(90, 45);
		add(EditBtn);
		EditBtn.setLocation(150, 560);
		EditBtn.addActionListener(new func());
		
		SubmitBtn = new JButton("저장");
		SubmitBtn.setSize(90, 45);
		add(SubmitBtn);
		SubmitBtn.setLocation(250, 560);
		SubmitBtn.addActionListener(new func());
		
		ResetBtn = new JButton("초기화");
		ResetBtn.setSize(90, 45);
		add(ResetBtn);
		ResetBtn.setLocation(350, 560);
		ResetBtn.addActionListener(new func());
	}
	private void setDummy()
	{
		cur = new MenuDetail();
		add(cur);
		cur.setLocation(15, 15);
	}
	private void setDetailDefault()
	{
		setDummy();
//		cur.setVisible(false);
		for(int c1 = 0; c1 < menuNum; c1++)
		{
			MenuData t = data.getCoffeeData(c1);
			CoffeeDetail[c1] = new MenuDetail();
			CoffeeDetail[c1].menuText.setText(t.getName());
			CoffeeDetail[c1].priceText.setText(Double.toString(t.getPrice()));
			CoffeeDetail[c1].primeCostText.setText(Double.toString(t.getPrimeCost()));
			CoffeeDetail[c1].bean.setText(Integer.toString(t.getBean()));
			CoffeeDetail[c1].berry.setText(Integer.toString(t.getBerry()));
			CoffeeDetail[c1].bread.setText(Integer.toString(t.getBread()));
			CoffeeDetail[c1].butter.setText(Integer.toString(t.getButter()));
			CoffeeDetail[c1].cream.setText(Integer.toString(t.getCream()));
			CoffeeDetail[c1].earlGrey.setText(Integer.toString(t.getEarlGrey()));
			CoffeeDetail[c1].greenTeaPouder.setText(Integer.toString(t.getGreenTeaPouder()));
			CoffeeDetail[c1].holder.setText(Integer.toString(t.getHolder()));
			CoffeeDetail[c1].hotCup.setText(Integer.toString(t.getHotCup()));
			CoffeeDetail[c1].iceCup.setText(Integer.toString(t.getIceCup()));
			CoffeeDetail[c1].hotLid.setText(Integer.toString(t.getHotLid()));
			CoffeeDetail[c1].iceLid.setText(Integer.toString(t.getIceLid()));
			CoffeeDetail[c1].lemonPouder.setText(Integer.toString(t.getLemonPouder()));
			CoffeeDetail[c1].milk.setText(Integer.toString(t.getMilk()));
			CoffeeDetail[c1].peachPouder.setText(Integer.toString(t.getPeachPouder()));
			CoffeeDetail[c1].straw.setText(Integer.toString(t.getStraw()));
			CoffeeDetail[c1].sugar.setText(Integer.toString(t.getSugar()));
			CoffeeDetail[c1].syrup.setText(Integer.toString(t.getSyrup()));
			CoffeeDetail[c1].vanilaSyrup.setText(Integer.toString(t.getVanilaSyrup()));
			CoffeeDetail[c1].waffleMix.setText(Integer.toString(t.getWaffleMix()));
			CoffeeDetail[c1].water.setText(Integer.toString(t.getWater()));
			
			
			t = data.getNonCoffeeData(c1);
			NonCoffeeDetail[c1] = new MenuDetail();
			NonCoffeeDetail[c1].menuText.setText(t.getName());
			NonCoffeeDetail[c1].priceText.setText(Double.toString(t.getPrice()));
			NonCoffeeDetail[c1].primeCostText.setText(Double.toString(t.getPrimeCost()));
			NonCoffeeDetail[c1].bean.setText(Integer.toString(t.getBean()));
			NonCoffeeDetail[c1].berry.setText(Integer.toString(t.getBerry()));
			NonCoffeeDetail[c1].bread.setText(Integer.toString(t.getBread()));
			NonCoffeeDetail[c1].butter.setText(Integer.toString(t.getButter()));
			NonCoffeeDetail[c1].cream.setText(Integer.toString(t.getCream()));
			NonCoffeeDetail[c1].earlGrey.setText(Integer.toString(t.getEarlGrey()));
			NonCoffeeDetail[c1].greenTeaPouder.setText(Integer.toString(t.getGreenTeaPouder()));
			NonCoffeeDetail[c1].holder.setText(Integer.toString(t.getHolder()));
			NonCoffeeDetail[c1].hotCup.setText(Integer.toString(t.getHotCup()));
			NonCoffeeDetail[c1].iceCup.setText(Integer.toString(t.getIceCup()));
			NonCoffeeDetail[c1].hotLid.setText(Integer.toString(t.getHotLid()));
			NonCoffeeDetail[c1].iceLid.setText(Integer.toString(t.getIceLid()));
			NonCoffeeDetail[c1].lemonPouder.setText(Integer.toString(t.getLemonPouder()));
			NonCoffeeDetail[c1].milk.setText(Integer.toString(t.getMilk()));
			NonCoffeeDetail[c1].peachPouder.setText(Integer.toString(t.getPeachPouder()));
			NonCoffeeDetail[c1].straw.setText(Integer.toString(t.getStraw()));
			NonCoffeeDetail[c1].sugar.setText(Integer.toString(t.getSugar()));
			NonCoffeeDetail[c1].syrup.setText(Integer.toString(t.getSyrup()));
			NonCoffeeDetail[c1].vanilaSyrup.setText(Integer.toString(t.getVanilaSyrup()));
			NonCoffeeDetail[c1].waffleMix.setText(Integer.toString(t.getWaffleMix()));
			NonCoffeeDetail[c1].water.setText(Integer.toString(t.getWater()));
			
			
			
			t = data.getDessertData(c1);
			DessertDetail[c1] = new MenuDetail();
			DessertDetail[c1].menuText.setText(t.getName());
			DessertDetail[c1].priceText.setText(Double.toString(t.getPrice()));
			DessertDetail[c1].primeCostText.setText(Double.toString(t.getPrimeCost()));
			DessertDetail[c1].bean.setText(Integer.toString(t.getBean()));
			DessertDetail[c1].berry.setText(Integer.toString(t.getBerry()));
			DessertDetail[c1].bread.setText(Integer.toString(t.getBread()));
			DessertDetail[c1].butter.setText(Integer.toString(t.getButter()));
			DessertDetail[c1].cream.setText(Integer.toString(t.getCream()));
			DessertDetail[c1].earlGrey.setText(Integer.toString(t.getEarlGrey()));
			DessertDetail[c1].greenTeaPouder.setText(Integer.toString(t.getGreenTeaPouder()));
			DessertDetail[c1].holder.setText(Integer.toString(t.getHolder()));
			DessertDetail[c1].hotCup.setText(Integer.toString(t.getHotCup()));
			DessertDetail[c1].iceCup.setText(Integer.toString(t.getIceCup()));
			DessertDetail[c1].hotLid.setText(Integer.toString(t.getHotLid()));
			DessertDetail[c1].iceLid.setText(Integer.toString(t.getIceLid()));
			DessertDetail[c1].lemonPouder.setText(Integer.toString(t.getLemonPouder()));
			DessertDetail[c1].milk.setText(Integer.toString(t.getMilk()));
			DessertDetail[c1].peachPouder.setText(Integer.toString(t.getPeachPouder()));
			DessertDetail[c1].straw.setText(Integer.toString(t.getStraw()));
			DessertDetail[c1].sugar.setText(Integer.toString(t.getSugar()));
			DessertDetail[c1].syrup.setText(Integer.toString(t.getSyrup()));
			DessertDetail[c1].vanilaSyrup.setText(Integer.toString(t.getVanilaSyrup()));
			DessertDetail[c1].waffleMix.setText(Integer.toString(t.getWaffleMix()));
			DessertDetail[c1].water.setText(Integer.toString(t.getWater()));
		}
	}
	public void setCoffeeDetail(int n)
	{
		remove(cur);
		repaint();
		cur = CoffeeDetail[n];
		add(cur);
		cur.setLocation(15, 15);
		curNum = n;
		curKind = "Coffee";
		cur.setTextEditableFalse(); //실험 
	}
	public void setNonCoffeeDetail(int n)
	{
		remove(cur);
		repaint();
		cur = NonCoffeeDetail[n];
		add(cur);
		cur.setLocation(15, 15);
		curNum = n;
		curKind = "NonCoffee";
		cur.setTextEditableFalse();
	}
	public void setDessertDetail(int n)
	{
		remove(cur);
		repaint();
		cur = DessertDetail[n];
		add(cur);
		cur.setLocation(15, 15);
		curNum = n;
		curKind = "Dessert";
		cur.setTextEditableFalse();
	}
	class func implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String actionCommand = e.getActionCommand();
			
			if(actionCommand.equals("편집"))
			{
				cur.setTextEditable();
			}
			else if(actionCommand.equals("저장"))
			{
				MenuData t = new MenuData();
				if(curKind.equals("Coffee"))
				{
					t = data.getCoffeeData(curNum);
				}
				else if(curKind.equals("NonCoffee"))
				{
					t = data.getNonCoffeeData(curNum);
				}
				else if(curKind.equals("Dessert"))
				{
					t = data.getDessertData(curNum);
				}
//				else
//				{
//					t = new MenuData(); //필요는 없지만 
//				}
				t.setName(cur.menuText.getText());
				t.setPrice(Double.parseDouble(cur.priceText.getText()));
//				t.setPrimeCost(Double.parseDouble(cur.primeCostText.getText()));
				
				t.setBean(Integer.parseInt(cur.bean.getText()));
				t.setMilk(Integer.parseInt(cur.milk.getText()));
				t.setWater(Integer.parseInt(cur.water.getText()));
				t.setSyrup(Integer.parseInt(cur.syrup.getText()));
				t.setVanilaSyrup(Integer.parseInt(cur.vanilaSyrup.getText()));
				t.setCream(Integer.parseInt(cur.cream.getText()));
				t.setPeachPouder(Integer.parseInt(cur.peachPouder.getText()));
				t.setLemonPouder(Integer.parseInt(cur.lemonPouder.getText()));
				t.setGreenTeaPouder(Integer.parseInt(cur.greenTeaPouder.getText()));
				t.setEarlGrey(Integer.parseInt(cur.earlGrey.getText()));
				t.setButter(Integer.parseInt(cur.butter.getText()));
				t.setBerry(Integer.parseInt(cur.berry.getText()));
				t.setBread(Integer.parseInt(cur.bread.getText()));
				t.setWaffleMix(Integer.parseInt(cur.waffleMix.getText()));
				t.setSugar(Integer.parseInt(cur.sugar.getText()));
				t.setIceCup(Integer.parseInt(cur.iceCup.getText()));
				t.setIceLid(Integer.parseInt(cur.iceLid.getText()));
				t.setHotCup(Integer.parseInt(cur.hotCup.getText()));
				t.setHotLid(Integer.parseInt(cur.hotLid.getText()));
				t.setStraw(Integer.parseInt(cur.straw.getText()));
				t.setHolder(Integer.parseInt(cur.holder.getText()));
				
				t.CalPrimeCost();
				
				if(cur.menuText.getText().equals("") || cur.priceText.getText().equals("0.0"))
				{
					t.setExist(false);
				}
				else
				{
					t.setExist(true);
				}
				cur.setTextEditableFalse();
				
				try
				{
					ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("MenuData.dat"));
					outputStream.writeObject(data);
					outputStream.close();
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
//				Actions.removeAtMainPnl("MenuBoard");
				Actions.addToMainPnl("메뉴");
//				remove(cur);
//				repaint();
//				Actions.removeAtMainPnl("DetailPanel");
//				setDummy();
			}
			else if(actionCommand.equals("초기화"))
			{
				cur.setTextReset();
			}
			
		}
	}
}
