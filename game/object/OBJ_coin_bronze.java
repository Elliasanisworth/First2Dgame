package game.object;

import entity.entity;
import game.gamepanel;

public class OBJ_coin_bronze extends entity {

    gamepanel gp;
    public OBJ_coin_bronze(gamepanel gp) {
        super(gp);
        this.gp = (gp);
        
        type = type_pickUpOnly;
        name = "Bronze Coin";
        Value = 1;
        down1 = setup("/objects/coin_bronze",gp.tilesize,gp.tilesize);
    }
    public void use(entity entity){
        gp.PlaySE(1);
        gp.ui.addMessage("coin +"+ Value);
        gp.Player.coin += Value;
    }
}
