/*
 * 전문가들의 개인 정보를 저장하는 클래스
 * 각 분야별로 상속되어 자손클래스를 생성하게 될 부모 클래스
 */
package Eumm;
public class Person {

	private String name;
	private int age;
	private String sex;
	private double like;
	private String telnum;
	private String email;
	private double price;
	private String type;
	private String addinfo;
	public Person() 
	{
		name = "no name";
		age = 0;
		sex = "no sex";
		like =0;
		telnum = "no telnum";
		email = "no email";
		price = 0;
		type = "no type";
		addinfo = "no addinfo";
	}
	public Person(String name, int age,String sex, double like, String telnum,String email, double price,String type,String addinfo)
	{
		this.name= name;
		this.age= age;
		this.sex =sex;
		this.like =like;
		this.telnum = telnum;
		this.email = email;
		this.price = price;
		this.type = type;
		this.addinfo = addinfo;
	}
	
	public String getType() {
		return type;
	}
	public String getAddinfo() {
		return addinfo;
	}
	public void setAddinfo(String addinfo) {
		this.addinfo = addinfo;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public double getLike() {
		return like;
	}
	public void setLike(double like) {
		this.like = like;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
