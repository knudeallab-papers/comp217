package Eumm;
/*
 * Mypage�� �ڼ�Ŭ������, �̿����� ������������ ������
 * -> ���� �������� : ����������� ������ ������ ������ �� �� ����.
 * -> ������� : ���侲��� �ۼ��� ���並 �� �� ����  -> WriteReview()ȣ��
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
		
	
		if(s.equals("�ڷ�"))
		{
			 dispose();
        	 new Category(id,infomap);
		}
		else if(s.equals("���� ��������"))
		{
			new ReservationInfo(infomap.get(id).getTelnum());
			
		}
		
	}

	@Override
	public void addActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
