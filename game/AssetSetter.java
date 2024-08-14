package game;

import game.object.Boots;
import game.object.Door;
import game.object.chest;
import game.object.key;

public class AssetSetter {
     
    gamepanel gp;

    public AssetSetter(gamepanel gp) {
        this.gp=gp;
    }
    public void setObject(){
        gp.obj[0] = new key();
        gp.obj[0].worldX = 23 * gp.tilesize;
        gp.obj[0].worldY = 7*gp.tilesize;

        gp.obj[1] = new key();
        gp.obj[1].worldX = 23 * gp.tilesize;
        gp.obj[1].worldY = 40*gp.tilesize;

        gp.obj[2] = new key();
        gp.obj[2].worldX = 38 * gp.tilesize;
        gp.obj[2].worldY = 8*gp.tilesize;

        gp.obj[3] = new Door();
        gp.obj[3].worldX = 10 * gp.tilesize;
        gp.obj[3].worldY = 11 *gp.tilesize;

        gp.obj[4] = new Door();
        gp.obj[4].worldX = 8 * gp.tilesize;
        gp.obj[4].worldY = 28 *gp.tilesize;

        gp.obj[5] = new Door();
        gp.obj[5].worldX = 12 * gp.tilesize;
        gp.obj[5].worldY = 22 *gp.tilesize;

        gp.obj[6] = new chest();
        gp.obj[6].worldX = 10 * gp.tilesize;
        gp.obj[6].worldY = 7 *gp.tilesize;

        gp.obj[7] = new Boots();
        gp.obj[7].worldX = 37 * gp.tilesize;
        gp.obj[7].worldY = 42 *gp.tilesize;
    }   
}
