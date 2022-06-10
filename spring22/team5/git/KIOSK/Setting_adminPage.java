package TeamProject;

import java.awt.*;
import javax.swing.*;

public class Setting_adminPage extends JPanel{
	public static final int NAME_LENGTH = 5;
	public static final int ID_LENGTH = 10;
	public static final int PHONE_LENGTH = 11;
    private JTextField nameInput;
    private JTextField IDInput;
    private JTextField phoneInput;
	
	public Setting_adminPage(String errorMSG, String name, String ID, String phone) {
		super();
        setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(8, 1));

        centerBoldLabel title = new centerBoldLabel("관리 정보 입력", 72);

        //add(new EmptyPanel());
        add(title);
        //add(new EmptyPanel());
        //
        nameInput = new JTextField(NAME_LENGTH);
        nameInput.setFont(new Font("IM혜민 regular", Font.PLAIN, 30));
        JLabel nameLB = new JLabel("이름");
        nameLB.setFont(new Font("IM혜민 regular", Font.PLAIN, 30));
        nameInput.setText(name);
        //
        IDInput = new JTextField(ID_LENGTH);
        IDInput.setFont(new Font("IM혜민 regular", Font.PLAIN, 30));
        JLabel IDLB = new JLabel("학번");
        IDLB.setFont(new Font("IM혜민 regular", Font.PLAIN, 30));
        IDInput.setText(ID);
        //
        phoneInput = new JTextField(PHONE_LENGTH);
        phoneInput.setFont(new Font("IM혜민 regular", Font.PLAIN, 30));
        JLabel phoneLB = new JLabel("전화번호");
        phoneLB.setFont(new Font("IM혜민 regular", Font.PLAIN, 30));
        phoneInput.setText(phone);

        JLabel errorMSGLabel = new errorMSGLabel(errorMSG);

        JPanel infoWrapperPanel = new JPanel(new GridLayout(5, 2));
        infoWrapperPanel.setBackground(PasswordMain.BACKGROUND_COLOR);
        infoWrapperPanel.add(nameLB);
        infoWrapperPanel.add(nameInput);
        infoWrapperPanel.add(new EmptyPanel());
        infoWrapperPanel.add(new EmptyPanel());
        infoWrapperPanel.add(IDLB);
        infoWrapperPanel.add(IDInput);
        infoWrapperPanel.add(new EmptyPanel());
        infoWrapperPanel.add(new EmptyPanel());
        infoWrapperPanel.add(phoneLB);
        infoWrapperPanel.add(phoneInput);

        add(infoWrapperPanel);
        add(errorMSGLabel);
        //add(new EmptyPanel());
        setVisible(true);
	}
	
	public String getName()
    {
        return nameInput.getText();
    }
    
    public String getID()
    {
        return IDInput.getText();
    }
    
    public String getPhone()
    {
        return phoneInput.getText();
    }
}
