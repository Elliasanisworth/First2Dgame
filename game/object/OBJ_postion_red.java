package game.object;

import entity.entity;
import game.gamepanel;

public class OBJ_postion_red extends entity {

    gamepanel gp;
   

    public OBJ_postion_red(gamepanel gp) {
        super(gp);
    
        this.gp = gp;

        type = type_consumable;
        name ="Red Life";
        Value = 5;
        down1 = setup( "/objects/potion_red",gp.tilesize,gp.tilesize);
        description = "[Red Potion]\nheals your life by"+ Value + ".";
    }   
    public void use(entity entity){

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the" + name + "!\n" + "Your life has been recover by" + Value +".";
        entity.life += Value;
        if(gp.Player.life > gp.Player.maxLife){
            gp.Player.life = gp.Player.maxLife;
        }
        gp.PlaySE(11); 
    }
    
}
