package TeamProject;

import java.awt.*;
import javax.swing.*;

public class AddMedi_completePage extends JPanel{
	public AddMedi_completePage(String medi_name, int medi_num)
    {
		super();
        setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(8, 1));
        
        centerBoldLabel title1 = new centerBoldLabel(medi_name + " " + medi_num + "", 72);
        centerBoldLabel title2 = new centerBoldLabel("수집 완료 :)",72);
        
        add(new EmptyPanel());
        add(title1);
        add(title2);
        add(new EmptyPanel());
    }
}
