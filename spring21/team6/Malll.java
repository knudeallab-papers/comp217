package teamproject;

public class Malll {
	private int location;//말 위치
	private int tryNumber;// 모나 윷, 잡았을때 한번 더 던질 수 있도록 카운트
	private int num;//낫는지 안낫는지 확인
	
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
	public int move() {//말 이동 
		if(location == 5)	{
			System.out.println("---");
			location=60;
			//모 나왔을때 옆으로 가는거
		}
		else if(location==10) {
			System.out.println("---");
			location=40;
	//		location=location+a.yutDun();
			//두번 쨰 꺽는 구간 
		}
		else if(location == 63) {
			System.out.println("---");
			location-=20;
		//	location+=a.yutDun();
		}	//중앙에 도착했을 때 꺽기
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
			//저런경우 없을때 던지기
		}*/
		else if(location>46) {
			//complite
		}
		if(num==0) {
			if(20<location&&location<40) {
				//ComplNanmall();//정석으로 돌아서 클리어
				//num++;
			}if(46<location&&location<60) {
				//super.ComplNanmall();//대각선 방향으로 들어와서 클리어
				//num++;
			}if(71<location) {
				//super.ComplNanmall();
				//num++;//처음 모 걸린 후 크게돌아 클리어
			}
		}
		return location;
	}
	public int preview(int a) {
		int lc2 = location + a;
		if(lc2 == 5)	{
			//System.out.println("---");
			lc2=60;
			//모 나왔을때 옆으로 가는거
		}
		else if(lc2==10) {
			//System.out.println("---");
			lc2=40;
	//		location=location+a.yutDun();
			//두번 쨰 꺽는 구간 
		}
		else if(lc2 == 63) {
			//System.out.println("---");
			lc2-=20;
		//	location+=a.yutDun();
		}	//중앙에 도착했을 때 꺽기
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
			//저런경우 없을때 던지기
		}*/
		else if(location>46) {
			//complite
		}
		
		return lc2;
	}
}
