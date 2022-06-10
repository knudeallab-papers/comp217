package GameProject;
import java.awt.Graphics2D;

public abstract class GameObject {
	
	enum Type{
		Button,
		Image,
		Music,
		Text,
		ETC
	}
	
	protected int ObjID;
	
	public int getObjId() {
		return ObjID;
	}
	
	public static int nextID = 1;
	
	protected Type ObjType;
	
	public Type getObjType() {
		return ObjType;
	}
	
	public GameObject(Type objtype) {
		ObjType =  objtype;
		ObjID = nextID++;
	}
	
	abstract public void Destroy();
}
