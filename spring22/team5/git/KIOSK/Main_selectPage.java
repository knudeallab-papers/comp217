package TeamProject;

import java.awt.*;
import javax.swing.*;

public class Main_selectPage extends JPanel
{
    public Main_selectPage()
    {
        super();
        setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(8, 1));
        centerBoldLabel title = new centerBoldLabel("서비스를 선택해주세요", 48);
        add(title);
        add(new EmptyPanel());
        add(new EmptyPanel());

    }
}
