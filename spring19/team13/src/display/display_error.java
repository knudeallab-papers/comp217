package display;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class display_error extends JFrame {
	
	public display_error(String message)
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 200);
		setLayout(new BorderLayout());
		
		ImageIcon errorIcon = new ImageIcon("images/error.png");
		errorIcon = imageChanger(errorIcon, 60, 80);
		JLabel errorLabel = new JLabel(errorIcon);
		JLabel errorMessage = new JLabel(message);
		JPanel btnPan = new JPanel(new FlowLayout());
		JButton checkBtn = new JButton("»Æ¿Œ");
		btnPan.add(checkBtn);
		checkBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
			
		});
		add(btnPan, BorderLayout.SOUTH);
		add(errorLabel, BorderLayout.WEST);
		add(errorMessage, BorderLayout.CENTER);
		
		
		
	}
	public static ImageIcon imageChanger(ImageIcon originIcon, int h, int w)
	{
		Image originImg = originIcon.getImage();
		Image chagedImg = originImg.getScaledInstance(h, w, Image.SCALE_SMOOTH);
		ImageIcon Icon = new ImageIcon(chagedImg);	
		return Icon;
	}
}
