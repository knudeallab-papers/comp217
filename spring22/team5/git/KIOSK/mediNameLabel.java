package TeamProject;

import javax.swing.*;
import java.awt.*;

public class mediNameLabel extends JLabel{
	public mediNameLabel(String str)
    {
        super(str);
        setFont(new Font("IM혜민 bold", Font.BOLD, 42));
        setForeground(new Color(0x292959));
        setHorizontalAlignment(JLabel.CENTER);
    }
}
