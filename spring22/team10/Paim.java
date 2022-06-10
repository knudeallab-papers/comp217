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

import java.awt.Color;
import java.awt.Graphics;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JColorChooser;

public class Paim extends JFrame implements ActionListener{

   public Paim(){
      super("Purchase and Inventory");
      setSize(800,1000);
      setBackground(Color.LIGHT_GRAY);
      setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      //setLayout(new BorderLayout());
      setLayout(null);
      JButton bt1 = new JButton("공식당");
      bt1.addActionListener(this);
       JButton bt2 = new JButton("정보센터");
       JButton bt3 = new JButton("첨성카페테리아");
         
       bt1.setBounds(150, 100, 500, 100);
       bt2.setBounds(150, 250, 500, 100);
       bt3.setBounds(150, 400, 500, 100);
       
       add(bt1);
       add(bt2);
       add(bt3);
       
      
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
       String btnStr = e.getActionCommand();
         
         if(btnStr.equals("공식당")) {
             Gong a = new Gong();
             a.setVisible(true);
         }
   }
}
