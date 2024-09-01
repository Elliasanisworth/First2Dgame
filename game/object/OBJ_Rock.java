package game.object;

import entity.Projectile;
import game.gamepanel;

public class OBJ_Rock extends Projectile {
    
    gamepanel gp;
    public OBJ_Rock(gamepanel gp){
        super(gp);
        this.gp = gp;

        name= "";
        speed =8;
        maxLife = 80;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage(){

        up1 = setup("/projectile/rock_down_1",gp.tilesize,gp.tilesize);
        up2 = setup("/projectile/rock_down_1",gp.tilesize,gp.tilesize);
        down1 = setup("/projectile/rock_down_1",gp.tilesize,gp.tilesize);
        down2 = setup("/projectile/rock_down_1",gp.tilesize,gp.tilesize);
        left1 = setup("/projectile/rock_down_1",gp.tilesize,gp.tilesize);
        left2 = setup("/projectile/rock_down_1",gp.tilesize,gp.tilesize);
        right1 = setup("/projectile/rock_down_1",gp.tilesize,gp.tilesize);
        right2 = setup("/projectile/rock_down_1",gp.tilesize,gp.tilesize);
    }
}
