package TeamProject;

import javax.swing.*;
import java.awt.*;

public class B_MedInfo extends JPanel
{
    public B_MedInfo(String name, int count)
    {
        super();
        setLayout(new GridLayout(3, 1));
        setBackground(BorrowMain.BGCOLOR);

        //print matname
        centerBoldLabel title = new centerBoldLabel(name, 30);
        //print date

        JLabel left = new JLabel((count == 0) ? "재고 없음" : count + " 개 남음");
        left.setFont(new Font("IM혜민 regular", Font.PLAIN, 24));

        add(title);
        add(new EmptyPanel());
        add(left);
    }
}