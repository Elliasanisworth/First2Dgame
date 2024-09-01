package game.object;

import entity.entity;
import game.gamepanel;

public class OBJ_sheild_wood extends entity {

    public OBJ_sheild_wood(gamepanel gp) {
        super(gp);
       
        type = type_shield;
        name = "Wooden Sheild";
        down1 = setup("/objects/shield_wood", gp.tilesize, gp.tilesize);
        defenseValue = 1;
        description = "["+ name + "]\nA Wooden Sheild.";
    }
        
}
