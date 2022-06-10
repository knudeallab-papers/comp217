package Monorail;

public class CurvedTile extends Tile{
	public static final int CURVED_1ST_QUADRANT = 2;
	public static final int CURVED_2ND_QUADRANT = 3;
	public static final int CURVED_3RD_QUADRANT = 4;
	public static final int CURVED_4TH_QUADRANT = 5;
	
	private int curvedType;
	
	public CurvedTile()
	{
		super();
		setCurvedType(CURVED_1ST_QUADRANT);
	}
	
	public CurvedTile(int xpos, int ypos, int curvedType)
	{
		super(xpos, ypos);
		setCurvedType(curvedType);
	}
	
	public CurvedTile(CurvedTile other)
	{
		super(other.getXPos(), other.getYPos());
		setCurvedType(other.getTileType());
	}
	
	public void setCurvedType(int n) { curvedType = n; }
	public int getTileType() { return curvedType; }
}
