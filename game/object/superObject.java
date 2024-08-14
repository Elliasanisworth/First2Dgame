package game.object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.gamepanel;

public class superObject {
    
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY; 
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int  solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;


    public void draw(Graphics2D g2, gamepanel gp){
        int screenX = worldX - gp.Player.worldX + gp.Player.screenX;
        int screenY = worldY - gp.Player.worldY + gp.Player.screenY;

        if(worldX + gp.tilesize > gp.Player.worldX - gp.Player.screenX && 
         worldX -gp.tilesize < gp.Player.worldX + gp.Player.screenX && 
         worldY + gp.tilesize > gp.Player.worldY - gp.Player.screenY && 
         worldY - gp.tilesize < gp.Player.worldY + gp.Player.screenY){

             g2.drawImage(image, screenX, screenY, gp.tilesize, gp.tilesize, null);
         }
    }
}
