import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Asset {
	public static int assetNum = 0;
	private String name;
	private int price;
	private String seller;
	private String contact;
	private int left;
	private int order;
	private int orderprice;
	
	public Asset(String name, int price, String seller, String contact) {
		this.name = name;
		this.price = price;
		this.seller = seller;
		this.contact = contact;
		this.left = 0;
		this.order = 0;
		this.orderprice = 0;
		assetNum++;
	}
	
	public static Asset getAssets(String name)
	{
		for(int i=0;i<assetNum;i++)
		{
			if(name.equals(TableDemo.AssetAry[i].getName()))
			{
				return TableDemo.AssetAry[i];
			}
		}
		return TableDemo.AssetAry[0];
	}
	
	public int getOrderprice() {
		return orderprice;
	}


	public void setOrderprice(int orderprice) {
		this.orderprice = orderprice;
	}


	public int getLeft() {
		return left;
	}
	
	public void setLeft(int left) {
		this.left = left;
	}
	
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	public String getSeller() {
		return seller;
	}
	public String getContact() {
		return contact;
	}
	
	public void setName(String name) {
		this.name = name;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public void setSeller(String seller) {
		this.seller = seller;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public static void savefile(){
		String path = Asset.class.getResource(".").getPath().toString()+"Asset"+".txt";

		try(FileWriter fout= new FileWriter(path)) {
			PrintWriter out = new PrintWriter(fout);
			out.println(assetNum+"");
			for( Asset elem : TableDemo.AssetAry ) 
				out.println(elem.toString());
		}catch(Exception e) {
			System.out.println("saveFile error: "+e.getMessage());
		}
	}
	
	public String toString() {
		return name +" "
				+ price + " "
				+ seller + " "
				+ contact + " "
				+ left;
	}
	
	public static void readfile() {
		int n = 0;

		try {
			String path = Members.class.getResource(".").getPath().toString()+"Asset"+".txt";
			File asset = new File(path);
			if(asset != null)
			{
				System.out.println("Asset file is found");
			}
			Scanner keyboard = new Scanner(asset);
			try {
				n = keyboard.nextInt();
			}catch(Exception e) {
				System.out.println("file is empty!!");
			}
			
			String name;
			int price;
			String seller;
			String contact;
			int left;
			
			for(int i = 0; i < n; i++) {
				name = keyboard.next();
				price = Integer.parseInt(keyboard.next());
				seller = keyboard.next();
				contact = keyboard.next();
				left = Integer.parseInt(keyboard.next());

				if(name == null) {
					break;
				}
		       TableDemo.AssetAry[i] = new Asset(name, price, seller, contact);
		       TableDemo.AssetAry[i].setLeft(left);

		    }
			return;
		}catch(Exception e) {
			System.out.println("Asset file not found");
		}
			
	}
}
