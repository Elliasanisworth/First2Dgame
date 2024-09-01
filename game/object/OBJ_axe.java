package game.object;

import entity.entity;
import game.gamepanel;

public class OBJ_axe extends entity {

    public OBJ_axe(gamepanel gp) {
        super(gp);
        
        type = type_axe;
        name = "An Axe";
        down1 = setup("/objects/axe",gp.tilesize,gp.tilesize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[Axe] \nA bit rusty but \nstill do the job done.";
    }
    
}
