package GameProject;

import java.io.Serializable;

public class EntrantData implements Serializable{
	private String name;
	private String country;
	private String city;
	private String sex;
	
	public EntrantData(String name, String country, String city, String sex) {
		this.name = name;
		this.country = country;
		this.city = city;
		this.sex = sex;
	}
	
	public EntrantData(EntrantData data) {
		this(data.name, data.country, data.city, data.sex);
	}

	public String getName() {
		return this.name;
	}
	public String getCountry() {
		return this.country;
	}
	public String getCity() {
		return this.city;
	}
	public String getSex() {
		return this.sex;
	}
}
