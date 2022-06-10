package TP;

import java.awt.BorderLayout;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JColorChooser;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.HashMap;

import java.util.Set;
import java.util.Map;
import java.util.ArrayList;

public class Inventory extends JFrame implements ActionListener{
   
   private int targetnum;
   ArrayList<food> arr1 = new ArrayList();
   HashMap<String, Integer>  MI= new HashMap<String, Integer>();//meal inventory
   private JTextArea area;
   private JButton plus1 , minus1;
   private JButton plus2 , minus2;
   private JButton plus3 , minus3;
   private JButton plus4 , minus4;
   private JButton plus5 , minus5;
   private JButton plus6 , minus6;
   private JButton plus7 , minus7;
   private JButton plus8 , minus8;
   private JButton plus9 , minus9;
   private JButton plus10 , minus10;
   private JButton plus11 , minus11;
   private JButton plus12 , minus12;
   private JButton plus13 , minus13;
   private JButton plus14 , minus14;
   private JButton plus15 , minus15;
   private JButton plus16 , minus16;
   private JButton plus17 , minus17;
   private JButton plus18 , minus18;
   private JButton plus19 , minus19;
   private JButton plus20 , minus20;
   private JButton plus21 , minus21;
   
   public class food{
      public int stock;
      public String name;
      public food(int num, String name)
      {
         this.stock = num;
         this.name = name;
      }
   }
   public Inventory(int targetmeal) throws IOException {
      super("������");
      setSize(800,1000);
      setBackground(Color.LIGHT_GRAY);
      setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      setLayout(null);
      
      area = new JTextArea();
      area.setBounds(50,50,300,850);
      add(area);
      
      Scanner stocktest = null;
      try {
         stocktest = new Scanner(new FileInputStream("stock.txt"),"UTF-8");
      }catch(FileNotFoundException e){
          System.out.println("Error opening the file stock.txt");
          System.exit(0);
       }
      //String stockteststr = stocktest.nextLine();
      if(stocktest.hasNextLine() == false)//             stock�� null�ϋ�
      {
      Scanner inputStream = null;
      String textfile = "menu" + targetmeal + ".txt";
       try {
               inputStream = new Scanner(new FileInputStream(textfile),"UTF-8");
               
               String str1 = "";
               int cnt = 0;
               String forhash = "";
               String puthash = "";
               String thename= "";
               
             while(inputStream.hasNextLine()) {//�Ĵ�ǥ �޾ƿ�
                cnt++;
                forhash = inputStream.nextLine();
                if(cnt == 1)//�޴� �ް� �ؽøʿ� ����
                {
                   thename = forhash;
                   //puthash = forhash;
                }
                if(cnt == 2)
                {
                   thename += forhash;
                   //puthash += forhash;
                   //MI.put(puthash, 100);
                   arr1.add(new food(100,thename));
                }
                //str1 += forhash;
                //str1 += "\n";
                if(cnt == 3)//���๮�� ������ cnt �ʱ�ȭ
                {
                   cnt = 0;
                }
             }
             String forshowmap = "";
             /*
             Set<String> keySet = MI.keySet();        
             for (String key : keySet) {    
                forshowmap += key + " " +MI.get(key) + "\n\n";
           
                System.out.println(key + " : " + MI.get(key));        
                }
             */
             String forshowarr = "";
             for(int i = 0 ; i<arr1.size(); i++)
             {
                forshowarr += (i+1)+ "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
             }
             area.setText(forshowarr);
             
               
            }catch(FileNotFoundException e){
               e.printStackTrace();
               System.exit(0);
               }
       
       PrintWriter outputStream = null;
       try {
            String fileName = "stock.txt";
            outputStream = 
                  new PrintWriter(new FileOutputStream(fileName));
            for(int i = 0 ; i < arr1.size() ; i++)
            {
               outputStream.println(arr1.get(i).name);
               outputStream.println(arr1.get(i).stock);
            }
            outputStream.close();
         }catch(FileNotFoundException e){
            System.out.println("Error opening the file stock.txt");
            System.exit(0);
         }
      }
      else {   /////////stock�� null�� �ƴҶ�
         int cnt = 0;
         String thename = "";
         int thestock = 0;
         
         while(stocktest.hasNextLine())
         {
           cnt++;
           if(cnt == 1)
           {
              thename = stocktest.nextLine();
           }
           if(cnt  == 2)
           {
              thestock = stocktest.nextInt();
              arr1.add(new food(thestock, thename));
           }
           if(cnt == 3)
           {
              stocktest.nextLine();
              cnt = 0;
           }
         }
         PrintWriter outputStream1 = null;String fileName = "stock.txt";
         //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (new FileOutputStream(fileName),"UTF-8"));
         
         try {
               FileOutputStream output=new FileOutputStream("stock.txt");
               OutputStreamWriter writer=new OutputStreamWriter(output,"UTF-8");
               BufferedWriter out=new BufferedWriter(writer);
               /*
               outputStream1 = 
                     new PrintWriter(new FileOutputStream(fileName));
               for(int i = 0 ; i < arr1.size() ; i++)
               {
                  outputStream1.println(arr1.get(i).name);
                  outputStream1.println(arr1.get(i).stock);
               }
               outputStream1.close();
               */
               for(int i = 0 ; i < arr1.size() ; i++)
               {
                  out.write(arr1.get(i).name);
                  out.write("\n");
                  out.write(arr1.get(i).stock);
                  out.write("\n");
               }
               out.close();
            }catch(FileNotFoundException e){
               System.out.println("Error opening the file stock.txt");
               System.exit(0);
            }
          
          String forshowarr = "";
          for(int i = 0 ; i<arr1.size(); i++)
          {
             forshowarr += (i+1)+ "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
          }
          area.setText(forshowarr);
      
      }
      stocktest.close();
      JButton stop = new JButton("����ߴ�");
      stop.setBounds(430, 700, 200, 20);
      stop.addActionListener(this);
      add(stop);
       plus1 = new JButton("(1��)+");plus2 = new JButton("(2��)+");
       plus3 = new JButton("(3��)+");plus4 = new JButton("(4��)+"); plus5 = new JButton("(5��)+");
       plus6 = new JButton("(6��)+");plus7 = new JButton("(7��)+");
       plus8 = new JButton("(8��)+");plus9 = new JButton("(9��)+");plus10 = new JButton("(10��)+");
       plus11 = new JButton("(11��)+");plus12 = new JButton("(12��)+"); plus13 = new JButton("(13��)+");
       plus14 = new JButton("(14��)+"); plus15 = new JButton("(15��)+");
       plus16 = new JButton("(16��)+");plus17 = new JButton("(17��)+");
       plus18 = new JButton("(18��)+"); plus19 = new JButton("(19��)+");
       plus20 = new JButton("(20��)+"); plus21 = new JButton("(21��)+");

       minus1 = new JButton("(1��)-");minus2 = new JButton("(2��)-");
       minus3 = new JButton("(3��)-");minus4 = new JButton("(4��)-");
       minus5 = new JButton("(5��)-");minus6 = new JButton("(6��)-");
       minus7 = new JButton("(7��)-"); minus8 = new JButton("(8��)-");
       minus9 = new JButton("(9��)-"); minus10 = new JButton("(10��)-");
       minus11 = new JButton("(11��)-"); minus12 = new JButton("(12��)-");
       minus13 = new JButton("(13��)-"); minus14 = new JButton("(14��)-");
       minus15 = new JButton("(15��)-"); minus16 = new JButton("(16��)-");
       minus17 = new JButton("(17��)-"); minus18 = new JButton("(18��)-");
       minus19 = new JButton("(19��)-"); minus20 = new JButton("(20��)-");
       minus21 = new JButton("(21��)-");
       
       plus1.setBounds(400, 50, 100, 20); minus1.setBounds(550, 50, 100, 20);
       plus1.addActionListener(this); minus1.addActionListener(this);
       add(plus1); add(minus1);
       
       plus2.setBounds(400, 80, 100, 20); minus2.setBounds(550, 80, 100, 20);
       plus2.addActionListener(this); minus2.addActionListener(this);
       add(plus2); add(minus2);
       
       plus3.setBounds(400, 110, 100, 20); minus3.setBounds(550, 110, 100, 20);
       plus3.addActionListener(this); minus3.addActionListener(this);
       add(plus3); add(minus3);
       
       plus4.setBounds(400, 140, 100, 20); minus4.setBounds(550, 140, 100, 20);
       plus4.addActionListener(this); minus4.addActionListener(this);
       add(plus4); add(minus4);
       
       plus5.setBounds(400, 170, 100, 20); minus5.setBounds(550, 170, 100, 20);
       plus5.addActionListener(this); minus5.addActionListener(this);
       add(plus5); add(minus5);
       
       plus6.setBounds(400, 200, 100, 20); minus6.setBounds(550, 200, 100, 20);
       plus6.addActionListener(this); minus6.addActionListener(this);
       add(plus6); add(minus6);
       
       plus7.setBounds(400, 230, 100, 20); minus7.setBounds(550, 230, 100, 20);
       plus7.addActionListener(this); minus7.addActionListener(this);
       add(plus7); add(minus7);
       
       plus8.setBounds(400, 260, 100, 20); minus8.setBounds(550, 260, 100, 20);
       plus8.addActionListener(this); minus8.addActionListener(this);
       add(plus8); add(minus8);
       
       plus9.setBounds(400, 290, 100, 20); minus9.setBounds(550, 290, 100, 20);
       plus9.addActionListener(this); minus9.addActionListener(this);
       add(plus9); add(minus9);
       
       plus10.setBounds(400, 320, 100, 20); minus10.setBounds(550, 320, 100, 20);
       plus10.addActionListener(this); minus10.addActionListener(this);
       add(plus10); add(minus10);
       
       plus11.setBounds(400, 350, 100, 20); minus11.setBounds(550, 350, 100, 20);
       plus11.addActionListener(this); minus11.addActionListener(this);
       add(plus11); add(minus11);
       
       plus12.setBounds(400, 380, 100, 20); minus12.setBounds(550, 380, 100, 20);
       plus12.addActionListener(this); minus12.addActionListener(this);
       add(plus12); add(minus12);
       
       plus13.setBounds(400, 410, 100, 20); minus13.setBounds(550, 410, 100, 20);
       plus13.addActionListener(this); minus13.addActionListener(this);
       add(plus13); add(minus13);
       
       plus14.setBounds(400, 440, 100, 20); minus14.setBounds(550, 440, 100, 20);
       plus14.addActionListener(this); minus14.addActionListener(this);
       add(plus14); add(minus14);
       
       plus15.setBounds(400, 470, 100, 20); minus15.setBounds(550, 470, 100, 20);
       plus15.addActionListener(this); minus15.addActionListener(this);
       add(plus15); add(minus15);
       
       plus16.setBounds(400, 500, 100, 20); minus16.setBounds(550, 500, 100, 20);
       plus16.addActionListener(this); minus16.addActionListener(this);
       add(plus16); add(minus16);
       
       plus17.setBounds(400, 530, 100, 20); minus17.setBounds(550, 530, 100, 20);
       plus17.addActionListener(this); minus17.addActionListener(this);
       add(plus17); add(minus17);
       
       plus18.setBounds(400, 560, 100, 20); minus18.setBounds(550, 560, 100, 20);
       plus18.addActionListener(this); minus18.addActionListener(this);
       add(plus18); add(minus18);
       
       plus19.setBounds(400, 590, 100, 20); minus19.setBounds(550, 590, 100, 20);
       plus19.addActionListener(this); minus19.addActionListener(this);
       add(plus19); add(minus19);
       
       plus20.setBounds(400, 620, 100, 20); minus20.setBounds(550, 620, 100, 20);
       plus20.addActionListener(this); minus20.addActionListener(this);
       add(plus20); add(minus20);
       
       plus21.setBounds(400, 650, 100, 20); minus21.setBounds(550, 650, 100, 20);
       plus21.addActionListener(this); minus21.addActionListener(this);
       add(plus21); add(minus21);
       
   }

   public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub
         String cmd = e.getActionCommand();
         if(cmd.equals("����ߴ�")) {
        	 try {
                 String fileName = "stock.txt";
                 PrintWriter outputStream = 
                       new PrintWriter(new FileOutputStream(fileName));
                 outputStream.write("");
                 area.setText("");
                 outputStream.close();
              }catch(FileNotFoundException e2){
                 System.out.println("Error opening the file stuff.txt");
                 System.exit(0);
              }
         }
         else if(cmd.equals("(1��)+"))
         {
            arr1.get(0).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   
               a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);   
         }
         else if(cmd.equals("(1��)-"))
         {
            arr1.get(0).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(2��)+"))
         {
            arr1.get(1).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(2��)-"))
         {
            arr1.get(1).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(3��)+"))
         {
            arr1.get(2).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(3��)-"))
         {
            arr1.get(2).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(4��)+"))
         {
            arr1.get(3).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(4��)-"))
         {
            arr1.get(3).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(5��)+"))
         {
            arr1.get(4).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(5��)-"))
         {
            arr1.get(4).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(6��)+"))
         {
            arr1.get(5).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(6��)-"))
         {
            arr1.get(5).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(7��)+"))
         {
            arr1.get(6).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(7��)-"))
         {
            arr1.get(6).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(8��)+"))
         {
            arr1.get(7).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(8��)-"))
         {
            arr1.get(7).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(9��)+"))
         {
            arr1.get(8).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(9��)-"))
         {
            arr1.get(8).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(10��)+"))
         {
            arr1.get(9).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(10��)-"))
         {
            arr1.get(9).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(11��)+"))
         {
            arr1.get(10).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(11��)-"))
         {
            arr1.get(10).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(12��)+"))
         {
            arr1.get(11).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(12��)-"))
         {
            arr1.get(11).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(13��)+"))
         {
            arr1.get(12).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(13��)-"))
         {
            arr1.get(12).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(14��)+"))
         {
            arr1.get(13).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(14��)-"))
         {
            arr1.get(13).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(15��)+"))
         {
            arr1.get(14).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(15��)-"))
         {
            arr1.get(14).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(16��)+"))
         {
            arr1.get(15).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(16��)-"))
         {
            arr1.get(15).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(17��)+"))
         {
            arr1.get(16).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(17��)-"))
         {
            arr1.get(16).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(18��)+"))
         {
            arr1.get(17).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(18��)-"))
         {
            arr1.get(17).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(19��)+"))
         {
            arr1.get(18).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(19��)-"))
         {
            arr1.get(18).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(20��)+"))
         {
            arr1.get(19).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(20��)-"))
         {
            arr1.get(19).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(21��)+"))
         {
            arr1.get(20).stock++;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
         else if(cmd.equals("(21��)-"))
         {
            arr1.get(20).stock--;
            PrintWriter a = null;
            try { a = new PrintWriter(new FileOutputStream("stock.txt")); } 
            catch (FileNotFoundException e1) { e1.printStackTrace(); }
            String stockstr="";
            for(int i = 0 ; i < arr1.size() ; i++)
            {   a.println(arr1.get(i).name);
               a.println(arr1.get(i).stock);
               stockstr += (i+1) + "�� " + arr1.get(i).name + " : " + arr1.get(i).stock + "\n\n";
            }
            a.close();
            area.setText(stockstr);
         }
      }
}