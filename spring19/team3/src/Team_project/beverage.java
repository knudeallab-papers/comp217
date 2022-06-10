package Team_project;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class beverage extends Category implements Category_operation, ActionListener{
   private static final String Int = null;
   HashMap<String,Integer> price;
   HashMap<String,Integer> stock;
   String item_name[] = new String[12];
   JPanel products = new JPanel();
   JPanel textpanel = new JPanel();
   private JTextField iofield;
   private JTextField changefield;
   String beverages[] = new String[12];
   double result=0;
   double change = 0;
   Order order[]=new Order[12];
   
   public beverage()
   {
      super("beverage.txt");
      try {
			ObjectInputStream inStream=
					new ObjectInputStream(new FileInputStream("beverage.dat"));
			order=(Order[])inStream.readObject();
			inStream.close();
		}catch(FileNotFoundException e) {
			System.out.println("Cannot find file number.dat.");
		}catch(IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
      Scanner inputStream=null;
      try {
         inputStream = new Scanner(new FileInputStream(super.get_file()));
      }catch(FileNotFoundException e){
         e.printStackTrace();
         System.exit(0);
      }
      price=new HashMap<String,Integer>();
      stock=new HashMap<String,Integer>();
      
      String name=null;
      int pri=0;
      int sto=0;
      while(inputStream.hasNext())
      {
         name=inputStream.next();
         pri=inputStream.nextInt();
         sto=inputStream.nextInt();
         price.put(name,pri);
         stock.put(name,sto);
      }
      inputStream.close();
   }
   
   @Override
   public String[] get_names() {
      Iterator<String> name=price.keySet().iterator();
      
      int i = 0;
      while(name.hasNext())
      {
        
         item_name[i] = name.next();
         i++;
      }
      
      return item_name;
   }

   @Override
   public int get_Price(String key) {
      return price.get(key);
      
   }

   @Override
   public void inventory_control(String key) {//주문 받은루 재고 -1
      int item_stock= stock.get(key)-1;
      stock.put(key,item_stock);
      
      PrintWriter outputStream = null;
      try {
         String filename = super.get_file();
         outputStream = new PrintWriter(new FileOutputStream(filename));
      }catch(FileNotFoundException e){
         System.out.println("Error opening the file stuff.txt");
         System.exit(0);
      }
      
      Iterator<String> name = price.keySet().iterator();
      while(name.hasNext())
      {
         String keyset = name.next();
         outputStream.println(keyset + " " + price.get(keyset) + " " + stock.get(keyset));
      }
      outputStream.close();
   }
   
   public void display()
   {
      setTitle("<VENDING MACHINE - beverage>");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(600, 800);
      
      setLayout(new BorderLayout());
      
      products.setLayout(new GridLayout(4, 3));
      products.setBackground(Color.LIGHT_GRAY);
      
      
      beverages = get_names();
      
      if ((beverages[0]!=null) && (stock.get(beverages[0])>0)) {
         JButton ram1 = new JButton(beverages[0] + " " +  get_Price(beverages[0]) + " won");
         ram1.addActionListener(this);
         products.add(ram1);
      }
      
      if ((beverages[1]!=null) && (stock.get(beverages[1])>0)) {
         JButton ram2 = new JButton(beverages[1] + " " +  get_Price(beverages[1]) + " won");
         ram2.addActionListener(this);
         products.add(ram2);
      }
      
      if ((beverages[2]!=null) && (stock.get(beverages[2])>0)) {
         JButton ram3 = new JButton(beverages[2] + " " +  get_Price(beverages[2]) + " won");
         ram3.addActionListener(this);
         products.add(ram3);
      }

      if ((beverages[3]!=null) && (stock.get(beverages[3])>0)) {
         JButton ram4 = new JButton(beverages[3] + " " +  get_Price(beverages[3]) + " won");
         ram4.addActionListener(this);
         products.add(ram4);
      }
      
      if ((beverages[4]!=null) && (stock.get(beverages[4])>0)) {
         JButton ram5 = new JButton(beverages[4] + " " +  get_Price(beverages[4]) + " won");
         ram5.addActionListener(this);
         products.add(ram5);
      }
      
      if ((beverages[5]!=null) && (stock.get(beverages[5])>0)) {
         JButton ram6 = new JButton(beverages[5] + " " +  get_Price(beverages[5]) + " won");
         ram6.addActionListener(this);
         products.add(ram6);
      }
      
      if ((beverages[6]!=null) && (stock.get(beverages[6])>0)) {
    	  JButton ram7 = new JButton(beverages[6] + " " +  get_Price(beverages[6]) + " won");
    	  ram7.addActionListener(this);
    	  products.add(ram7);
      }
      
      if ((beverages[7]!=null) && (stock.get(beverages[7])>0)) {
         JButton ram8 = new JButton(beverages[7] + " " +  get_Price(beverages[7]) + " won");
         ram8.addActionListener(this);
         products.add(ram8);
      }
      
      if ((beverages[8]!=null) && (stock.get(beverages[8])>0)) {
         JButton ram9 = new JButton(beverages[8] + " " +  get_Price(beverages[8]) + " won");
         ram9.addActionListener(this);
         products.add(ram9);
      }
      
      if ((beverages[9]!=null) && (stock.get(beverages[9])>0)) {
         JButton ram10 = new JButton(beverages[9] + " " +  get_Price(beverages[9]) + " won");
         ram10.addActionListener(this);
         products.add(ram10);
      }
      
      if ((beverages[10]!=null) && (stock.get(beverages[10])>0)) {
         JButton ram11 = new JButton(beverages[10] + " " +  get_Price(beverages[10]) + " won");
         ram11.addActionListener(this);
         products.add(ram11);
      }
      
      if ((beverages[11]!=null) && (stock.get(beverages[11])>0)) {
         JButton ram12 = new JButton(beverages[11] + " " +  get_Price(beverages[11]) + " won");
         ram12.addActionListener(this);
         products.add(ram12);
      }
      
      add(products, BorderLayout.CENTER);
      
      textpanel.setLayout(new GridLayout(2, 2));
      textpanel.setBackground(Color.LIGHT_GRAY);
      
      iofield = new JTextField("Insert money", 20);
      textpanel.add(iofield);
      
      changefield = new JTextField("Here is your change", 20);
      textpanel.add(changefield);
      
      JButton enter = new JButton("Enter");
      enter.addActionListener(this);
      textpanel.add(enter);
      
      JButton getChange = new JButton("Get");
      getChange.addActionListener(this);
      textpanel.add(getChange);
      
      
      add(textpanel, BorderLayout.SOUTH);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Complete this stub code
      try {
         assumingCorrectNumberFormats(e);
      }catch(NumberFormatException e2) {
         iofield.setText("Error: Reenter Number.");
      }
   }
   
   public void assumingCorrectNumberFormats(ActionEvent e) {
	      // TODO Complete this stub code
	      String actioncommand = e.getActionCommand();
	      
	      Payment payment;
	      
	      if(actioncommand.equals("Enter"))
	      {
	         result = stringToDouble(iofield.getText());
	         
	         iofield.setText(Double.toString(result));
	         changefield.setText(Double.toString(result));
	         change = result;
	      }
	      else if(actioncommand.equals("Get"))
	      {
	         change = 0;
	         result = 0;
	         iofield.setText(Double.toString(result));
	         changefield.setText(Double.toString(change));
		      try {
					ObjectOutputStream outputStream = 
							new ObjectOutputStream(new FileOutputStream("beverage.dat"));
					outputStream.writeObject(order);
					outputStream.close();
				}catch(IOException e1) {
					System.err.println("Error writing to file.");
					System.exit(0);
				}
		      System.exit(0);
	      }
	      else if(actioncommand.equals(beverages[0] + " " +  get_Price(beverages[0]) + " won"))
	      {
	    	 if(stock.get(beverages[0]) == 0) //재고가 0일때
	     	 {
	    		 Caution cau = new Caution();
	    		 cau.nostock();
	    		 cau.setVisible(true);
	     	 }
	         payment = new Payment(result, get_Price(beverages[0]), change);
	         if((get_Price(beverages[0]) <= change)&&(stock.get(beverages[0])>0))
	         {
	             inventory_control(beverages[0]);
	        	 change = payment.get_change();
	        	 changefield.setText(Double.toString(change));
	        	 for(int i=0;i<12;i++)
	        	 {
	        		 if(order[i].getName().equals(beverages[0]))
	        		 {
	        			 order[i].setNum(order[i].getNum()+1);
	        		 }
	        	 }
	         }
	         else if(get_Price(beverages[0]) > change)
	         {
	        	 Caution cau = new Caution();
	        	 cau.nomoney();
	        	 cau.setVisible(true);
	        	 changefield.setText(Double.toString(change));
	         }
	         result = payment.get_insert();         
	      }
	      else if (actioncommand.equals(beverages[1] + " " +  get_Price(beverages[1]) + " won"))
	      {
	    	 if(stock.get(beverages[1]) == 0) //재고가 0일때
	      	 {
	    		 Caution cau = new Caution();
	    		 cau.nostock();
	    		 cau.setVisible(true);
	      	 }
	         payment = new Payment(result, get_Price(beverages[1]), change);
	         if((get_Price(beverages[1]) <= change)&&(stock.get(beverages[1])>0))
	         {
	             inventory_control(beverages[1]);
	        	 change = payment.get_change();
	        	 changefield.setText(Double.toString(change));
	        	 for(int i=0;i<12;i++)
	        	 {
	        		 if(order[i].getName().equals(beverages[1]))
	        		 {
	        			 order[i].setNum(order[i].getNum()+1);
	        		 }
	        	 }
	         }
	         else if(get_Price(beverages[1]) > change)
	         {
	        	 Caution cau = new Caution();
	        	 cau.nomoney();
	        	 cau.setVisible(true);
	        	 changefield.setText(Double.toString(change));
	         }
	         result = payment.get_insert(); 
	      }
	      else if (actioncommand.equals(beverages[2] + " " +  get_Price(beverages[2]) + " won"))
	      {
	    	 if(stock.get(beverages[2]) == 0) //재고가 0일때
	       	 {
	    		 Caution cau = new Caution();
	    		 cau.nostock();
	    		 cau.setVisible(true);
	       	 }
	         payment = new Payment(result, get_Price(beverages[2]), change);
	         if((get_Price(beverages[2]) <= change)&&(stock.get(beverages[2])>0))
	         {
	             inventory_control(beverages[2]);
	        	 change = payment.get_change();
	        	 changefield.setText(Double.toString(change));
	        	 for(int i=0;i<12;i++)
	        	 {
	        		 if(order[i].getName().equals(beverages[2]))
	        		 {
	        			 order[i].setNum(order[i].getNum()+1);
	        		 }
	        	 }
	         }
	         else if(get_Price(beverages[2]) > change)
	         {
	        	 Caution cau = new Caution();
	        	 cau.nomoney();
	        	 cau.setVisible(true);
	        	 changefield.setText(Double.toString(change));
	         }
	         result = payment.get_insert(); 
	      }
	      else if (actioncommand.equals(beverages[3] + " " +  get_Price(beverages[3]) + " won"))
	      {
	    	  if(stock.get(beverages[3]) == 0) //재고가 0일때
	       	 {
	    		  Caution cau = new Caution();
	     		 cau.nostock();
	     		 cau.setVisible(true);
	       	 }
	         payment = new Payment(result, get_Price(beverages[3]), change);
	         if((get_Price(beverages[3]) <= change)&&(stock.get(beverages[3])>0))
	         {
	             inventory_control(beverages[3]);
	        	 change = payment.get_change();
	        	 changefield.setText(Double.toString(change));
	        	 for(int i=0;i<12;i++)
	        	 {
	        		 if(order[i].getName().equals(beverages[3]))
	        		 {
	        			 order[i].setNum(order[i].getNum()+1);
	        		 }
	        	 }
	         }
	         else if(get_Price(beverages[3]) > change)
	         {
	        	 Caution cau = new Caution();
	        	 cau.nomoney();
	        	 cau.setVisible(true);
	        	 changefield.setText(Double.toString(change));
	         }
	         result = payment.get_insert(); 
	      }
	      else if (actioncommand.equals(beverages[4] + " " +  get_Price(beverages[4]) + " won"))
	      {
	    	  if(stock.get(beverages[4]) == 0) //재고가 0일때
	       	 {
	    		  Caution cau = new Caution();
	     		 cau.nostock();
	     		 cau.setVisible(true);
	       	 }
	         payment = new Payment(result, get_Price(beverages[4]), change);
	         if((get_Price(beverages[4]) <= change)&&(stock.get(beverages[4])>0))
	         {
	             inventory_control(beverages[4]);
	        	 change = payment.get_change();
	        	 changefield.setText(Double.toString(change));
	        	 for(int i=0;i<12;i++)
	        	 {
	        		 if(order[i].getName().equals(beverages[4]))
	        		 {
	        			 order[i].setNum(order[i].getNum()+1);
	        		 }
	        	 }
	         }
	         else if(get_Price(beverages[4]) > change)
	         {
	        	 Caution cau = new Caution();
	        	 cau.nomoney();
	        	 cau.setVisible(true);
	        	 changefield.setText(Double.toString(change));
	         }
	         result = payment.get_insert(); 
	      }
	      else if (actioncommand.equals(beverages[5] + " " +  get_Price(beverages[5]) + " won"))
	      {
	    	  if(stock.get(beverages[5]) == 0) //재고가 0일때
	       	 {
	    		  Caution cau = new Caution();
	     		 cau.nostock();
	     		 cau.setVisible(true);
	       	 }
	         payment = new Payment(result, get_Price(beverages[5]), change);
	         if((get_Price(beverages[5]) <= change)&&(stock.get(beverages[5])>0))
	         {
	             inventory_control(beverages[5]);
	        	 change = payment.get_change();
	        	 changefield.setText(Double.toString(change));
	        	 for(int i=0;i<12;i++)
	        	 {
	        		 if(order[i].getName().equals(beverages[5]))
	        		 {
	        			 order[i].setNum(order[i].getNum()+1);
	        		 }
	        	 }
	         }
	         else if(get_Price(beverages[5]) > change)
	         {
	        	 Caution cau = new Caution();
	        	 cau.nomoney();
	        	 cau.setVisible(true);
	        	 changefield.setText(Double.toString(change));
	         }
	         result = payment.get_insert(); 
	      }
	      else if (actioncommand.equals(beverages[6] + " " +  get_Price(beverages[6]) + " won"))
	      {
	    	  if(stock.get(beverages[6]) == 0) //재고가 0일때
	       	 {
	    		  Caution cau = new Caution();
	     		  cau.nostock();
	     		  cau.setVisible(true);
	       	 }
	    	  payment = new Payment(result, get_Price(beverages[6]), change);
	          if((get_Price(beverages[6]) <= change)&&(stock.get(beverages[6])>0))
	         {
	             inventory_control(beverages[6]);
	        	 change = payment.get_change();
	        	 changefield.setText(Double.toString(change));
	        	 for(int i=0;i<12;i++)
	        	 {
	        		 if(order[i].getName().equals(beverages[6]))
	        		 {
	        			 order[i].setNum(order[i].getNum()+1);
	        		 }
	        	 }
	         }
	          else if(get_Price(beverages[6]) > change)
	         {
	        	 Caution cau = new Caution();
	        	 cau.nomoney();
	        	 cau.setVisible(true);
	        	 changefield.setText(Double.toString(change));
	         }
	         result = payment.get_insert(); 
	      }
	      else if (actioncommand.equals(beverages[7] + " " +  get_Price(beverages[7]) + " won"))
	      {
	    	  if(stock.get(beverages[7]) == 0) //재고가 0일때
	       	 {
	    		  Caution cau = new Caution();
	     		  cau.nostock();
	     		  cau.setVisible(true);
	       	 }
	         payment = new Payment(result, get_Price(beverages[7]), change);
	         if((get_Price(beverages[7]) <= change)&&(stock.get(beverages[7])>0))
	         {
	             inventory_control(beverages[7]);
	        	 change = payment.get_change();
	        	 changefield.setText(Double.toString(change));
	        	 for(int i=0;i<12;i++)
	        	 {
	        		 if(order[i].getName().equals(beverages[7]))
	        		 {
	        			 order[i].setNum(order[i].getNum()+1);
	        		 }
	        	 }
	         }
	         else if(get_Price(beverages[7]) > change)
	         {
	        	 Caution cau = new Caution();
	        	 cau.nomoney();
	        	 cau.setVisible(true);
	        	 changefield.setText(Double.toString(change));
	         }
	         result = payment.get_insert(); 
	      }
	      else if (actioncommand.equals(beverages[8] + " " +  get_Price(beverages[8]) + " won"))
	      {
	    	  if(stock.get(beverages[8]) == 0) //재고가 0일때
	       	 {
	    		  Caution cau = new Caution();
	    		  cau.nostock();
	     		  cau.setVisible(true);
	       	 }
	         payment = new Payment(result, get_Price(beverages[8]), change);
	         if((get_Price(beverages[8]) <= change)&&(stock.get(beverages[8])>0))
	         {
	             inventory_control(beverages[8]);
	        	 change = payment.get_change();
	        	 changefield.setText(Double.toString(change));
	        	 for(int i=0;i<12;i++)
	        	 {
	        		 if(order[i].getName().equals(beverages[8]))
	        		 {
	        			 order[i].setNum(order[i].getNum()+1);
	        		 }
	        	 }
	         }
	         else if(get_Price(beverages[8]) > change)
	         {
	        	 Caution cau = new Caution();
	        	 cau.nomoney();
	        	 cau.setVisible(true);
	        	 changefield.setText(Double.toString(change));
	         }
	         result = payment.get_insert(); 
	      }
	      else if (actioncommand.equals(beverages[9] + " " +  get_Price(beverages[9]) + " won"))
	      {
	    	  if(stock.get(beverages[9]) == 0) //재고가 0일때
	       	 {
	    		  Caution cau = new Caution();
	     		  cau.nostock();
	     		  cau.setVisible(true);
	       	 }
	         payment = new Payment(result, get_Price(beverages[9]), change);
	         if((get_Price(beverages[9]) <= change)&&(stock.get(beverages[9])>0))
	         {
	             inventory_control(beverages[9]);
	        	 change = payment.get_change();
	        	 changefield.setText(Double.toString(change));
	        	 for(int i=0;i<12;i++)
	        	 {
	        		 if(order[i].getName().equals(beverages[9]))
	        		 {
	        			 order[i].setNum(order[i].getNum()+1);
	        		 }
	        	 }
	         }
	         else if(get_Price(beverages[9]) > change)
	         {
	        	 Caution cau = new Caution();
	        	 cau.nomoney();
	        	 cau.setVisible(true);
	        	 changefield.setText(Double.toString(change));
	         }
	         result = payment.get_insert(); 
	      }
	      else if (actioncommand.equals(beverages[10] + " " +  get_Price(beverages[10]) + " won"))
	      {
	    	  if(stock.get(beverages[10]) == 0) //재고가 0일때
	       	 {
	    		  Caution cau = new Caution();
	     		  cau.nostock();
	     		  cau.setVisible(true);
	       	 }
	         payment = new Payment(result, get_Price(beverages[10]), change);
	         if((get_Price(beverages[10]) <= change)&&(stock.get(beverages[10])>0))
	         {
	             inventory_control(beverages[10]);
	        	 change = payment.get_change();
	        	 changefield.setText(Double.toString(change));
	        	 for(int i=0;i<12;i++)
	        	 {
	        		 if(order[i].getName().equals(beverages[10]))
	        		 {
	        			 order[i].setNum(order[i].getNum()+1);
	        		 }
	        	 }
	         }
	         else if(get_Price(beverages[10]) > change)
	         {
	        	 Caution cau = new Caution();
	        	 cau.nomoney();
	        	 cau.setVisible(true);
	        	 changefield.setText(Double.toString(change));
	         }
	         result = payment.get_insert(); 
	      }
	      else if (actioncommand.equals(beverages[11] + " " +  get_Price(beverages[11]) + " won"))
	      {
	    	  if(stock.get(beverages[11]) <= 0) //재고가 0일때
	       	 {
	    		  Caution cau = new Caution();
	     		  cau.nostock();
	     		  cau.setVisible(true);
	       	 }
	         payment = new Payment(result, get_Price(beverages[11]), change);
	         if((get_Price(beverages[11]) <= change)&&(stock.get(beverages[11])>0))
	         {
	             inventory_control(beverages[11]);
	        	 change = payment.get_change();
	        	 changefield.setText(Double.toString(change));
	        	 for(int i=0;i<12;i++)
	        	 {
	        		 if(order[i].getName().equals(beverages[11]))
	        		 {
	        			 order[i].setNum(order[i].getNum()+1);
	        		 }
	        	 }
	         }
	         else if(get_Price(beverages[11]) > change)
	         {
	        	 Caution cau = new Caution();
	        	 cau.nomoney();
	        	 cau.setVisible(true);
	        	 changefield.setText(Double.toString(change));
	         }
	         result = payment.get_insert(); 
	      }
	      else
	      {
	         iofield.setText("Unexpected error");
	      }
	   }
   
   // Throws NumberFormatException.
   private static double stringToDouble(String str) {
      // TODO stub code
      double res = 0;
      res = Double.parseDouble(str.trim());
      return res;
   }

   @Override
   public void add_stock() {
	   // TODO Auto-generated method stub
	   Iterator<String> name=stock.keySet().iterator();
	   while(name.hasNext())
	   {
		   stock.put(name.next(),10);
	   }
	      PrintWriter outputStream = null;
	      try {
	         String filename = super.get_file();
	         outputStream = new PrintWriter(new FileOutputStream(filename));
	      }catch(FileNotFoundException e){
	         System.out.println("Error opening the file stuff.txt");
	         System.exit(0);
	      }
	      
	      Iterator<String> names = price.keySet().iterator();
	      while(names.hasNext())
	      {
	         String keyset = names.next();
	         outputStream.println(keyset + " " + price.get(keyset) + " " + stock.get(keyset));
	      }
	      outputStream.close();
   }

}