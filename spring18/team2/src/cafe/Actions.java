package cafe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Actions
{
	private static MainWindow mainWindow;
	private static SideBar side;
	private static TopBar top;
	private static MainPanel mainPanel;
	private static MoneyData money;
	private static curSaleData curSale;
//	private static Data data;
	
	public Actions()
	{
		super();
	}
	public void setMoney(MoneyData m)
	{
		money = m;
	}
	public void setCurSale(curSaleData c)
	{
		curSale = c;
	}
	public void setMainWindow(MainWindow m)
	{
		mainWindow = m;
	}
	public void setMainPanel(MainPanel m)
	{
		mainPanel = m;
	}
	public void setSideBar(SideBar s)
	{
		side = s;
	}
	public void setTopBar(TopBar t)
	{
		top = t;
	}
	public void setMoneyDataOnTop()
	{
		top.setMoneyData(money);
	}
//	public void setData(Data d)
//	{
//		data = d;
//	}
	public static void update()
	{
		top.update();
		
		
		
		try
		{
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("MoneyData.dat"));
			outputStream.writeObject(money);
			outputStream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void addToCurSale(String name, double price, int num, double t_price)
	{
		curSale.addToContents(name, t_price, num, t_price);
		
		try
		{
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("CurSaleData.dat"));
			outputStream.writeObject(curSale);
			outputStream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void resetCurSale()
	{
		curSale.reset();
		
		try
		{
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("CurSaleData.dat"));
			outputStream.writeObject(curSale);
			outputStream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void EndingDay()
	{
		mainPanel.InvenPnl.goNextDay(); //재고 주문 계산. 
		
		
	}
	public static void addToDaySale(double n)
	{
		money.getProfit(n);
	}
	public static void removeSideBar(String act)
	{
		
		if(act.equals("LOGIN"))
		{
//			mainPanel.removeAll();
			side.removePasswordBar();
		}
		else if(act.equals("Setting"))
		{
			mainPanel.removeAll();
			side.removeSettingBar();
		}
		else if(act.equals("Product"))
		{
			mainPanel.removeAll();
			side.removeProductBar();
		}
		else if(act.equals("Person"))
		{
			mainPanel.removeAll();
			side.removePersonBar();
		}
		else if(act.equals("Sell"))
		{
			mainPanel.SellPnl.setMenuFile();
			mainPanel.removeAll();
			side.removeSellBar();
		}
		else if(act.equals("Business"))
		{
			mainPanel.removeAll();
			side.removeBusinessBar();
		}
		mainPanel.repaint();
		side.addManageBar();
	}
	public static void addSideBar(String act)
	{
		side.removeManageBar();
		if(act.equals("판매관리"))
		{
			side.addSellBar();
		}
		else if(act.equals("상품관리"))
		{
			side.addProductBar();
		}
		else if(act.equals("영업관리"))
		{
			side.addBusinessBar();
		}
		else if(act.equals("회원/직원"))
		{
			side.addPersonBar();
		}
		else if(act.equals("설정"))
		{
			side.addSettingBar();
		}
		else if(act.equals("시스템종료"))
		{
			System.exit(0);
		}
	}
	public static void addToMoney(double n)
	{
		money.earnMoney(n);
	}
	public static void removeToMoney(double n)
	{
		money.spendMoney(n);
	}
	public static void addToMainPnl(String act)
	{
		if(act.equals("비밀번호 관리"))
		{
//			mainPanel.removeAll();
//			mainPanel.repaint();
			mainPanel.setPasswordChange();
		}
		else if(act.equals("메뉴"))
		{
			mainPanel.removeAll();
			mainPanel.repaint();
			mainPanel.setMenuBoard("메뉴", "Coffee", "NonCoffee", "Dessert");
			mainPanel.setDetailPnl();
		}
		else if(act.equals("재고"))
		{
			mainPanel.removeAll();
			mainPanel.repaint();
			mainPanel.setMenuBoard("재고", "Ingredient", "Refrigerator", "Disposable");
			mainPanel.setInvenDetailPnl();
		}
		else if(act.equals("회원 관리"))
		{
			mainPanel.removeAll();
			mainPanel.repaint();
			mainPanel.setMember();
		}
		else if(act.equals("직원 관리"))
		{
			mainPanel.removeAll();
			mainPanel.repaint();
			mainPanel.setEmployee();
		}
		else if(act.equals("홀"))
		{
			mainPanel.SellPnl.setMenuFile();
			mainPanel.removeAll();
			mainPanel.repaint();
			mainPanel.setSellDetailPnl();
			mainPanel.setTablePnl();
			mainPanel.setMenuBoard("판매", "Coffee", "NonCoffee", "Dessert");
		}
		else if(act.equals("포장"))
		{
			mainPanel.SellPnl.setMenuFile();
			mainPanel.removeAll();
			mainPanel.repaint();
			mainPanel.setSellDetailPnl();
			mainPanel.SellPnl.setDetail(6);
			mainPanel.setMenuBoard("판매", "Coffee", "NonCoffee", "Dessert");
		}
		else if(act.equals("배달"))
		{
			
		}
		else if(act.equals("상품별 매출"))
		{
			mainPanel.removeAll();
			mainPanel.repaint();
			mainPanel.setProductSale();
		}
		
	}
	public static void removeAtMainPnl(String act)
	{
		if(act.equals("All"))
		{
			mainPanel.removeAll();
		}
		else if(act.equals("MenuBoard"))
		{
			mainPanel.removeMenuBoard();
		}
		else if(act.equals("PasswordChange"))
		{
			mainPanel.removePasswordChange();
		}
		else if(act.equals("DetailPanel"))
		{
			mainPanel.removeDetailPnl();
			//!!!
//			mainPanel.setDetailPnl();
		}
		else
		{
//			System.out.println("1");
			mainPanel.removeAll();
		}
		mainPanel.repaint();
	}
	public static void addToDetailPnl(String type, int num)
	{
		if(type.equals("Coffee"))
		{
			mainPanel.DetailPnl.setCoffeeDetail(num);
		}
		else if(type.equals("NonCoffee"))
		{
			mainPanel.DetailPnl.setNonCoffeeDetail(num);
		}
		else if(type.equals("Dessert"))
		{
			mainPanel.DetailPnl.setDessertDetail(num);
		}
		else if(type.equals("Ingredient"))
		{
			mainPanel.InvenPnl.setDetail(num);
		}
		else if(type.equals("Refrigerator"))
		{
			mainPanel.InvenPnl.setDetail(num + 15);
		}
		else if(type.equals("Disposable"))
		{
			mainPanel.InvenPnl.setDetail(num + 30);
		}
	}
	public static void addToSellDetailPnl(int num)
	{
//		mainPanel.SellPnl.setDetail(num);
		SellDetailPanel pnl = mainPanel.getSellDetailPnl();
		pnl.setDetail(num);
		
	}
	public static void addtoSellTable(String category, int num)
	{
//		SellDetail t = mainPanel.SellPnl.curDetail();
//		t.addToTable(kind, price);
		
		SellDetailPanel pnl = mainPanel.getSellDetailPnl();
		SellDetail t = pnl.curDetail();
		t.addToTable(category, num);
	}
	public static boolean getisEmpty(int n)
	{
		return mainPanel.SellPnl.isEmpty(n);
		
	}
}
