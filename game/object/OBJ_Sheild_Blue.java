package game.object;

import entity.entity;
import game.gamepanel;

public class OBJ_Sheild_Blue extends entity {

    public OBJ_Sheild_Blue(gamepanel gp) {
        super(gp);
       
        type = type_shield;
        name = "Blue Sheild";
        down1 = setup("/objects/shield_blue", gp.tilesize, gp.tilesize);
        defenseValue = 1;
        description = "["+ name + "]\nSinny Blue Sheild.";
    }
    
}
