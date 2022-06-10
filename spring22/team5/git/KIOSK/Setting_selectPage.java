package TeamProject;

import java.awt.*;
import javax.swing.*;

public class Setting_selectPage extends JPanel{
	public Setting_selectPage() {
		super();
        setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(8, 1));

        centerBoldLabel title = new centerBoldLabel("메뉴를 선택하세요", 72);
        
        add(new EmptyPanel());
        add(title);
        add(new EmptyPanel());
	}
}
