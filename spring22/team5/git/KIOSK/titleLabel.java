package TeamProject;

import javax.swing.*;
import java.awt.*;

public class titleLabel extends JLabel
{
    public titleLabel(String title, int fontSize)
    {
        super(title);
        setFont(new Font("IM혜민 bold", Font.BOLD, fontSize));
        //title.setForeground(Color.BLUE);
        setHorizontalAlignment(JLabel.CENTER);
    }
}
