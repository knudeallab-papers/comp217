package Team_project;

public interface Category_operation {

	public String[] get_names();		// ��� ��ǰ�� ������
	public int get_Price(String key); // key������ ��ǰ�� ������ ã�� ��ȯ��
	public void inventory_control(String key); // �ֹ��� ��ǰ�� ��� ���¸� �ٲ���
	public void add_stock();
}
