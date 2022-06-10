package TeamProject;

import javax.swing.*;

import java.awt.*;

public class ButtonForm extends JButton
{
    public ButtonForm(String imgPath)
    {
    	 super();
         setBorderPainted(false);
         setContentAreaFilled(false);
         setFocusPainted(false);

         ImageIcon imgIcon = new ImageIcon(imgPath+".png");
         ImageIcon imgIconDark = new ImageIcon(imgPath+"_dark.png");

         Image Img = imgIcon.getImage();
         Img = Img.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
         Image ImgDark = imgIconDark.getImage();
         ImgDark = ImgDark.getScaledInstance(200, 100, Image.SCALE_SMOOTH);

         imgIcon.setImage(Img);
         imgIconDark.setImage(ImgDark);

         setIcon(imgIcon);
         setRolloverIcon(imgIconDark);
     }
}
