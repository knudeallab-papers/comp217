package team_project;

import java.io.Serializable;
import java.util.ArrayList;

public class petData implements Serializable {

	private String nameField;
	private String speciesField;
	private String ageField;
	private String animalFilePath;
	private ArrayList<schedule> scheduleList;
	private ArrayList<schedule> mission = new ArrayList<schedule>(2);
	private int happiness;

	public petData() {
		nameField = "name";
		speciesField = "";
		ageField = "";
		animalFilePath = "";
		scheduleList = null;
		happiness = 0;
	}

	public petData(petData p) {
		this.nameField = p.getName();
		this.speciesField = p.getSpecies();
		this.ageField = p.ageField;
		this.animalFilePath = p.animalFilePath;
		this.scheduleList = (ArrayList<schedule>) p.scheduleList.clone();
		this.happiness = p.happiness;
		this.mission = p.getMission();

	}

	public petData(String nameField, String speciesField, String ageField, ArrayList<schedule> newSchedule, String path,
			int happiness) {
		this.nameField = nameField;
		this.speciesField = speciesField;
		this.ageField = ageField;
		scheduleList = (ArrayList<schedule>) newSchedule.clone();
		animalFilePath = path;
		this.happiness = happiness;
	}

	public void updateMission() {
		mission = new ArrayList<schedule>(2);
		mission.add(new schedule(makeMission.make_Mission(), 1, 5));
		mission.add(new schedule(makeMission.make_distance(ageField), 1, 5));
		mission.get(0).setIsMission(true);
		mission.get(1).setIsMission(true);
	}

	public void setMissionList(ArrayList<schedule> n) {
		mission = (ArrayList<schedule>) n.clone();
	}

	public ArrayList<schedule> getMission() {
		return mission;
	}

	public String getName() {
		return nameField;
	}

	public String getAge() {
		return ageField;
	}

	public String getSpecies() {
		return speciesField;
	}

	public String getFilePath() {
		return animalFilePath;
	}

	public ArrayList<schedule> getSchedule() {
		return scheduleList;
	}

	public int getHappiness() {
		return happiness;
	}

	public void setFilePath(String path) {
		animalFilePath = path;
	}

	public void setName(String otherName) {
		this.nameField = otherName;
	}

	public void setAge(String otherAge) {
		this.ageField = otherAge;
	}

	public void setSpecies(String otherSpecies) {
		this.speciesField = otherSpecies;
	}

	public void setScheduleList(ArrayList<schedule> newSchedule) {
		scheduleList = (ArrayList<schedule>) newSchedule.clone();
	}

	public void setHappiness(int otherHappiness) {
		this.happiness = otherHappiness;
	}

	public void printPetData() {
		System.out.println(nameField);
		System.out.println(speciesField);
		System.out.println(ageField);
		System.out.println("행복도: " + happiness);
		for (schedule a : scheduleList) {
			a.printSchedule();
		}

		System.out.println();
	}

	public void updateHappiness() {
		for (schedule i : scheduleList) {
			if (i.getIsDone()) {
				happiness += i.getImportance();
			} else
				happiness -= i.getImportance();
		}
	}

}
