package teamp2;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class bgmplay {


	

public void play_bgm() {
	
	File bgm;
	AudioInputStream input_stream;
	AudioFormat format;
	DataLine.Info info;
	
	bgm = new File("./src/teamp2/music.wav");
	
	Clip clip;
	
	try {
		input_stream = AudioSystem.getAudioInputStream(bgm);
		format = input_stream.getFormat();
		info = new DataLine.Info(Clip.class, format);
		clip = (Clip)AudioSystem.getLine(info);
		clip.open(input_stream);
		clip.start();
		
	} catch (Exception e) {
		System.out.println("unexpect error : " + e);
		}
	
}
}