package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Direction {
	private Location start; // 시작지점 경도 위도 저장
	private Location end; // 끝지점 경도 위도 저장
	private ArrayList<Location> waypoint; // 경유지 경도 위도 저장
	private ArrayList<Location> path; // 경로 경도 위도 저장
	private int taxiFare; 
	private int distance;

	public Direction() {}; 	
	
	public Direction(Location start, Location end) {
		super();
		this.start = start;
		this.end = end;
		this.waypoint = null;
		this.path = null;
		this.taxiFare = 0;
		this.distance = 0;
	}

	
	public Direction(Location start, Location end, ArrayList<Location> waypoint) {
		super();
		this.start = start;
		this.end = end;
		this.waypoint = waypoint;
		this.path = null;
		this.taxiFare = 0;
		this.distance = 0;
	}



	public Location getStart() {
		return start;
	}


	public void setStart(Location start) {
		this.start = start;
	}
	
	public ArrayList<Location> getPath() {
		return path;
	}

	public void setPath(ArrayList<Location> path) {
		this.path= path;
	}
	
	
	public int getTaxiFare() {
		return taxiFare;
	}
	
	public void setTaxiFare(int taxiFare) {
		this.taxiFare = taxiFare;
	}

	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public Location getEnd() {
		return end;
	}
	
	public void setEnd(Location end) {
		this.end = end;
	}
	
	public ArrayList<Location> getWaypoint() {
		return waypoint;
	}
	
	public void setWaypoint(ArrayList<Location> waypoint) {
		this.waypoint = waypoint;
	}
	
	// method

	public void findDirection() {
		String apiURL="https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?"; 
		String client_id="8rxe3ob7je";
		String client_secret="fdM3vLbqPC1k4Gd35zazHyZnTsytiEjiygTA1eCj";
		
		try {
			//URL 완성
			//출발지, 목적지
			String reqURL = apiURL;
			
			String startAddr = start.getLongitude() + "," + start.getLatitude(); // 경도 위도
			String endAddr = end.getLongitude() + "," + end.getLatitude();

			reqURL += "start="+startAddr;
			reqURL += "&goal="+endAddr;		
			
			if(waypoint != null) {
				reqURL += "&waypoints="; 
				for(int i  =0; i < waypoint.size(); i++) {
					String waypointAddr = waypoint.get(i).getLongitude() + ","+waypoint.get(i).getLatitude();
					if(i == waypoint.size() - 1) {
						reqURL += waypointAddr;
					}else {
						reqURL += waypointAddr + "|";
					}
				}
			}
			
			reqURL += "&option=trafast";
			
			
			
			// request
			URL url = new URL(reqURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection(); 
			con.setRequestMethod("GET");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", client_id);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", client_secret);
			
			
			//response
			BufferedReader br;
			int responseCode = con.getResponseCode(); 
			
			
			if(responseCode == 200) { // 성공
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8")); 				
			}else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream())); 
			}
			
			String line;
			StringBuffer res = new StringBuffer(); // JSON 저장
			while((line = br.readLine()) != null) {
				res.append(line); 
			}
			br.close(); 
			
			// JSON parsing
			
			JSONTokener tokener = new JSONTokener(res.toString()); 
			JSONObject object = new JSONObject(tokener); 
			
			//System.out.println(object.toString(2));
			
			JSONObject routeObj = (JSONObject)object.get("route");
			
			JSONArray trafastArray = (JSONArray)routeObj.get("trafast"); 	
			
			JSONObject trafastObj = (JSONObject)trafastArray.get(0);
			
			//System.out.println(trafastObj.toString(2));
			
			// summary 파싱 데이터 - taxiFare
			JSONObject summaryObj = (JSONObject)trafastObj.get("summary"); 
			setTaxiFare((int)summaryObj.get("taxiFare")); 
			setDistance((int)summaryObj.get("distance")); 
			
			//path 얻기
			JSONArray pathArray = (JSONArray)trafastObj.get("path"); 
			
			//ArrayList에 저장			
			ArrayList<Location> tmpPath = new ArrayList<Location>(); 
			tmpPath.add(this.start); 
			for(int i = 0; i < pathArray.length(); i++){
				JSONArray tmpArr = (JSONArray)pathArray.get(i);
				tmpPath.add(new Location(tmpArr.getDouble(1), tmpArr.getDouble(0)));
			}
			tmpPath.add(this.end); 
			
			setPath(tmpPath); // class에  저장

			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
