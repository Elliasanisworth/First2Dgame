package game.object;

import entity.entity;
import game.gamepanel;

public class key extends entity{
    public key(gamepanel gp){
        super(gp);

        name= "kKy";
        down1 = setup("/objects/key",gp.tilesize, gp.tilesize);

    }

}
