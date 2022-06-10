package javaPr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class Graph extends JPanel {
	
	static int count = 0;
	static int[] newData;
	
	JPanel frame;
	JLabel[] markText = new JLabel[11];
	
	static int[] data = new int[7];
	static String[] date = new String[7];
	
	Font markF = new Font("SansSerif",Font.PLAIN,30);
	Font letterF = new Font("SansSerif",Font.PLAIN,20);
	public Graph()
	{
		setLayout(new BorderLayout());
		
		
		if (MainFrame.isLoaded == false || MainFrame.isGraphOpened == true) {
		} else {
			String filename = "profit.dat";
			try {
				ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename));
				count = inputStream.readInt();
				data = (int[]) inputStream.readObject();
				date = (String[]) inputStream.readObject();
				inputStream.close();
				if (data == null) {
					count = 0;
					data = new int[7];
					date = new String[7];
				}
				MainFrame.isGraphOpened = true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		/* test data
		data[0] = 100000;
		data[1] = 48000;
		data[2] = 70000;
		data[3] = 38000;
		data[4] = 10000;
		data[5] = 48000;
		data[6] = 30000;
		count = 7;
		*/
		
		int max = getHighest() / 100;
		if(max == 0)
		{
			max = 1;
		}
		newData = new int[count];
		
		for(int i = 0;i<count;i++)
		{
			newData[i] = data[i]/max;
		}
		
		frame = new JPanel();
		frame.setLayout(new BorderLayout());
		frame.setBackground(Color.LIGHT_GRAY);
		
		add(frame,BorderLayout.CENTER);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1600, 800);
		g.setColor(Color.black);
		g.setFont(markF);
		for(int i = 0; i<11;i++)
		{
			g.drawString(Integer.toString(100-(i*10)), 200, 90+(i*50));
			g.drawLine(260, 80+(i*50), 1400, 80+(i*50));
		}
		g.drawString("(기준 : %)", 170, 650);
		g.setColor(Color.CYAN);
		for(int i =0;i<count;i++) {
			float temp = (float) (newData[i] * 5);
			g.fillRect(300+(i*160), 580 - (int)temp , 100, (int)temp);
		}
		g.setFont(letterF);
		g.setColor(Color.BLACK);
		for(int i=0;i<count;i++)
		{
			float temp = (float) (newData[i] * 5);
			g.drawString(date[i], 300+(i*160), 600);
			if(data[i] != 0) {
				g.drawString(Integer.toString(data[i]), 325+(i*160), 600 - (int)temp);
			}
		}
		g.setFont(new Font("SansSerif",Font.PLAIN,40));
		g.drawString("지난 일주일간의 매출 추이", 550, 40);
	}
	
	
	public static int getHighest()
	{
		int temp = 0;
		
		for(int i = 0;i<count;i++)
		{
			if(temp<data[i])
			{
				temp = data[i];
			}
		}
		
		if(temp==0)
			temp = 1;
		return temp;
	}
	
	public static void updateData()
	{
		if(count==0)
		{
			data[0] = MainFrame.todaysMoney;
			date[0] = MainFrame.getDay();
			count++;
		}
		else
		{
			if(count == 7)
			{
				data[6] = data[5];
				date[6] = date[5];
				for(int i=count-2;i>=0;i--)
				{
					data[i+1] = data[i];
					date[i+1] = date[i];
				}
			}
			else {
				for (int i=count-1;i>=0;i--) {
					data[i + 1] = data[i];
					date[i + 1] = date[i];
				}
				count++;
			}
			data[0] = MainFrame.todaysMoney;
			date[0] = MainFrame.getDay();
		}
		int max = getHighest() / 100;
		if(max == 0)
		{
			max = 1;
		}
		newData = new int[count];

		for (int i = 0; i < count; i++) {
			newData[i] = data[i] / max;
		}

	}
	
	public static void saveProfit()
	{
		ObjectOutputStream outputStream = null;
		String filename = "profit.dat";
		
		try{
			outputStream = new ObjectOutputStream(new FileOutputStream(filename));
			outputStream.writeInt(count);
			outputStream.writeObject(data);
			outputStream.writeObject(date);
			outputStream.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
