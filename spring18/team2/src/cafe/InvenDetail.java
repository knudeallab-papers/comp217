package cafe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InvenDetail extends JPanel
{
	private static final int WIDTH = 420;
	private static final int HEIGHT = 540;
	
	public JTextField name;
	public JTextField price;
	public JTextField stock;
	public JTextField order;
	public JTextField loc;
	public JTextField contact;
	
	private Font LabelFont = new Font("Monospaced", Font.PLAIN, 20);
	private Font TextFont = new Font("Monospaced", Font.PLAIN, 20);
	
	public InvenDetail()
	{
		super();
		setSize(WIDTH, HEIGHT);
		
		setBackground(Color.WHITE);
		
		setLabelUI();
	}
	private void setLabelUI()
	{
		JLabel nameLab = new JLabel();
		JLabel priceLab = new JLabel();
		JLabel stockLab = new JLabel();
		JLabel orderLab = new JLabel();
		JLabel locLab = new JLabel();
		JLabel contactLab = new JLabel();
		
		nameLab.setBackground(Color.WHITE);
		nameLab.setSize(390, 50);
		nameLab.setText("*재료명");
		nameLab.setFont(LabelFont);
		name = new JTextField(30);
		name.setSize(270, 50);
		name.setFont(TextFont);		
		name.setEditable(false);
		nameLab.add(name);
		name.setLocation(100, 0);	
		add(nameLab);
		nameLab.setLocation(15, 15);
		
		priceLab.setBackground(Color.WHITE);
		priceLab.setSize(390, 50);
		priceLab.setText("*가격");
		priceLab.setFont(LabelFont);
		price = new JTextField(30);
		price.setSize(270, 50);
		price.setFont(TextFont);		
		price.setEditable(false);
		priceLab.add(price);
		price.setLocation(100, 0);	
		add(priceLab);
		priceLab.setLocation(15, 80);
		
		stockLab.setBackground(Color.WHITE);
		stockLab.setSize(390, 50);
		stockLab.setText("*재고량");
		stockLab.setFont(LabelFont);
		stock = new JTextField(30);
		stock.setSize(270, 50);
		stock.setFont(TextFont);		
		stock.setEditable(false);
		stockLab.add(stock);
		stock.setLocation(100, 0);	
		add(stockLab);
		stockLab.setLocation(15, 145);
		
		orderLab.setBackground(Color.WHITE);
		orderLab.setSize(390, 50);
		orderLab.setText("*주문량");
		orderLab.setFont(LabelFont);
		order = new JTextField(30);
		order.setSize(270, 50);
		order.setFont(TextFont);		
		order.setEditable(false);
		orderLab.add(order);
		order.setLocation(100, 0);	
		add(orderLab);
		orderLab.setLocation(15, 210);
		
		locLab.setBackground(Color.WHITE);
		locLab.setSize(390, 50);
		locLab.setText("*판매처");
		locLab.setFont(LabelFont);
		loc = new JTextField(30);
		loc.setSize(270, 50);
		loc.setFont(TextFont);		
		loc.setEditable(false);
		locLab.add(loc);
		loc.setLocation(100, 0);	
		add(locLab);
		locLab.setLocation(15, 275);
		
		contactLab.setBackground(Color.WHITE);
		contactLab.setSize(390, 50);
		contactLab.setText("*연락처");
		contactLab.setFont(LabelFont);
		contact = new JTextField(30);
		contact.setSize(270, 50);
		contact.setFont(TextFont);		
		contact.setEditable(false);
		contactLab.add(contact);
		contact.setLocation(100, 0);	
		add(contactLab);
		contactLab.setLocation(15, 340);
		
		JButton orderBtn = new JButton("주문");
		orderBtn.setSize(120, 80);
		add(orderBtn);
		orderBtn.setLocation(115, 425);
		orderBtn.addActionListener(new orderAct());
		
		JButton cancleBtn = new JButton("주문 취소");
		cancleBtn.setSize(120, 80);
		add(cancleBtn);
		cancleBtn.setLocation(265, 425);
		cancleBtn.addActionListener(new orderAct());
	}
	public void setTextEditable()
	{
		name.setEditable(true);
		price.setEditable(true);
		order.setEditable(true);
		stock.setEditable(true);
		loc.setEditable(true);
		contact.setEditable(true);
	}
	public void setTextEditableFalse()
	{
		name.setEditable(false);
		price.setEditable(false);
		order.setEditable(false);
		stock.setEditable(false);
		loc.setEditable(false);
		contact.setEditable(false);
	}
	public void setTextReset()
	{
		name.setText("");
		price.setText("0.0");
		order.setText("0");
		stock.setText("0");
		loc.setText("");
		contact.setText("");
	}
	public void nextDayAct()
	{
		int curOrder = Integer.parseInt(order.getText());
		double curPrice = Double.parseDouble(price.getText());
		int curStock = Integer.parseInt(stock.getText());
		
		curPrice *= curOrder;
		curStock += curOrder;
		curOrder = 0;
		
		order.setText(Integer.toString(curOrder));
		stock.setText(Integer.toString(curStock));
		
		Actions.removeToMoney(curPrice);
		Actions.update();
	}
	class orderAct implements ActionListener
	{

		public void actionPerformed(ActionEvent e) 
		{
			String actionCommand = e.getActionCommand();
			int input = 0;
			
			if(actionCommand.equals("주문"))
			{
				input = Integer.parseInt(JOptionPane.showInputDialog("주문할 양 입력"));
				order.setText(Integer.toString(Integer.parseInt(order.getText()) + input));
			}
			else if(actionCommand.equals("주문 취소"))
			{
				input = Integer.parseInt(JOptionPane.showInputDialog("취소할 양 입력"));
				if(input > Integer.parseInt(order.getText()))
				{
					JOptionPane.showMessageDialog(null, "ERROR : 현재 주문량 보다 많습니다.");
				}
				else
				{
					order.setText(Integer.toString(Integer.parseInt(order.getText()) - input));
				}
			}
		}
		
	}
}
