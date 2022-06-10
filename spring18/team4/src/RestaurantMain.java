import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;



public class RestaurantMain {
//수제 버거
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StartRestaurant gui= new StartRestaurant();
		gui.setVisible(true);
		File file = new File("test.wav");
        //System.out.println(file.exists()); //true
        
        try {
            
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
            clip.loop(10);
            
        } catch(Exception e) {
            
            e.printStackTrace();
        }
        
	}

}