package TeamProject;

import javax.swing.*;
import java.awt.*;

public class ChangePage extends JPanel
{
    private JTextField PWInput;
    public ChangePage(String errorMSG, String PWWritten)
    {
        super();
        setLayout(new GridLayout(3, 1));
        setBackground(PasswordMain.BACKGROUND_COLOR);

        JLabel title = new centerBoldLabel("비밀번호 변경 방법", 40);
        JLabel step1 = new centerBoldLabel("사물함을 연 상태에서, DONE버튼을 3초간 누릅니다", 20);
        JLabel step2 = new centerBoldLabel("소리가 나면 변경하실 비밀번호를 입력합니다", 20);
        JLabel step3 = new centerBoldLabel("CLEAR버튼을 누르시면 비밀번호 설정이 완료됩니다", 20);
        step1.setForeground(new Color(0x696999));
        step2.setForeground(new Color(0x696999));
        step3.setForeground(new Color(0x696999));

        JPanel stepPanel = new JPanel(new GridLayout(4,1));
        stepPanel.setBackground(PasswordMain.BACKGROUND_COLOR);
        stepPanel.add(title);
        stepPanel.add(step1);
        stepPanel.add(step2);
        stepPanel.add(step3);

        JLabel announce = new centerBoldLabel("변경하신 비밀번호를 입력해주세요", 24);

        PWInput = new JTextField(4);
        PWInput.setFont(new Font("IM혜민 regular", Font.BOLD, 56));
        PWInput.setText(PWWritten);

        JPanel PWPanel = new JPanel(new FlowLayout());
        PWPanel.setBackground(PasswordMain.BACKGROUND_COLOR);
        PWPanel.add(PWInput);

        JLabel errorMSGLabel = new errorMSGLabel(errorMSG);

        JPanel PWWrapperPanel = new JPanel(new GridLayout(4,1));
        PWWrapperPanel.setBackground(PasswordMain.BACKGROUND_COLOR);
        PWWrapperPanel.add(new EmptyPanel());
        PWWrapperPanel.add(announce);
        PWWrapperPanel.add(PWPanel);
        PWWrapperPanel.add(errorMSGLabel);

        add(stepPanel);
        add(PWWrapperPanel);
    }

    public String getChangedPassword()
    {
        return PWInput.getText();
    }
}