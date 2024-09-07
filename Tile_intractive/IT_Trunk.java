package Tile_intractive;

import game.gamepanel;

public class IT_Trunk extends intractiveTile {
    
    gamepanel gp;

    public IT_Trunk(gamepanel gp,int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tilesize *col;
        this.worldY = gp.tilesize *row;

        down1 = setup("/tiles/Tiles_Interactive/trunk",gp.tilesize,gp.tilesize);
        
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    
    }
}
