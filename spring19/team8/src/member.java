import java.awt.Label;
import java.io.Serializable;
import java.util.ArrayList;

public class member implements Serializable {
//   int[] index = new int[2]; //�� ���� ������ ����� index��
//   ArrayList<String> friend = new ArrayList<String>();
//	Label l1= new Label("�̸�");
//    Label l2 = new Label("���̵�");
//    Label l3= new Label("�н�����");
//    Label l4 = new Label("�ּ�");
//    Label l6 = new Label("�й�");
//    Label l7 = new Label("����");
	private String id;
	private String password;
	private String name;
	private String address;
	private String sid;
	private String major;
	//int[] time = new int[4];
	private int[] list = new int[2]; //�� ���� ������ ����� index��
	int index=0;
	public void setIndex(int plus) {
		if(index==2) {
			System.out.println("�� á��");
		}
		else {
			list[index++]=plus;
		}
	}
	public int[] getIndex() {
		return list;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSid() {
		return sid;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getMajor() {
		return major;
	}
}