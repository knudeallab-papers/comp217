package Eumm;
/*
 * Mypage의 자손클래스로, 이용자의 마이페이지를 보여줌
 * -> 나의 예약정보 : 전문가들과의 만남을 예약한 정보를 볼 수 있음.
 * -> 리뷰관리 : 리뷰쓰기와 작성한 리뷰를 볼 수 있음  -> WriteReview()호출
 */
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class MypageCustomer extends Mypage {

	String id;
	HashMap<String, Member> infomap;
	public MypageCustomer() {
		// TODO Auto-generated constructor stub
	}

	public MypageCustomer(String id, HashMap<String, Member> infomap) {
		super(id, infomap);
		
		this.id = id;
		this.infomap = infomap;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		String s = e.getActionCommand();
		
	
		if(s.equals("뒤로"))
		{
			 dispose();
        	 new Category(id,infomap);
		}
		else if(s.equals("나의 예약정보"))
		{
			new ReservationInfo(infomap.get(id).getTelnum());
			
		}
		
	}

	@Override
	public void addActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
