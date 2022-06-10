
public class Enemy2 extends Enemy{
	final static public int base_hp=2;
	final static public int _speed=5;
	private int move_code;
	
	public Enemy2(int x,int y) {
		super(x,y);
		this.speed = _speed;
		this.hp = base_hp;
		move_code =0;
	}
	public void move() {
		y+=speed;
		if(move_code<20) {
			x+=3;
		}
		else {
			x-=3;
		}
		move_code=(move_code+1)%40;
	}
}
