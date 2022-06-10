package TeamProject;

import java.awt.*;
import javax.swing.*;

public class AddMedi_medInfo extends JPanel
{
    public AddMedi_medInfo(String med_name, int med_num)
    {
        super();
        setLayout(new GridLayout(1,1));
        setBackground(BorrowMain.BGCOLOR);
        
        String med_info = med_name + " " + med_num + "";
        mediNameLabel title = new mediNameLabel(med_info);
        
        add(title);
    }
}