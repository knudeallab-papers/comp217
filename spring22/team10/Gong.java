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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Gong extends JFrame implements ActionListener{

   private int cnt1;
   private int cnt2;
   private int cnt3;
   private int cnt4;
   private int cnt5;
   private int cnt6;
   private int total;
   private JTextField totprice;
   private Font font1;
   
   public Gong() {
      
      super("식권 구매");
      setSize(800,1000);
      setBackground(Color.LIGHT_GRAY);
      setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      setLayout(null);
      
      JPanel massPanel = new JPanel();
      massPanel.setBounds(0, 100, 300, 400);
      int i=1;
      JLabel inform = new JLabel("재고");
      massPanel.add(inform);
      try{
    	  Scanner keyboard = new Scanner(new FileInputStream("stock.txt"));
    	  while(keyboard.hasNext()) {
        	  JLabel menu = new JLabel("("+(i++)+") "+keyboard.nextLine()+":"+keyboard.nextLine()+"개");
        	  massPanel.add(menu);
          }
      }catch(FileNotFoundException e) {
    	  e.printStackTrace();
    	  System.exit(0);
      }
      massPanel.setLayout(new GridLayout(i,1));
      add(massPanel);
      
      JPanel BtnPanel = new JPanel();
      BtnPanel.setBounds(350, 300, 400, 400);
      BtnPanel.setLayout(new GridLayout(3,3));
      JButton Btn1 = new JButton("5,000원");
      Btn1.addActionListener(this);
      JButton Btn2 = new JButton("4,500원");
      Btn2.addActionListener(this);
      JButton Btn3 = new JButton("4,000원");
      Btn3.addActionListener(this);
      JButton Btn4 = new JButton("3,500원");
      Btn4.addActionListener(this);
      JButton Btn5 = new JButton("3,000원");
      Btn5.addActionListener(this);
      JButton Btn6 = new JButton("1,000원");
      Btn6.addActionListener(this);
      
      BtnPanel.add(Btn1);
      BtnPanel.add(Btn2);
      BtnPanel.add(Btn3);
      BtnPanel.add(Btn4);
      BtnPanel.add(Btn5);
      BtnPanel.add(Btn6);
      add(BtnPanel);
      
      JButton payco = new JButton("페이코");
      JButton cash = new JButton("현금");
      JButton cancel = new JButton("취소");
      payco.setBounds(500, 800, 100, 100);
      payco.addActionListener(this);
      cash.setBounds(600, 800, 100, 100);
      cash.addActionListener(this);
      cancel.setBounds(400, 800, 100, 100);
      cancel.addActionListener(this);
      add(payco);
      add(cash);
      add(cancel);
      
      font1 = new Font("맑은 고딕", Font.BOLD, 100);
      totprice = new JTextField();
      totprice.setEnabled(false);//수정불가
      totprice.setBounds(350, 100, 400, 200);
      totprice.setFont(font1);
      add(totprice);
      
      
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      
      String btnStr = e.getActionCommand();

      if(btnStr.equals("5,000원"))
      {
         cnt1++;
         total += 5000;
         String toshow = Integer.toString(total);
         System.out.print(toshow);
         System.out.println(cnt1);
         totprice.setText(toshow);
      }
      else if(btnStr.equals("4,500원"))
      {
         cnt2++;
         total += 4500;
         String toshow = Integer.toString(total);
         System.out.print(toshow);
         System.out.println(cnt2);
         totprice.setText(toshow);
      }
      else if(btnStr.equals("4,000원"))
      {
         cnt3++;
         total += 4000;
         String toshow = Integer.toString(total);
         System.out.print(toshow);
         System.out.println(cnt3);
         totprice.setText(toshow);
      }
      else if(btnStr.equals("3,500원"))
      {
         cnt4++;
         total += 3500;
         String toshow = Integer.toString(total);
         System.out.print(toshow);
         System.out.println(cnt4);
         totprice.setText(toshow);
      }
      else if(btnStr.equals("3,000원"))
      {
         cnt5++;
         total += 3000;
         String toshow = Integer.toString(total);
         System.out.print(toshow);
         System.out.println(cnt5);
         totprice.setText(toshow);
      }
      else if(btnStr.equals("1,000원"))
      {
         cnt6++;
         total += 1000;
         String toshow = Integer.toString(total);
         System.out.print(toshow);
         System.out.println(cnt6);
         totprice.setText(toshow);
      }
      else if(btnStr.equals("페이코"))
      {
         mealTicket a = new mealTicket(total,cnt1,cnt2,cnt3,cnt4,cnt5,cnt6,"페이코");
         a.setVisible(true);
      }
      else if(btnStr.equals("현금"))
      {
         mealTicket a = new mealTicket(total,cnt1,cnt2,cnt3,cnt4,cnt5,cnt6,"현금");
         a.setVisible(true);
      }
      else if(btnStr.equals("취소"))
      {
         total = 0;
         cnt1 = 0; cnt2 = 0; cnt3 = 0; cnt4 = 0; cnt5 = 0; cnt6 = 0;
         String zero = "0";
         totprice.setText(zero);
      }
      
   }

}
