package five_nights_at_freddy;

import java.awt.GridLayout;
import java.io.Serializable;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FoodMenu implements Serializable{
	public int foodIndex = 0;
	
	public String[] menuListName = new String[15];
	public int[] menuListPrice = new int[15];
	public String[] menuListIndex = new String[15];
	public int[] menuCostProduction = new int[15];
	
	public FoodMenu(){
		menuListName[foodIndex] = "";
		menuListPrice[foodIndex] = 0;
	}
	
	public void setMenu(int num,String food, int price, int costproduction, String index) {
		
		menuListName[num] = food;
		menuListPrice[num] = price;
		menuCostProduction[num] = costproduction;
		menuListIndex[num] = index;
		foodIndex++;
	}
	
	public int getPrice() {
		int price = 0;
		for(int i = 0 ; i < foodIndex; i++ ) {
			price += menuListPrice[i];
		}
		return price;
	}
	
	public int returnPrice(int num) {
		
		return menuListPrice[num];
	}
	
	public String returnName(int num) {
		return menuListName[num];
	}
	
	public String returnIndex(int num) {
		return menuListIndex[num];
	}
	
	public int returnCostProduction(int num) {
		return menuCostProduction[num];
	}
	public boolean has(int num) {
		if(menuListIndex[num] == null)
			return false;
		
		return true;
	}
}
