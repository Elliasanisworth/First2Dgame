package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
// import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


import game.gamepanel;
import game.keyhandler;

public class player extends entity {
   
     keyhandler keyH;
     public final int screenX;
     public final int screenY;
    
     int counter2 = 0;
     int standCounter = 0;

     public player(gamepanel gp,keyhandler keyH){
        
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tilesize/2);
        screenY = gp.screenHeight/2 - (gp.tilesize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        attackArea.width = 36;
        attackArea.height = 36;
       
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
     }
     public void setDefaultValues(){
      worldX = gp.tilesize * 23;
      worldY = gp.tilesize * 21;
      speed=4;
      direction = "down";

      //PLAYER STATUS
        maxLife = 6;
        life = maxLife;
     }
     public void getPlayerImage(){

        up1 = setup("/Player/blue-boy/boy_up_1",gp.tilesize, gp.tilesize);
        up2 = setup("/Player/blue-boy/boy_up_2",gp.tilesize, gp.tilesize);
        down1 = setup("/Player/blue-boy/boy_down_1",gp.tilesize, gp.tilesize);
        down2 = setup("/Player/blue-boy/boy_down_2",gp.tilesize, gp.tilesize);
        left1 = setup("/Player/blue-boy/boy_left_1",gp.tilesize, gp.tilesize);
        left2 = setup("/Player/blue-boy/boy_left_2",gp.tilesize, gp.tilesize);
        right1 = setup("/Player/blue-boy/boy_right_1",gp.tilesize, gp.tilesize);
        right2 = setup("/Player/blue-boy/boy_right_2",gp.tilesize, gp.tilesize);
     }

     public void getPlayerAttackImage(){
        attackup1 = setup("/Player/blue-boy/attack/boy_attack_up_1",gp.tilesize, gp.tilesize*2);
        attackup2 = setup("/Player/blue-boy/attack/boy_attack_up_2",gp.tilesize, gp.tilesize*2);
        attackdown1 = setup("/Player/blue-boy/attack/boy_attack_down_1",gp.tilesize, gp.tilesize*2);
        attackdown2 = setup("/Player/blue-boy/attack/boy_attack_down_2",gp.tilesize, gp.tilesize*2);
        attackleft1 = setup("/Player/blue-boy/attack/boy_attack_left_1",gp.tilesize*2, gp.tilesize); 
        attackleft2 = setup("/Player/blue-boy/attack/boy_attack_left_2",gp.tilesize*2, gp.tilesize);
        attackright1 = setup("/Player/blue-boy/attack/boy_attack_right_1",gp.tilesize*2, gp.tilesize);
        attackright2 = setup("/Player/blue-boy/attack/boy_attack_right_2",gp.tilesize*2, gp.tilesize);
     }
     public void update(){

        if(attacking == true){
            attacking();
        }
    else  if (keyH.uppressed == true || keyH.downpressed == true || keyH.leftpressed == true || keyH.rightpressed == true || keyH.enterpressed == true) {

        if(keyH.uppressed == true) {
          direction = "up";  
       }
       else if(keyH.downpressed == true) {
          direction = "down";
       }
       else if(keyH.leftpressed == true) {
          direction = "left";
       }
       else if(keyH.rightpressed == true) {
          direction = "right";
       } 
       

       // CHECK TILE COLLISION
       collisionOn = false;
       gp.colichecker.checkTile(this);

       //CHECK OBJECT COLLITSION
       int objIndex = gp.colichecker.checkObject(this,true);
       pickUpObj(objIndex);

       //CHECK NPC COLLISION
       int npcIndex = gp.colichecker.checkEntity(this, gp.Npc);
       interactNpc(npcIndex);

       // CKECK MONSTER COLLISION
       int monsterIndex = gp.colichecker.checkEntity(this, gp.monster);
       contactMonster(monsterIndex);

       //CHECK EVENT
       gp.eHandler.checkEvent();

    }
   
        //if collision is false,player can move
        if(collisionOn == false  && keyH.enterpressed == false){ 
            switch(direction){
                case"up": worldY -= speed; break;
                case"down": worldY += speed; break;
                case"left": worldX -=speed; break;
                case"right": worldX += speed; break;
                
            }
            gp.keyH.enterpressed = false;

        spriteCounter++;
       if(spriteCounter > 12){
        if(spriteNumber == 1){
            spriteNumber = 2;
        }else if(spriteNumber == 2){
            spriteNumber = 1;
        }
        spriteCounter = 0;
       }
     }
    else{
        standCounter++;

        if(standCounter == 20){
            spriteNumber = 1;
            standCounter = 0;
        }
    }
        //outside of key if
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void attacking(){
        spriteCounter++;
        if(spriteCounter <= 5){
            spriteNumber = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNumber = 2;

            // save the current worldx, worldy,solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //adjust player's worldx/y for the attackArea
            switch(direction){
                case"up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case"left": worldX -= attackArea.width; break;
                case"right": worldX += attackArea.width;break; 
            }
            //attackArea becom soidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            // check monster collision with updated worldx wolrdy and solidarea
            int monsterIndex = gp.colichecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);
            //after checking collison, restore the original date
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 25){
            spriteNumber =1;
            spriteCounter = 0;
            attacking = false;
        }
    }

     public void pickUpObj(int i){
        if( i != 999){
            //handle object pickup logic here
        }
     }
     public void interactNpc(int i){
        if(gp.keyH.enterpressed == true){

         if(i !=999){
                
                    gp.gameState = gp.dialogueState;
                    gp.Npc[i].speak();                 
            
        }
            else{
                
                  attacking = true;
                }
            }
        }
     public void contactMonster(int i){
        if(i != 999){
            if(invincible == false){
                life -= 1;
                invincible = true;
            }
        }
     }
     public void damageMonster(int i){
        if(i != 999){
            if(gp.monster[i].invincible == false){

                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;
                if(gp.monster[i].life <= 0){
                    gp.monster[i] = null;
                }
            }
        }
     }
     public void draw(Graphics2D g2){

        BufferedImage image = null;
        
        int tempScrenX = screenX;
        int tempScreenY = screenY;

        switch(direction){
            case"up":
            if(attacking==false){
                if(spriteNumber == 1) {image = up1;}
               if(spriteNumber == 2) {image = up2;}
            }
             if(attacking == true){
                tempScreenY = screenY - gp.tilesize;
                if(spriteNumber == 1) {image = attackup1;}
               if(spriteNumber == 2) {image = attackup2;}
            }
                break;
            case "down":
            if(attacking == false){
                if(spriteNumber == 1) {image = down1;}
               if(spriteNumber == 2)  {image = down2;}
            }
            if(attacking == true){
               if(spriteNumber == 1) {image = attackdown1;}
               if(spriteNumber == 2) {image = attackdown2;}
            }
                break;
            case"left":
            if(attacking == false){
                if(spriteNumber == 1) {image = left1;}
               if(spriteNumber == 2) {image = left2;}
            }
             if(attacking == true){
                tempScrenX = screenX - gp.tilesize;
                if(spriteNumber == 1) {image = attackleft1;}
                if(spriteNumber == 2) {image = attackleft2;}
            }
                break;
            case "right":
            if(attacking == false){
                if(spriteNumber == 1) {image = right1;}
               if(spriteNumber == 2) {image = right2;}
            }
             if(attacking == true){
                if(spriteNumber == 1) {image = attackright1;}
               if(spriteNumber == 2) {image = attackright2;}
            }
                break;        
        }

        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F));
        }

        g2.drawImage(image, tempScrenX, tempScreenY,null);
        
        //reset aplha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));

        //DEBUG
         g2.setColor(Color.red);   // to check the collison rectangle.
        // g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

        // g2.setFont(new Font("Arial", Font.PLAIN, 26));
        // g2.setColor(Color.white);
        // g2.drawString("invincible:"+invincibleCounter, 10, 400);
     }
}
