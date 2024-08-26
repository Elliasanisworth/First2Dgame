package game.object;


import entity.entity;
import game.gamepanel;

public class Boots extends entity {
    public Boots(gamepanel gp){
        super(gp);
        name = "Boots";
        down1 = setup("/objects/boots",gp.tilesize, gp.tilesize);
     }
}
