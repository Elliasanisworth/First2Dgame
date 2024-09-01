package game.object;

import entity.entity;
import game.gamepanel;

public class OBJ_Sword_normal extends entity {

    public OBJ_Sword_normal(gamepanel gp) {
        super(gp);
        
        type = type_sword;
        name = "Normal Sword";
        down1 = setup("/objects/sword_normal",gp.tilesize,gp.tilesize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "["+ name + "]\nAn old Sword.";
    }
    
}
