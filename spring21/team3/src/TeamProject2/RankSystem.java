package TeamProject2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RankSystem {
	private String Name;
	private int Score;
	private String Date;
	public int Rank;
	String fileName = "RankSystem.txt";
	
	RankSystem(){
		Name="";
		Score=0;
		Date=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		Rank=0;
	}
	
	RankSystem(String rank, String name, String score, String date){
		setName(name);
		setScore(score);
		setDate(date);
		setRank(rank);
	}
	
	RankSystem(RankSystem RankSystem){
		setName(RankSystem.getName());
		setScore(Integer.toString(RankSystem.getScore()));
		setDate(RankSystem.getDate());
		setRank(Integer.toString(RankSystem.getRank()));
	}
	
	public void setName(String name) {
		this.Name=name;
	}
	public void setScore(String score) {
		this.Score=Integer.parseInt(score);
	}
	public void setDate(String date) {
		this.Date=date;
	}
	public void setRank(String rank) {
		this.Rank=Integer.parseInt(rank);
	}
	
	public String getName() {
		return Name;
	}
	public int getScore() {
		return Score;
	}
	public String getDate() {
		return Date;
	}
	public int getRank() {
		return Rank;
	}
	
	public String toString() {
		return getRank() + " " + getName()+" "+getScore()+" "+getDate(); 
	}
	
}
