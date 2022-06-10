package Interface;

import javax.swing.*;

public class discussion extends JPanel {

    public JButton profile;
    public JButton match;
    public JTextPane FilDiscu;
    public JTextPane ListUsers;
    public JTextField InputChat;
    public JButton send;

    //Window sizes
    public static final int WIDTH = 900;
    public static final int HEIGHT = 700;

    public discussion(){
        setLayout(null);

        profile = new JButton("Profile");
        profile.setBounds(25,HEIGHT - 100 ,120,30);
        add(profile);

        match = new JButton("Match");
        match.setBounds(WIDTH - 145,HEIGHT - 100, 120, 30);
        add(match);

        FilDiscu = new JTextPane();
        FilDiscu.setBounds(25,25,580,460);
        FilDiscu.setEditable(false);
        JScrollPane FilDiscuSP = new JScrollPane(FilDiscu);
        FilDiscuSP.setBounds(25, 25, 580, 460);
        FilDiscu.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
        add(FilDiscu);

        ListUsers = new JTextPane();
        ListUsers.setBounds(630,25,245,460);
        ListUsers.setEditable(false);
        JScrollPane ListUsersSP = new JScrollPane(ListUsers);
        ListUsersSP.setBounds(630, 25, 245, 460);
        ListUsers.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
        add(ListUsers);

        InputChat = new JTextField();
        InputChat.setBounds(25,510,580,60);
        add(InputChat);

        send = new JButton("send");
        send.setBounds(630,510,245,60);
        add(send);
    }
}