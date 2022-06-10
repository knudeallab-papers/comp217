import java.io.Serializable;

/**********
 * Filename : Store.java
 * Author   : Team1 - mwJeong, jyRyu
 * Purpose  : Store Table`s Information
 */

public class Table implements Serializable{
	Menu[] dish = new Menu[9];
	private int priceTotal;
	private int usedIdx;
	
	public Table() {
		for (int i = 0; i < 9; i++)
			dish[i] = new Menu();
		priceTotal = 0;
		usedIdx = 0;
	}
	
	public void setIdx(int idx) {
		usedIdx = idx;
	}
	
	public int getIdx() {
		return usedIdx;
	}
	
	public void calPrice() {
		int res = 0;
		
		for (int i = 0; i < usedIdx; i++)
			res += dish[i].getPrice();
		
		priceTotal = res;
	}
	
	public int getPrice() {
		return priceTotal;
	}
	
	public void setPrice(int i) {
		priceTotal = i;
	}
}
