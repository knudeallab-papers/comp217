package Interface;

import javax.swing.*;
import java.awt.*;

public class login extends JPanel{

    private JLabel kakao;
    private JLabel id;
    public JTextField ID;
    private JLabel password;
    public JTextField PASSWORD;
    public JButton connect;


    public static final int WIDTH = 700;
    public static final int HEIGHT = 400;

    public login(){
        setLayout(null);
        setBackground(Color.ORANGE);

        kakao = new JLabel("Kakao Match", SwingConstants.CENTER);
        kakao.setFont(new Font("Serif",Font.PLAIN,32));
        kakao.setBounds(WIDTH/3,0,WIDTH/3,60);
        kakao.setForeground(Color.WHITE);
        add(kakao);

        id = new JLabel("userID");
        id.setBounds(25,HEIGHT/4 -40,50,40);
        add(id);

        ID = new JTextField();
        ID.setBounds(25,HEIGHT/4,WIDTH-50,50);
        ID.setBackground(Color.WHITE);
        add(ID);

        password = new JLabel("password");
        password.setBounds(25, HEIGHT/4 +60,100,40);
        add(password);

        PASSWORD = new JPasswordField();
        PASSWORD.setBounds(25,HEIGHT/4 + 100,WIDTH-50,50);
        PASSWORD.setBackground(Color.WHITE);
        add(PASSWORD);

        connect = new JButton("Log In");
        connect.setBounds(WIDTH/2-50,HEIGHT-100,100,30);
        add(connect);

    }

}
