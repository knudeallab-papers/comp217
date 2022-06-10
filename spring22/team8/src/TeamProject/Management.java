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
	 * 생성자 : FoodList/NonFoodList는 현재 물류를 저장하는 배열이고,
	 *        OrderFoodList/OrderNonFoodList는 발주 목록을 저장하는 배열,
	 *        TempOrderList는 발주 확인목록을 저장하는 배열,
	 *        ExpFoodList는 폐기 목록을 저장하는 배열이다.
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
	 * Method 구성
	 * 1. 발주관련 (Order.... )
	 * 2. 물류관련 (Item... / Food.... / NonFood... )
	 * 3. 페기관련 (Exp... )
	 * 4. 기타
	 */
	
	//1. 발주 관련 Method
	
	//Order : 발주 목록을 Order.txt에서 가져와 각각 OrderFoodList/ OrderNonFoodList에 나눠 넣는다.
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
	
	//OrderItem : 물품을 발주할 때 쓰는 Method로 OrderList에 있던 물류에 유통기한과 개수를 받아 FoodList나 NonFoodList로 넣는다.
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
	
	// TempOrderToReal : 발주 확인목록에 있는 물류들을 발주할 때 사용한다.
	public void TempOrderToReal() {
		for(ArrayList<String> data : TempOrderList) {
			String theID = data.get(0);
			int theNum = stringToInt(data.get(3));
			OrderItem(theID,theNum);
		}
	}
	
	// TempOrderFindIndex : 발주 확인목록에서 물류를 찾아서 그 index값을 반환. 없다면 -1을 반환
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
	
	// AddTempOrderItem : 발주 확인목록에 물류를 추가한다.
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
	
	// DelTempOrderItem : 발주 확인목록에 물류를 제거한다.
	public void DelTempOrderItem(String[] data) {
		int index = TempOrderFindIndex(data);
		TempOrderList.remove(index);
	}
	
	// getTempPrice : 지금까지 발주한 물류들의 총 가격을 반환한다.
	public String getTempPrice() {
		double Total = 0;
		for(ArrayList<String> data : TempOrderList) {
			double price = stringToDouble(data.get(2));
			double num = stringToDouble(data.get(3));
			Total += price * num;
		}
		return doubleToString(Total);
	}
	
	// CalExp : 물류 Type에 따라 유통기한을 계산하여 반환한다. 발주 목록에서 물류 목록으로 옮길 때 사용.
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
	
	// KindsCompare : 물품들의 type을 통해 무슨 종류인지 파악한다. (ex. type:Milk -> Kind:신선식품) 분류를 위해 필요함.
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
	
	// FoodKinds : 전체 물류를 종류별로 나눈 부분 물류를 반환한다. (음식 전용) 종류별 필터에 필요함.
	public ArrayList<Food> FoodKinds(String Kinds) {
		ArrayList<Food> res = new ArrayList<Food>();

		for(Food FL : FoodList) {
			if(KindsCompare(Kinds,FL.getType())) {
				res.add(FL);
			}
		}
		return res;
	}
	
	// NonFoodKinds : 전체 물류를 종류별로 나눈 부분 물류를 반환한다. (비음식 전용) 종류별 필터에 필요함.
	public ArrayList<NonFood> NonFoodKinds(String Kinds) {
		ArrayList<NonFood> res = new ArrayList<NonFood>();
		
		for(NonFood NFL : NonFoodList) {
			if(KindsCompare(Kinds,NFL.getType())) {
				res.add(NFL);
			}
		}
		return res;
	}
	
	// OrderList : 발주 한 목록들을 모아 반환한다. 발주 확인목록에 필요.
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
	
	// isFoodOrder : 이 ID가 식품 발주 목록에 있는 지 확인
	public boolean isFoodOrder(String ID) {
		if(OrderFindFoodIndex(ID) != -1) return true;
		else return false;
	}
	
	// isNonFoodOrder : 이 ID가 비식품 발주 목록에 있는 지 확인
	public boolean isNonFoodOrder(String ID) {
		if(OrderFindNonFoodIndex(ID) != -1) return true;
		else return false;
	}
	
	// OrderFindFoodIndex : 발주 목록에서 물류를 찾아 index를 반환, 없다면 -1 반환 (식품 전용)
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
	
	// OrderFindNonFoodIndex : 발주 목록에서 물류를 찾아 index를 반환, 없다면 -1 반환 (비식품 전용)
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
	
	// 2. 물류 관련 Method
	
	// load : ItemList에 저장되어 있는 물류 데이터들을 FoodList와 NonFoodList에 넣는다.
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
	
	// save : FoodList와 NonFoodList에 있는 정보들을 ItemList에 저장한다.
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
	
	// addItem : 물류를 추가한다. 식품이라면 FoodList에, 비식품이라면 NonFoodList에 추가한다.
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
	
	// delItem : 물류를 제거한다. 식품이라면 FoodList에, 비식품이라면 NonFoodList에서 제거한다.
	public void delItem(String theID) {
		if(FindFoodIndex(theID) != -1) {
			FoodList.remove(FindFoodIndex(theID));
		}else if(FindNonFoodIndex(theID) != -1) {
			NonFoodList.remove(FindNonFoodIndex(theID));
		}
	}
	
	// FindFoodIndex : 식품 목록에서 물류를 찾아 index를 반환, 없다면 -1 반환 (식품 전용)
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
		
	// FindFoodIndex(Id,Exp) : 식품 목록에서 같은 물류 중 유통기한도 같은 물류를 찾아 index를 반환, 없다면 -1 반환 (식품 전용)
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
		
	// FindNonFoodIndex : 비식품 목록에서 물류를 찾아 index를 반환, 없다면 -1 반환 (비식품 전용)
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
	
	// isFood : 물류 코드를 기반으로 식품과 비식품을 구별한다. (물류 코드 String형)
	protected boolean isFood(String str) {
		if(str.equals("8") || str.equals("9") || str.equals("10"))
			return false;
		else
			return true;
	}
		
	// isFood : 물류 코드를 기반으롤 식품과 비식품을 구별한다. (물류 코드 int형)
	protected boolean isFood(int str) {
		if(str == 8 || str == 9 || str == 10)
			return false;
		else
			return true;
	}
	
	// 3. 폐기 관련 Method
	
	// ExpLoad : 폐기 목록을 생성한다.
    public void ExpLoad() {
		for(Food FL : FoodList) {
			Date exp = FL.getExpiration();
			if(isExpired(exp)) {
				ExpFoodList.add(FL);
			}
		}
    }
	
	// delAllExp : 특정 폐기 물류를 물류 목록에서 제거한다.
	public void delAllExp() {
		for(Food Item : ExpFoodList) {
			delItem(Item.getID());
		}
	}
	
	// isExpired : 폐기 물품인지 구별
    public boolean isExpired(Date a) {
		Date now = new Date();
		now.setToday();
		
		if(now.DateCompare(a) == 1) {
			return false;
		}else {
			return true;
		}
	}
    
    
    // ExpFoodKinds : 폐기 목록에서 종류별로 부분 목록을 반환한다.
    public ArrayList<Food> ExpFoodKinds(String Kinds) {
		ArrayList<Food> res = new ArrayList<Food>();

		for(Food FL : ExpFoodList) {
			if(KindsCompare(Kinds,FL.getType())) {
				res.add(FL);
			}
		}
		return res;
	}
	
    // 4. 기타 유용한 Method
    
	// doubleToString : double형 변수를 String으로 변환
	private static String doubleToString(Double num) {
		String res = "";
		res = Double.toString(num);
		return res;
	}
	
	// intToString : int형 변수를 String으로 변환
	private static String intToString(int num) {
		String res = "";
		res = Integer.toString(num);
		return res;
	}
	
	// stringToDouble : String을 double형 변수로 변환
	private static double stringToDouble(String str) {
		double res = 0;
		res = Double.parseDouble(str.trim());
		return res;
	}
	
	// stringToInt : String을 int형 변수로 변환
	private static int stringToInt(String str) {
		int res = 0;
		res = Integer.parseInt(str.trim());
		return res;
	}
	

    // StringType : 분류 코드를 기반으로 물류의 종류를 반환한다.
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
