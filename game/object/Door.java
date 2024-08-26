package game.object;



import entity.entity;
import game.gamepanel;

public class Door extends entity {
     public Door(gamepanel gp){
        super(gp);
        name = "Door";
        down1 = setup("/objects/door",gp.tilesize, gp.tilesize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
     }
}
