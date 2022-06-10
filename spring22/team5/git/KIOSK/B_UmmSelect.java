package TeamProject;
import java.awt.*;
import javax.swing.*;

public class B_UmmSelect extends JPanel {
	public B_UmmSelect(){
		super();
		setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(8, 1));

        centerBoldLabel title = new centerBoldLabel("우산 대여 현황", 42);

        add(new EmptyPanel());
        add(title);
	}
}
