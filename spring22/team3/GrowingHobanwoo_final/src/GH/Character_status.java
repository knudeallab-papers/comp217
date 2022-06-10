package GH;

import javax.swing.*;
import java.awt.*;

import static GH.Character_status.hp;
import static GH.Main_HOBAN.user_name;

public class Character_status extends JFrame {
   static int stress = 0;
   static int score = 0;
   static int happy = 50;
   static int hp = 100;
   private int totalhp = 100;
   private int totalstress = 100;
   private int totalhappy = 100;
   private int totalscore = 18;
  
   public Character_status() {
      setSize(200, 200);
      setLocationRelativeTo(null);
      
      if(stress < 0)
         stress = 0;
      else if(stress >= 100) {
         stress = 100;
         Ending_Gapyear gye = new Ending_Gapyear();
         gye.setVisible(true);
         setVisible(false);
         }
      if(hp <= 0) {
         hp = 0;
         Ending_Guaro gre = new Ending_Guaro();
         gre.setVisible(true);
         setVisible(false);
      }else if(hp > 100)
         hp = 100;
      if(happy < 0)
         happy = 0;
      else if(happy > 100)
         happy = 100;
      JLabel stressLabel = new JLabel("스트레스 : " + stress + "/" + totalstress);
      JLabel scoreLabel = new JLabel("학     점 : " +score+"/"+totalscore);
      JLabel sleepLabel = new JLabel("행 복 도 : " + happy + "/" + totalhappy);
      JLabel hpLabel = new JLabel("체     력 : " + hp + "/" + totalhp);
      JLabel status = new JLabel("< "+user_name+" 상태>");
      Font font = new Font("", Font.BOLD, 20);
      status.setFont(font);
      status.setHorizontalAlignment(JLabel.CENTER);

      ChPanel ch = new ChPanel();
      ch.setBackground(Color.WHITE);
      ch.setLayout(new GridLayout(5,1));
      add(ch, BorderLayout.CENTER);

      ch.add(status);
      ch.add(hpLabel);
      ch.add(stressLabel);
      ch.add(sleepLabel);
      ch.add(scoreLabel);

      setVisible(true);
   }
   private class ChPanel extends JPanel{
      public void paintComponent(Graphics g) {

         super.paintComponent(g);

         g.setColor(Color.RED);
         g.fillRect(51, 44, hp, 10);

         g.setColor(Color.YELLOW);
         g.fillRect(58, 76, stress, 10);

         g.setColor(Color.lightGray);
         g.fillRect(52, 108, happy, 10);

      }
   }
   public static void main(String[] args) {
      Character_status ch = new Character_status();

   }
}