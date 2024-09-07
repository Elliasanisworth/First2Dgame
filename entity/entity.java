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
    public String dialogues[] = new String[20];
    
    //STATE
    public int worldX,worldY;
    public String direction = "down";
    public int spriteNumber = 1;
    public int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible  = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean HpBarOn = false;
    
    //COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    int HpBarCounter = 0;
    
    //CHRACTER ATTRIBUTE
    public int speed;
    public String name;
    public int maxLife;
    public int life;
    public int maxMana; 
    public int mana;
    public int ammo;
    public int level;
    public int Strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public entity currentWeapon;
    public entity currentsheild;
    public Projectile projectile;

    //ITEM ATTRIBUTE
    public int Value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    
    //TYPE
    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickUpOnly = 7;

    public entity(gamepanel gp){
        this.gp = gp;
    }
    public void setAction(){}
    public void damageReaction(){}
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
    public void use(entity entity){}
   public void checkDrop(){}
   public void dropItem(entity droppedItem){
        for(int i = 0; i< gp.obj.length; i++){
            if(gp.obj[i] == null){
                gp.obj[i] = droppedItem;
                gp.obj[i].worldX = worldX;
                gp.obj[i].worldY = worldY;
                break;
            }
        }
   }
    public void update(){

        setAction();
        
        collisionOn = false;
        gp.colichecker.checkTile(this);
        gp.colichecker.checkObject(this, false);
        gp.colichecker.checkEntity(this, gp.Npc);
        gp.colichecker.checkEntity(this, gp.monster);
        gp.colichecker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.colichecker.checkPlayer(this); 


        if(this.type == type_monster && contactPlayer == true){
           damagePlayer(attack);
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
            if(shotAvailableCounter < 30){
                shotAvailableCounter++;
            }
    }
    public void  damagePlayer(int attack){
        if(gp.Player.invincible == false){
            //we can give damage
            gp.PlaySE(6);
            int damage = attack - gp.Player.defense;
            if(damage < 0 ){
                damage = 0;
            }
            gp.Player.life -= damage;
            gp.Player.invincible = true;
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

            // monster health bar
            if(type == 2 && HpBarOn ==true){
                
                double onescale = (double)gp.tilesize/maxLife;
                double HpBarSlime = onescale*life;

                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX-1, screenY-16, gp.tilesize+2, 12);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY-15, (int)HpBarSlime, 10);
                
                HpBarCounter++;

                if(HpBarCounter > 600){
                    HpBarCounter = 0;
                    HpBarOn =false;
                }
           
            }

            if(invincible == true){
                HpBarOn = true;
                HpBarCounter = 0;
                changeAlpha(g2,  0.4F);
            }
            if(dying == true){
                dyingAnimation(g2);
            }

             g2.drawImage(image, screenX, screenY, null);
             changeAlpha(g2,  1F);

            }
             //DEBUG hitbox or enitity other than player
             g2.setColor(Color.red);   // to check the collison rectangle.
            //  g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
    public void dyingAnimation(Graphics2D g2){

        dyingCounter++;
        int i = 5;
        if(dyingCounter <= i){changeAlpha(g2,0F);}
       else if(dyingCounter > i && dyingCounter <= i*2 ){changeAlpha(g2,1F);}
       else if(dyingCounter > i*2 && dyingCounter <= i*3 ){changeAlpha(g2,0F);}
       else if(dyingCounter > i*3 && dyingCounter <= i*4 ){changeAlpha(g2,1F);}
       else if(dyingCounter > i*4 && dyingCounter <= i*5 ){changeAlpha(g2,0F);}
       else if(dyingCounter > i*5 && dyingCounter <= i*6 ){changeAlpha(g2,1F);}
       else if(dyingCounter > i*6 && dyingCounter <= i*7 ){changeAlpha(g2,0F);}
       else if(dyingCounter > i*7 && dyingCounter <= i*8 ){changeAlpha(g2,1F);}
       else if(dyingCounter > i*8){
            alive = false;
            if(gp.gameState != gp.dialogueState){
                gp.PlaySE(8);
            }
        }
    }
    public void changeAlpha(Graphics2D g2, float aplhaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, aplhaValue));
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
