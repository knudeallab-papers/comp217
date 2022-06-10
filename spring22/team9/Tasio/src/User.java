import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class User {
	String id;
	String pw;
//	String sex;
	String coupon;
	int account = 0;
	
	public User(String id, String pw, String coupon, int account) {
		this.id = id;
		this.pw = pw;
//		this.sex = sex;
		this.coupon = coupon;
		this.account = account;
	}

	public void updateCharge(int money) {
		String userfile = "./newTerm/taxiojoin.txt";
		File fp = new File(userfile);
		String dummy = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fp)));
			
			String line;
			int currAccount = 0;
			while ((line = br.readLine())!=null) {
				String[] tokens = line.split("\t");
				if (this.id.equals(tokens[0])) {
					currAccount = Integer.parseInt(tokens[3]);
					continue;
				}
				dummy += (line + "\r\n");				
			}
			br.close();
			dummy += this.id + "\t" + this.pw + "\t" + this.coupon + "\t" + (money + currAccount) + "\r\n";
			
			FileWriter fw = new FileWriter(userfile);
			fw.write(dummy);
			fw.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
