package TeamProject;

import javax.swing.*;
import java.awt.*;

public class CodeVerifyPage extends JPanel
{
    private JTextField codeInput;

    public CodeVerifyPage(String errorMSG)
    {
        super();
        setLayout(new GridLayout(6, 1));
        setBackground(PasswordMain.BACKGROUND_COLOR);

        JLabel title = new centerBoldLabel("4자리 인증코드를 입력해주세요", 42);

        codeInput = new JTextField(4);
        codeInput.setFont(new Font("IM혜민 regular", Font.BOLD, 64));

        JPanel codeVerifyPanel = new JPanel(new FlowLayout());
        codeVerifyPanel.setBackground(PasswordMain.BACKGROUND_COLOR);
        codeVerifyPanel.add(codeInput);

        JLabel errorMSGLabel = new errorMSGLabel(errorMSG);

        JPanel codeVerifyWrapperPanel = new JPanel(new GridLayout(2, 1));
        codeVerifyWrapperPanel.setBackground(PasswordMain.BACKGROUND_COLOR);
        codeVerifyWrapperPanel.add(codeVerifyPanel);
        codeVerifyWrapperPanel.add(errorMSGLabel);

        add(new EmptyPanel());
        add(title);
        add(codeVerifyWrapperPanel);
        add(new EmptyPanel());

        setVisible(true);
    }

    public String getCodeInput()
    {
        return codeInput.getText();
    }
}