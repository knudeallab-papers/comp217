
public class Enemy3 extends Enemy{
	final static public int base_hp=1;
	final static public int _speed=7;
	
	private boolean direction;
	
	public Enemy3(int x,int y,boolean d) {
		super(x,y);
		this.hp = base_hp;
		this.speed = _speed;
		direction = d;
	}
	public void move() {
		y+=speed;
		if(direction ==true) {
			x+=speed;
		}
		else {
			x-=speed;
		}
	}

}
