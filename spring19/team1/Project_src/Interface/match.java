package Interface;

import javax.swing.*;


public class match extends JPanel {

    public JButton profile;
    public JButton discussion;
    public JButton like;
    public JButton dislike;
    public  JLabel picture ;
    public  JTextField Name;
    public  JTextField FamilyName;
    public  JTextField Age;
    public  JTextArea description;

    public static final int WIDTH = 900;

    public static final int HEIGHT = 700;

    public match(){
        setLayout(null);

        profile = new JButton("Profile");
        profile.setBounds(25,HEIGHT - 100 ,120,30);
        add(profile);

        discussion = new JButton("Discussion");
        discussion.setBounds(WIDTH - 145,HEIGHT - 100, 120, 30);
        add(discussion);
        //change path
        ImageIcon IMGlike = new ImageIcon("/home/matthieu/IdeaProjects/GroupeProject/src/multiplepanel/" +
                "image/kakaolike.png");
        like = new JButton(IMGlike);
        like.setBounds(550, 420, 150,150);
        add(like);
	//change path
        ImageIcon IMGdislike = new ImageIcon("/home/matthieu/IdeaProjects/GroupeProject/src/multiplepanel/" +
                "image/kakodislike.png");
        dislike = new JButton(IMGdislike);
        dislike.setBounds(200,420,150,150);
        add(dislike);

        picture = new JLabel();
        picture.setBounds(50,50,250,250);
        add(picture);

        Name = new JTextField();
        Name.setBounds(400,50,200,50);
        add(Name);

        FamilyName = new JTextField();
        FamilyName.setBounds(650,50,200,50);
        add(FamilyName);

        Age = new JTextField();
        Age.setBounds(400,125,200,50);
        add(Age);

        description = new JTextArea();
        description.setBounds(400,200,450,150);
        add(description);

    }
}
