package dataClass;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;


public class member implements Serializable{
	private String name;
	private String year,month,day;
	private String sex;
	private String address;
	private String Tel1, Tel2, Tel3;
	private String mail;
	private String img_address;
	private ArrayList<book> lentList = new ArrayList<book>();
	boolean overdue;
	
	public boolean equal(member inputMember) {
		if(inputMember.getName().equals(name)&&
		inputMember.getPhoneNumber().equals(getPhoneNumber())) {		   
			return true; 
		}
		return false;
	}
	public Vector<Object> getall() { // 제네릭 설정
		Vector<Object> mylist = new Vector<Object>();
		mylist.add(name);
		mylist.add(Tel1+"-"+Tel2+"-"+Tel3);
		mylist.add(overdue);
		return mylist;
	}
	
	public void setName(String inputName) {
		name = inputName;
	}
	
	public void setYear(String inputYear)
	{
		year= inputYear;
	}
	public void setMonth(String inputMonth)
	{
		month = inputMonth;
	}
	public void setDay(String inputDay)
	{
		day = inputDay;
	}
	public void setSex(String inputSex)
	{
		sex = inputSex;
	}
	public void setAderess(String inputAddress)
	{
		address = inputAddress;
	}
	public void setTel1(String inputTel1)
	{
		Tel1 = inputTel1;
	}
	public void setTel2(String inputTel2)
	{
		Tel2 = inputTel2;
	}
	public void setTel3(String inputTel3)
	{
		Tel3 = inputTel3;
	}
	public void setMail(String inputMail)
	{
		mail = inputMail;
	}
	public void setLentList(ArrayList<book> input)
	{
		lentList = input;
	}
	public void setImgAddress(String inputImgAddress)
	{
		img_address = inputImgAddress;
	}
	public void setOverdue(boolean Chk)
	{
		overdue = Chk;
	}
	public String getName()
	{
		return name;
	}
	public String getYear()
	{
		return year;
	}
	public String getMonth()
	{
		return month;
	}
	public String getDay()
	{
		return day;
	}
	public String getSex()
	{
		return sex;
	}
	public String getAddress()
	{
		return address;
	}
	public String getTel1() {
		return Tel1;
	}
	public String getTel2() {
		return Tel2;
	}
	public String getTel3() {
		return Tel3;
	}
	public String getPhoneNumber() {
		return Tel1+"-"+Tel2+"-"+Tel3;
	}
	public String getMail() {
		return mail;
	}
	public String getImgAddress()
	{
		return img_address;
	}
	public ArrayList<book> getLentList()
	{
		return lentList;
	}
	public boolean getOverdue()
	{
		return overdue;
	}
	
	
	public static ImageIcon makeImage(String img_adrr)
	{
		ImageIcon imgicon = new ImageIcon(img_adrr);
		return imgicon;
	}
	
	
	
}
