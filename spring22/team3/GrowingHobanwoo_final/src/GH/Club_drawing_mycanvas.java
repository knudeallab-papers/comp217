package GH;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Club_drawing_mycanvas extends Canvas {
	 
	 int x=-50; int y=-50; int w=7; int h=7;
	 Color cr = Color.black;
	
	 @Override
	 public void paint(Graphics g){
	  g.setColor(cr);
	  g.fillOval(x, y, w, h);
	 }
	 
	 @Override
	 public void update(Graphics g){
	  paint(g);
	 }
	 
	}