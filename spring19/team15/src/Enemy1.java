
public class Enemy1 extends Enemy{
	final static public int base_hp=3;
	final static public int _speed=5;
	
	public Enemy1(int x,int y) {
		super(x,y);
		this.hp = base_hp;
		this.speed = _speed;

	}
	public void move() {
		y+=speed;
		
	}
}
