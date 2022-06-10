package cafe;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import java.awt.Color;

public class SideBar extends JPanel
{
	public static final int WIDTH = 270;
	public static final int HEIGHT = 800;
	
	JPanel Password;
	JPanel Manage;
	JPanel Setting;
	JPanel Product;
	JPanel Person;
	JPanel Sell;
	JPanel Business;
	
	public Color midGray = new Color(180, 180, 180);
	
	public SideBar()
	{
		super();
		setSize(WIDTH, HEIGHT);
		this.setBackground(midGray);
	}
	public void addPasswordBar()
	{
		Password = new PasswordBar();
		add(Password);
		Password.setLocation(30, 100);
	}
	public void removePasswordBar()
	{
		remove(Password);
	}
	public void addManageBar()
	{
		Manage = new ManageBar();
		add(Manage);
		Manage.setLocation(30, 100);
	}
	public void removeManageBar()
	{
		remove(Manage);
	}
	public void addSettingBar()
	{
		Setting = new SettingBar();
		add(Setting);
		Setting.setLocation(30, 100);
	}
	public void removeSettingBar()
	{
		remove(Setting);
	}
	public void addProductBar()
	{
		Product = new ProductBar();
		add(Product);
		Product.setLocation(30, 100);
	}
	public void removeProductBar()
	{
		remove(Product);
	}
	public void addPersonBar()
	{
		Person = new PersonBar();
		add(Person);
		Person.setLocation(30, 100);
	}
	public void removePersonBar()
	{
		remove(Person);
	}
	public void addSellBar()
	{
		Sell = new SellBar();
		add(Sell);
		Sell.setLocation(30, 100);
	}
	public void removeSellBar()
	{
		remove(Sell);
	}
	public void addBusinessBar()
	{
		Business = new BusinessBar();
		add(Business);
		Business.setLocation(30, 100);
	}
	public void removeBusinessBar()
	{
		remove(Business);
	}
}
