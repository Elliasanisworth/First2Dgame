package game.object;

import entity.entity;
import game.gamepanel;

public class OBJ_mana extends entity {

    gamepanel gp;
    public OBJ_mana(gamepanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        name = "Mana Crystal";
        Value = 1;
        down1 = setup("/objects/manacrystal_full", gp.tilesize,gp.tilesize);
        image = setup("/objects/manacrystal_full", gp.tilesize,gp.tilesize);
        image2 = setup("/objects/manacrystal_blank", gp.tilesize,gp.tilesize);
    }
    public void use(entity entity){
        gp.PlaySE(2);
        gp.ui.addMessage("mana +" + Value);
        entity.mana += Value;
    }
    
}
