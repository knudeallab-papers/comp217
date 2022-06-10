package TeamProject;

import javax.swing.*;
import java.awt.*;

public class findPasswordError extends JPanel
{
    public findPasswordError(Student User)
    {
        super();
        setLayout(new GridLayout(10, 1));
        setBackground(PasswordMain.BACKGROUND_COLOR);

        JLabel title1 = new centerBoldLabel(User.getName() + " 님의", 42);
        JLabel title2 = new centerBoldLabel("사물함 조회 내역입니다", 42);
        JLabel showError = new centerBoldLabel("사물함을 조회할 수 없습니다", 30);
        showError.setForeground(Color.RED);
        JLabel showSol1 = new centerBoldLabel("컴퓨터학부 학생회에 문의해 주세요", 24);
        JLabel showSol2 = new centerBoldLabel("집행부장 유지훈 : 010-2345-6789", 24);
        showSol1.setForeground(new Color(0x595979));
        showSol2.setForeground(new Color(0x595979));

        add(title1);
        add(title2);
        add(new EmptyPanel());
        add(showError);
        add(showSol1);
        add(showSol2);
        add(new EmptyPanel());
    }
}
