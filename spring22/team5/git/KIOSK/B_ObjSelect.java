package TeamProject;

import java.awt.*;
import javax.swing.*;

public class B_ObjSelect extends JPanel{
	public B_ObjSelect(){
		super();
		setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(8, 1));

        centerBoldLabel title = new centerBoldLabel("대여물품 선택", 42);

        add(new EmptyPanel());
        add(title);
        add(new EmptyPanel());

	}


}
