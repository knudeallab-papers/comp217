package TeamProject;

import javax.swing.*;
import java.awt.*;

public class errorMSGLabel extends JLabel
{
    public errorMSGLabel(String errorStr)
    {
        super(errorStr);
        setForeground(Color.RED);
        setFont(new Font("IM혜민 regular", Font.PLAIN, 20));
        setHorizontalAlignment(JLabel.CENTER);
    }
}
