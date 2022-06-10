package Team_project;

public interface Category_operation {

	public String[] get_names();		// 모든 상품을 보여줌
	public int get_Price(String key); // key값으로 상품의 가격을 찾아 반환함
	public void inventory_control(String key); // 주문된 상품의 재고 상태를 바꿔줌
	public void add_stock();
}
