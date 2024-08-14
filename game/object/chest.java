package game.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class chest extends superObject {
     public chest(){
        name = "Chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/chest.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
     }
}
