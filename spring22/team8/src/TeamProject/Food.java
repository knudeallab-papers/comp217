package TeamProject;

//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

public class Food extends Item implements Comparable<Food>{
	private Date expiration;
	
	public Food() {
		super();
		Date a = new Date(1,1,2000);
		expiration = a;
	}
	
	public Food(String theName, String theID, double thePrice, int theNum, String theType,Date theExp) {
		super(theName,theID,thePrice,theNum,theType);
		Date a = new Date(theExp);
		expiration = a;
	}
	
	@Override
	public String toString() {
		int tnum = TypeNum(type);
		String exp = expiration.toString();
		return (tnum + "/" + id + "/" + name + "/" + price + "/" + exp
				+ "/" + num + "\n");
	}
	
	@Override
	public int compareTo(Food F) {
		Date F2 = F.getExpiration();
		Date F1 = expiration;
		
		return F1.DateCompare(F2);
	}
	
	public void setExpiration(Date theExpiration) {
		Date exp = new Date(theExpiration);
		expiration = exp;
	}
	
	public Date getExpiration() {
		Date exp = new Date(expiration);
		return exp;
	}
	
	protected int TypeNum(String theType) {
		switch(theType) {
		case "Milk" : return 0;
		case "Freezed" : return 1;
		case "Ramen" : return 2;
		case "Can" : return 3;
		case "Oil" : return 4;
		case "Bread" : return 5;
		case "Egg" : return 6;
		case "Drink" : return 7;
		default : return -1;
		}
	}
}
