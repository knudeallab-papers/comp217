package GH;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import static GH.Character_status.hp;
import static GH.Character_status.stress;
import static GH.Character_status.happy;

import static GH.Main_HOBAN.happy_;

public class Dining_eatgame extends JFrame implements ActionListener{
   GamePanel panel;
   GameThread gThread;
   int score;
   int totalscore;
   String time;
   JFrame exit;
   
   public Dining_eatgame(String food) {
      setTitle("학식먹기게임");
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      setBounds(470, 50, 700, 700);
      setResizable(false);
      panel = new GamePanel(food);
      add(panel,BorderLayout.CENTER);
      if(hp <= 0) {
         Ending_Guaro gre = new Ending_Guaro();
         gre.setVisible(true);
         setVisible(false);
      }else if(stress >= 100) {
         Ending_Gapyear gye = new Ending_Gapyear();
         gye.setVisible(true);
         setVisible(false);
      }else
         setVisible(true);
      gThread = new GameThread();
      gThread.start();
      addKeyListener(new KeyListener() { 
         public void keyTyped(KeyEvent e) {
         } 
         public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();

            switch( keyCode ) {
            case KeyEvent.VK_LEFT:
               panel.dx = 0;
               break;
            case KeyEvent.VK_RIGHT:
               panel.dx = 0;
               break;
            }

         } 
         public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch( keyCode ) {
               case KeyEvent.VK_LEFT:
                  panel.dx = -8;
                  break;

               case KeyEvent.VK_RIGHT:
                  panel.dx = 8;
                  break;

            }
         }

      });
      
         exit = new JFrame();
         exit.setSize(210,100);
         exit.setDefaultCloseOperation(EXIT_ON_CLOSE);
         exit.setLayout(new BorderLayout());
         exit.setBounds(750,250,210,100);
         JButton cancle = new JButton("닫기");
         cancle.addActionListener(this);
         
         exit.add(cancle,BorderLayout.SOUTH);
         
         Timer timer = new Timer();
         
         timer.scheduleAtFixedRate(new TimerTask() {
            int i = 30;
            
            public void run() {
               time = "Time left: " + i;
               i--;
               
               if(i<0) {
                  timer.cancel();
                  time = "Time Over";
                  JLabel announce = new JLabel("체력 : "+score+" 회복!  "+ "스트레스 : "+score+" 해소!");
                  exit.add(announce,BorderLayout.CENTER);
                  totalscore = score;
                  
                  hp += (totalscore/3)*2;
                  stress -= (totalscore/3)*2;
                  happy += (totalscore/3)*2;
                  
                  happy_ += (totalscore/3)*2;
                  
                  exit.setVisible(true);
               }
            }
         }, 0, 1000);
         
      }
   class GamePanel extends JPanel{
      
       Image imgBack, imgPlayer, imgEnemy;
       int width, height;
       int x, y, w, h;
       int dx = 0, dy = 0;
       ArrayList<Dining_enemy> enemies = new ArrayList<Dining_enemy>();
      
       public GamePanel(String fp) {
          Toolkit toolkit = Toolkit.getDefaultToolkit();
          imgBack = toolkit.getImage("학식당.jpg");//배경 이미지
          imgPlayer = toolkit.getImage("학식호반우4.png");//플레이어 이미지 객체
          imgEnemy = toolkit.getImage(fp);
       }
       protected void paintComponent(Graphics g) {
      
          if( width == 0 || height == 0) {
             width = getWidth();
             height = getHeight();
             imgBack = imgBack.getScaledInstance(width, height, Image.SCALE_SMOOTH);
             imgPlayer = imgPlayer.getScaledInstance(128, 128, Image.SCALE_SMOOTH);
             x = width/2;
             y = height - 100;
             w = 64;
             h = 64;
          } 
          g.drawImage(imgBack, 0, 0, this);
          for(Dining_enemy t : enemies) {
             g.drawImage(t.img, t.x-t.w, t.y-t.h, this);
          }
          g.drawImage(imgPlayer, x - w, y - h, this);
          g.setFont(new Font(null, Font.BOLD, 20));
          g.drawString("Score : " + score,10, 30);
          g.drawString(time, 560, 30);
       }                                     

       void move() { 
          for(int i = enemies.size()-1; i >= 0; i--) {
             Dining_enemy t = enemies.get(i); 
             t.move();
             if(t.isDead)
                enemies.remove(i);
          } 
          x += dx;
          y += dy;
          if(x < w) x = w;
          if(x > width - w) x = width - w;
          if(y < h) y = h;
          if(y > height - h) y = height - h;
       }

       void makeEnemy() {
          if(width == 0 || height == 0) return;
          Random rnd = new Random();
          int n = rnd.nextInt(15);

          if( n == 0 ) {
             enemies.add(new Dining_enemy(imgEnemy, width, height));
          }

       }
       void checkCollision() {
          for(Dining_enemy t : enemies) {

             int left = t.x - t.w;
             int right = t.x + t.w;
             int top = t.y - t.h;
             int bottom = t.y + t.h;

             if(x > left && x < right && y > top && y < bottom) {
                t.isDead = true;
                score += 1;
                totalscore += 1;
             }

          } 

       } 
   }
   class GameThread extends Thread {
      
       public void run() {
          while(true) {
             panel.makeEnemy();
             panel.move(); 
             panel.checkCollision();
             panel.repaint();
             try {
                sleep(20);
             } catch (InterruptedException e) {}

          }

       }

   }

   @Override
   public void actionPerformed(ActionEvent e) {
      String actionCmd = e.getActionCommand();
      if(actionCmd.equals("닫기")) {
         dispose();
         exit.dispose();
      }
      
   }
   
}