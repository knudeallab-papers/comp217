
//적들이 공통으로 가지고 있는 부모 클래스
abstract class Enemy{
	//각 개체들의 좌표값
	public int x;
	public int y;
	
	public int cnt;	//각 개체들의 패턴을 위한 변수
	public int hp;	//체력
	
	public int speed; // 적 이동 속도 변수를 추가

	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.hp = 1;
		this.speed = 3;
		
		cnt = 0;
		
	}
	
	public abstract void move();
		

}

