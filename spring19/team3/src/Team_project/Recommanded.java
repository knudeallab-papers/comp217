package Team_project;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.ImageIcon;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class Recommanded extends JFrame{

	public Recommanded()
	{
		setTitle("<Recommanded Items>");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 300);
		setLayout(new BorderLayout());
		JPanel items = new JPanel();
		JLabel lab = new JLabel("People preference");
		items.add(lab);
		JTextArea texts = new JTextArea(12, 30);
		Order order1[]=new Order[12];
		Order order2[]=new Order[12];
		Order order3[]=new Order[12];
	      try {
				ObjectInputStream inStream=
						new ObjectInputStream(new FileInputStream("beverage.dat"));
				order1=(Order[])inStream.readObject();
				inStream.close();
			}catch(FileNotFoundException e) {
				System.out.println("Cannot find file number.dat.");
			}catch(IOException e){
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      int max;
	      Order temp;
	      for(int i=0;i<12;i++) 
	      {
	    	  max=i;
	    	  for(int j=i;j<12;j++)
	    	  {
	    		  if(order1[j].getNum()>order1[max].getNum())
	    			  max=j;
	    	  }
	    	  temp=order1[max];
	    	  order1[max]=order1[i];
	    	  order1[i]=temp;
	      }
	      String line;
	      line="BEVERAGE\n";
		for(int i=0;i<3;i++)
		{
			line=line+(i+1)+". "+order1[i].getName()+" "+order1[i].getNum()+"\n";
		}
		
	      try {
				ObjectInputStream inStream=
						new ObjectInputStream(new FileInputStream("snack.dat"));
				order2=(Order[])inStream.readObject();
				inStream.close();
			}catch(FileNotFoundException e) {
				System.out.println("Cannot find file number.dat.");
			}catch(IOException e){
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      for(int i=0;i<12;i++) 
	      {
	    	  max=i;
	    	  for(int j=i;j<12;j++)
	    	  {
	    		  if(order2[j].getNum()>order2[max].getNum())
	    			  max=j;
	    	  }
	    	  temp=order2[max];
	    	  order2[max]=order2[i];
	    	  order2[i]=temp;
	      }
	      line=line+"SNACK\n";
		for(int i=0;i<3;i++)
		{
			line=line+(i+1)+". "+order2[i].getName()+" "+order2[i].getNum()+"\n";
		}
		
	      try {
				ObjectInputStream inStream=
						new ObjectInputStream(new FileInputStream("ramen.dat"));
				order3=(Order[])inStream.readObject();
				inStream.close();
			}catch(FileNotFoundException e) {
				System.out.println("Cannot find file number.dat.");
			}catch(IOException e){
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      for(int i=0;i<12;i++) 
	      {
	    	  max=i;
	    	  for(int j=i;j<12;j++)
	    	  {
	    		  if(order3[j].getNum()>order3[max].getNum())
	    			  max=j;
	    	  }
	    	  temp=order3[max];
	    	  order3[max]=order3[i];
	    	  order3[i]=temp;
	      }
	      line=line+"RAMEN\n";
		for(int i=0;i<3;i++)
		{
			line=line+(i+1)+". "+order3[i].getName()+" "+order3[i].getNum()+"\n";
		}
		texts.setText(line);
		
		items.add(texts);
		add(items);
	}
		
}
