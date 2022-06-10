package GH;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Club_drawing_painttoolframe extends JFrame{

 JButton btPlus, btMinus, btClear, btAllClear, btClose, btColor;
 JPanel p;
 
 public Club_drawing_painttoolframe(){
  super("PaintTool");
  Container c=getContentPane();
  
  p=new JPanel();
  c.add(p, "Center");
  p.add(btPlus=new JButton("+"));
  p.add(btMinus=new JButton("-"));
  p.add(btClear=new JButton("지우개"));
  p.add(btAllClear=new JButton("모두지우기"));
  p.add(btColor=new JButton("색상"));
  p.add(btClose=new JButton("닫기"));
  
  
 }
 
}