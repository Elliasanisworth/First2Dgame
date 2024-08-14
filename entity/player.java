package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.gamepanel;
import game.keyhandler;

public class player extends entity {
     gamepanel gp;
     keyhandler keyH;

     public final int screenX;
     public final int screenY;
     int haskeys = 0;

     int counter2 = 0;

     public player(gamepanel gp,keyhandler keyH){
        this.gp = gp;
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
       
        setDefaultValues();
        getPlayerImage();
     }
     public void setDefaultValues(){
      worldX=gp.tilesize * 23;
      worldY=gp.tilesize * 21;
      speed=4;
      direction = "down";
     }
     public void getPlayerImage(){
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/blue-boy/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/blue-boy/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream( "/res/Player/blue-boy/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream( "/res/Player/blue-boy/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream( "/res/Player/blue-boy/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream( "/res/Player/blue-boy/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream( "/res/Player/blue-boy/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream( "/res/Player/blue-boy/boy_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
     }
     public void update(){
        if (keyH.uppressed == true || keyH.downpressed == true || keyH.leftpressed == true || keyH.rightpressed == true) {

            if(keyH.uppressed == true) {
              direction = "up";
              
           }
           else if(keyH.downpressed) {
              direction = "down";
               
           }
           else if(keyH.leftpressed) {
              direction = "left";
               
           }
           else if(keyH.rightpressed) {
              direction = "right";
              
           }

           // CHECK TILE COLLISION
           collision = false;
           gp.colichecker.checkTile(this);

           //CHECK OBJECT COLLITSION
           int objIndex = gp.colichecker.checkObject(this,true);
           pickUpObj(objIndex);

           // if collision is false, player can move
           if(collision == false) {
             switch (direction) {
                case "up":
                worldY -= speed; // playerY = playerY - playerSpeed;
                    break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;    
               }
           }

              spriteCounter++;
              if (spriteCounter > 10) {
                  if(spriteNumber == 1) {
                      spriteNumber = 2;
                  }
                  else if(spriteNumber == 2){
                      spriteNumber = 1;
                  }
                  spriteCounter = 0;
              }
        }
     }

     public void pickUpObj(int i){

            if(i != 999){
             String ObjectName = gp.obj[i].name;

             switch (ObjectName) {
                case "key":
                    gp.PlaySE(1);
                    haskeys++;
                    gp.obj[i]=null;
                    System.out.println("Key:"+haskeys);
                    break;
                case "Door":
                if(haskeys > 0){
                    gp.PlaySE(3);
                    gp.obj[i] = null;
                    haskeys--;
                }
                System.out.println("Key:"+haskeys);
                    break;
                case "Boots":
                    gp.PlaySE(2);
                    speed += 1; //player speed change
                    gp.obj[i]=null;
                    break;
                
             }
            }
     }
     public void draw(Graphics2D g2){

    //   g2.setColor(Color.white);
    //   g2.fillRect(x, y, gp.tilesize, gp.tilesize);

    BufferedImage image = null;

        switch(direction){
            case "up":
            if (spriteNumber == 1) {
                image = up1;
            }
            if (spriteNumber == 2) {
                image = up2;
            }
            break;
            case "down":
            if (spriteNumber == 1) {
                image = down1;
            }
            if (spriteNumber ==2) {
                image = down2;
            }
            break;
            case "left":
            if (spriteNumber == 1) {
                image = left1;
            }
            if (spriteNumber ==2) {
                image = left2;
            }
            break;
            case "right":
            if (spriteNumber == 1) {
                image = right1;
            }
            if (spriteNumber ==2) {
                image = right2;
            }
            break;
        }
        g2.drawImage(image, screenX, screenY, gp.tilesize, gp.tilesize, null);
     }
}
