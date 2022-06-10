package TeamProject;

public abstract class Item {
	protected String name;
	protected String id;
	protected double price;
	protected int num;	
	protected String type;
	
	protected abstract int TypeNum(String theType);
	
	public Item() {
		name = "";
		id = "";
		price = 0;
		num = 0;
		type = "";
	}
	
	public Item(String theName, String theID, double thePrice, int theNum, String theType) {
		name = theName;
		id = theID;
		price = thePrice;
		num = theNum;
		type = theType;
	}
	
	public void setName(String theName) {
		name = theName;
	}

	public void setID(String theID) {
		id = theID;
	}

	public void setPrice(double thePrice) {
		price = thePrice;
	}

	public void setNum(int theNum) {
		num = theNum;
	}
	
	public void setType(String theType) {
		type = theType;
	}

	public String getName() {
		return name;
	}

	public String getID() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public int getNum() {
		return num;
	}

	
	public String getType() {
		return type;
	}
	@Override
	public String toString() {
		return (id + "/" + name + "/" + price + "/"
				+ "/" + num + "\n");
	}
	
	
}
