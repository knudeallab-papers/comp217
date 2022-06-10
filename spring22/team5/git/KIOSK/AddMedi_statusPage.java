package TeamProject;

import java.awt.*;
import javax.swing.*;

public class AddMedi_statusPage extends JPanel{
	public AddMedi_statusPage() 
	{
		super();
        setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(8, 1));

        centerBoldLabel title = new centerBoldLabel("현재 보유 현황", 72);
        
        add(new EmptyPanel());
        add(title);
        add(new EmptyPanel());
	}
}
