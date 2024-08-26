package game;

import Monster.MON_greenslime;
import entity.NPC_OldMan;


public class AssetSetter {
     
    gamepanel gp;

    public AssetSetter(gamepanel gp) {
        this.gp=gp;
    }
    public void setObject(){

    }   
    public void setNPC(){

        gp.Npc[0] = new NPC_OldMan(gp);
        gp.Npc[0].worldX =gp.tilesize*21;
        gp.Npc[0].worldY = gp.tilesize*21;
    }
    public void setMonster(){

        gp.monster[0] = new MON_greenslime(gp);
        gp.monster[0].worldX = gp.tilesize*23;
        gp.monster[0].worldY = gp.tilesize*36;

        gp.monster[1] = new MON_greenslime(gp);
        gp.monster[1].worldX = gp.tilesize*23;
        gp.monster[1].worldY = gp.tilesize*37;

    }
}
