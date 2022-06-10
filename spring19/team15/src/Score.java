import java.io.Serializable;

public class Score implements Serializable{
	String name;
	int score;
	int time;
	
	public String toString()
	{
		return "Name : "+ name + " Score : "+ score +"  Time : " + time;
	}
}
