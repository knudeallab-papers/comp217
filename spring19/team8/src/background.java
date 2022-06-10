
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.imageio.*;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
public class background extends JPanel{
	private Image backgroundImage;
	public background(String fileName) throws IOException{
		backgroundImage=ImageIO.read(new File(fileName));
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage,0,0,this);
	}
}
