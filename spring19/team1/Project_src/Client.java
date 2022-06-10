import Interface.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;



public class Client extends Thread{

    private String serverName; // the name of the server to connect to
    private int PORT; // the port of the server to connect to
    public ArrayList<User> list; // list of matching users
    public User user; // class that holds all the information about our client
    public User matchUser;
    BufferedReader input;
    PrintWriter output;
    Socket socket;
    private app Interface;

    public static void main(String[] args) throws IOException {
        Client client1 = new Client(); // you have to log first on the window under the one you see
    }

    public Client() throws IOException {

        Interface = new app();
        this.serverName = "localhost";
        this.PORT = 12345;
        list = new ArrayList<>();

        socket = new Socket(serverName, PORT);
        Interface.login.connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    output = new PrintWriter(socket.getOutputStream(), true);

                    output.println(Interface.login.ID.getText());
                    output.println(Interface.login.PASSWORD.getText());

                    if (input.readLine().equals("true")){
                        Interface.log();
                        user = (User)objectInputStream.readObject();
                        setProfile();
                        list.addAll((ArrayList<User>)objectInputStream.readObject());
                        objectInputStream.close();

                        next();
                    }
                }
                catch (Exception e){
                    System.out.println("something wrong");
                    e.printStackTrace();
                }
            }
        });

        Interface.discussion.send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        Interface.match.dislike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });
        Interface.match.like.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.list.add(matchUser);
                if (user.isfriend(matchUser))
                    JOptionPane.showMessageDialog(Interface,"It's a match");

                next();
            }
        });
    }

    private void setProfile(){
        Interface.profile.picture.setIcon(user.Picture);
        Interface.profile.Name.setText(user.name);
        Interface.profile.FamilyName.setText(user.FamilyName);
        Interface.profile.Age.setText(user.Age + " years old");
        Interface.profile.description.setText(user.Description);

    }
    private void next(){
        if (list.size() != 0){
            User user = list.get(0);
            matchUser = user;
            list.remove(0);
            Interface.match.picture.setIcon(user.Picture);
            Interface.match.Name.setText(user.name);
            Interface.match.FamilyName.setText(user.FamilyName);
            Interface.match.Age.setText(user.Age + " years old");
            Interface.match.description.setText(user.Description);
        }
        else {
            Interface.match.picture.setVisible(false);
            Interface.match.Name.setText("");
            Interface.match.FamilyName.setText("");
            Interface.match.Age.setText("");
            Interface.match.description.setText("no more users");

        }
    }

    public void sendMessage() {
        try {
            String message = Interface.discussion.InputChat.getText();
            output.println(message);
            Interface.discussion.InputChat.requestFocus();
            Interface.discussion.InputChat.setText(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.exit(0);
        }
    }
}
