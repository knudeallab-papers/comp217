import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**********
 * Filename : StoreMain.java
 * Author   : Team1 - mwJeong, jyRyu
 * Purpose  : Implement store`s main
 */
public class StoreMain{
	
	public static void main(String args[]) throws IOException {
		Store s = new Store();
		s.setVisible(true);
		
		Play("test1.wav");
		SimpleServer simpleServer = new SimpleServer();
		simpleServer.ServerRun();
	}
	
	public static void Play(String filename)
	{
		try {
			String path = StoreMain.class.getResource("").getPath();
			DataLine.Info info;
			AudioFormat format;
			Clip clip;
			
			System.out.println(path);
//			File file = new File(path + filename);
			File file = new File(filename);
			System.out.println(file.exists());
			System.out.println("음악 재생");
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			format = ais.getFormat();
			info = new DataLine.Info(Clip.class, format );
			clip = (Clip)AudioSystem.getLine(info);
			clip.stop();
			clip.open(ais);
			clip.start();
		} catch (Exception e) {
			
		}
	};
	
}