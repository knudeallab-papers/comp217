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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager extends JFrame implements ActionListener{
   
   private JButton create;
   private JButton login;
   
   public Manager() {
      super("包府葛靛");
      setSize(300,200);
      setBackground(Color.LIGHT_GRAY);
      setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      setLayout(null);
      create = new JButton("create");
      create.setBounds(50, 50, 100, 50);
      login = new JButton("login");
      login.setBounds(150, 50, 100, 50);
      create.addActionListener(this);
      login.addActionListener(this);
      add(create);
      add(login);
      
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      String btnStr = e.getActionCommand();

      if(btnStr.equals("create"))
      {
         try {
            ObjectOutputStream outputStream = 
                  new ObjectOutputStream(new FileOutputStream("password.dat"));
            JFrame password = new JFrame("create");
            password.setSize(300,200);
            password.setVisible(true);
            password.setLayout(null);
            JTextField passwordIn = new JTextField();
            passwordIn.setBounds(25, 50, 250, 20);
            password.add(passwordIn);
            
            JButton ok = new JButton("积己");
            ok.setBounds(90, 100, 100, 50);
            password.add(ok);
            ok.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  String cmd = e.getActionCommand();
                  if (cmd.equals("积己")) {
                     String pass = passwordIn.getText();
                        
                        try {  
                        outputStream.writeUTF(pass);
                        outputStream.close();
                        password.setVisible(false);
                        } catch(FileNotFoundException e2) {
                           e2.printStackTrace();
                        } catch(IOException e3) {
                           e3.printStackTrace();
                        }
                  }
               }
            });
            
            
         } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         
      }
      else if(btnStr.equals("login"))
      {
         JFrame loginframe = new JFrame("login");
         loginframe.setVisible(true);
         loginframe.setSize(300,200);
         loginframe.setLayout(null);
         JTextField logintext = new JTextField();
         logintext.setBounds(25, 50, 250, 20);
         loginframe.add(logintext);
         JButton send = new JButton("send");
         send.setBounds(90, 100, 100, 50);
         send.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               // TODO Auto-generated method stub
               String cmd = e.getActionCommand();
               if(cmd.equals("send"))
               {
                  String key = "";
                  try {
                     ObjectInputStream InputStream = 
                           new ObjectInputStream(new FileInputStream("password.dat"));
                     key += InputStream.readUTF();
                     InputStream.close();
                  } catch (FileNotFoundException e1) {
                     // TODO Auto-generated catch block
                     e1.printStackTrace();
                  } catch (IOException e1) {
                     // TODO Auto-generated catch block
                     e1.printStackTrace();
                  }
                  if(key.equals(logintext.getText()))
                  {
                     //犁绊包府
                     JFrame selectmeal = new JFrame("select meal");
                     selectmeal.setVisible(true);
                     selectmeal.setSize(300,200);
                     selectmeal.setLayout(new BorderLayout());
                     JTextField selecttext = new JTextField();
                     selectmeal.add(selecttext,BorderLayout.NORTH);
                     JButton select = new JButton("select");
                     select.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                           String cmd = e.getActionCommand();
                           if(cmd.equals("select"))
                           {
                              int targetmeal;
                              targetmeal = Integer.parseInt(selecttext.getText());
                              loginframe.dispose();
                              Inventory Inven=null;
							try {
								Inven = new Inventory(targetmeal);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
                              Inven.setVisible(true);
                           }
                        }
                        
                     });
                     selectmeal.add(select, BorderLayout.SOUTH);
                     
                  }
                  else
                  {
                     JFrame showwrong = new JFrame("Wrong password");
                     showwrong.setVisible(true);
                     showwrong.setSize(300,200);
                     showwrong.setLayout(null);
                  }
               }
            }
      
         });//send actionListener end
         loginframe.add(send);
      }
   }
   
   
   
}