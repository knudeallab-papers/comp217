package cafe;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

public class SmallBar extends JPanel 
{
	public static final int WIDTH = 220;
	public static final int HEIGHT = 650;
	
	public static final String srcPath = "src";
	public static final String packageName = "cafe";
	
	
	public Color briGray = new Color(215, 215, 215);
	public Color darkGray = new Color(100, 100, 100);
	public Color midGray = new Color(180, 180, 180);
	
	protected JButton NBtn[] = new JButton[13];
	
	public SmallBar()
	{
		super();
		setSize(WIDTH, HEIGHT);
		setBackground(briGray);
		
		
//		setExitBtn();
	}
	protected void setExitBtn(String str)
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
		
		String ExitFileName = "Exit.png";
		String ExitFilePath = currentPath + "/" + srcPath + "/" + packageName + "/" + ExitFileName;
		ImageIcon ExitIcon = new ImageIcon(ExitFilePath);
		JButton ExitBtn = new JButton("");
		ExitBtn.setIcon(ExitIcon);
		ExitBtn.setSize(40, 40);
		ExitBtn.setBackground(darkGray);
		ExitBtn.setOpaque(true);
		
		ExitBtn.addActionListener(new ExitAct());
		ExitBtn.setActionCommand(str);
		ExitBtn.setBorderPainted(false);
		
		add(ExitBtn);
		ExitBtn.setLocation(180, 0);

	}
	protected void setCupLab()
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
		
		String CupFileName = "Cup2.png";
		String CupFilePath = currentPath + "/" + srcPath + "/" + packageName + "/" + CupFileName;
		ImageIcon CupIcon = new ImageIcon(CupFilePath);
		
		JLabel CupLab = new JLabel();
		CupLab.setIcon(CupIcon);
		CupLab.setSize(180, 165);
		CupLab.setBackground(briGray);
//		CupLab.setOpaque(false);
		add(CupLab);
		CupLab.setLocation(40, 55);
	}
	protected void setNumPad(String str)
	{
		JPanel NumPad = new JPanel();
		Font btnFont = new Font("SanSerif", Font.BOLD, 20);
		
		NumPad.setSize(190, 315);
		NumPad.setBackground(Color.WHITE);
		BevelBorder border =new BevelBorder(BevelBorder.RAISED);
		NumPad.setBorder(border);
		add(NumPad);
		NumPad.setLocation(15, 325);
		
		NBtn[7] = new JButton("7");
		NBtn[7].setBackground(Color.WHITE);
		NBtn[7].setSize(50, 50);
		NBtn[7].setFont(btnFont);
//		Btn7.addActionListener(new inputNum());
		NumPad.add(NBtn[7]);
		NBtn[7].setLocation(15, 20);
		
		NBtn[8] = new JButton("8");
		NBtn[8].setBackground(Color.WHITE);
		NBtn[8].setSize(50, 50);
		NBtn[8].setFont(btnFont);
//		Btn8.addActionListener(new inputNum());
		NumPad.add(NBtn[8]);
		NBtn[8].setLocation(70, 20);
		
		NBtn[9] = new JButton("9");
		NBtn[9].setBackground(Color.WHITE);
		NBtn[9].setSize(50, 50);
		NBtn[9].setFont(btnFont);
//		Btn9.addActionListener(new inputNum());
		NumPad.add(NBtn[9]);
		NBtn[9].setLocation(125, 20);
		
		
		NBtn[4] = new JButton("4");
		NBtn[4].setBackground(Color.WHITE);
		NBtn[4].setSize(50, 50);
		NBtn[4].setFont(btnFont);
//		Btn4.addActionListener(new inputNum());
		NumPad.add(NBtn[4]);
		NBtn[4].setLocation(15, 75);
		
		NBtn[5] = new JButton("5");
		NBtn[5].setBackground(Color.WHITE);
		NBtn[5].setSize(50, 50);
		NBtn[5].setFont(btnFont);
//		Btn5.addActionListener(new inputNum());
		NumPad.add(NBtn[5]);
		NBtn[5].setLocation(70, 75);
		
		NBtn[6] = new JButton("6");
		NBtn[6].setBackground(Color.WHITE);
		NBtn[6].setSize(50, 50);
		NBtn[6].setFont(btnFont);
//		Btn6.addActionListener(new inputNum());
		NumPad.add(NBtn[6]);
		NBtn[6].setLocation(125, 75);
		
		NBtn[1] = new JButton("1");
		NBtn[1].setBackground(Color.WHITE);
		NBtn[1].setSize(50, 50);
		NBtn[1].setFont(btnFont);
//		Btn1.addActionListener(new inputNum());
		NumPad.add(NBtn[1]);
		NBtn[1].setLocation(15, 130);
		
		NBtn[2] = new JButton("2");
		NBtn[2].setBackground(Color.WHITE);
		NBtn[2].setSize(50, 50);
		NBtn[2].setFont(btnFont);
//		Btn2.addActionListener(new inputNum());
		NumPad.add(NBtn[2]);
		NBtn[2].setLocation(70, 130);
		
		NBtn[3] = new JButton("3");
		NBtn[3].setBackground(Color.WHITE);
		NBtn[3].setSize(50, 50);
		NBtn[3].setFont(btnFont);
//		Btn3.addActionListener(new inputNum());
		NumPad.add(NBtn[3]);
		NBtn[3].setLocation(125, 130);
		
		NBtn[0] = new JButton("0");
		NBtn[0].setBackground(Color.WHITE);
		NBtn[0].setSize(50, 50);
		NBtn[0].setFont(btnFont);
//		Btn0.addActionListener(new inputNum());
		NumPad.add(NBtn[0]);
		NBtn[0].setLocation(15, 185);
		
		NBtn[10] = new JButton("<");
		NBtn[10].setBackground(Color.WHITE);
		NBtn[10].setSize(50, 50);
		NBtn[10].setFont(btnFont);
//		BtnD.addActionListener(new inputNum());
		NumPad.add(NBtn[10]);
		NBtn[10].setLocation(70, 185);
		
		NBtn[11] = new JButton("C");
		NBtn[11].setBackground(Color.WHITE);
		NBtn[11].setSize(50, 50);
		NBtn[11].setFont(btnFont);
//		Btn[11].addActionListener(new inputNum());
		NumPad.add(NBtn[11]);
		NBtn[11].setLocation(125, 185);
		
		if(str.equals("Password"))
		{
			NBtn[12] = new JButton("LOGIN");
		}
		else
		{
			NBtn[12] = new JButton("ENTER");
		}
//		JButton BtnL = new JButton("LOGIN");
		NBtn[12].setBackground(Color.WHITE);
		NBtn[12].setSize(160, 50);
		NBtn[12].setFont(btnFont);
//		BtnL.addActionListener(new inputNum());
		NumPad.add(NBtn[12]);
		NBtn[12].setLocation(15, 255);
		
	}
	class ExitAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String actionCommand = e.getActionCommand();
			
			if(actionCommand.equals("Exit"))
			{
				System.exit(0);
			}
			else
			{
				Actions.removeSideBar(actionCommand);
			}
		}
	}
}

