package Crypto;
import java.util.*;
import java.io.*;
import java.net.*;

public class Serveur {

		public static void main (String[] args) {
			ServerSocket server;
			Socket socket;
			final BufferedReader in;
			final PrintWriter out;
			final Scanner sc = new Scanner(System.in); // lire les messages
			
			try {
				//System.out.println("lalal");
				//server = new ServerSocket(4780); // creation du server avec port
				server = new ServerSocket(5780);
			//	server.setSoTimeout(9000);
				System.out.println("1");
				socket = new Socket();
				socket = server.accept();
				System.out.println("1");
				out = new PrintWriter(socket.getOutputStream());  // flux pour envoyer
				in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // flux pour recevoir
				//System.out.println("3");//"" + socket.getInetAddress().getHostAddress());
				Thread send = new Thread(new Runnable() {
					String msg;
					public void run() {
						while(true) {
							msg = sc.nextLine();
							//sc.nextLine();
							out.println(msg);
							out.flush();
						}
					}
				});
				send.start();
				//send.run();
				Thread receive = new Thread(new Runnable() {
					String msg;
					public void run() {
						while(true) {
							try {
								msg = in.readLine();
							}catch(IOException e) {
								e.printStackTrace();
							}
							System.out.println("Kevin : " + msg);
						}
					}
				});
				receive.start();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		
}
