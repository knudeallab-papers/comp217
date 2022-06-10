package Eumm;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ExpertOrCustomer extends JFrame{

   public ExpertOrCustomer() {
      setLayout(new GridLayout(1,2));
      
      Font f = new Font("경기천년바탕 Regular", Font.PLAIN, 30);
      
      JPanel expertpanel = new JPanel(new GridLayout(2,1));
      Choice cat = new Choice();
      cat.add("분야 선택");
      cat.add("고등과외");
       cat.add("심리");
       cat.add("악기");
       cat.add("언어");
       cat.add("요리");
       cat.add("운동");
       expertpanel.add(cat);
      cat.setFont(f);
      JButton expertbutton = new JButton("전문가");
      expertbutton.setFont(f);
      JButton customerbutton = new JButton("사용자");
      customerbutton.setFont(f);
      
      //Font f = new Font("경기천년바탕 Regular", Font.PLAIN,17);
      
      expertpanel.setBackground(Color.orange);
      expertpanel.add(expertbutton);
      expertbutton.setBackground(Color.orange);
      customerbutton.setBackground(Color.pink);
      
      add(expertpanel);
      add(customerbutton);
      
      
      expertbutton.addActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
            
            dispose();
            
            new JoinExpert(cat.getSelectedItem());
         }
      });
      
      customerbutton.addActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
            
            dispose();
            new JoinCustomer();
         }
      });
      
      
      
      
      setVisible(true);
      setSize(600,300);
      setLocationRelativeTo(null);       //윈도우를 컴퓨터 중간에 띄우기
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

}