import java.lang.StringBuffer;

public class Table {
	public static int tablenum = 0;
	
	private int price;
	private Menu[] menu = new Menu[TableDemo.MENU_NUM];
	public int ordered = 0;
	
	public Table()
	{
		price = 0;
		ordered = 0;
		tablenum++;
	}
	
	public int getPrice()//이때까지 주문한 가격 가져오
	{
		return price;
	}
	public Menu[] getMenu()
	{
		return menu;
	}
	
	public String[] getMenuString()
	{
		String[] strary = new String[TableDemo.MENU_NUM];
		
		for(int i = 0; i<TableDemo.MENU_NUM;i++)
		{
			strary[i] = menu[i].getName();
		}
		
		return strary;
	}

	
	public void ApplyMile(String membername)
	{
		for(int i = 0; i< Members.membernum;i++)
		{
			if(membername.equals(TableDemo.MemberAry[i].toString()))
			{
				System.out.println("Add mileage to "+ membername);
				TableDemo.MemberAry[i].addMile(price*0.02);
				TableDemo.MemberAry[i].mileage -= price*(1-TableDemo.MemberAry[i].getSale());
				price*=TableDemo.MemberAry[i].getSale();
			}
			
		}
	}
	
	public void ApplyMile(int i)
	{
				TableDemo.MemberAry[i].addMile(price*0.02);
				//TableDemo.MemberAry[i].mileage -= price*(1-TableDemo.MemberAry[i].getSale());
				price*=TableDemo.MemberAry[i].getSale();
	}

	
	public String getOrder()//주문 문자열 가져오
	{
		StringBuffer str = new StringBuffer();
		for(int i = 0;i<ordered;i++)
		{
			str.append(menu[i].getName() + ", ");
		}
		//str.append(menu[ordered-1].toString());
		
		return str.toString();
	}

	
	public void addOrder(String order)//주문 추
	{
		for(int i= 0; i<Menu.menunum;i++)
		{
			if(order.equals(TableDemo.MenuAry[i].getName()))
			{
				System.out.println("Find Menu to add Order: succeed //result ->" + order);
				menu[ordered] = TableDemo.MenuAry[i];
				price += menu[ordered].getPrice();
				ordered++;
				return;
			}
		}
	}
	
	public void TableEnd()//결제&마감 동시처리 
	{
		price = 0;
		for(int i=0;i<ordered;i++)
		{
			for(int j=0;j<menu[i].assetnum;j++)
			{
				menu[i].asset[j].setLeft(menu[i].asset[j].getLeft()-1);
			}
		}
		ordered = 0;
		tablenum--;
		for(int i= 0; i<10;i++)
		{
			menu[i] = null;
		}
	}
	
	
}