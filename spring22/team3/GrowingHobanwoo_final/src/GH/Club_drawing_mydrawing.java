package GH;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
//�̹��� �߰�, �������� ����, ��ġ ����
//����, �ҷ�����
import static GH.Character_status.stress;
import static GH.Character_status.hp;
import static GH.Character_status.happy;

import static GH.Main_HOBAN.happy_;


public class Club_drawing_mydrawing extends JFrame {
 
	JPanel p1,p2,p3,p4;
	JButton btR, btG, btB, btBL, btOpen, btsave, btCollection;
	Canvas can;
	Club_drawing_painttoolframe pt;
	
	String chLo[] = {"무민.png","리랔.png","시바.png","햄토리.png","라이언.png"};
	
	public Club_drawing_mydrawing(){
		super("DrawingGame");
		pt=new Club_drawing_painttoolframe();
		p1=new JPanel(); add(p1, "North");
		p3 = new JPanel(new GridLayout(1,2)); add(p3, "Center");
		p2 = new JPanel();
		
		Random rnnum = new Random();
	    int r = rnnum.nextInt(5);
		
		ImageIcon icon = new ImageIcon(chLo[r]);
		
		p4 = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 20, 100, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
		p3.add(p4);
		p3.add(p2);
		
		btBL = new JButton("Black"); btBL.setBackground(Color.BLACK); btBL.setFocusPainted(false); p1.add(btBL);
		btR=new JButton("Red"); btR.setBackground(Color.RED); p1.add(btR);
		btG=new JButton("Green"); btG.setBackground(Color.GREEN); p1.add(btG);
		btB=new JButton("Blue"); btB.setBackground(Color.BLUE); p1.add(btB);
		btOpen=new JButton("Paint Tool"); p1.add(btOpen);
  
		can=new Club_drawing_mycanvas();
		can.setSize(350, 700);
		can.setBackground(Color.white);
		p2.add(can);

		MyHandler my=new MyHandler();
		can.addMouseMotionListener(my);
		btBL.addActionListener(my);
		btR.addActionListener(my);
		btG.addActionListener(my);
		btB.addActionListener(my);
		btOpen.addActionListener(my);

		pt.btPlus.addActionListener(my);
		pt.btMinus.addActionListener(my);
		pt.btClear.addActionListener(my);
		pt.btAllClear.addActionListener(my);
		pt.btColor.addActionListener(my);
		pt.btClose.addActionListener(my);
		
		hp -= 5;
		stress -= 5;
		happy += 5;
		
		happy_ += 5;
    
		setBounds(400,50,700,700);
		setResizable(false);
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
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	class MyHandler implements MouseMotionListener, ActionListener{
  
		public void mouseDragged(MouseEvent e){
			int xx=e.getX(); int yy=e.getY();
			((Club_drawing_mycanvas)can).x=xx; ((Club_drawing_mycanvas)can).y=yy;
			can.repaint();
		}
  
		public void mouseMoved(MouseEvent e){
		}
  
		public void actionPerformed(ActionEvent e){
			Object o=e.getSource();
			Club_drawing_mycanvas can2 = (Club_drawing_mycanvas)can;
   
			if(o==btBL){
				can2.cr=Color.BLACK;
			}else if(o==btR){
				can2.cr=Color.red;
			}else if(o==btG){
				can2.cr=Color.GREEN;
			}else if(o==btB){
				can2.cr=Color.blue;
			}else if(o==btOpen){
				pt.pack();
				pt.setLocation(getWidth(),0);
				pt.setVisible(true);
			}else if(o==pt.btPlus){
				can2.w +=1; can2.h+=1;
			}else if(o==pt.btMinus){
				if(can2.w>3){ 
					can2.w -= 1; can2.h -= 1; 
				}
			}else if(o==pt.btClear)
				can2.cr=can.getBackground();
			else if(o==pt.btAllClear){
				Graphics g = can2.getGraphics();
				g.clearRect(0, 0, can.getWidth(), can.getHeight()); 
			}else if(o==pt.btColor){
				Color selCr = JColorChooser.showDialog(null, "Color", Color.blue);
				can2.cr=selCr; 
			}else if(o==pt.btClose){
				pt.dispose();
			}
		}
	}
	
	public static void main(String[] args) {
			new Club_drawing_mydrawing();
	}
}