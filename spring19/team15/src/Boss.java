
public class Boss extends Enemy{
	final static public int base_hp=20;
	final static public int _speed=5;
	private int move_code=0;
	private int direction= 1 ;
	
	public Boss(int x,int y) {
		super(x,y);
		this.hp = base_hp;
		this.speed = _speed;
	}
	public void move() {
		if(move_code<250) {
			x+=3;
		}
		else {
			x-=3;
		}
		move_code=(move_code+1)%500;
	}
}
