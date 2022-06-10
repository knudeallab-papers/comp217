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

public class mealTicket extends JFrame implements ActionListener{
   
   private JTextArea result;
   private Font font1;
   
   public mealTicket(int total, int cnt1, int cnt2 , int cnt3, int cnt4, int cnt5, int cnt6, String paymethod) {
      super("구매창");
      setSize(600,400);
      setBackground(Color.LIGHT_GRAY);
      setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      setLayout(null);
      
      String content = "5000 x " + cnt1 + " 4500 x " + cnt2 + " 4000 x " + cnt3 + "\n3500 x " + cnt4 +
            " 3500 x " + cnt5 + " 1000 x " + cnt6 + "\n합계 = " + total + paymethod + " 결재하시겠습니까?";
      result = new JTextArea(content);
      result.setBounds(50 ,100, 500, 100);
      result.setEnabled(false);//수정불가
      font1 = new Font("맑은 고딕", Font.BOLD, 25);
      result.setFont(font1);
      add(result);
      
      JButton confirm = new JButton("확인");
      JButton cancel = new JButton("취소");
      confirm.setBounds(200, 300, 100, 50);
      cancel.setBounds(300, 300, 100, 50);
      confirm.addActionListener(this);
      cancel.addActionListener(this);
      add(confirm);
      add(cancel);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      String cmd = e.getActionCommand();
      
      if(cmd.equals("확인"))
      {
         JFrame a = new JFrame("구매 성공!");
         a.setSize(300,100);
         setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
         a.setVisible(true);
         new MainMenu();
      }
      else if(cmd.equals("취소"))
      {
         JFrame a = new JFrame("구매 취소");
         a.setSize(300,100);
         setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
         a.setVisible(true);

      }
   }
}