package GameProject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JLabel;

public class GLabel extends GameObject{
	private Container container;
	private JLabel label;

	public GLabel(String text, int x, int y, int width, int height ,int fontsize, Container container) {
		super(Type.Text);
		this.container = container;
		label = new JLabel(text);
		label.setBounds(x,y,width, height);
		Font font = label.getFont();
		label.setFont(new Font(font.getName(), font.getStyle(), fontsize));
		container.add(label);
	}
	
	public void resetText(String newText) {
		label.setText(newText);
	}

	@Override
	public void Destroy() {
		container.remove(label);
	}
}
