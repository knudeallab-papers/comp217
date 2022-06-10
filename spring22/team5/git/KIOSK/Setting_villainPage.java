package TeamProject;

import java.awt.*;
import javax.swing.*;

public class Setting_villainPage extends JPanel
{
    public Setting_villainPage()
    {
        super();
        setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(8, 1));

        centerBoldLabel title = new centerBoldLabel("관리자 권한이", 72);
        centerBoldLabel title2 = new centerBoldLabel("없습니다", 72);
        //웃는 이미지 넣기

        centerBoldLabel subtitle = new centerBoldLabel("n초후 돌아가기", 32);

        add(new EmptyPanel());
        add(title);
        add(title2);
        add(new EmptyPanel());
        add(new EmptyPanel());

        add(new EmptyPanel());

    }
}
