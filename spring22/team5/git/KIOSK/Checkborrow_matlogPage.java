package TeamProject;

import javax.swing.*;
import java.awt.*;

public class Checkborrow_matlogPage extends JPanel{
	public Checkborrow_matlogPage() {
		super();
        setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(8, 1));

        centerBoldLabel title = new centerBoldLabel("돗자리 대여 기록", 72);
        
        add(title);
        add(new EmptyPanel());
	}
}
