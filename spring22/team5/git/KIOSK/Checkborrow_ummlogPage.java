package TeamProject;

import java.awt.*;
import javax.swing.*;

public class Checkborrow_ummlogPage extends JPanel {
	public Checkborrow_ummlogPage() {
		super();
        setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(8, 1));

        centerBoldLabel title = new centerBoldLabel("우산 대여 기록", 72);
        
        add(title);
        add(new EmptyPanel());
	}
}
