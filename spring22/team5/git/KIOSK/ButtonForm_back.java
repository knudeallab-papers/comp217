package TeamProject;

import javax.swing.*;
import java.awt.*;

public class ButtonForm_back extends JButton {
    public ButtonForm_back(String imgPath) {
        super();
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBackground(PasswordMain.BACKGROUND_COLOR);

        ImageIcon imgIcon = new ImageIcon(imgPath + ".png");

        Image Img = imgIcon.getImage();
        Img = Img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        imgIcon.setImage(Img);

        setIcon(imgIcon);
    }

}