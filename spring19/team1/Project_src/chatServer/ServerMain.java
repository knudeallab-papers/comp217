package chatServer;

//Simply got this to run the server, we don't want to implement this as runnable
public class ServerMain {
    private ChatServer server;

    public ServerMain(int port) {
        server = new ChatServer(port);
    }

    public static void main(String[] args) {
        new ServerMain(5555);
    }

}
