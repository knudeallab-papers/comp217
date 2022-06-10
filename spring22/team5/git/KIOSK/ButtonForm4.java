package TeamProject;

import javax.swing.*;

import java.awt.*;

public class ButtonForm4 extends JButton {
	public ButtonForm4(String imgPath) {
		super();
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBackground(PasswordMain.BACKGROUND_COLOR);

		ImageIcon imgIcon = new ImageIcon(imgPath + ".png");
		ImageIcon imgIconDark = new ImageIcon(imgPath + "_dark.png");

		Image Img = imgIcon.getImage();
		Img = Img.getScaledInstance(140, 70, Image.SCALE_SMOOTH);
		Image ImgDark = imgIconDark.getImage();
		ImgDark = ImgDark.getScaledInstance(140, 70, Image.SCALE_SMOOTH);

		imgIcon.setImage(Img);
		imgIconDark.setImage(ImgDark);
		
		setIcon(imgIcon);
		setRolloverIcon(imgIconDark);

	}

}
