package TeamProject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Management{

	protected ArrayList<Food> FoodList = null;
	protected ArrayList<NonFood> NonFoodList = null;
	
	protected ArrayList<Food> OrderFoodList = null; 
	protected ArrayList<NonFood> OrderNonFoodList = null;
	
	protected ArrayList<ArrayList<String>> TempOrderList = null;
	
	protected ArrayList<Food> ExpFoodList = null;
	
	/*
	 * ������ : FoodList/NonFoodList�� ���� ������ �����ϴ� �迭�̰�,
	 *        OrderFoodList/OrderNonFoodList�� ���� ����� �����ϴ� �迭,
	 *        TempOrderList�� ���� Ȯ�θ���� �����ϴ� �迭,
	 *        ExpFoodList�� ��� ����� �����ϴ� �迭�̴�.
	*/
	public Management() {
		FoodList = new ArrayList<>();
		NonFoodList = new ArrayList<>();
		OrderFoodList = new ArrayList<>();
		OrderNonFoodList = new ArrayList<>();
		TempOrderList = new ArrayList<>();
		ExpFoodList = new ArrayList<>();
	}
	
	/*
	 * Method ����
	 * 1. ���ְ��� (Order.... )
	 * 2. �������� (Item... / Food.... / NonFood... )
	 * 3. ������ (Exp... )
	 * 4. ��Ÿ
	 */
	
	//1. ���� ���� Method
	
	//Order : ���� ����� Order.txt���� ������ ���� OrderFoodList/ OrderNonFoodList�� ���� �ִ´�.
	public void Order() {  
		try {
			String path = Management.class.getResource("").getPath();
			path = java.net.URLDecoder.decode(path,"UTF-8");
			File file = new File(path + "../../src/docs/Order.txt");
			BufferedReader fr = new BufferedReader(new FileReader(file));
			String str;
			while((str = fr.readLine())!= null) {
				ArrayList<String> TokenList = new ArrayList<>();
				StringTokenizer stk = new StringTokenizer(str,"/");
				while(stk.hasMoreTokens()) {
					TokenList.add(stk.nextToken());
				}
				if(isFood(TokenList.get(0))) {
					Food theFood = new Food();
					theFood.setType(StringType(stringToInt(TokenList.get(0))));
					theFood.setID(TokenList.get(1));
					theFood.setName(TokenList.get(2));
					theFood.setPrice(stringToDouble(TokenList.get(3)));
					OrderFoodList.add(theFood);
				}else {
					NonFood theNonFood = new NonFood();
					theNonFood.setType(StringType(stringToInt(TokenList.get(0))));
					theNonFood.setID(TokenList.get(1));
					theNonFood.setName(TokenList.get(2));
					theNonFood.setPrice(stringToDouble(TokenList.get(3)));
					OrderNonFoodList.add(theNonFood);
				}
			    }
			fr.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//OrderItem : ��ǰ�� ������ �� ���� Method�� OrderList�� �ִ� ������ ������Ѱ� ������ �޾� FoodList�� NonFoodList�� �ִ´�.
	public void OrderItem(String ID, int num) {
		int index;
		if(isFoodOrder(ID)) {
			index = OrderFindFoodIndex(ID);
			Food OFL = OrderFoodList.get(index);
			OFL.setExpiration(CalExp(OFL.getType()));

			int Fidx = FindFoodIndex(ID,OFL.getExpiration());
			if(Fidx != -1) {
				Food theFood = FoodList.get(Fidx);
				int theNum = num + theFood.num;
				OFL.setNum(theNum);
				FoodList.remove(Fidx);
				FoodList.add(OFL);
			}else {
				OFL.setNum(num);
				FoodList.add(OFL);
			}
		}else if(isNonFoodOrder(ID)) {
			index = OrderFindNonFoodIndex(ID);
			NonFood ONFL = OrderNonFoodList.get(index);
			ONFL.setNum(num);
			NonFoodList.add(ONFL);
		}else {
			System.out.println("Error");
		}
	}
	
	// TempOrderToReal : ���� Ȯ�θ�Ͽ� �ִ� �������� ������ �� ����Ѵ�.
	public void TempOrderToReal() {
		for(ArrayList<String> data : TempOrderList) {
			String theID = data.get(0);
			int theNum = stringToInt(data.get(3));
			OrderItem(theID,theNum);
		}
	}
	
	// TempOrderFindIndex : ���� Ȯ�θ�Ͽ��� ������ ã�Ƽ� �� index���� ��ȯ. ���ٸ� -1�� ��ȯ
	public int TempOrderFindIndex(String[] data) {
		int i = 0;
		for(ArrayList<String> A: TempOrderList) {
			if(A.get(0).equals(data[0])) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	// AddTempOrderItem : ���� Ȯ�θ�Ͽ� ������ �߰��Ѵ�.
	public void AddTempOrderItem(String[] data) {
		ArrayList<String> itemA = new ArrayList<>();
		int index = TempOrderFindIndex(data);
		itemA.add(data[0]);
		itemA.add(data[1]);
		itemA.add(data[2]);
		if(index != -1) {
			String OriginalNum = TempOrderList.get(index).get(3);
			int finNum = stringToInt(data[3]) + stringToInt(OriginalNum);
			TempOrderList.remove(index);
			itemA.add(intToString(finNum));
		}else {
			itemA.add(data[3]);
		}
		TempOrderList.add(itemA);	
	}
	
	// DelTempOrderItem : ���� Ȯ�θ�Ͽ� ������ �����Ѵ�.
	public void DelTempOrderItem(String[] data) {
		int index = TempOrderFindIndex(data);
		TempOrderList.remove(index);
	}
	
	// getTempPrice : ���ݱ��� ������ �������� �� ������ ��ȯ�Ѵ�.
	public String getTempPrice() {
		double Total = 0;
		for(ArrayList<String> data : TempOrderList) {
			double price = stringToDouble(data.get(2));
			double num = stringToDouble(data.get(3));
			Total += price * num;
		}
		return doubleToString(Total);
	}
	
	// CalExp : ���� Type�� ���� ��������� ����Ͽ� ��ȯ�Ѵ�. ���� ��Ͽ��� ���� ������� �ű� �� ���.
	private Date CalExp(String theType) {
		Date exp = new Date();
		exp.setToday();
		switch(theType) {
		case "Milk" : exp.addDate(14); break;
 		case "Freezed" : exp.addDate(270); break;
		case "Ramen" : exp.addDate(150); break;
		case "Can" : exp.setYear(exp.getYear()+7); break;
		case "Oil" : exp.setYear(exp.getYear()+2); break;
		case "Bread" : exp.addDate(3); break;
		case "Egg" : exp.addDate(20); break;
		case "Drink" : exp.setYear(exp.getYear()+1); break;
		default : exp.setDate("9999-01-01");
		}
		return exp;
	}
	
	// KindsCompare : ��ǰ���� type�� ���� ���� �������� �ľ��Ѵ�. (ex. type:Milk -> Kind:�ż���ǰ) �з��� ���� �ʿ���.
	private boolean KindsCompare(String Kinds, String type) {
		switch(Kinds) {
		case "Fresh": 
			if(type == "Milk" || type == "Egg") {
				return true;
			}else {
				return false;
			}
		case "Freezed":
			if(type == "Freezed") {
				return true;
			}else {
				return false;
			}
		case "Normal":
			if(type == "Ramen" || type == "Can" || type == "Bread" || type == "Oil") {
				return true;
			}else {
				return false;
			}
		case "Drinks":
			if(type == "Drink") {
				return true;
			}else {
				return false;
			}
		case "Grocery":
			if(type == "Grocery") {
				return true;
			}else
				return false;
		case "Others":
			if(type == "Others") {
				return true;
			}else {
				return false;
			}
		case "All" :
			return true;
		default:
			return false;
		}
	}
	
	// FoodKinds : ��ü ������ �������� ���� �κ� ������ ��ȯ�Ѵ�. (���� ����) ������ ���Ϳ� �ʿ���.
	public ArrayList<Food> FoodKinds(String Kinds) {
		ArrayList<Food> res = new ArrayList<Food>();

		for(Food FL : FoodList) {
			if(KindsCompare(Kinds,FL.getType())) {
				res.add(FL);
			}
		}
		return res;
	}
	
	// NonFoodKinds : ��ü ������ �������� ���� �κ� ������ ��ȯ�Ѵ�. (������ ����) ������ ���Ϳ� �ʿ���.
	public ArrayList<NonFood> NonFoodKinds(String Kinds) {
		ArrayList<NonFood> res = new ArrayList<NonFood>();
		
		for(NonFood NFL : NonFoodList) {
			if(KindsCompare(Kinds,NFL.getType())) {
				res.add(NFL);
			}
		}
		return res;
	}
	
	// OrderList : ���� �� ��ϵ��� ��� ��ȯ�Ѵ�. ���� Ȯ�θ�Ͽ� �ʿ�.
	public ArrayList<ArrayList<String>> OrderList(String Kinds) {
	
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		
		for(Food OFL : OrderFoodList) {
			if(KindsCompare(Kinds,OFL.getType())) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(OFL.getID());
			list.add(OFL.getName());
			list.add(doubleToString(OFL.getPrice()));
			res.add(list);
			}
		}
		for(NonFood ONFL : OrderNonFoodList) {
			if(KindsCompare(Kinds,ONFL.getType())) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(ONFL.getID());
			list.add(ONFL.getName());
			list.add(doubleToString(ONFL.getPrice()));
			res.add(list);
			}
		}
		return res;
	}
	
	// isFoodOrder : �� ID�� ��ǰ ���� ��Ͽ� �ִ� �� Ȯ��
	public boolean isFoodOrder(String ID) {
		if(OrderFindFoodIndex(ID) != -1) return true;
		else return false;
	}
	
	// isNonFoodOrder : �� ID�� ���ǰ ���� ��Ͽ� �ִ� �� Ȯ��
	public boolean isNonFoodOrder(String ID) {
		if(OrderFindNonFoodIndex(ID) != -1) return true;
		else return false;
	}
	
	// OrderFindFoodIndex : ���� ��Ͽ��� ������ ã�� index�� ��ȯ, ���ٸ� -1 ��ȯ (��ǰ ����)
	public int OrderFindFoodIndex(String ID) {
		int i = 0;
		for(Food f: OrderFoodList) {
			if(f.id.equals(ID)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	// OrderFindNonFoodIndex : ���� ��Ͽ��� ������ ã�� index�� ��ȯ, ���ٸ� -1 ��ȯ (���ǰ ����)
	public int OrderFindNonFoodIndex(String ID) {
		int i = 0;
		for(NonFood f: OrderNonFoodList) {
			if(f.id.equals(ID)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	// 2. ���� ���� Method
	
	// load : ItemList�� ����Ǿ� �ִ� ���� �����͵��� FoodList�� NonFoodList�� �ִ´�.
	public void load() {	
		try {
			String path = Management.class.getResource("").getPath();
			path = java.net.URLDecoder.decode(path,"UTF-8");
			File file = new File(path + "../../src/docs/ItemList.txt");
			BufferedReader fr = new BufferedReader(new FileReader(file));
			String str;
			FoodList.clear();
			NonFoodList.clear();
			while((str = fr.readLine())!= null) {
				ArrayList<String> TokenList = new ArrayList<>();
				StringTokenizer stk = new StringTokenizer(str,"/");
				while(stk.hasMoreTokens()) {
					TokenList.add(stk.nextToken());
				}
				if(isFood(TokenList.get(0))) {
					Food theFood = new Food();
					Date ExpDate = new Date();
					theFood.setType(StringType(stringToInt(TokenList.get(0))));
					theFood.setID(TokenList.get(1));
					theFood.setName(TokenList.get(2));
					theFood.setPrice(stringToDouble(TokenList.get(3)));
					ExpDate.setDate(TokenList.get(4));
					theFood.setExpiration(ExpDate);
					theFood.setNum(stringToInt(TokenList.get(5)));
					FoodList.add(theFood);
				}else {
					NonFood theNonFood = new NonFood();
					theNonFood.setType(StringType(stringToInt(TokenList.get(0))));
					theNonFood.setID(TokenList.get(1));
					theNonFood.setName(TokenList.get(2));
					theNonFood.setPrice(stringToDouble(TokenList.get(3)));
					theNonFood.setNum(stringToInt(TokenList.get(5)));
					NonFoodList.add(theNonFood);
				}
			}
			fr.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	// save : FoodList�� NonFoodList�� �ִ� �������� ItemList�� �����Ѵ�.
	public void save() {
		try {
			String path = Management.class.getResource("").getPath();
			path = java.net.URLDecoder.decode(path,"UTF-8");
			File file = new File(path + "../../src/docs/ItemList.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			FoodList.sort(Comparator.reverseOrder());
			for(Food items : FoodList) {
				writer.write(items.toString());
			}
			for(NonFood items : NonFoodList) {
				writer.write(items.toString());
			}
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// addItem : ������ �߰��Ѵ�. ��ǰ�̶�� FoodList��, ���ǰ�̶�� NonFoodList�� �߰��Ѵ�.
	public void addItem(int theType, String theID, String theName, double thePrice, Date theDate) {
		if(isFood(theType)) {
			Food FAdded = new Food();
			FAdded.setType(StringType(theType));
			FAdded.setID(theID);
			FAdded.setName(theName);
			FAdded.setPrice(thePrice);
			FAdded.setExpiration(theDate);
			FoodList.add(FAdded);
		}else {
			NonFood NAdded = new NonFood();
			NAdded.setType(StringType(theType));
			NAdded.setID(theID);
			NAdded.setName(theName);
			NAdded.setPrice(thePrice);
			NonFoodList.add(NAdded);
		}
		System.out.println("Item Added!");
	}
	
	// delItem : ������ �����Ѵ�. ��ǰ�̶�� FoodList��, ���ǰ�̶�� NonFoodList���� �����Ѵ�.
	public void delItem(String theID) {
		if(FindFoodIndex(theID) != -1) {
			FoodList.remove(FindFoodIndex(theID));
		}else if(FindNonFoodIndex(theID) != -1) {
			NonFoodList.remove(FindNonFoodIndex(theID));
		}
	}
	
	// FindFoodIndex : ��ǰ ��Ͽ��� ������ ã�� index�� ��ȯ, ���ٸ� -1 ��ȯ (��ǰ ����)
	public int FindFoodIndex(String ID) {
		int i = 0;
		for(Food f: FoodList) {
			if(f.id.equals(ID)) {
				return i;
			}
			i++;
			}
		return -1;
	}
		
	// FindFoodIndex(Id,Exp) : ��ǰ ��Ͽ��� ���� ���� �� ������ѵ� ���� ������ ã�� index�� ��ȯ, ���ٸ� -1 ��ȯ (��ǰ ����)
	public int FindFoodIndex(String ID, Date Exp) {
		int i = 0;
		for(Food f: FoodList) {
			if(f.id.equals(ID)) {
				if(f.getExpiration().isEqual(Exp)) {
					return i;
				}
				}
			i++;
		}
		return -1;
	}
		
	// FindNonFoodIndex : ���ǰ ��Ͽ��� ������ ã�� index�� ��ȯ, ���ٸ� -1 ��ȯ (���ǰ ����)
	public int FindNonFoodIndex(String ID) {
		int i = 0;
		for(NonFood f: NonFoodList) {
			if(f.id.equals(ID)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	// isFood : ���� �ڵ带 ������� ��ǰ�� ���ǰ�� �����Ѵ�. (���� �ڵ� String��)
	protected boolean isFood(String str) {
		if(str.equals("8") || str.equals("9") || str.equals("10"))
			return false;
		else
			return true;
	}
		
	// isFood : ���� �ڵ带 ������� ��ǰ�� ���ǰ�� �����Ѵ�. (���� �ڵ� int��)
	protected boolean isFood(int str) {
		if(str == 8 || str == 9 || str == 10)
			return false;
		else
			return true;
	}
	
	// 3. ��� ���� Method
	
	// ExpLoad : ��� ����� �����Ѵ�.
    public void ExpLoad() {
		for(Food FL : FoodList) {
			Date exp = FL.getExpiration();
			if(isExpired(exp)) {
				ExpFoodList.add(FL);
			}
		}
    }
	
	// delAllExp : Ư�� ��� ������ ���� ��Ͽ��� �����Ѵ�.
	public void delAllExp() {
		for(Food Item : ExpFoodList) {
			delItem(Item.getID());
		}
	}
	
	// isExpired : ��� ��ǰ���� ����
    public boolean isExpired(Date a) {
		Date now = new Date();
		now.setToday();
		
		if(now.DateCompare(a) == 1) {
			return false;
		}else {
			return true;
		}
	}
    
    
    // ExpFoodKinds : ��� ��Ͽ��� �������� �κ� ����� ��ȯ�Ѵ�.
    public ArrayList<Food> ExpFoodKinds(String Kinds) {
		ArrayList<Food> res = new ArrayList<Food>();

		for(Food FL : ExpFoodList) {
			if(KindsCompare(Kinds,FL.getType())) {
				res.add(FL);
			}
		}
		return res;
	}
	
    // 4. ��Ÿ ������ Method
    
	// doubleToString : double�� ������ String���� ��ȯ
	private static String doubleToString(Double num) {
		String res = "";
		res = Double.toString(num);
		return res;
	}
	
	// intToString : int�� ������ String���� ��ȯ
	private static String intToString(int num) {
		String res = "";
		res = Integer.toString(num);
		return res;
	}
	
	// stringToDouble : String�� double�� ������ ��ȯ
	private static double stringToDouble(String str) {
		double res = 0;
		res = Double.parseDouble(str.trim());
		return res;
	}
	
	// stringToInt : String�� int�� ������ ��ȯ
	private static int stringToInt(String str) {
		int res = 0;
		res = Integer.parseInt(str.trim());
		return res;
	}
	

    // StringType : �з� �ڵ带 ������� ������ ������ ��ȯ�Ѵ�.
	private String StringType(int theType) {
		switch(theType) {
		case 0: return "Milk";
		case 1: return "Freezed";
		case 2: return "Ramen";
		case 3: return "Can";
		case 4: return "Oil";
		case 5: return "Bread";
		case 6: return "Egg";
		case 7: return "Drink";
		case 8: return "Smoke";
		case 9: return "Grocery";
		case 10: return "Others";
		default : return "error";
		}
	}
	
}
