package TeamProject;

import java.awt.*;
import javax.swing.*;

public class Checkborrow_selectPage extends JPanel{
	public Checkborrow_selectPage() 
	{
		super();
        setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(8, 1));

        centerBoldLabel title = new centerBoldLabel("물품 선택하세요", 72);
        
        add(new EmptyPanel());
        add(title);
        add(new EmptyPanel());
	}
}
