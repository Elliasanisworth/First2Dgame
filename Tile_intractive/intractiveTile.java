package Tile_intractive;

import entity.entity;
import game.gamepanel;

public class intractiveTile extends entity {

    gamepanel gp;
    public boolean destructible = false;

    public intractiveTile(gamepanel gp, int Col, int Row) {
        super(gp);
        this.gp = gp;
        
    }
    public boolean isCorrectItem(entity entity){
        boolean isCorrectItem = false;
        return isCorrectItem;
    }
    public void playeSE(){}
    public intractiveTile getDestroyedForm(){

        intractiveTile tile = new IT_Trunk(gp, worldX/gp.tilesize, worldY/gp.tilesize);
        return tile;
    }
    public void update(){

        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 20){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    
}
