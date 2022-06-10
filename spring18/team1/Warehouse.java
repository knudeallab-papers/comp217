import java.io.Serializable;

public class Warehouse implements Serializable{
	private String name;
	private int count;
	private int order;
	private int price;
	private String place;
	private String call;
	
	public Warehouse() {
		name = null;
		count = 0;
		order = 0;
		price = 0;
		place = null;
		call = null;
	}
	
	public void setName(String n) {
		this.name = new String(n);
	}
	
	public String getName() {
		return new String(name);
	}
	
	public void setCount(int c) {
		this.count = c;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setOrder(int o) {
		this.order = o;
	}
	
	public int getOrder() {
		return order;
	}
	
	public void setPrice(int p) {
		price = p;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPlace(String p) {
		place = new String(p);
	}
	
	public String getPlace() {
		return place;
	}
	
	public void setCall(String c) {
		call = new String(c);
	}
	
	public String getCall() {
		return new String(call);
	}
}
