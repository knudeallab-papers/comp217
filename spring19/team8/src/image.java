
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
public class image extends JFrame{
ImageIcon background=new ImageIcon("background.jpg");
	public static void main(String[] args) {
		image gui=new image();
		gui.setVisible(true);
	}
	public image()
	{
		setSize(1000,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel back=new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(background.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}

		};
		add(back);
		JButton button=new JButton("HI");
		back.add(button);
	    JLabel label = new JLabel(new ImageIcon("main_logo.png"));
		back.add(label);
	}
}