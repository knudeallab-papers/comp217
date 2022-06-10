import java.awt.Label;
import java.io.Serializable;
import java.util.ArrayList;

public class member implements Serializable {
//   int[] index = new int[2]; //몇 번쨰 수업을 듣는지 index값
//   ArrayList<String> friend = new ArrayList<String>();
//	Label l1= new Label("이름");
//    Label l2 = new Label("아이디");
//    Label l3= new Label("패스워드");
//    Label l4 = new Label("주소");
//    Label l6 = new Label("학번");
//    Label l7 = new Label("전공");
	private String id;
	private String password;
	private String name;
	private String address;
	private String sid;
	private String major;
	//int[] time = new int[4];
	private int[] list = new int[2]; //몇 번쨰 수업을 듣는지 index값
	int index=0;
	public void setIndex(int plus) {
		if(index==2) {
			System.out.println("꽉 찼음");
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