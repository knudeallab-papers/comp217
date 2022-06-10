package cafe;

import java.awt.Color;

import javax.swing.JPanel;

public class MainPanel extends JPanel 
{
	private static final int WIDTH = 930;
	private static final int HEIGHT = 650;
	
	
	JPanel PasswordChangePnl;
	JPanel MenuBoardPnl;
	JPanel EmployeePnl;
	JPanel MemberPnl;
	public DetailPanel DetailPnl;
	public InvenDetailPanel InvenPnl = new InvenDetailPanel();
	public SellDetailPanel SellPnl = new SellDetailPanel();
	public TablePanel TablePnl;
	public ProductSale productSale;
	
	MainPanel()
	{
		super();
		setSize(WIDTH, HEIGHT);
		setBackground(Color.LIGHT_GRAY);
	}
	public void setPasswordChange()
	{
		PasswordChangePnl = new PasswordChange();
		
		add(PasswordChangePnl);
		PasswordChangePnl.setLocation(315, 125);
	}
	public void removePasswordChange()
	{
		remove(PasswordChangePnl);
	}
	public void setMenuBoard(String sys, String s1, String s2, String s3)
	{
		MenuBoardPnl = new MenuBoard(sys, s1, s2, s3);
		
		add(MenuBoardPnl);
		MenuBoardPnl.setLocation(470, 10);
	}
	public void removeMenuBoard()
	{
		remove(MenuBoardPnl);
//		repaint();
	}
	public void setDetailPnl()
	{
		DetailPnl = new DetailPanel();
		
		add(DetailPnl);
		DetailPnl.setLocation(10, 10);
	}
	public void setInvenDetailPnl()
	{
		InvenPnl = new InvenDetailPanel();
		
		add(InvenPnl);
		InvenPnl.setLocation(10, 10);
	}
	public void removeDetailPnl()
	{
		remove(DetailPnl);
	}
	public void setSellDetailPnl()
	{
		SellPnl = new SellDetailPanel();
		
		add(SellPnl);
		SellPnl.setLocation(10, 10);
	}
	public SellDetailPanel getSellDetailPnl()
	{
		return SellPnl;
	}
	public void removeSellDetailPnl()
	{
		remove(SellPnl);
	}
	public void setTablePnl()
	{
		TablePnl = new TablePanel();
		
		add(TablePnl);
		TablePnl.setLocation(10, 390);
	}
	public void removeTablePnl()
	{
		remove(TablePnl);
	}
	public void setEmployee()
	{
		EmployeePnl = new EmployeeBoard();
		
		add(EmployeePnl);
		EmployeePnl.setLocation(10, 10);
	}
	public void removeEmployee()
	{
		remove(EmployeePnl);
	}
	public void setMember()
	{
		MemberPnl = new MemberBoard();
		
		add(MemberPnl);
		MemberPnl.setLocation(10, 10);
	}
	public void removeMember()
	{
		remove(MemberPnl);
	}
	public void setProductSale()
	{
		productSale = new ProductSale();
		
		add(productSale);
		productSale.setLocation(200, 10);
	}
	public void removeProductSale()
	{
		remove(productSale);
	}
}
