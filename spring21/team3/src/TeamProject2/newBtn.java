package TeamProject2;

import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.JButton;


import javax.swing.Icon;

public class newBtn extends JButton {
	public newBtn() { 
    	super(); 
    	decorate(); 
    } 
    public newBtn(String text) { 
    	super(text); 
    	decorate(); 
    } 
    public newBtn(Action action) { 
    	super(); 
    	decorate(); 
    } 
    public newBtn(Icon icon) { 
    	super(icon); 
    	decorate(); 
    } 
    public newBtn(String text, Icon icon) { 
    	super(text, icon); 
    	decorate(); 
    	} 
    protected void decorate() { 
    	setBorderPainted(false); 
    	setOpaque(false); 
    }
    @Override 
    protected void paintComponent(Graphics g) {
       Color c=new Color(212,244,250); //버튼 배경색
       Color o=new Color(0,0,12); //글자색
       
       int width = getWidth(); 
       int height = getHeight(); 
       
       Graphics2D graphics = (Graphics2D) g; 
       
       graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
       
       if (getModel().isArmed()) {  //버튼 눌렀을때 어둡게
    	   graphics.setColor(c.darker()); 
       } else if (getModel().isRollover()) {  //버튼에 마우스 올렸을때 밝게
    	   graphics.setColor(c.brighter()); 
       } else { 
    	   graphics.setColor(c); 
       } 
       graphics.fillRoundRect(0, 0, width, height, 20, 20); 
       
       FontMetrics fontMetrics = graphics.getFontMetrics(); 
       Rectangle stringBounds = 
    		   fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
       
       int textX = (width - stringBounds.width) / 2; 
       int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
       
       graphics.setColor(o); //글자색
       graphics.setFont(getFont()); 
       graphics.drawString(getText(), textX, textY); 
       graphics.dispose(); 
       
       super.paintComponent(g); 
       }
}
