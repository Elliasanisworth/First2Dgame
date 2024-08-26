package entity;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Color;

import game.gamepanel;
import game.utilityTool;

public class entity  {
     
     gamepanel gp; 
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackup1, attackup2,attackdown1, attackdown2,attackleft1, attackleft2,attackright1, attackright2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int  solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision =false;
    String dialogues[] = new String[20];
    
    //STATE
    public int worldX,worldY;
    public String direction = "down";
    public int spriteNumber = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible  = false;
    boolean attacking = false;
    
    //COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    
    //CHRACTER ATTRIBUTE
    public int speed;
    public String name;
    public int type;
    public int maxLife;
    public int life;

    public entity(gamepanel gp){
        this.gp = gp;
    }
    public void setAction(){}
    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gp.Player.direction){
            case"up":
            direction = "down";
            break;
            case"down":
            direction = "up";
            break;
            case"left":
            direction = "right";
            break;
            case"right":
            direction="left";
            break;
        }
    }
    public void update(){

        setAction();
        
        collisionOn = false;
        gp.colichecker.checkTile(this);
        gp.colichecker.checkObject(this, false);
        gp.colichecker.checkEntity(this, gp.Npc);
        gp.colichecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.colichecker.checkPlayer(this); 


        if(this.type == 2 && contactPlayer == true){
            if(gp.Player.invincible == false){
                //we can give damage
                gp.Player.life -= 1;
                gp.Player.invincible = true;
            }
        }

        // if collision is false, player can move
           if(collisionOn == false) {
             switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;    
               }
           }

              spriteCounter++;
              if (spriteCounter > 12) {
                  if(spriteNumber == 1) {
                      spriteNumber = 2;
                  }
                  else if(spriteNumber == 2){
                      spriteNumber = 1;
                  }
                  spriteCounter = 0;
              }
              if(invincible == true){
                invincibleCounter++;
                if(invincibleCounter > 40){
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        
        int screenX = worldX - gp.Player.worldX + gp.Player.screenX;
        int screenY = worldY - gp.Player.worldY + gp.Player.screenY;

        if(worldX + gp.tilesize > gp.Player.worldX - gp.Player.screenX && 
         worldX -gp.tilesize < gp.Player.worldX + gp.Player.screenX && 
         worldY + gp.tilesize > gp.Player.worldY - gp.Player.screenY && 
         worldY - gp.tilesize < gp.Player.worldY + gp.Player.screenY){

            switch(direction){
                case "up":
                if (spriteNumber == 1) {image = up1;}
                if (spriteNumber == 2) {image = up2;}
                break;
                case "down":
                if (spriteNumber == 1) {image = down1;}
                if (spriteNumber ==2) {image = down2;}
                break;
                case "left":
                if (spriteNumber == 1) {image = left1;}
                if (spriteNumber ==2) {image = left2;}
                break;
                case "right":
                if (spriteNumber == 1) {image = right1;}
                if (spriteNumber ==2) {image = right2;}
                break;
            }
            if(invincible == true){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4F));
            }

             g2.drawImage(image, screenX, screenY, gp.tilesize, gp.tilesize, null);

             g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
            }
             // hitbox or enitity other than player
             g2.setColor(Color.red);   // to check the collison rectangle.
            //  g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
     public BufferedImage setup(String imagepath , int width , int height){

        utilityTool uTool = new utilityTool();
        BufferedImage Image = null;

        try{
            Image = ImageIO.read(getClass().getResourceAsStream( "/res/"+ imagepath +".png"));
            Image = uTool.scaleImage(Image, width, height);
        }catch(IOException e){
            e.printStackTrace();
        }
        return Image;
     }
}
