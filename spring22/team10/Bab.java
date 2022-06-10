package TP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Bab extends JFrame {
   
   private Font font1;
   
   public Bab() {
      super();
      setSize(800, 1000);
      
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setTitle("식단표");
      
      font1 = new Font("돋움", Font.BOLD,40);
      
      JButton btn1 = new JButton("월");
      btn1.setFont(font1);
      btn1.setBounds(55, 50, 80, 80);
      
      JButton btn2 = new JButton("화");
      btn2.setFont(font1);
      btn2.setBounds(155, 50, 80, 80);
      
      JButton btn3 = new JButton("수");
      btn3.setFont(font1);
      btn3.setBounds(255, 50, 80, 80);
      
      JButton btn4 = new JButton("목");
      btn4.setFont(font1);
      btn4.setBounds(355, 50, 80, 80);
      
      JButton btn5 = new JButton("금");
      btn5.setFont(font1);
      btn5.setBounds(455, 50, 80, 80);
      
      JButton btn6 = new JButton("토");
      btn6.setFont(font1);
      btn6.setBounds(555, 50, 80, 80);
      
      JButton btn7 = new JButton("일");
      btn7.setFont(font1);
      btn7.setBounds(655, 50, 80, 80);
      
      btn1.setBackground(Color.WHITE);
      btn2.setBackground(Color.WHITE);
      btn3.setBackground(Color.WHITE);
      btn4.setBackground(Color.WHITE);
      btn5.setBackground(Color.WHITE);
      btn6.setBackground(Color.WHITE);
      btn7.setBackground(Color.WHITE);
      
      add(btn1);
      add(btn2);
      add(btn3);
      add(btn4);
      add(btn5);
      add(btn6);
      add(btn7);
      
      
      JTextArea a = new JTextArea(10, 23);
      String line = "★로 표시된 항목은 돈육이 포함된 메뉴입니다.";
      a.setText(line);
      
      JPanel back0 = new JPanel();
      back0.setBounds(10, 10, 300, 30);
      back0.add(a);
      add(back0);
      
      
      JTextArea lunch1 = new JTextArea(12, 8);
      JTextArea lunch2 = new JTextArea(12, 8);
      JTextArea lunch3 = new JTextArea(12, 8);
      JTextArea lunch4 = new JTextArea(12, 8);
      JTextArea lunch5 = new JTextArea(12, 8);
      JTextArea lunch6 = new JTextArea(12, 8);
      JTextArea lunch7 = new JTextArea(12, 8);
      JTextArea lunch8 = new JTextArea(12, 8);
      JTextArea lunch9 = new JTextArea(12, 8);
      JTextArea lunch10 = new JTextArea(12, 8);
      JTextArea lunch11 = new JTextArea(12, 8);
      JTextArea lunch12 = new JTextArea(12, 8);
      JTextArea lunch13 = new JTextArea(12, 8);
      JTextArea lunch14 = new JTextArea(12, 8);
      JTextArea lunch15 = new JTextArea(12, 8);
      JTextArea lunch16 = new JTextArea(12, 8);
      JTextArea lunch17 = new JTextArea(12, 8);
      JTextArea lunch18 = new JTextArea(12, 8);
      JTextArea lunch19 = new JTextArea(12, 8);
      JTextArea lunch20 = new JTextArea(12, 8);
      JTextArea lunch21 = new JTextArea(12, 8);   
      
      
      lunch1.setBackground(Color.WHITE);
      lunch2.setBackground(Color.WHITE);
      lunch3.setBackground(Color.WHITE);
      lunch4.setBackground(Color.WHITE);
      lunch5.setBackground(Color.WHITE);
      lunch6.setBackground(Color.WHITE);
      lunch7.setBackground(Color.WHITE);
      lunch8.setBackground(Color.WHITE);
      lunch9.setBackground(Color.WHITE);
      lunch10.setBackground(Color.WHITE);
      lunch11.setBackground(Color.WHITE);
      lunch12.setBackground(Color.WHITE);
      lunch13.setBackground(Color.WHITE);
      lunch14.setBackground(Color.WHITE);
      lunch15.setBackground(Color.WHITE);
      lunch16.setBackground(Color.WHITE);
      lunch17.setBackground(Color.WHITE);
      lunch18.setBackground(Color.WHITE);
      lunch19.setBackground(Color.WHITE);
      lunch20.setBackground(Color.WHITE);
      lunch21.setBackground(Color.WHITE);
      
      
      JPanel back1 = new JPanel();
      JPanel back2 = new JPanel();
      JPanel back3 = new JPanel();
      JPanel back4 = new JPanel();
      JPanel back5 = new JPanel();
      JPanel back6 = new JPanel();
      JPanel back7 = new JPanel();
      JPanel back8 = new JPanel();
      JPanel back9 = new JPanel();
      JPanel back10 = new JPanel();
      JPanel back11 = new JPanel();
      JPanel back12 = new JPanel();
      JPanel back13 = new JPanel();
      JPanel back14 = new JPanel();
      JPanel back15 = new JPanel();
      JPanel back16 = new JPanel();
      JPanel back17 = new JPanel();
      JPanel back18 = new JPanel();
      JPanel back19 = new JPanel();
      JPanel back20 = new JPanel();
      JPanel back21 = new JPanel();

      back1.setBounds(50, 150, 100, 250);
      back2.setBounds(150, 150, 100, 250);
      back3.setBounds(250, 150, 100, 250);
      back4.setBounds(350, 150, 100, 250);
      back5.setBounds(450, 150, 100, 250);
      back6.setBounds(550, 150, 100, 250);
      back7.setBounds(650, 150, 100, 250);
      
      back8.setBounds(50, 400, 100, 250);
      back9.setBounds(150, 400, 100, 250);
      back10.setBounds(250, 400, 100, 250);
      back11.setBounds(350, 400, 100, 250);
      back12.setBounds(450, 400, 100, 250);
      back13.setBounds(550, 400, 100, 250);
      back14.setBounds(650, 400, 100, 250);
      
      back15.setBounds(50, 650, 100, 250);
      back16.setBounds(150, 650, 100, 250);
      back17.setBounds(250, 650, 100, 250);
      back18.setBounds(350, 650, 100, 250);
      back19.setBounds(450, 650, 100, 250);
      back20.setBounds(550, 650, 100, 250);
      back21.setBounds(650, 650, 100, 250);
      
      JScrollPane scroll1 = new JScrollPane(lunch1);   
      JScrollPane scroll2 = new JScrollPane(lunch2);
      JScrollPane scroll3 = new JScrollPane(lunch3);
      JScrollPane scroll4 = new JScrollPane(lunch4);
      JScrollPane scroll5 = new JScrollPane(lunch5);
      JScrollPane scroll6 = new JScrollPane(lunch6);
      JScrollPane scroll7 = new JScrollPane(lunch7);
      JScrollPane scroll8 = new JScrollPane(lunch8);
      JScrollPane scroll9 = new JScrollPane(lunch9);
      JScrollPane scroll10 = new JScrollPane(lunch10);
      JScrollPane scroll11 = new JScrollPane(lunch11);
      JScrollPane scroll12 = new JScrollPane(lunch12);
      JScrollPane scroll13 = new JScrollPane(lunch13);
      JScrollPane scroll14 = new JScrollPane(lunch14);
      JScrollPane scroll15 = new JScrollPane(lunch15);
      JScrollPane scroll16 = new JScrollPane(lunch16);
      JScrollPane scroll17 = new JScrollPane(lunch17);
      JScrollPane scroll18 = new JScrollPane(lunch18);
      JScrollPane scroll19 = new JScrollPane(lunch19);
      JScrollPane scroll20 = new JScrollPane(lunch20);
      JScrollPane scroll21 = new JScrollPane(lunch21);
      
      scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll4.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll5.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll5.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll6.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll6.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll7.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll7.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll8.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll8.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll9.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll9.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll10.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll10.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll11.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll11.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll12.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll12.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll13.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll13.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll14.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll14.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll15.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll15.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll16.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll16.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll17.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll17.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll18.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll18.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll19.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll19.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll20.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll20.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      scroll21.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll21.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      back1.add(scroll1);
      back2.add(scroll2);
      back3.add(scroll3);
      back4.add(scroll4);
      back5.add(scroll5);
      back6.add(scroll6);
      back7.add(scroll7);
      back8.add(scroll8);
      back9.add(scroll9);
      back10.add(scroll10);
      back11.add(scroll11);
      back12.add(scroll12);
      back13.add(scroll13);
      back14.add(scroll14);
      back15.add(scroll15);
      back16.add(scroll16);
      back17.add(scroll17);
      back18.add(scroll18);
      back19.add(scroll19);
      back20.add(scroll20);
      back21.add(scroll21);
      
      add(back1);
      add(back2);
      add(back3);
      add(back4);
      add(back5);
      add(back6);
      add(back7);
      add(back8);
      add(back9);
      add(back10);
      add(back11);
      add(back12);
      add(back13);
      add(back14);
      add(back15);
      add(back16);
      add(back17);
      add(back18);
      add(back19);
      add(back20);
      add(back21);
      
      setLayout(null);
      
      Scanner inputStream = null;
      
      try {
            inputStream = new Scanner(new FileInputStream("menu1.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch1.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
         }

      try {
            inputStream = new Scanner(new FileInputStream("menu2.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch2.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
         }
      
      try {
            inputStream = new Scanner(new FileInputStream("menu3.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch3.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
         }
      
      try {
            inputStream = new Scanner(new FileInputStream("menu4.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch4.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
         }
      
      try {
            inputStream = new Scanner(new FileInputStream("menu5.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch5.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
         }
      
      try {
            inputStream = new Scanner(new FileInputStream("menu6.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch6.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
         }
      
      try {
            inputStream = new Scanner(new FileInputStream("menu7.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch7.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
            }
   
      try {
            inputStream = new Scanner(new FileInputStream("menu8.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch8.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
            }
       
      try {
            inputStream = new Scanner(new FileInputStream("menu9.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch9.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
            }
      
      try {
            inputStream = new Scanner(new FileInputStream("menu10.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch10.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
            }
      
      try {
            inputStream = new Scanner(new FileInputStream("menu11.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch11.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
            }
      
      try {
            inputStream = new Scanner(new FileInputStream("menu12.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch12.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
            }

      
      try {
            inputStream = new Scanner(new FileInputStream("menu13.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch13.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
            }
      
      try {
            inputStream = new Scanner(new FileInputStream("menu14.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch14.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
            }
      
      try {
            inputStream = new Scanner(new FileInputStream("menu15.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch15.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
            }
      
      
      try {
            inputStream = new Scanner(new FileInputStream("menu16.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch16.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
            }
      
      try {
            inputStream = new Scanner(new FileInputStream("menu17.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch17.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
            }
      
      try {
            inputStream = new Scanner(new FileInputStream("menu18.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch18.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
            }
      
      try {
            inputStream = new Scanner(new FileInputStream("menu19.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch19.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
            }
      
      try {
            inputStream = new Scanner(new FileInputStream("menu20.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch20.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
            }
      
      try {
            inputStream = new Scanner(new FileInputStream("menu21.txt"),"UTF-8");
            
            String str1 = "";
            
          while(inputStream.hasNextLine()) {
             str1 += inputStream.nextLine();
             str1 += "\n";
          }
          
          lunch21.setText(str1);
            
         }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
            }
   }
}
