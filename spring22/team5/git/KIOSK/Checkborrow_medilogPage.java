package TeamProject;

import java.awt.*;
import javax.swing.*;

public class Checkborrow_medilogPage extends JPanel{
	public Checkborrow_medilogPage() {
		super();
        setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(8, 1));

        centerBoldLabel title = new centerBoldLabel("상비약 대여 기록", 72);
        
        add(title);
        add(new EmptyPanel());
	}
}
