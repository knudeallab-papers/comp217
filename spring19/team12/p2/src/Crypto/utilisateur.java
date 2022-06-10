package Crypto;

import java.util.ArrayList;

public class utilisateur {
	
	private static ArrayList<String> user = new ArrayList<String>();
	
	private static ArrayList<String[]> listeu = new ArrayList<String[]>();
	
	
	
	//getter setter

		public static ArrayList<String[]> getListeu() {
			return listeu;
		}


		public static void setListeu(ArrayList<String[]> listeu) {
			utilisateur.listeu = listeu;
		}
		
		public static ArrayList<String> getUser() {
			return user;
		}

		public static void setUser(ArrayList<String> user) {
			utilisateur.user = user;
		}
		

	
	
	public static void addList (ArrayList<String> user, String username) {
		user.add(username);
	}
	
	public static void addlisteu (ArrayList<String[]> listeu, String username, String password)
	{
		String[] passuser = new String[2];
		passuser[0] = username;
		passuser[1] = password;
		listeu.add(passuser);
	}
	
	
	
	
	public static boolean checkpresence(ArrayList<String> user, String username) {
		return user.contains(username);
	}
	
	public static boolean checkpres(ArrayList<String[]> user, String username) {
		
		boolean ok = false;
		for (String[] us: user) {
		    if (us[0].equals(username))
		    		ok = true;
		}	
		return ok;
	}

	//check if password match with usermame
	public static boolean checkmatch(ArrayList<String[]> user, String username, String password) {
		boolean ok = false;
		for (String[] us: user) {
		    if (us[0].equals(username))
		    		ok = (us[1].equals(password));
		}	
		return ok;
	}

	public static void main(String[] args) {
		
		addList(user, "Test");
		System.out.println("IS PRESENT : " + checkpresence(user, "Test"));
		

	}



	

}