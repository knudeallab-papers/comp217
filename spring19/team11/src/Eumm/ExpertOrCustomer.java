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
      
      Font f = new Font("���õ����� Regular", Font.PLAIN, 30);
      
      JPanel expertpanel = new JPanel(new GridLayout(2,1));
      Choice cat = new Choice();
      cat.add("�о� ����");
      cat.add("������");
       cat.add("�ɸ�");
       cat.add("�Ǳ�");
       cat.add("���");
       cat.add("�丮");
       cat.add("�");
       expertpanel.add(cat);
      cat.setFont(f);
      JButton expertbutton = new JButton("������");
      expertbutton.setFont(f);
      JButton customerbutton = new JButton("�����");
      customerbutton.setFont(f);
      
      //Font f = new Font("���õ����� Regular", Font.PLAIN,17);
      
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
      setLocationRelativeTo(null);       //�����츦 ��ǻ�� �߰��� ����
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

}