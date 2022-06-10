package cafe;

import java.io.Serializable;

public class MoneyData implements Serializable
{
	private double money;
	private double daySale;
	
	public MoneyData()
	{
		
	}
	
	public void spendMoney(double cost)
	{
		money = money - cost;
	}
	public void end()
	{
		daySale = 0;
	}
	public void earnMoney(double earn)
	{
		money = money + earn;
	}
	public void getProfit(double m)
	{
		daySale += m;
	}
	public double getMoneyData()
	{
		return money;
	}
	public double getDaySaleData()
	{
		return daySale;
	}
    public void initMoney()
    {
        money = 1000000;
    }
    public void initdaySale()
    {
    		daySale = 0;
    }
}