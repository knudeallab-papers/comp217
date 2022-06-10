package TeamProject;

import javax.swing.*;
import java.awt.*;

public class centerBoldLabel extends JLabel
{
    public centerBoldLabel(String title, int fontSize)
    {
        super(title);
        setFont(new Font("IM혜민 bold", Font.BOLD, fontSize));
        setForeground(new Color(0x292959));
        setHorizontalAlignment(JLabel.CENTER);
    }
}
