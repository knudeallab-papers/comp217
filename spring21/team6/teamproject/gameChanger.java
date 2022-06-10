package teamproject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public interface gameChanger extends ActionListener{
	
	// if i == 1 you win the game but i == -1 you lose the game
	public abstract void setGameChanger(int i); 
				
	
	public abstract int getGameChanger();
	
}
