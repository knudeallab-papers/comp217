package Monorail;

public abstract class Tile {
	private int xpos;
	private int ypos;
	
	public Tile()
	{
		setXPos(0);
		setYPos(0);
	}
	
	public Tile(int xpos, int ypos)
	{
		setXPos(xpos);
		setYPos(ypos);
	}
	
	public Tile(Tile other)
	{
		setXPos(other.getXPos());
		setYPos(other.getYPos());
	}
	
	public void setXPos(int n) { xpos = n; }
	public void setYPos(int n) { ypos = n; }
	public int getXPos() { return xpos; }
	public int getYPos() { return ypos; }
	
	abstract public int getTileType();
}
