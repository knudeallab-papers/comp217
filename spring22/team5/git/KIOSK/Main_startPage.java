package TeamProject;

import java.awt.*;
import javax.swing.*;

public class Main_startPage extends JPanel
{
    public Main_startPage()
    {
        super();
        setBackground(new Color(255, 255, 255, 0));
        setLayout(new GridLayout(4, 1));

        centerBoldLabel title = new centerBoldLabel("학생회 키오스크", 72);

        ImageIcon img = new ImageIcon("./image/background.png");
        Image i = img.getImage();
        img = new ImageIcon(i.getScaledInstance(350, 350,Image.SCALE_DEFAULT));
        JLabel IMG = new JLabel(img);


        //add(new EmptyPanel());
        add(title);
        add(IMG);
        add(new EmptyPanel());
        //add(new EmptyPanel());
        //add(new EmptyPanel());
        //(new EmptyPanel());

    }
}
