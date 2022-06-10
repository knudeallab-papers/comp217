package Test;
import java.util.ArrayList;
import java.util.Scanner;

import model.Direction;
import model.Location;
import model.Map; 


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		// 주소 알기		
	
		Location start = new Location("경북대학교 정문"); 	
		
		Location end = new Location("신세계 백화점 동대구역");
		
//		 //경유지 - 다른회원 시작위치
//		ArrayList<Location> waypoints = new ArrayList<Location> (); 
//		waypoints.add(new Location("경북대학교 북문"));
//	
		
		//direction
		Direction dir = new Direction(start, end);	
		dir.findDirection(); 
		System.out.println("택시요금"+ dir.getTaxiFare()); 
		System.out.println("이동거리" + dir.getDistance());
		
		//Map.mapService(dir);
		
		
	}

}
