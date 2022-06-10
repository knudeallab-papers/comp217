package Crypto;

import java.util.*;
import java.io.*;
import java.net.*;
public class client {
	public static void main (String[] args) {
		Socket socket;
		final BufferedReader in;
		final PrintWriter out;
		final Scanner sc = new Scanner(System.in); // lire les messages
		
		try {
			socket = new Socket("192.168.0.10", 5002);    // creation de la socket du server pour recup les messages
			out = new PrintWriter(socket.getOutputStream());  // flux pour envoyer
			in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // flux pour recevoir
			
			Thread send = new Thread(new Runnable() {
				String msg;
				public void run() {
					while(true) {
						msg = sc.nextLine();
						out.println(msg);
						out.flush();
					}
				}
			});
			send.start();
			Thread receive = new Thread(new Runnable() {
				String msg;
				public void run() {
					while(true) {
						try {
							msg = in.readLine();
						}catch(IOException e) {
							e.printStackTrace();
						}
						System.out.println("Edmond : " + msg);
					}
				}
			});
			receive.start();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}