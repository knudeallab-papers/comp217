package Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.swing.JOptionPane;
public class User implements Serializable{
	private String name;
	private int win;
	private int lose;
	
	public User()
	{
		setName("");
		setWin(0);
		setLose(0);
	}
	
	public User(String name)
	{
		setName(name);
		setWin(0);
		setLose(0);
	}
	
	public User(String name, int win, int lose)
	{
		setName(name);
		setWin(win);
		setLose(lose);
	}
	
	public void setName(String name) { this.name = name; }
	public String getName() { return name; }
	public void setWin(int win) { this.win = win; }
	public int getWin() { return win; }
	public void setLose(int lose) { this.lose = lose; }
	public int getLose() { return lose; }
	
	public static void p1Win(User p1, User p2)
	{
		p1.setWin(p1.getWin() + 1);
		p2.setLose(p2.getLose() + 1);
		File f1 = new File(p1.getName() + ".txt");
		File f2 = new File(p2.getName() + ".txt");
		PrintWriter outputStream1 = null, outputStream2 = null;
		if (!f1.exists() || !f2.exists())
		{
			JOptionPane.showMessageDialog(null, "Fatal Error", "Message", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		else
		{
			try {
				outputStream1 = new PrintWriter(f1);
				outputStream2 = new PrintWriter(f2);
			}
			catch (FileNotFoundException g)
			{
				g.printStackTrace();
				System.exit(0);
			}
			outputStream1.println(p1.getName() + "," + p1.getWin() + "," + p1.getLose());
			outputStream2.println(p2.getName() + "," + p2.getWin() + "," + p2.getLose());
			outputStream1.close();
			outputStream2.close();
		}
		JOptionPane.showMessageDialog(null, "1P WIN!", "Game Set", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void p2Win(User p1, User p2)
	{
		File f1 = new File(p1.getName() + ".txt");
		File f2 = new File(p2.getName() + ".txt");
		PrintWriter outputStream1 = null, outputStream2 = null;
		if (!f1.exists() || !f2.exists())
		{
			JOptionPane.showMessageDialog(null, "Fatal Error", "Message", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		else
		{
			try {
				outputStream1 = new PrintWriter(f1);
				outputStream2 = new PrintWriter(f2);
			}
			catch (FileNotFoundException g)
			{
				g.printStackTrace();
				System.exit(0);
			}
			outputStream1.println(p1.getName() + "," + p1.getWin() + "," + (p1.getLose() + 1));
			outputStream2.println(p2.getName() + "," + (p2.getWin() + 1) + "," + p2.getLose());
			outputStream1.close();
			outputStream2.close();
		}	
		JOptionPane.showMessageDialog(null, "2P WIN!", "Game Set", JOptionPane.INFORMATION_MESSAGE);
	}
}
