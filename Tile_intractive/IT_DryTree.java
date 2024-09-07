package Tile_intractive;

import entity.entity;
import game.gamepanel;

public class IT_DryTree extends intractiveTile {

    gamepanel gp;

    public IT_DryTree(gamepanel gp,int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tilesize *col;
        this.worldY = gp.tilesize *row; 

        down1 = setup("/tiles/Tiles_Interactive/drytree",gp.tilesize,gp.tilesize);
        destructible = true;
        life = 3;
        
    }
    public boolean isCorrectItem(entity entity){
        boolean isCorrectItem = false;

        if(entity.currentWeapon.type == type_axe){
            isCorrectItem =true;
        }
        return isCorrectItem;
    }
    // public void playeSE(){
    //         gp.PlaySE(13);
    // }
    public intractiveTile getDestroysForm(){
        intractiveTile tile = null;
        return tile;
    }
    
}
