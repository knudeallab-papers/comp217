
import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {

    private int port;
    private List<User> clients;
    private ServerSocket server;
    private List<User> connectedClient;

    public static void main(String[] args) throws IOException {
        new Server(12345).run();
    }

    public Server(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
        this.connectedClient = new ArrayList<>();
    }

    public void run() throws IOException {

        UserlistInit();
        server = new ServerSocket(port) {
            protected void finalize() throws IOException {
                this.close();
            }
        };
        System.out.println("Server is now running...");
        while (true) {
            // accepts a new client
            Socket socket = server.accept();

            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(socket.getOutputStream());

            PrintStream printStream = new PrintStream(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(socket.getInputStream());

            String ID = scanner.nextLine();
            String Password = scanner.nextLine();
            System.out.println(ID + "  " + Password);

            if (checkLogin(ID, Password)) {

                printStream.println("true");

                User newUser = getUser(ID, Password);

                objectOutputStream.writeObject(newUser);
                objectOutputStream.writeObject(listWithoutUser(ID, Password));


                this.connectedClient.add(newUser);


            } else {
                printStream.println("false");

            }


        }
    }

    public boolean checkLogin(String ID, String Password) {
        for (User user : clients
        ) {
            if (user.checkConnection(ID, Password))
                return true;
        }
        return false;
    }

    public User getUser(String ID, String Password) {

        for (User user : clients
        ) {
            if (user.checkConnection(ID, Password))
                return user;
        }
        return null;
    }

    public ArrayList<User> listWithoutUser(String ID, String Password) {
        ArrayList<User> res = new ArrayList<>();
        for (User user : clients
        ) {
            if (!user.checkConnection(ID, Password))
                res.add(user);

        }
        return res;
    }

    // image are in the image file
    public void UserlistInit() {
        ImageIcon one = new ImageIcon("/home/matthieu/IdeaProjects/final_project/src/image/profilepicmatthieu-.jpg");
        User user1 = new User("matthieu", "Bolliand", 19, "I like Java", one, 1);
        clients.add(user1);

        ImageIcon two = new ImageIcon("/home/matthieu/IdeaProjects/final_project/src/image/profilepicfrederik-.jpg");
        User user2 = new User("Frederik", "Julin", 24, "I like south korea", two, 2);
        clients.add(user2);

        ImageIcon tree = new ImageIcon("/home/matthieu/IdeaProjects/final_project/src/image/profilepicbabacar-.jpg");
        User user3 = new User("babacar", "cisse", 25, "I like Paris", tree, 3);
        user3.list.add(user1);
        user3.list.add(user2);
        clients.add(user3);

        ImageIcon four = new ImageIcon("/home/matthieu/IdeaProjects/final_project/src/image/profilepickevin-.jpg");
        User user4 = new User("kevin", "teng", 19, "I like brassiere", four, 4);
        clients.add(user4);

        ImageIcon five = new ImageIcon("/home/matthieu/IdeaProjects/final_project/src/image/profilepicantoine-.jpg");
        User user5 = new User("antoine", "cheng", 23, "I love China", five, 5);
        clients.add(user5);
    }

}

