package TeamProject;

import java.awt.*;
import javax.swing.*;

public class Checkborrow_gotomainPage extends JPanel{
	public Checkborrow_gotomainPage() {
		super();
        setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(8, 1));

        centerBoldLabel title = new centerBoldLabel("확인이 끝났습니다", 72);
        
        add(new EmptyPanel());
        add(title);
        add(new EmptyPanel());
	}
}
