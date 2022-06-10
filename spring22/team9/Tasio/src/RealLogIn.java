
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RealLogIn {
	private final static String userfile = "./newTerm/taxiojoin.txt";
	
	public static User login(String id, String pw) throws IOException {
		File file = new File(userfile);
		int pass = 0;
		BufferedReader bufReader = null;
		
		try {
			FileReader filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
			String line = "";
			
			while((line = bufReader.readLine()) != null) {
				String[] token = line.split("\t");
				String passId = token[0];
				String passPw = token[1];
				String coupon = token[2];
				System.out.println(token[0]);
				System.out.println(token[1]);
				System.out.println(token[2]);
				System.out.println(token[3]);
				int account = Integer.parseInt(token[3]);
				if(passId.compareTo(id)==0 && passPw.compareTo(pw)==0) {
					System.out.println("로그인 성공");
					return new User(id, pw, coupon, account);
//					return User(id, pw, sex, coupon);
//					pass = -1;
//					break;
				}
			}
			if(pass == 0) {
				System.out.println("로그인 실패");
			}
		}
		catch (IOException e) {
				e.printStackTrace();
				System.out.println("로그인 실패");
		}
		finally {
			bufReader.close();
		}
		
		return new User("","","",0);
	}
	
	public static void join(String id, String pw, String coupon) {
		File file = new File(userfile);
		try {
			FileWriter filewriter = new FileWriter(file, true);
			if(file.isFile() && file.canWrite()) {
				filewriter.append(id);
				filewriter.append("\t");
				filewriter.append(pw);
				filewriter.append("\t");
				filewriter.append(coupon);
				filewriter.append("\t");
				filewriter.append("10000");
				filewriter.append("\r");
			}
			filewriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
