package game;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class sound {
  Clip clip ;
  URL SoundURL[] = new URL[30];
 
 public sound() {
    SoundURL[0] = getClass().getResource("/res/Sound/BlueBoyAdventure.wav");
    SoundURL[1] = getClass().getResource("/res/Sound/coin.wav");
    SoundURL[2] = getClass().getResource("/res/Sound/powerup.wav");
    SoundURL[3] = getClass().getResource("/res/Sound/unlock.wav");
    SoundURL[4] = getClass().getResource("/res/Sound/fanfare.wav");
    SoundURL[5] = getClass().getResource("/res/Sound/hitmonster.wav");
    SoundURL[6] = getClass().getResource("/res/Sound/receivedamage.wav");
    SoundURL[7] = getClass().getResource("/res/Sound/swingweapon.wav");
    SoundURL[8] = getClass().getResource("/res/Sound/slimedeath.wav");
    SoundURL[9] = getClass().getResource("/res/Sound/levelup.wav");
    SoundURL[10] = getClass().getResource("/res/Sound/cursor.wav");
    SoundURL[11] = getClass().getResource("/res/Sound/potion_drink.wav");
    SoundURL[12] = getClass().getResource("/res/Sound/fireball.wav");

   }

 public void setFile(int i){
    try {
        AudioInputStream ais = AudioSystem.getAudioInputStream(SoundURL[i]);
        clip = AudioSystem.getClip();
        clip.open(ais);

    } catch (Exception e) {
    }
 }
 public void Play(){
   if(clip != null){
      clip.start();
   }else{
      System.out.println("clip is not initialized!");
   }
 }
 public  void loop(){
    clip.loop(Clip.LOOP_CONTINUOUSLY);
 }
 public void Stop(){
    clip.stop();
 }
} 
