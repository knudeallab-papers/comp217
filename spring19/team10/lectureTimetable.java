import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class lectureTimetable extends JFrame implements Serializable, ActionListener{

   public static Lecture[] lecList = new Lecture[30];
   public static int currentNum = 0;
   public static final int DAY = 5, INTERVAL = 28; //day divided by INTERVAL times
   public static final int WIDTH = 600, HEIGHT = 900;
   public static final int TIME_X = 550, TIME_Y = 840; // timePanel size
   public static final int BOX_X = TIME_X / DAY; 
   public static final int BOX_Y = TIME_Y / INTERVAL;  
   
   JFrame check = new JFrame();
   JPanel timePanel = new JPanel();
   public String FileName;
   
   public lectureTimetable() {
      
      super("Lecture " + login.roomnum+" Time");
      // Menu----------------------------------------------
      JMenuBar bar = new JMenuBar();
      bar.setBounds(0, 0, WIDTH, 30);
      
      JMenu lecMenu = new JMenu("add"); 
      bar.add(lecMenu);
      //item 1
      JMenuItem itemAdd = new JMenuItem("Add Lecture");
      itemAdd.addActionListener(this);
      lecMenu.add(itemAdd);
      //item 2
      JMenuItem itemSub = new JMenuItem("Sub Lecture");
      itemSub.addActionListener(this);
      lecMenu.add(itemSub);
      
      JMenu optionMenu = new JMenu("Option");
      bar.add(optionMenu);
      
      JMenuItem refresh = new JMenuItem("Refresh");
      refresh.addActionListener(this);
      optionMenu.add(refresh);
      
      
      setJMenuBar(bar);
      
      // Panel
      setSize(WIDTH+20, HEIGHT+80);
      setLocation(500,50);
      setVisible(true);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      JLabel timeLabel;
      String labelName;
      setLayout(null);
      for(int i = 0; i < INTERVAL / 2; i++) {
         labelName = Integer.toString(i+9);
         timeLabel = new JLabel(labelName);
         timeLabel.setBounds(10, (HEIGHT - TIME_Y) + BOX_Y * i * 2, BOX_X, BOX_Y);
         add(timeLabel);
      }
      
      JLabel dayLabel;
      for(int i = 0; i < DAY; i++) {
    	  if(i == 0)
    		  labelName = "Monday";
    	  else if(i == 1)
    		  labelName = "Tuesday";
    	  else if(i == 2)
    		  labelName = "Wednesday";
    	  else if(i == 3)
    		  labelName = "Thursday";
    	  else
    		  labelName = "Friday";
    		  
    	  dayLabel = new JLabel(labelName);
    	  dayLabel.setBounds((WIDTH - TIME_X) + BOX_X * i, 30, BOX_X, BOX_Y);
    	  add(dayLabel);
      }
      
      
      timePanel.setBounds((WIDTH - TIME_X), (HEIGHT - TIME_Y), TIME_X, TIME_Y);
      
      timePanel.setBackground(Color.WHITE);
      add(timePanel);
      timePanel.setLayout(null);
      
      readData();
      
      
   }
   
   public void inputData() {
	   FileName = login.roomnum+".dat";
	   try { 
      	 
           ObjectOutputStream outputStream = 
                 new ObjectOutputStream(new FileOutputStream(FileName));
           for(int i = 0; i < currentNum; i++) {
        	   outputStream.writeObject(lecList[i]);
           }
           outputStream.close();
        } catch(EOFException g) {}
        catch (IOException e) { e.printStackTrace(); }
      
   }
   
   public void readData() {
      
	   FileName = login.roomnum+".dat";
      try { 
	         ObjectInputStream inStream = 
	               new ObjectInputStream(new FileInputStream(FileName));
	         try {
	        	 currentNum = 0;
	            while(true) {
	               Lecture lec = (Lecture)inStream.readObject();
	               lecList[currentNum++] = lec;
	               newPanel(lecList[currentNum-1],timePanel);
	               
	            }
	         }catch(EOFException | ClassNotFoundException e) {} 
	         inStream.close();
	      } catch (IOException e) { e.printStackTrace(); }
   
   }
   
   public void newPanel(Lecture lec, JPanel parentPanel) {
      JPanel panel = new JPanel();
      
      JLabel lecName, profName, dayString;
      JLabel start, end;
      
      panel.setLayout(new GridLayout(5, 1));
      
      lecName = new JLabel(lec.getLecName());
      profName = new JLabel(lec.getprofessor());
      
      panel.add(lecName);
      panel.add(profName);      
      parentPanel.add(panel);
      
      panel.setBounds(BOX_X * (lec.getDay()-1), lec.getStart().getTotalMin() - 540
            , BOX_X , lec.getDuring().getTotalMin());
      
      
      int index = currentNum - 1;
      index %= 8;
      Color c = new Color(100,200,50);
      if	 (index == 0)
    	  panel.setBackground(c);
      else if(index == 1)
         panel.setBackground(Color.BLUE);
      else if(index == 2)
         panel.setBackground(Color.CYAN);
      else if(index == 3)
         panel.setBackground(Color.ORANGE);
      else if(index == 4)
         panel.setBackground(Color.LIGHT_GRAY);
      else if(index == 5)
         panel.setBackground(Color.GREEN);
      else if(index == 6)
         panel.setBackground(Color.MAGENTA);
      else if(index == 7)
         panel.setBackground(Color.PINK);
      else if(index == 8)
         panel.setBackground(Color.YELLOW);
      
      setVisible(true);
   }

   public void checkDelete() {
	    
	    check.setLocation(500, 280);
	    check.setSize(250, 200);
	    check.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    check.setVisible(true);
	    check.setLayout(null);
	    
	    JLabel checkText = new JLabel("Delete All Data");
	    checkText.setBounds(70, 30, 100, 30);
	    
	    JButton checkButton = new JButton("Yes");
	    checkButton.setBounds(70, 100, 100, 50);
	    checkButton.addActionListener(this);
	    checkButton.setActionCommand("checkButton");
	    
	    check.add(checkText);
	    check.add(checkButton);
	    
   }

   @Override
   public void actionPerformed(ActionEvent e) {
	   String s = e.getActionCommand();
	   
	   if(s.equals("Add Lecture")) { // add lecture
		   addLec a = new addLec();
	   }
	   else if(s.equals("Sub Lecture")) { // sub all lecture 
		  checkDelete();
		
	   }
	   else if(s.equals("Refresh")) { //refresh
		   this.newPanel(lecList[currentNum-1], timePanel);
		   inputData();
	   }
	   else if(s.equals("checkButton")) {
		   FileName = login.roomnum+".dat";
		   try { 
			   	ObjectOutputStream outputStream = 
			   			new ObjectOutputStream(new FileOutputStream(FileName));
			   	outputStream.close();
	        	} catch(EOFException g) {}
	        	catch (IOException a) { a.printStackTrace();}
		   check.dispose();
		   dispose();
	   }
   }
   
}