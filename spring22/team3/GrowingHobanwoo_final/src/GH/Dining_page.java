package GH;

import static GH.Character_status.hp;
import static GH.Character_status.stress;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
//Dining클래스의 foodLo배열의 절대경로만 수정해주면 됩니다.
public class Dining_page extends JFrame implements ActionListener, btMain{
   //체력 ↑, 스트레스 ↓
   JScrollPane scrollPane;
   ImageIcon icon;
   String food[] = {"돈까스", "육회비빔밥", "양푼이비빔밥", "떡라면", "치킨마요", "쟁반수육"};
   String foodLo[] = {"돈까스2.png","육회비빔밥.png","양푼이비빔밥.png","떡라면.png","치킨마요.png","수육.png"};
   String foodpath;
   JButton main_btn;
   public Dining_page() {
      super("식당");
       setSize(610,490);
       setLayout(null);
       setResizable(false);
       setBounds(500,100,610,490);
        icon = new ImageIcon("학식당.jpg");

        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
               g.drawImage(icon.getImage(), 0, 0, null);

                setOpaque(false);
                super.paintComponent(g);
            }
        };
        
        panel.setLayout(null);

        mainbt();
        panel.add(main_btn);
        
        ImageIcon hh = new ImageIcon("학식호반우4.png");
        ImageIcon menu = new ImageIcon("메뉴판3.jpg");
        
        JPanel hhPanel = new JPanel() {
           public void paintComponent(Graphics g) {
              g.drawImage(hh.getImage(),0,0,null);
              setOpaque(false);
              super.paintComponent(g);
           }
        };
        JPanel menuPanel = new JPanel() {
           public void paintComponent(Graphics g) {
              g.drawImage(menu.getImage(),0,0,null);
              setOpaque(false);
              super.paintComponent(g);
           }
        };
        JLabel haksik = new JLabel("<오늘의 학식>");
        Font font = new Font("", Font.TYPE1_FONT, 30);
        haksik.setFont(font);
        haksik.setForeground(Color.WHITE);
        haksik.setBounds(205, 60, 500, 100);
        panel.add(haksik);
        
        JButton eat = new JButton("먹기");
        eat.setFocusPainted(false);
        eat.setBounds(395,240, 60,25);
        eat.addActionListener(this);
        panel.add(eat);
        
        
        hhPanel.setBounds(230, 270, 150, 181);
        menuPanel.setBounds(130, 30, 350, 258);
        
        Random rnnum = new Random();
        int r = rnnum.nextInt(6);
        
        ImageIcon FOOD = new ImageIcon(foodLo[r]);
        JPanel foodPanel = new JPanel() {
           public void paintComponent(Graphics g) {
              g.drawImage(FOOD.getImage(),0,0,null);
              setOpaque(false);
              super.paintComponent(g);
           }
        };
        foodpath = foodLo[r];
        JLabel fLabel = new JLabel(food[r]);
        fLabel.setBounds(280,240,100,20);
        fLabel.setForeground(Color.WHITE);
        Font font2 = new Font("", Font.TYPE1_FONT, 15);
        fLabel.setFont(font2);
        
        foodPanel.setLayout(new BorderLayout());
        foodPanel.setBounds(248,130, 150,150);
        
        panel.add(fLabel);
        panel.add(foodPanel);
        panel.add(hhPanel);
        panel.add(menuPanel);
        
        if(hp <= 0) {
         Ending_Guaro gre = new Ending_Guaro();
         gre.setVisible(true);
         setVisible(false);
      }else if(stress >= 100) {
         Ending_Gapyear gye = new Ending_Gapyear();
         gye.setVisible(true);
         setVisible(false);
      }else {
         scrollPane = new JScrollPane(panel);
         setContentPane(scrollPane);
        }
        
   }
   

   @Override
   public void actionPerformed(ActionEvent e) {
      String actionCmd = e.getActionCommand();
      if(actionCmd.equals("먹기")) {
         new Dining_eatgame(foodpath);
         dispose();
      }else if(actionCmd.equals("Back")) {
         setVisible(false);
      }
      
   }


   @Override
   public void mainbt() {
      main_btn = new JButton("Back");
       main_btn.setBounds(500, 400, 80, 30);
       main_btn.setFocusPainted(false);
       main_btn.addActionListener(this);
      
   }
}