package game.object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class key extends superObject{
    
    public key(){
        name= "key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
