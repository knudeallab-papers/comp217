package GameProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import GameProject.GameObject.Type;

public class EntrantsDataMgr {
	private String filePath = "./GameData/entrants_data.csv";
	public boolean CriminalIn = false;
	private int criminalindex;
	public ArrayList<EntrantData> entrants = new ArrayList<EntrantData>();
	
	public EntrantsDataMgr() {
		LoadData();
	}
	
	public void LoadData() {
		System.out.println("LoadData");
		Scanner fin = new Scanner(getClass().getClassLoader().getResourceAsStream("entrants_data.csv"));
		
		ReadCSV(fin);
		for(EntrantData en : entrants) {
			System.out.println(en.getName());
		}
	}
	
	public void ReadCSV(Scanner scnr) {
		scnr.nextLine();
		while(scnr.hasNextLine()) {
			String line = scnr.nextLine();
			
			Scanner rowScanner = new Scanner(line);
			rowScanner.useDelimiter(",");
			
			String name = rowScanner.next();
			String country = rowScanner.next();
			String city = rowScanner.next();
			String sex = rowScanner.next();
			
			EntrantData newEntrant = new EntrantData(name, country, city, sex);
			entrants.add(newEntrant);
		}
	}
	
	public EntrantData getRandomEntrant() {
		int randomNumber = (int)(Math.random()*entrants.size());
		while(randomNumber == 10)
			randomNumber = (int)(Math.random()*entrants.size());
		return entrants.get(randomNumber);
	}
	
	public EntrantData getRandomPass(EntrantData origindata) {//mod
		EntrantData pass = origindata;
		if(Math.random() >= 0.8) {
			while(pass.equals(origindata)) {
				pass = getRandomEntrant();
			}
		}
		return pass;
	}
}
