package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;

public class Map {
	
	private String imgFileName;
	
	
	public Map() {};
	

	public String getImgFileName() {
		return imgFileName;
	}


	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}


	// 지도 이미지 생성 메서드
	public void mapService(Direction otherDirection) {
		String apiurl = "https://maps.googleapis.com/maps/api/staticmap"
				+ "?size=230x230&maptype=roadmap&language=ko";
		String apiKey = "AIzaSyD610ReaBADiKwHyYZ_8HzVcjQB6XfRXYQ";
		
		try {
			// marker
			//start
			String startMark = "&markers=size:mid%7Ccolor:red%7Clabel:S%7C" + otherDirection.getStart().getLatitude() + ","+otherDirection.getStart().getLongitude();
			String endMark = "&markers=size:mid%7Ccolor:red%7Clabel:E%7C" + otherDirection.getEnd().getLatitude() + ","+otherDirection.getEnd().getLongitude();
			
			String waypointsMark = "";
			
			ArrayList<Location> waypoints = otherDirection.getWaypoint(); 
			if(waypoints != null) {
				for(int i = 0; i < waypoints.size(); i++) {
					waypointsMark += "&markers=size:mid%7Ccolor:blue%7Clabel:P%7C" + waypoints.get(i).getLatitude() + ","+ waypoints.get(i).getLongitude();
				}
			}

			// 경로
			StringBuilder sb = new StringBuilder(); 			
			String path = "&path=color:orange|weight:5";
			sb.append(path);
			ArrayList<Location> pathpoints = otherDirection.getPath(); 
			for(int i = 0; i < pathpoints.size(); i++) {
				sb.append("|"+pathpoints.get(i).getLatitude()+","+pathpoints.get(i).getLongitude()); 						
			}
			
			//url
			String reqURL = apiurl + startMark+ endMark+waypointsMark + sb.toString() + "&key=" + apiKey;
			
			//System.out.println(reqURL);
			
			// 요청
			URL url = new URL(reqURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			con.setRequestMethod("GET");
			
			int responseCode = con.getResponseCode(); 
			
			BufferedReader br;
			
			if(responseCode == 200) {
				InputStream is = con.getInputStream(); 
				int read = 0;
				byte[] bytes = new byte[1024]; 
				
				//랜덤한 이름으로 파일 생성
				String tempname = Long.valueOf(new Date().getTime()).toString(); 
				File f = new File(tempname+".jpg"); 
				setImgFileName(f.getName()); 
				f.createNewFile(); 
				
				OutputStream outputStream = new FileOutputStream(f); 
				while((read = is.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read); 
				}
				is.close(); 
				
			}else { // error
				br = new BufferedReader(new InputStreamReader(con.getErrorStream())); 
				String inputLine;
				StringBuffer response = new StringBuffer(); 
				while((inputLine = br.readLine()) != null) {
					response.append(inputLine); 
				}
				br.close();
				System.out.println(response.toString()); 
				
				
			}
			
		}catch(Exception e) {
			System.out.println(e); 
		}
	
		
	}
}
