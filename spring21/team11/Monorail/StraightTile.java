package Monorail;

public class StraightTile extends Tile{
	public static final int STRAIGHT_HORIZONTAL = 0;
	public static final int STRAIGHT_VERTICAL = 1;
	
	private int straightType;
	
	public StraightTile()
	{
		super();
		setStraightType(STRAIGHT_HORIZONTAL);
	}
	
	public StraightTile(int xpos, int ypos, int straightType)
	{
		super(xpos, ypos);
		setStraightType(straightType);
	}
	
	public StraightTile(StraightTile other)
	{
		super(other.getXPos(), other.getYPos());
		setStraightType(other.getTileType());
	}
	
	public void setStraightType(int n) { straightType = n; }
	public int getTileType() { return straightType; }
}
