package TeamProject;

import java.awt.*;
import javax.swing.*;

public class AddMedi_editstatusPage extends JPanel
{
    JTextField inputText;

    public AddMedi_editstatusPage(String med_name)
    {
        super();
        setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(8, 1));

        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.setBackground(new Color(0xededf9));

        inputText = new JTextField(2);
        //medi 개수 변수에 저장하기
        inputText.setFont(new Font("IM혜민 bold", Font.BOLD, 42));

        mediNameLabel inputLabel = new mediNameLabel(med_name);
        mediNameLabel addLabel = new mediNameLabel(" 개 를");

        titlePanel.add(inputLabel);
        titlePanel.add(inputText);
        titlePanel.add(addLabel);

        JPanel subtitlePanel = new JPanel(new GridLayout(1, 1));
        subtitlePanel.setBackground(new Color(0xededf9));

        mediNameLabel subLabel = new mediNameLabel("추가하시겠습니까?");
        subtitlePanel.add(subLabel);


        add(new EmptyPanel());
        add(titlePanel);
        add(subtitlePanel);
        add(new EmptyPanel());
    }

    public int getNumber()
    {
        return Integer.parseInt(inputText.getText());
    }

}
