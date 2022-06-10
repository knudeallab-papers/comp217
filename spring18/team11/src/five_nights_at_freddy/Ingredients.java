package five_nights_at_freddy;

import java.io.Serializable;

public class Ingredients implements Serializable{
	public String name;
	public int leaving;
	public int calling;
	public int price;
	public String seller;
	public String phoneNumber;
	
	public Ingredients() {
		name = "";
		leaving = 0;
		calling = 0;
		price = 0;
		seller = "";
		phoneNumber = "";
	}
	
	public Ingredients(String newName, int newLeaving, int newPrice, String newSeller, String newPhoneNumber) {
		super();
		name = newName;
		leaving = newLeaving;
		price = newPrice;
		seller = newSeller;
		phoneNumber = newPhoneNumber;
	}
	
	public void plusLeaving(int newLeaving) {
		leaving += newLeaving;
	}
	
	public void plusCalling(int newCalling) {
		calling += newCalling;
	}
}
