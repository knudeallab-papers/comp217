import java.awt.Point;

public class Missile{ // 미사일 위치 파악 및 이동을 위한 클래스 추가 

	int x; //미사일 좌표 변수
	int y;
	int speed;
	
	int who;	//미사일 종류 (플레이어 미사일 : 0, 적 미사일 : 1)
	
	Missile(int x, int y, int speed, int who){ //미사일 좌표를 입력 받는 메소드
		this.x = x;
		this.y= y;
		this.speed = speed;
		this.who = who;
	}
	public void move(){ //미사일 이동을 위한 메소드
		if(who == 0)
			this.y -= speed;
		else if(who == 1)
			this.y += speed;
	}
}