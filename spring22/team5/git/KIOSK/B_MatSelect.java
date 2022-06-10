package TeamProject;

import java.awt.*;
import javax.swing.*;

public class B_MatSelect extends JPanel {
	public B_MatSelect(){
		super();
		setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(8, 1));

        centerBoldLabel title = new centerBoldLabel("돗자리 대여 현황", 42);

        add(new EmptyPanel());
        add(title);
	}

}
