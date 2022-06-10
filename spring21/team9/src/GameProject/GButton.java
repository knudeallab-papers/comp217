package GameProject;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GButton extends GameObject{
	
	private ImageIcon normalIcon;
	private ImageIcon activeIcon;
	private JButton button;
	private Container container;
	
	public interface clickAction{
		public void act(MouseEvent event);
	}
	
	public GButton(String normalIconName, String activeIconName, Container container,
			int x, int y, int width, int height, clickAction action) {
		super(Type.Image);
		normalIcon = new ImageIcon(getClass().getClassLoader().getResource(normalIconName));
		activeIcon = new ImageIcon(getClass().getClassLoader().getResource(activeIconName));
		button = new JButton(normalIcon);
		
		Initialize(x,y,width, height, action);
		container.add(button);
		this.container = container;
	}
	
	private void Initialize(int x, int y, int width, int height, clickAction action) {
		button.setBounds(x, y, width, height);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setIcon(activeIcon);
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button.setIcon(normalIcon);
				button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent event) {
				action.act(event);
			}
		});
	}

	@Override
	public void Destroy() {
		container.remove(button);
	}
}
