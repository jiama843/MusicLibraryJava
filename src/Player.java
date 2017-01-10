import java.io.File;
import java.net.URL;

import javax.sound.sampled.*;

public class Player {

	/*The player class represents an instance of an audio player
	 * 
	 * It accepts a music file, creates a clip and can play/stop it
	 * 
	 * The player class can only play/stop one song at a time
	 * A new instance must be declared each time a song changes
	 */
	
	private File file;
	private Clip clip;
	
	public Player(File f){
		
		try{
			
		file = f;
		clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(file));
		
		}catch(Exception e){
			
		}
	}
	
	//This method plays the music
	public void playMusic(){
		
		clip.start();
		
	}
	
	//This method stops the music
	public void stopMusic(){
		
		clip.stop();
		
	}
	
	//This is an unused method that loops the music
	public void loopMusic(){
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	
}
