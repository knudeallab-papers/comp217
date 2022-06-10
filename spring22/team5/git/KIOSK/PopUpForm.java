package TeamProject;

import javax.swing.*;
import java.awt.*;

public class PopUpForm extends JOptionPane
{
    public static int showOptionDialog(String[] buttons, String imgSource, int img_x, int img_y, String title, String text, String holdingbutton)
    {
        JFrame popupFrame = new JFrame();
        popupFrame.setBackground(PasswordMain.BACKGROUND_COLOR);
        popupFrame.setFont(new Font("IM혜민 bold", Font.PLAIN, 20));
        popupFrame.setForeground(new Color(0x292959));

        ImageIcon popupImg = new ImageIcon(imgSource);
        Image img = popupImg.getImage();
        img = img.getScaledInstance(img_x, img_y, Image.SCALE_SMOOTH);
        popupImg.setImage(img);

        return showOptionDialog(popupFrame, text, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, popupImg, buttons, holdingbutton);
    }
}
