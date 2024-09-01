package game;

import Monster.MON_greenslime;
import entity.NPC_OldMan;
import game.object.OBJ_Sheild_Blue;
import game.object.OBJ_axe;
import game.object.OBJ_mana;
import game.object.OBJ_postion_red;
import game.object.key;


public class AssetSetter {
     
    gamepanel gp;

    public AssetSetter(gamepanel gp) {
        this.gp=gp;
    }
    public void setObject(){
        int i = 0;
        gp.obj[i] = new key(gp);
        gp.obj[i].worldX = gp.tilesize*25;
        gp.obj[i].worldY = gp.tilesize*23;
        i++; 
        gp.obj[i] = new key(gp);
        gp.obj[i].worldX = gp.tilesize*21;
        gp.obj[i].worldY = gp.tilesize*19;
        i++; 
        gp.obj[i] = new key(gp);
        gp.obj[i].worldX = gp.tilesize*26;
        gp.obj[i].worldY = gp.tilesize*21;
        i++; 
        gp.obj[i] = new OBJ_axe(gp);
        gp.obj[i].worldX = gp.tilesize*33;
        gp.obj[i].worldY = gp.tilesize*21;
        i++; 
        gp.obj[i] = new OBJ_Sheild_Blue(gp);
        gp.obj[i].worldX = gp.tilesize*35;
        gp.obj[i].worldY = gp.tilesize*21;
        i++; 
        gp.obj[i] = new OBJ_postion_red(gp);
        gp.obj[i].worldX = gp.tilesize*22;
        gp.obj[i].worldY = gp.tilesize*27;
        i++;
        gp.obj[i] = new OBJ_mana(gp);
        gp.obj[i].worldX = gp.tilesize*22;
        gp.obj[i].worldY = gp.tilesize*30;
        i++;  
    }   
    public void setNPC(){

        gp.Npc[0] = new NPC_OldMan(gp);
        gp.Npc[0].worldX =gp.tilesize*21;
        gp.Npc[0].worldY = gp.tilesize*21;
    }
    public void setMonster(){

        int i = 0;
        gp.monster[i] = new MON_greenslime(gp);
        gp.monster[i].worldX = gp.tilesize*23;
        gp.monster[i].worldY = gp.tilesize*36;
        i++;
        gp.monster[i] = new MON_greenslime(gp);
        gp.monster[i].worldX = gp.tilesize*23;
        gp.monster[i].worldY = gp.tilesize*42;
        i++;
        gp.monster[i] = new MON_greenslime(gp);
        gp.monster[i].worldX = gp.tilesize*24;
        gp.monster[i].worldY = gp.tilesize*37;
        i++;
        gp.monster[i] = new MON_greenslime(gp);
        gp.monster[i].worldX = gp.tilesize*34;
        gp.monster[i].worldY = gp.tilesize*42;
        i++;


    }
}
