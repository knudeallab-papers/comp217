package TeamProject;
import javax.swing.*;
import java.awt.*;

public class ButtonForm2 extends JButton
{
    public ButtonForm2(String imgPath)
    {
        super();
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);

        ImageIcon imgIcon = new ImageIcon(imgPath+".png");
        ImageIcon imgIconDark = new ImageIcon(imgPath+"_dark.png");

        Image Img = imgIcon.getImage();
        Img = Img.getScaledInstance(400, 100, Image.SCALE_SMOOTH);
        Image ImgDark = imgIconDark.getImage();
        ImgDark = ImgDark.getScaledInstance(400, 100, Image.SCALE_SMOOTH);

        imgIcon.setImage(Img);
        imgIconDark.setImage(ImgDark);

        setIcon(imgIcon);
        setRolloverIcon(imgIconDark);
    }
}
