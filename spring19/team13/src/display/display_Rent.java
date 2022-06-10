package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dataClass.DateCheck;
import dataClass.book;
import dataClass.member;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.Font;

public class display_Rent extends JFrame implements ActionListener, ListSelectionListener{

   private JButton RentBtn, ReturnBtn, BackBtn, actionBtn;
   private DefaultTableModel bookModel, memberModel, lentModel;
   private JTable leftTable, rightTable;
   private JPanel leftPan, rightPan;
   private JScrollPane leftScroll, rightScroll;
   private ArrayList<member> memberList;
   private ArrayList<book> bookList;
   private boolean lentMenu = true;
   private JPanel actionPanel;
   private JButton button;
   
   public static void main(String[] args) {
      display_Rent a = new display_Rent();
      a.setVisible(true);
   }
   
   public display_Rent() {
      memberList = new ArrayList<member>();
      bookList = new ArrayList<book>();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 1200, 800);
      this.setResizable(false);
      getContentPane().setLayout(null);
   
      JPanel BtnPanel = new JPanel();
      BtnPanel.setBounds(0, 0, 400, 91);
      getContentPane().add(BtnPanel);
      BtnPanel.setLayout(new GridLayout(1, 0, 0, 0));
      
      RentBtn = new JButton("Borrow Menu");
      RentBtn.setFont(new Font("굴림", Font.PLAIN, 25));
      RentBtn.setActionCommand("Rent");
      RentBtn.setBackground(Color.LIGHT_GRAY);
      RentBtn.addActionListener(this);
      BtnPanel.add(RentBtn);
      
      ReturnBtn = new JButton("Return Menu");
      ReturnBtn.setFont(new Font("굴림", Font.PLAIN, 25));
      ReturnBtn.setActionCommand("Return");
      ReturnBtn.setBackground(Color.LIGHT_GRAY);
      ReturnBtn.addActionListener(this);
      BtnPanel.add(ReturnBtn);
      
      BackBtn = new JButton();
      BackBtn.setBounds(1032, 0, 150, 100);
      getContentPane().add(BackBtn);
      ImageIcon BackIcon = new ImageIcon("images/back.png");
      BackIcon = imageChanger(BackIcon, 150, 100);
      BackBtn.setIcon(BackIcon);
      BackBtn.addActionListener(this);
      
      RentBtn.setBorder(new LineBorder(Color.black,3));
      ReturnBtn.setBorder(new LineBorder(Color.black,1));

      leftPan = new JPanel(new BorderLayout());
      leftPan.setBounds(0, 103, 600, 650);
      getContentPane().add(leftPan);

      rightPan = new JPanel(new BorderLayout());
      rightPan.setBounds(600,103,600,650);
      getContentPane().add(rightPan);

      String headerBook[] = {"책 제목","저자","대출 가능 여부"};
      bookModel = new DefaultTableModel(headerBook, 0){
          public boolean isCellEditable(int row, int column) {
              if(column >= 0) {
                 return false;
              }else {
                 return true;
              }
           }
        };
      leftTable = new JTable(bookModel);
      leftScroll = new JScrollPane(leftTable);
      leftTable.getSelectionModel().addListSelectionListener(this);
      leftTable.getTableHeader().setReorderingAllowed(false);
      leftPan.add(leftScroll);
      
      String headerMember[] = {"이름","휴대폰번호","미납현황"};
      memberModel = new DefaultTableModel(headerMember,0){
          public boolean isCellEditable(int row, int column) {
              if(column >= 0) {
                 return false;
              }else {
                 return true;
              }
           }
        };
      rightTable = new JTable(memberModel);
      rightScroll = new JScrollPane(rightTable);
      rightPan.add(rightScroll);
      
      leftTable.setRowHeight(25);
      rightTable.setRowHeight(25);
      leftTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      rightTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      
      rightTable.getTableHeader().setReorderingAllowed(false);
      actionPanel = new JPanel();
      actionPanel.setBounds(465, 20, 250, 70);
      getContentPane().add(actionPanel);
      actionPanel.setLayout(new BorderLayout(0, 0));
      
      actionBtn = new JButton("대여하기");
      actionBtn.setFont(new Font("굴림", Font.PLAIN, 20));
      actionPanel.add(actionBtn, BorderLayout.CENTER);
      actionBtn.addActionListener(this);
      
      String headRentList[] = {"책 제목","저자","대출 가능 여부"};
      lentModel = new DefaultTableModel(headRentList,0) {
          public boolean isCellEditable(int row, int column) {
              if(column >= 0) {
                 return false;
              }else {
                 return true;
              }
           }
        };;
      
      load();

   }
   public void actionPerformed(ActionEvent e) {
      if(e.getActionCommand().equals("Rent")) {
         lentMenu = true;
         RentBtn.setBorder(new LineBorder(Color.black,3));
         ReturnBtn.setBorder(new LineBorder(Color.black,1));
         leftTable.setModel(bookModel);
         rightTable.setModel(memberModel);
         actionBtn.setText("대여하기");
      }
      else if(e.getActionCommand().equals("Return")) {
         ReturnBtn.setBorder(new LineBorder(Color.black,3));
         RentBtn.setBorder(new LineBorder(Color.black,1));
         leftTable.setModel(memberModel);
         rightTable.setModel(lentModel);
         actionBtn.setText("반납하기");
         lentMenu = false;
      }
      if(e.getSource() == BackBtn)
      {
         display_main main = new display_main();
         main.setVisible(true);
         dispose();
      }
      if(e.getSource() == actionBtn)
      {
        //대여 메뉴
       if(lentMenu == true)
       {
          if(leftTable.getSelectedRow() == -1)
          {
             display_error erorrD = new display_error("대출할 책 선택해 주세요");
             erorrD.setVisible(true);
          }
          else if(rightTable.getSelectedRow()== -1)
          {
             display_error erorrD = new display_error("회원 선택해 주세요");
             erorrD.setVisible(true);
          }
          else {
             if(memberList.get(rightTable.getSelectedRow()).getLentList().size()<3 &&
                   !memberList.get(rightTable.getSelectedRow()).getOverdue() &&
                   bookList.get(leftTable.getSelectedRow()).getLentChk())
             {
                memberList.get(rightTable.getSelectedRow()).getLentList().add(bookList.get(leftTable.getSelectedRow()));
                bookList.get(leftTable.getSelectedRow()).setLentChk(false);
                bookList.get(leftTable.getSelectedRow()).setBorrowDate(DateCheck.getCurDate());
                reset();
                display_notice notice = new display_notice("대여하였습니다");
                notice.setVisible(true);
                save();
             }
             else if(memberList.get(rightTable.getSelectedRow()).getLentList().size()>=3)
             {
                display_error erorrD = new display_error("더이상 대출할 수 없습니다");
                erorrD.setVisible(true);
             }
             else if(memberList.get(rightTable.getSelectedRow()).getOverdue())
             {
                display_error erorrD = new display_error("현재 회원은 연체 상태입니다");
                erorrD.setVisible(true);
             }
             else if(!bookList.get(leftTable.getSelectedRow()).getLentChk())
             {
                display_error erorrD = new display_error("이미 대여된 책입니다");
                erorrD.setVisible(true);
             }
             
          }
          
       }
       //반납 메뉴
       else {
    	  if(memberList.get(leftTable.getSelectedRow()).getOverdue() == true)
    	  {
    		  display_error erorrD = new display_error("책이 미납되어 빌릴수 없습니다");
              erorrD.setVisible(true);
    	  }
    	  else {
	          for(int i=0;i<bookList.size();i++) {
	             if(memberList.get(leftTable.getSelectedRow()).getLentList()
	            		 .get(rightTable.getSelectedRow()).equal(bookList.get(i))) {
	                bookList.get(i).setLentChk(true);
	                memberList.get(leftTable.getSelectedRow()).getLentList().remove(rightTable.getSelectedRow());
	                lentModel.removeRow(rightTable.getSelectedRow());
	                reset();
	                save();
	                break;
	             }
	          }
    	  }
          
       }
      }
   }

   public void load()
   {
      ObjectInputStream inputStream = null;
      memberList.clear();
      memberModel.setNumRows(0);
      try {
         inputStream = new ObjectInputStream(new FileInputStream("./data/member.dat"));
         try {
            while(true)
            {
               try {
                  member tmpMember = (member) inputStream.readObject();
                  memberList.add(tmpMember);
                  memberModel.addRow(tmpMember.getall());
               } catch (ClassNotFoundException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }      
            }
         } catch(EOFException e) {
            
         }
         inputStream.close();
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      inputStream = null;
      bookList.clear();
      bookModel.setNumRows(0);
      try {
         inputStream = new ObjectInputStream(new FileInputStream("./data/book.dat"));
         try {
            while(true)
            {
               try {
                  book tmpBook = (book) inputStream.readObject();
                  bookList.add(tmpBook);
                  bookModel.addRow(tmpBook.getall());
               } catch (ClassNotFoundException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
               
            }
            
         } catch(EOFException e) {
            
         }
         inputStream.close();
      } catch (FileNotFoundException e) {
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }      
      for(int i =0; i<memberList.size(); i++)
      {
        for(int j=0;j<memberList.get(i).getLentList().size(); j++)
        {
          String begin =  memberList.get(i).getLentList().get(j).getBorrowDate();
          try {
            if(DateCheck.diffOfDate(begin, DateCheck.getCurDate()) > 7)
            {
                memberList.get(i).setOverdue(true);
                save();
                reset();
                
            }
         } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
        }
       
      }
      
   }
   public void save() {
      try {
         ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./data/member.dat"));
         for(int i=0; i<memberList.size(); i++)
            outputStream.writeObject(memberList.get(i));
         outputStream.close();
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   try {
      ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./data/book.dat"));
      for(int i=0; i<bookList.size(); i++)
         outputStream.writeObject(bookList.get(i));
      outputStream.close();
   } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
   } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
   }
   }
   public void reset() {
      for(int i=0; i<memberList.size(); i++)
      {
         memberModel.removeRow(0);
      }
      for(int i =0; i<memberList.size(); i++)
      {
         memberModel.addRow(memberList.get(i).getall());
      }
      for(int i=0; i<bookList.size(); i++)
      {
         bookModel.removeRow(0);
      }
      for(int i =0; i<bookList.size(); i++)
      {
         bookModel.addRow(bookList.get(i).getall());
      }
   }

   @Override
   public void valueChanged(ListSelectionEvent e) {
      // TODO Auto-generated method stub
      
      if(!e.getValueIsAdjusting() && !lentMenu && leftTable.getSelectedRow() != -1)
      {
         ArrayList<book> lentList = null;
         lentList = memberList.get(leftTable.getSelectedRow()).getLentList();
         String headerBook[] = {"책 제목","저자","대출 가능 여부"};
         lentModel = new DefaultTableModel(headerBook, 0);
         for(int i= 0; lentList != null && i<lentList.size(); i++)
         {
            
            try {
            lentModel.addRow(lentList.get(i).getall());
         } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
            
         }
         rightTable.setModel(lentModel);
      }
   }
   public static ImageIcon imageChanger(ImageIcon originIcon, int h, int w)
   {
      Image originImg = originIcon.getImage();
      Image chagedImg = originImg.getScaledInstance(h, w, Image.SCALE_SMOOTH);
      ImageIcon Icon = new ImageIcon(chagedImg);   
      return Icon;
   }
}