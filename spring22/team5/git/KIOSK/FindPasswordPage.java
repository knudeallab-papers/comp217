package TeamProject;

import javax.swing.*;
import java.awt.*;

public class FindPasswordPage extends JPanel
{
    public FindPasswordPage(Student user, boolean b, int ID, String PW)
    {
        super();
        setLayout(new GridLayout(10, 1));
        setBackground(PasswordMain.BACKGROUND_COLOR);

        JLabel title1 = new centerBoldLabel(user.getName() + "님의", 54);
        JLabel title2 = new centerBoldLabel("사물함 "+(b?"조회":"변경")+" 내역입니다", 54);
        JLabel cabID = new centerBoldLabel("사물함 번호 - " + ID, 24);
        JLabel cabPW = new centerBoldLabel("사물함 비밀번호 - " + PW, 24);

        add(title1);
        add(title2);
        add(new EmptyPanel());
        add(cabID);
        add(cabPW);
        add(new EmptyPanel());
    }
}
