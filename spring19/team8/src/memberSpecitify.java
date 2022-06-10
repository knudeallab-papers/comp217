import java.util.ArrayList;

public class memberSpecitify extends member {
//	private int[] list = new int[2]; //몇 번쨰 수업을 듣는지 index값
	private ArrayList<String> friend = new ArrayList<String>();
	
	public void addFriend(String friend) {
		this.friend.add(friend);
	}
	
	public ArrayList<String> getFriend(){
		return friend;
	}
}
