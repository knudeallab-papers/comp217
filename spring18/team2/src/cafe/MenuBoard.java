package cafe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MenuBoard extends JPanel
{
	private static final int WIDTH = 450;
	private static final int HEIGHT = 610;
	
	private JPanel backBoard;
	private JPanel board1;
	private JPanel board2;
	private JPanel board3;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private Font BtnFont = new Font("SanSerif", Font.BOLD, 20);
	
	private Color Clicked = new Color(180, 200, 255);
	private Color unClicked = new Color(240, 240, 240);
	
	private String sys;
	private String b1, b2, b3;

	public MenuBoard(String sys, String b1, String b2, String b3)
	{
		super();
		setSize(WIDTH, HEIGHT);
		
		this.sys = sys;
		this.b1 = b1;
		this.b2 = b2;
		this.b3 = b3;
		
		setMenuTab();
		setBoard();
		
	}
	public void setMenuTab()
	{
		btn1 = new JButton(b1);
		if(b1.equals("Ingredient"))
		{
			btn1.setText("식재료");
			btn1.setActionCommand(b1);
		}
		btn2 = new JButton(b2);
		if(b2.equals("Refrigerator"))
		{
			btn2.setText("냉장");
			btn2.setActionCommand(b2);
		}
		btn3 = new JButton(b3);
		if(b3.equals("Disposable"))
		{
			btn3.setText("일회용품");
			btn3.setActionCommand(b3);
		}
		
		
		btn1.setSize(140, 120);
		btn2.setSize(140, 120);
		btn3.setSize(140, 120);
		
		add(btn1);
		add(btn2);
		add(btn3);
		
		btn1.setFont(BtnFont);
		btn2.setFont(BtnFont);
		btn3.setFont(BtnFont);
		
		btn1.setOpaque(true);
		btn2.setOpaque(true);
		btn3.setOpaque(true);
		
		btn1.setLocation(10, 10);
		btn2.setLocation(155, 10);
		btn3.setLocation(300, 10);
		
		btn1.addActionListener(new SetBoardAct());
		btn2.addActionListener(new SetBoardAct());
		btn3.addActionListener(new SetBoardAct());

	}
	public void setBoard()
	{
		backBoard = new Board();
		add(backBoard);
		backBoard.setLocation(0, 140);
		
		if(b1.equals("Coffee"))
		{
			board1 = new CoffeeBoard(sys);
		}
		else
		{
			board1 = new IngredientBoard(b1);
			//재고관리 보드 
		}
		if(b2.equals("NonCoffee"))
		{
			board2 = new NonCoffeeBoard(sys);
		}
		else
		{
			board2 = new IngredientBoard(b2);
		}
		if(b3.equals("Dessert"))
		{
			board3 = new DessertBoard(sys);
		}
		else
		{
			board3 = new IngredientBoard(b3);
		}
		
		
		
	}
	class SetBoardAct implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String actionCommand = e.getActionCommand();
			btn1.setBackground(unClicked);
			btn2.setBackground(unClicked);
			btn3.setBackground(unClicked);
			backBoard.removeAll();
			backBoard.repaint();
			if(actionCommand.equals(b1))
			{
				backBoard.add(board1);
				board1.setLocation(0, 0);
				btn1.setBackground(Clicked);
				
			}
			else if(actionCommand.equals(b2))
			{
				backBoard.add(board2);
				board2.setLocation(0, 0);
				btn2.setBackground(Clicked);
			}
			else if(actionCommand.equals(b3))
			{
				backBoard.add(board3);
				board3.setLocation(0, 0);
				btn3.setBackground(Clicked);
			}
			
		}
	}
}
