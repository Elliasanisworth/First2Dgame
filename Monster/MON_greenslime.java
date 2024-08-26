package Monster;

import java.util.Random;

import entity.entity;
import game.gamepanel;

public class MON_greenslime extends entity {

    gamepanel gp;
    public MON_greenslime(gamepanel gp) {
        super(gp);

        this.gp = gp;
        
        type = 2;
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){

        up1 = setup("/monster/greenslime_down_1",gp.tilesize, gp.tilesize);
        up2 = setup("/monster/greenslime_down_2",gp.tilesize, gp.tilesize);
        down1 = setup("/monster/greenslime_down_1",gp.tilesize, gp.tilesize);
        down1 = setup("/monster/greenslime_down_2",gp.tilesize, gp.tilesize);
        left1 = setup("/monster/greenslime_down_1",gp.tilesize, gp.tilesize);
        left2 = setup("/monster/greenslime_down_2",gp.tilesize, gp.tilesize);
        right1 = setup("/monster/greenslime_down_1",gp.tilesize, gp.tilesize);
        right2 = setup("/monster/greenslime_down_2",gp.tilesize, gp.tilesize);
    }
    public void setAction(){

        actionLockCounter++;
       if(actionLockCounter == 120) {

           
           Random random = new Random();
           int i = random.nextInt(100)+1;
           
           if(1 < 25){
               direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;
          }
    }
    
}
