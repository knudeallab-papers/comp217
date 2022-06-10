import java.io.Serializable;

/**********
 * Filename : Store.java
 * Author   : Team1 - mwJeong, jyRyu
 * Purpose  : Implements other class`s member
 */


public class Menu implements Serializable{
	
	int num;
	public String name;
	private int salePrice;
	private int makePrice;
	Material[] material = new Material[7];
	private int usedIdx;
	
	public Menu() {
		name = null;
		salePrice = 0;
		makePrice = 0;
		for (int i = 0; i < 7; i++)
			material[i] = new Material();
		usedIdx = 0;
	}
	
	public void setName(String n) {
		name = new String(n);
	}
	
	public void setPrice(int p) {
		salePrice = p;
	}
	
	public void setMPrice(int p) {
		makePrice = p;
	}
	
	public void setMaterial(Material[] m, int uIdx) {
		for (int i = 0; i < uIdx; i++)
			m[i] = new Material(m[i]);
		
		usedIdx = uIdx;
	}
	
	public String getName() {
		if (name == null)
			return null;
		return new String(name);
	}
	
	public int getPrice() {
		return salePrice;
	}
	
	public int getMPrice() {
		return makePrice;
	}
	
	public int getIdx() {
		return usedIdx;
	}
	
	public void setIdx(int idx) {
		usedIdx = idx;
	}
	
	public String toOrderString() {
		return name + " " + num +"개 ";
	}
}
