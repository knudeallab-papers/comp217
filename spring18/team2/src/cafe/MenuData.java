package cafe;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class MenuData implements Serializable
{
	private int bean;
	private int milk;
	private int water;
	private int syrup;
	private int vanilaSyrup;
	private int cream;
	private int peachPouder;
	private int lemonPouder;
	private int greenTeaPouder;
	private int earlGrey;
	private int berry;
	private int butter;
	private int bread;
	private int waffleMix;
	private int sugar;
	private int iceCup;
	private int hotCup;
	private int iceLid;
	private int hotLid;
	private int straw;
	private int holder;
	
	
	private boolean exist;
	
	private String name;
	private double price;
	private double primeCost;
	
	public MenuData()
	{
		bean = 0;
		milk = 0;
		water = 0;
		syrup = 0;
		vanilaSyrup = 0;
		cream = 0;
		peachPouder = 0;
		lemonPouder = 0;
		greenTeaPouder = 0;
		earlGrey = 0;
		berry = 0;
		butter = 0;
		bread = 0;
		waffleMix = 0;
		sugar = 0;
		iceCup = 0;
		hotCup = 0;
		iceLid = 0;
		hotLid = 0;
		straw = 0;
		holder = 0;
		name = "";
		price = 0.0;
		primeCost = 0.0;
		exist = false;
	}
	public void CalPrimeCost()
	{
		InvenDataSet data = new InvenDataSet();
		double k = 0;
		try
		{
			ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream("InvenData.dat"));
			
			data = (InvenDataSet)inputStream.readObject();
			
			
			inputStream.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		if(bean > 0)
		{
			k += bean * data.getData(data.findData("원두")).price;
		}
		if(milk > 0)
		{
			k += milk * data.getData(data.findData("우유")).price;
		}
		if(water > 0)
		{
			k += water * data.getData(data.findData("물")).price;
		}
		if(syrup > 0)
		{
			k += syrup * data.getData(data.findData("슈가시럽")).price;
		}
		if(vanilaSyrup > 0)
		{
			k += vanilaSyrup * data.getData(data.findData("바닐라시럽")).price;
		}
		if(cream > 0)
		{
			k += cream * data.getData(data.findData("휘핑크림")).price;
		}
		if(peachPouder > 0)
		{
			k += peachPouder * data.getData(data.findData("복숭아파우더")).price;
		}
		if(lemonPouder > 0)
		{
			k += lemonPouder * data.getData(data.findData("레몬파우더")).price;
		}
		if(greenTeaPouder > 0)
		{
			k += greenTeaPouder * data.getData(data.findData("그린티파우더")).price;
		}
		if(earlGrey > 0)
		{
			k += earlGrey * data.getData(data.findData("얼그레이")).price;
		}
		if(berry > 0)
		{
			k += berry * data.getData(data.findData("트리플베리")).price;
		}
		if(butter > 0)
		{
			
			k += butter * data.getData(data.findData("버터")).price;
		}
		if(bread > 0)
		{
			k += bread * data.getData(data.findData("식빵")).price;
		}
		if(waffleMix > 0)
		{
			k += waffleMix * data.getData(data.findData("와플믹스")).price;
		}
		if(sugar > 0)
		{
			k += sugar * data.getData(data.findData("설탕")).price;
		}
		if(iceCup > 0)
		{
			k += iceCup * data.getData(data.findData("아이스컵")).price;
		} 
		if(hotCup > 0)
		{
			k += hotCup * data.getData(data.findData("핫컵")).price;
		}
		if(iceLid > 0)
		{
			k += iceLid * data.getData(data.findData("아이스컵리드")).price;
		}
		if(hotLid > 0)
		{
			k += hotLid * data.getData(data.findData("핫컵리드")).price;
		}
		if(straw > 0)
		{
			k += straw * data.getData(data.findData("빨대")).price;
			
		}
		if(holder > 0)
		{
			k += holder * data.getData(data.findData("컵홀더")).price;
		}
		primeCost = k;
	}
	public boolean getExist()
	{
		return exist;
	}
	public void setExist(boolean sys)
	{
		exist = sys;
	}
	public int getBean()
	{
		return bean;
	}
	public void setBean(int n)
	{
		bean = n;
	}
	public int getMilk()
	{
		return milk;
	}
	public void setMilk(int n)
	{
		milk = n;
	}
	public int getWater()
	{
		return water;
	}
	public void setWater(int n)
	{
		water = n;
	}
	public int getSyrup()
	{
		return syrup;
	}
	public void setSyrup(int n)
	{
		syrup = n;
	}
	public int getVanilaSyrup()
	{
		return vanilaSyrup;
	}
	public void setVanilaSyrup(int n)
	{
		vanilaSyrup = n;
	}
	public int getCream()
	{
		return cream;
	}
	public void setCream(int n)
	{
		cream = n;
	}
	public int getPeachPouder()
	{
		return peachPouder;
	}
	public void setPeachPouder(int n)
	{
		peachPouder = n;
	}
	public int getLemonPouder()
	{
		return lemonPouder;
	}
	public void setLemonPouder(int n)
	{
		lemonPouder = n;
	}public int getGreenTeaPouder()
	{
		return greenTeaPouder;
	}
	public void setGreenTeaPouder(int n)
	{
		greenTeaPouder= n;
	}
	public int getEarlGrey()
	{
		return earlGrey;
	}
	public void setEarlGrey(int n)
	{
		earlGrey = n;
	}
	public int getBerry()
	{
		return berry;
	}
	public void setBerry(int n)
	{
		berry = n;
	}
	public int getBread()
	{
		return bread;
	}
	public void setBread(int n)
	{
		bread = n;
	}
	public int getButter()
	{
		return butter;
	}
	public void setButter(int n)
	{
		butter = n;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String n)
	{
		name = n;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double n)
	{
		price = n;
	}
	public double getPrimeCost()
	{
		return primeCost;
	}
	public void setPrimeCost(double n)
	{
		primeCost = n;
	}
	public int getWaffleMix()
	{
		return waffleMix;
	}
	public void setWaffleMix(int n)
	{
		waffleMix = n;
	}	
	public void setSugar(int n)
	{
		sugar = n;
	}
	public int getSugar()
	{
		return sugar;
	}
	public void setIceCup(int n)
	{
		iceCup = n;
	}
	public int getIceCup()
	{
		return iceCup;
	}
	public void setIceLid(int n)
	{
		iceLid = n;
	}
	public int getIceLid()
	{
		return iceLid;
	}
	public void setHotCup(int n)
	{
		hotCup = n;
	}
	public int getHotCup()
	{
		return hotCup;
	}
	public void setHotLid(int n)
	{
		hotLid = n;
	}
	public int getHotLid()
	{
		return hotLid;
	}
	public void setStraw(int n)
	{
		straw = n;
	}
	public int getStraw()
	{
		return straw;
	}
	public void setHolder(int n)
	{
		holder = n;
	}
	public int getHolder()
	{
		return holder;
	}
}
