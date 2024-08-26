package game.object;



import entity.entity;
import game.gamepanel;

public class chest extends entity {
     public chest(gamepanel gp){
        super(gp);
        name = "Chest";
        down1 = setup("/objects/chest",gp.tilesize, gp.tilesize);

     }
}
