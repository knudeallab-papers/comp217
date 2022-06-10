import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {
	public static int menunum = 0;
	private String KindOfMenu = "none";
	int price;
	public	Asset[] asset = new Asset[10];
	int assetnum = 0;
	
	public static Menu searchMenu(String name)
	{
		for(int i = 0; i<menunum;i++)
		{
			if(name.equals(TableDemo.MenuAry[i].toString()))
			{
				return TableDemo.MenuAry[i];
			}
		}
		return null;
	}
	
	public void addAsset(String name)
	{
		for(int i = 0; i<Asset.assetNum;i++)
		{
			if(TableDemo.AssetAry[i].getName().equals(name))
			{
				asset[assetnum] = TableDemo.AssetAry[i];
				assetnum++;
			}
		}
	}
	
	public String getAsset()
	{
		StringBuffer str = new StringBuffer();
		for(int i = 0; i<assetnum;i++)
		{
			str.append(asset[i].getName() + ", ");
			System.out.println("N"+ i+": "+str.toString());
		}
		return str.toString();
	}
	
	public String[] AssetAryToStringAry(Asset[] array)
	{
		String[] result = new String[assetnum];
		
		for(int i = 0; i<assetnum;i++)
		{
			result[i] = array[i].getName();
		}
		
		return result;
	}
	
	public static String[] MenuAryToStringAry(Menu[] array)
	{
		String[] result = new String[menunum];
		
		for(int i = 0; i<menunum;i++)
		{
			result[i] = array[i].getName();
		}
		
		return result;
	}
	
	
	public Menu(String str, String pricenum)
	{
		KindOfMenu = str;
		price = Integer.parseInt(pricenum);
		menunum++;
	}
	
	public int getPrice()
	{
		return price;
	}
	
	public String getName()
	{
		return KindOfMenu;
	}
	
	public static void savefile(){
		String path = Menu.class.getResource(".").getPath().toString()+"Menu"+".txt";

		try(FileWriter fout= new FileWriter(path)) {
			PrintWriter out = new PrintWriter(fout);
			out.println(menunum + "");
			for( Menu elem : TableDemo.MenuAry ) 
				out.println(elem.toString());
		}catch(Exception e) {
			System.out.println("saveFile error: "+e.getMessage());
			//System.exit(-1);
		}
	}
   
	public void setKindOfMenu(String kindOfMenu) {
		KindOfMenu = kindOfMenu;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(this.KindOfMenu + " " +this.price+" "+this.assetnum+" ");
		System.out.println(str.toString());
		for(int i = 0;i<this.assetnum;i++)
		{
			str.append(this.asset[i].getName()+" ");
			System.out.println(str.toString());
		}
		System.out.println(str.toString());
		return str.toString();
	}
   

	public static void readfile() {
		int n = 0;

		try {
			String path = Members.class.getResource(".").getPath().toString()+"Menu"+".txt";
			File menu = new File(path);
			if(menu != null)
			{
				System.out.println("Menu file is found");
			}
			Scanner keyboard = new Scanner(menu);
			try {
				n = keyboard.nextInt();
			}catch(Exception e) {
				System.out.println("file is empty!!");
			}
			
			String KindOfMenu = "none";
			String price;
			String asnum;

			
			for(int i = 0; i < n; i++) {
				KindOfMenu = keyboard.next();
				price = keyboard.next();
	
		       TableDemo.MenuAry[i] = new Menu(KindOfMenu, price);
		       asnum = keyboard.next();
		       System.out.println(asnum);
		       TableDemo.MenuAry[i].assetnum = 0;
				for(int j = 0; j<Integer.parseInt(asnum);j++)
				{
					TableDemo.MenuAry[i].asset[TableDemo.MenuAry[i].assetnum++] = Asset.getAssets(keyboard.next());
				}
		    }
			return;
		}catch(Exception e) {
			System.out.println("Menu file not found");
		}
			
	}
}