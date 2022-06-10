package TP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class MainMenu extends JFrame implements ActionListener{
   
   public MainMenu() {
      super();
      setSize(800, 1000);
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("�ı� �����ý���");
      
      JButton bt1 = new JButton("�Ĵ�ǥ");
      JButton bt2 = new JButton("����");
      JButton bt3 = new JButton("����");
      JButton bt4 = new JButton("������");
      
      bt1.setBounds(150, 50, 500, 100);
      bt2.setBounds(150, 175, 500, 100);
      bt3.setBounds(150, 300, 500, 100);
      bt4.setBounds(150, 420, 500, 100);
      
      
      
      add(bt1);
      add(bt2);
      add(bt3);
      add(bt4);
      
      bt1.addActionListener(this);
      bt2.addActionListener(this);
      bt3.addActionListener(this);
      bt4.addActionListener(this);
      setLayout(null);
      setVisible(true);
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      String btnStr = e.getActionCommand();
      
      
      if(btnStr.equals("�Ĵ�ǥ")) {
          School a = new School();
          a.setVisible(true);
         
         //Bab b = new Bab();
         //b.setVisible(true);
      }
      else if(btnStr.equals("����")) {
          Paim a = new Paim();
          a.setVisible(true);
         
         //Bab b = new Bab();
         //b.setVisible(true);
      }
      else if(btnStr.equals("������")) {
          Manager a = new Manager();
          a.setVisible(true);
         
         //Bab b = new Bab();
         //b.setVisible(true);
      }
      else if(btnStr.equals("����")) {
    	  Review a = new Review();
    	  a.setVisible(true);
      }
   }
}
