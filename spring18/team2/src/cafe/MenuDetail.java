package cafe;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MenuDetail extends JPanel 
{
	private static final int WIDTH = 420;
	private static final int HEIGHT = 540;
	private static final int igtNum = 21;
	
	public JTextField menuText;
	public JTextField priceText;
	public JTextField primeCostText;
	private JLabel igtLab[] = new JLabel[igtNum];
	
	public JTextField bean;
	public JTextField milk;
	public JTextField water;
	public JTextField syrup;
	public JTextField vanilaSyrup;
	public JTextField cream;
	public JTextField peachPouder;
	public JTextField lemonPouder;
	public JTextField greenTeaPouder;
	public JTextField earlGrey;
	public JTextField berry;
	public JTextField butter;
	public JTextField bread;
	public JTextField waffleMix;
	public JTextField sugar;
	public JTextField iceCup;
	public JTextField hotCup;
	public JTextField iceLid;
	public JTextField hotLid;
	public JTextField straw;
	public JTextField holder;
	
	
	private Font igtFont = new Font("Monospaced", Font.PLAIN, 12);
	private Font LabelFont = new Font("Monospaced", Font.PLAIN, 20);
	private Font TextFont = new Font("Monospaced", Font.PLAIN, 20);

	public MenuDetail()
	{
		super();
		setSize(WIDTH, HEIGHT);
		
		setBackground(Color.WHITE);
		
		setLabelUI();
	}
	private void setLabelUI()
	{
		JLabel menuLab = new JLabel();
		JLabel priceLab = new JLabel();
		JLabel primeCostLab = new JLabel();
		JLabel ingredientLab = new JLabel();
		
		menuLab.setBackground(Color.WHITE);
		menuLab.setSize(390, 50);
		menuLab.setText("*메뉴명");
		menuLab.setFont(LabelFont);
		menuText = new JTextField(30);
		menuText.setSize(270, 50);
		menuText.setFont(TextFont);		
		menuText.setEditable(false);
		menuLab.add(menuText);
		menuText.setLocation(100, 0);	
		add(menuLab);
		menuLab.setLocation(15, 15);
		
		priceLab.setBackground(Color.WHITE);
		priceLab.setSize(390, 50);
		priceLab.setText("*가격");
		priceLab.setFont(LabelFont);
		priceText = new JTextField(30);
		priceText.setSize(270, 50);
		priceText.setFont(TextFont);		
		priceText.setEditable(false);
		priceLab.add(priceText);
		priceText.setLocation(100, 0);	
		add(priceLab);
		priceLab.setLocation(15, 80);
		
		primeCostLab.setBackground(Color.WHITE);
		primeCostLab.setSize(390, 50);
		primeCostLab.setText("*원가");
		primeCostLab.setFont(LabelFont);
		primeCostText = new JTextField(30);
		primeCostText.setSize(270, 50);
		primeCostText.setFont(TextFont);		
		primeCostText.setEditable(false);
		primeCostLab.add(primeCostText);
		primeCostText.setLocation(100, 0);	
		add(primeCostLab);
		primeCostLab.setLocation(15, 145);
		
		ingredientLab.setBackground(Color.WHITE);
		ingredientLab.setSize(390, 50);
		ingredientLab.setText("*재료");
		ingredientLab.setFont(LabelFont);	
		add(ingredientLab);
		ingredientLab.setLocation(15, 210);
		
		for(int c1 = 0, locX = 15, locY = 225; c1 < igtNum; c1++)
		{
			if(c1 % 3 == 0)
			{
				locY += 40;
				locX = 15;
			}
			else
			{
				locX += 135;
			}
			igtLab[c1] = new JLabel();
			igtLab[c1].setSize(120, 30);
//			igtLab[0].setText("복숭아파우더(g)");
			igtLab[c1].setFont(igtFont);
			add(igtLab[c1]);
			igtLab[c1].setLocation(locX, locY);
		}
		
		bean = new JTextField(3);
		bean.setSize(40, 30);
		bean.setLocation(80, 0);
		bean.setEditable(false);
		igtLab[0].add(bean);
		igtLab[0].setText("원두(g)");
		
		milk = new JTextField(3);
		milk.setSize(40, 30);
		milk.setLocation(80, 0);
		milk.setEditable(false);
		igtLab[1].add(milk);
		igtLab[1].setText("우유(mL)");
		
		water = new JTextField(3);
		water.setSize(40, 30);
		water.setLocation(80, 0);
		water.setEditable(false);
		igtLab[2].add(water);
		igtLab[2].setText("물(mL)");
		
		syrup = new JTextField(3);
		syrup.setSize(40, 30);
		syrup.setLocation(80, 0);
		syrup.setEditable(false);
		igtLab[3].add(syrup);
		igtLab[3].setText("슈가시럽(mL)");
		
		vanilaSyrup = new JTextField(3);
		vanilaSyrup.setSize(40, 30);
		vanilaSyrup.setLocation(80, 0);
		vanilaSyrup.setEditable(false);
		igtLab[4].add(vanilaSyrup);
		igtLab[4].setText("바닐라시럽(mL)");
		
		cream = new JTextField(3);
		cream.setSize(40, 30);
		cream.setLocation(80, 0);
		cream.setEditable(false);
		igtLab[5].add(cream);
		igtLab[5].setText("휘핑크림(mL)");
		
		peachPouder = new JTextField(3);
		peachPouder.setSize(40, 30);
		peachPouder.setLocation(80, 0);
		peachPouder.setEditable(false);
		igtLab[6].add(peachPouder);
		igtLab[6].setText("복숭아파우더(g)");
		
		lemonPouder = new JTextField(3);
		lemonPouder.setSize(40, 30);
		lemonPouder.setLocation(80, 0);
		lemonPouder.setEditable(false);
		igtLab[7].add(lemonPouder);
		igtLab[7].setText("레몬파우더(g)");
		
		greenTeaPouder = new JTextField(3);
		greenTeaPouder.setSize(40, 30);
		greenTeaPouder.setLocation(80, 0);
		greenTeaPouder.setEditable(false);
		igtLab[8].add(greenTeaPouder);
		igtLab[8].setText("그린티파우더(g)");
		
		earlGrey = new JTextField(3);
		earlGrey.setSize(40, 30);
		earlGrey.setLocation(80, 0);
		earlGrey.setEditable(false);
		igtLab[9].add(earlGrey);
		igtLab[9].setText("얼그레이(개)");
		
		berry = new JTextField(3);
		berry.setSize(40, 30);
		berry.setLocation(80, 0);
		berry.setEditable(false);
		igtLab[10].add(berry);
		igtLab[10].setText("트리플베리(g)");
		
		bread = new JTextField(3);
		bread.setSize(40, 30);
		bread.setLocation(80, 0);
		bread.setEditable(false);
		igtLab[11].add(bread);
		igtLab[11].setText("식빵(개)");
		
		butter = new JTextField(3);
		butter.setSize(40, 30);
		butter.setLocation(80, 0);
		butter.setEditable(false);
		igtLab[12].add(butter);
		igtLab[12].setText("버터(개)");
		
		waffleMix = new JTextField(3);
		waffleMix.setSize(40, 30);
		waffleMix.setLocation(80, 0);
		waffleMix.setEditable(false);
		igtLab[13].add(waffleMix);
		igtLab[13].setText("와플믹스(개)");
		
		sugar = new JTextField(3);
		sugar.setSize(40, 30);
		sugar.setLocation(80, 0);
		sugar.setEditable(false);
		igtLab[14].add(sugar);
		igtLab[14].setText("설탕(g)");
		
		iceCup = new JTextField(3);
		iceCup.setSize(40, 30);
		iceCup.setLocation(80, 0);
		iceCup.setEditable(false);
		igtLab[15].add(iceCup);
		igtLab[15].setText("아이스컵(개)");
		

		iceLid = new JTextField(3);
		iceLid.setSize(40, 30);
		iceLid.setLocation(80, 0);
		iceLid.setEditable(false);
		igtLab[16].add(iceLid);
		igtLab[16].setText("아이스컵리드(개)");
		

		hotCup = new JTextField(3);
		hotCup.setSize(40, 30);
		hotCup.setLocation(80, 0);
		hotCup.setEditable(false);
		igtLab[17].add(hotCup);
		igtLab[17].setText("핫컵(개)");
		

		hotLid= new JTextField(3);
		hotLid.setSize(40, 30);
		hotLid.setLocation(80, 0);
		hotLid.setEditable(false);
		igtLab[18].add(hotLid);
		igtLab[18].setText("핫컵리드(개)");
		

		straw = new JTextField(3);
		straw.setSize(40, 30);
		straw.setLocation(80, 0);
		straw.setEditable(false);
		igtLab[19].add(straw);
		igtLab[19].setText("빨대(개)");
		
		holder = new JTextField(3);
		holder.setSize(40, 30);
		holder.setLocation(80, 0);
		holder.setEditable(false);
		igtLab[20].add(holder);
		igtLab[20].setText("컵홀더(개)");
	}
	public void setTextEditable()
	{
		menuText.setEditable(true);
		priceText.setEditable(true);
//		primeCostText.setEditable(true);
		bean.setEditable(true);
		milk.setEditable(true);
		water.setEditable(true);
		syrup.setEditable(true);
		vanilaSyrup.setEditable(true);
		cream.setEditable(true);
		peachPouder.setEditable(true);
		lemonPouder.setEditable(true);
		greenTeaPouder.setEditable(true);
		earlGrey.setEditable(true);
		berry.setEditable(true);
		bread.setEditable(true);
		butter.setEditable(true);
		waffleMix.setEditable(true);
		sugar.setEditable(true);
		iceCup.setEditable(true);
		iceLid.setEditable(true);
		hotCup.setEditable(true);
		hotLid.setEditable(true);
		straw.setEditable(true);
		holder.setEditable(true);
	}
	public void setTextEditableFalse()
	{
		menuText.setEditable(false);
		priceText.setEditable(false);
		primeCostText.setEditable(false);
		bean.setEditable(false);
		milk.setEditable(false);
		water.setEditable(false);
		syrup.setEditable(false);
		vanilaSyrup.setEditable(false);
		cream.setEditable(false);
		peachPouder.setEditable(false);
		lemonPouder.setEditable(false);
		greenTeaPouder.setEditable(false);
		earlGrey.setEditable(false);
		berry.setEditable(false);
		bread.setEditable(false);
		butter.setEditable(false);
		waffleMix.setEditable(false);
		sugar.setEditable(false);
		iceCup.setEditable(false);
		iceLid.setEditable(false);
		hotCup.setEditable(false);
		hotLid.setEditable(false);
		straw.setEditable(false);
		holder.setEditable(false);
	}
	public void setTextReset()
	{
		menuText.setText("");
		priceText.setText("0.0");
		primeCostText.setText("0.0");
		bean.setText("0");
		milk.setText("0");
		water.setText("0");
		syrup.setText("0");
		vanilaSyrup.setText("0");
		cream.setText("0");
		peachPouder.setText("0");
		lemonPouder.setText("0");
		greenTeaPouder.setText("0");
		berry.setText("0");
		bread.setText("0");
		butter.setText("0");
		waffleMix.setText("0");
		sugar.setText("0");
		iceCup.setText("0");
		iceLid.setText("0");
		hotCup.setText("0");
		hotLid.setText("0");
		straw.setText("0");
		holder.setText("0");
		earlGrey.setText("0");
	}
}
