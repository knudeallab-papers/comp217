package teamproject;

public class Malll {
	private int location;//�� ��ġ
	private int tryNumber;// �� ��, ������� �ѹ� �� ���� �� �ֵ��� ī��Ʈ
	private int num;//������ �ȳ����� Ȯ��
	
	public Malll() {
		location = 0;
		tryNumber = 0;
		num = 0;
	}
	
	public int getTryNumber() {
		return tryNumber;
	}
	public int getLocation() {
		return location;
	}
	public int getNum() {
		return num;
	}
	public void setTryNumber(int a) {
		tryNumber = a;
	}
	public void goStart() {
		location = 0;
	}
	public void setLocation(int a) {
		location += a;
		location = move();
	}
	public void setNum(int a) {
		num = a;
	}
	public int move() {//�� �̵� 
		if(location == 5)	{
			System.out.println("---");
			location=60;
			//�� �������� ������ ���°�
		}
		else if(location==10) {
			System.out.println("---");
			location=40;
	//		location=location+a.yutDun();
			//�ι� �� ���� ���� 
		}
		else if(location == 63) {
			System.out.println("---");
			location-=20;
		//	location+=a.yutDun();
		}	//�߾ӿ� �������� �� ����
		else if(location>65) {
			System.out.println("---");
			location-=51;
		}
		else if(location==59) {
			System.out.println("---");
			location = 4;
		}
		else if(location==46) {
			System.out.println("---");
			location=0;
		}
		/*else if(num==0){
			//location+=a.yutDun();
			//������� ������ ������
		}*/
		else if(location>46) {
			//complite
		}
		if(num==0) {
			if(20<location&&location<40) {
				//ComplNanmall();//�������� ���Ƽ� Ŭ����
				//num++;
			}if(46<location&&location<60) {
				//super.ComplNanmall();//�밢�� �������� ���ͼ� Ŭ����
				//num++;
			}if(71<location) {
				//super.ComplNanmall();
				//num++;//ó�� �� �ɸ� �� ũ�Ե��� Ŭ����
			}
		}
		return location;
	}
	public int preview(int a) {
		int lc2 = location + a;
		if(lc2 == 5)	{
			//System.out.println("---");
			lc2=60;
			//�� �������� ������ ���°�
		}
		else if(lc2==10) {
			//System.out.println("---");
			lc2=40;
	//		location=location+a.yutDun();
			//�ι� �� ���� ���� 
		}
		else if(lc2 == 63) {
			//System.out.println("---");
			lc2-=20;
		//	location+=a.yutDun();
		}	//�߾ӿ� �������� �� ����
		else if(lc2>65) {
			//System.out.println("---");
			lc2-=51;
		}
		else if(lc2==59) {
			//System.out.println("---");
			lc2 = 4;
		}
		else if(lc2==46) {
			//System.out.println("---");
			lc2=0;
		}
		/*else if(num==0){
			//location+=a.yutDun();
			//������� ������ ������
		}*/
		else if(location>46) {
			//complite
		}
		
		return lc2;
	}
}
