package TP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class School extends JFrame implements ActionListener {
   
   public School() {
      super();
      setSize(800, 1000);
      
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setTitle("�Ĵ� ����");
      
      JButton bt1 = new JButton("���Ĵ�");
      JButton bt2 = new JButton("��������");
      JButton bt3 = new JButton("÷��ī���׸���");
      
      bt1.setBounds(150, 100, 500, 100);
      bt2.setBounds(150, 250, 500, 100);
      bt3.setBounds(150, 400, 500, 100);
      
      add(bt1);
      add(bt2);
      add(bt3);
      
      bt1.addActionListener(this);
      
      setLayout(null);
      //setVisible(true);
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      String btnStr2 = e.getActionCommand();
      
      if(btnStr2.equals("���Ĵ�")) {
          Bab b = new Bab();
          b.setVisible(true);
      } else {
         System.err.println("Error");
      }
   }
}