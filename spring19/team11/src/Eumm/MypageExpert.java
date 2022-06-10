package Eumm;
/*
 * Mypage의 자손클래스로, 전문가의 마이페이지를 보여줌
 * -> 나의 예약정보 : 사용자들과의 만남을 예약한 정보를 볼 수 있음.
 * -> 리뷰관리 : 사용자들이 작성한 리뷰를 볼 수 있고, 답글을 달 수 있음 -> ReadReview()호출
 */
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class MypageExpert extends Mypage {

	String id;
	HashMap<String, Member> infomap;
	public MypageExpert() {
		// TODO Auto-generated constructor stub
	}

	public MypageExpert(String id, HashMap<String, Member> infomap) {
		super(id, infomap);
		this.id = id;
		this.infomap = infomap;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		
		if(s.equals("뒤로"))
		{
			 dispose();
        	 new LogIn();
		}
		else if(s.equals("나의 예약정보"))
		{
			new ReservationE(id,infomap);
		}
		
	}

	@Override
	public void addActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
