package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class app extends JFrame {

    //Declaration of all windows required in for client side
    public Interface.login login;
    public Interface.match match;
    public Interface.discussion discussion;
    public Interface.profile profile;

    //Window sizes
    public static final int WIDTH = 700;
    public static final int HEIGHT = 400;

    //Main just creates the app
    public static void main(String[] args) {
        app test = new app();
    }

    //Initializes the whole application for client
    public app(){
        setTitle("Connection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());
        setBounds(100,100,WIDTH,HEIGHT);
        Connection();
        setResizable(false);
        setVisible(true);

        SetMatch();
        SetDiscussion();
        SetProfile();

    }

    //START instance windows BLOCK
    public void log(){
        login.setVisible(false);
        Match();
    }

    public void Connection(){
        login = new login();
        add(login);
        login.setVisible(true);

    }

    public void Discussion(){
        setTitle("Discussion");
        setSize(900,700);
        discussion.setVisible(true);

    }

    public void Match(){
        setTitle("Match");
        setSize(900,700);
        match.setVisible(true);
    }

    public void Profile(){
        setTitle("Profile");
        setSize(900,700);
        profile.setVisible(true);
    }
    //END instance windows BLOCK


    //START actionlistener BLOCK
    public void SetMatch(){

        match = new match();
        match.profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                match.setVisible(false);
                Profile();
            }
        });
        match.discussion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                match.setVisible(false);
                Discussion();
            }
        });
        add(match);
    }

    public void SetDiscussion(){
        discussion = new discussion();
        discussion.profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                discussion.setVisible(false);
                Profile();
            }
        });
        discussion.match.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                discussion.setVisible(false);
                Match();
            }
        });
        add(discussion);
    }

    public void SetProfile(){
        profile = new profile();
        profile.match.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profile.setVisible(false);
                Match();
            }
        });
        profile.discussion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profile.setVisible(false);
                Discussion();
            }
        });
        add(profile);

    }
    //END actionlistener BLOCK
}
