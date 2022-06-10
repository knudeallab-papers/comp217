package TeamProject;

import javax.swing.*;
import java.awt.*;

public class B_MedSelect extends JPanel
{
    public B_MedSelect()
    {
        super();
        setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(8, 1));

        centerBoldLabel title = new centerBoldLabel("상비약 구비 현황", 42);

        add(new EmptyPanel());
        add(title);
        add(new EmptyPanel());
        setVisible(true);
    }

}
