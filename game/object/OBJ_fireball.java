package game.object;

import entity.Projectile;
import entity.entity;
import game.gamepanel;

public class OBJ_fireball extends Projectile {

    gamepanel gp;

    public OBJ_fireball(gamepanel gp) {
        super(gp);
        this.gp = gp;

        name="Fire Ball";
        speed = 5;
        maxLife = 80;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage(){
        up1 = setup("/projectile/fireball_up_1",gp.tilesize,gp.tilesize);
        up2 = setup("/projectile/fireball_up_2",gp.tilesize,gp.tilesize);
        down1 = setup("/projectile/fireball_down_1",gp.tilesize,gp.tilesize);
        down2 = setup("/projectile/fireball_down_2",gp.tilesize,gp.tilesize);
        left1 = setup("/projectile/fireball_left_1",gp.tilesize,gp.tilesize);
        left2 = setup("/projectile/fireball_left_2",gp.tilesize,gp.tilesize);
        right1 = setup("/projectile/fireball_right_1",gp.tilesize,gp.tilesize);
        right2 = setup("/projectile/fireball_right_2",gp.tilesize,gp.tilesize);
    }
    public boolean haveResource(entity user){
        boolean haveResource =false;
        if(user.mana >= useCost){
            haveResource = true;
        }
        return haveResource;
    }
    public void subtractResource(entity user){
        user.mana -= useCost;
    }
    
}
