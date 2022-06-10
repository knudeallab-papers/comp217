
public class Explosion {
	int x; //이미지를 그릴 x 좌표
	int y; //이미지를 그릴 y 좌표
	int ex_cnt; //이미지를 순차적으로 그리기 위한 카운터
	int damage;	// 누가 맞는지에 대한 변수
				// 적이 맞을 경우 : 0
				// 플레이어가 맞을 경우 : 1

	Explosion(int x, int y, int damage){
		this.x = x;
		this.y = y;
		this.damage = damage;
		ex_cnt = 0;
	}
	public void effect(){
		ex_cnt ++; //해당 메소드 호출 시 카운터를 +1 시킨다.
	}
}
