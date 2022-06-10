package Interface;

import javax.swing.*;

public class profile extends JPanel {

    public JButton match;
    public JButton discussion;
    public  JLabel picture ;
    public  JTextField Name;
    public  JTextField FamilyName;
    public  JTextField Age;
    public  JTextArea description;


    public static final int WIDTH = 900;

    public static final int HEIGHT = 700;

    public profile(){
        setLayout(null);

        match = new JButton("Match");
        match.setBounds(25,HEIGHT - 100 ,120,30);
        add(match);

        discussion = new JButton("Discussion");
        discussion.setBounds(WIDTH - 145,HEIGHT - 100, 120, 30);
        add(discussion);

        picture = new JLabel();
        picture.setBounds(150,100,250,250);
        add(picture);

        Name = new JTextField();
        Name.setBounds(550,100,200,50);
        add(Name);

        FamilyName = new JTextField();
        FamilyName.setBounds(550,200,200,50);
        add(FamilyName);

        Age = new JTextField();
        Age.setBounds(550,300,200,50);
        add(Age);

        description = new JTextArea();
        description.setBounds(100,400,700,125);
        add(description);



    }
}
