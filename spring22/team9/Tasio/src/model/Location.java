package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Location {
	private String address;
	private double latitude;
	private double longitude;
	
	public Location() {}

	public Location(String address) {
		super();
		this.address = address;
		geocoding(address); 
	}
	
	public Location(double otherLatitude, double otherLongitude) {
		this.address = ""; 
		this.latitude = otherLatitude;
		this.longitude = otherLongitude; 
	}

	public Location(String address, double latitude, double longitude) {
		super();
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "Location [address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	// geocoding parser json -> obj
	public void geocoding(String otherAddress) {
		String apiURL = "https://maps.googleapis.com/maps/api/geocode/json?address=";
		String apiKey = "AIzaSyD610ReaBADiKwHyYZ_8HzVcjQB6XfRXYQ"; 
		
		try {
			String addr = URLEncoder.encode(otherAddress, "UTF-8");
			String reqURL = apiURL + addr + "&language=ko&key=" + apiKey;
			
			URL url = new URL(reqURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET"); 
			
			//System.out.println(url);
			
			BufferedReader br; 			
			int responseCode = con.getResponseCode(); // 200 시 성공		
			//System.out.println(responseCode);
			if(responseCode == 200) { // 성공하면 json 형태로 날아오고 한줄 한줄 읽어야 하기 때문에 BufferedReader 사용 
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8")); 
				
			}else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream())); 
			}
			
			String line; 
			StringBuffer response = new StringBuffer(); //JSON 저장 
			while((line = br.readLine()) != null) {
				response.append(line); 
			}
			br.close();
			
			
			JSONTokener tokener = new JSONTokener(response.toString());
			JSONObject object = new JSONObject(tokener);
			
			//System.out.println(object.toString(2));
			
			JSONArray arr = object.getJSONArray("results");
			
			JSONObject tmp = (JSONObject)arr.get(0);
			
			// 정확한 주소
			String adr = (String)tmp.get("formatted_address"); 			
			
			//location
			JSONObject geo = (JSONObject) tmp.get("geometry"); 
			JSONObject loc = (JSONObject) geo.get("location");
			
			// class 값 입력
			setAddress(adr);
			setLatitude((double)loc.get("lat"));
			setLongitude((double)loc.get("lng"));
			
			} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
