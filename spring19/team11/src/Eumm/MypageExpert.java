package Eumm;
/*
 * Mypage�� �ڼ�Ŭ������, �������� ������������ ������
 * -> ���� �������� : ����ڵ���� ������ ������ ������ �� �� ����.
 * -> ������� : ����ڵ��� �ۼ��� ���並 �� �� �ְ�, ����� �� �� ���� -> ReadReview()ȣ��
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
		
		if(s.equals("�ڷ�"))
		{
			 dispose();
        	 new LogIn();
		}
		else if(s.equals("���� ��������"))
		{
			new ReservationE(id,infomap);
		}
		
	}

	@Override
	public void addActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
