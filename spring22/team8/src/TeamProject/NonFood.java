package TeamProject;
public class NonFood extends Item {
	
	public NonFood() {
		super();
	}
	
	public NonFood(String theName, String theID, double thePrice, int theNum, String theType) {
		super(theName,theID,thePrice,theNum,theType);
	}
	
	@Override
	public String toString() {
		int tnum = TypeNum(type);
		String exp = "9999-01-01";
		return (tnum + "/" + id + "/" + name + "/" + price + "/" + exp
				+ "/" + num + "\n");
	}
	
	protected int TypeNum(String theType) {
		switch(theType) {
		case "Smoke" : return 8;
		case "Grocery" : return 9;
		case "Others" : return 10;
		default : return -1;
		}
	}
}
