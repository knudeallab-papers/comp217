package TeamProject2;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class numBtn extends JButton {
	public numBtn() { 
    	super();  
    } 
    public numBtn(String text) { 
    	super(text); 
    }
    
    protected void paintComponent(Graphics g) {
        Color bgColor=this.getBackground(); //��ư ����
        Color tColor=this.getForeground(); //���ڻ�
        
        int width = getWidth(); 
        int height = getHeight(); 
        
        Graphics2D graphics = (Graphics2D) g; 
        
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        
        if (getModel().isArmed()) {  //��ư �������� ��Ӱ�
     	   graphics.setColor(bgColor); 
        } else if (getModel().isRollover()) {  //��ư�� ���콺 �÷����� ���
     	   graphics.setColor(bgColor); 
        } else { 
     	   graphics.setColor(bgColor); 
        } 
        graphics.fillRoundRect(0, 0, width, height, 20, 20); 
        
        FontMetrics fontMetrics = graphics.getFontMetrics(); 
        Rectangle stringBounds = 
     		   fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
        
        int textX = (width - stringBounds.width) / 2; 
        int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
        
        graphics.setColor(tColor); //���ڻ�
        graphics.setFont(getFont()); 
        graphics.drawString(getText(), textX, textY); 
        graphics.dispose(); 
        
        super.paintComponent(g); 
        }
}