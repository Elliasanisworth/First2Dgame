package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
// import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.gamepanel;
import game.keyhandler;
import game.object.OBJ_Sword_normal;
import game.object.OBJ_fireball;
import game.object.OBJ_sheild_wood;
import game.object.key;

public class player extends entity {
   
     keyhandler keyH;
     public final int screenX;
     public final int screenY;
     public boolean attackCancled = false;
     int standCounter = 0;
     public ArrayList<entity> inventory = new ArrayList<>();
     public final int maxInventorySize = 20;

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
       
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
     }
     public void setDefaultValues(){
      worldX = gp.tilesize * 23;
      worldY = gp.tilesize * 21;
      speed=4;
      direction = "down";

      //PLAYER STATUS
      level =1;
      Strength = 1;
      dexterity = 1;
      exp = 0;
      nextLevelExp = 5;
      maxLife = 6;
      maxMana =4;
      mana = maxMana;
      life = maxLife;
      coin = 0;
      currentWeapon = new OBJ_Sword_normal(gp);
      currentsheild = new OBJ_sheild_wood(gp);
      projectile = new OBJ_fireball(gp);
      attack = getAttack();
      defense = getDefense(); 
    }
    public void setItems(){
        inventory.add(currentWeapon);
        inventory.add(currentsheild);
        inventory.add(new key(gp));
    }
    public  int getAttack(){
        attackArea = currentWeapon.attackArea;
        return attack = Strength*currentWeapon.attackValue;
    }
    public int getDefense(){
        return defense = dexterity*currentsheild.defenseValue; 
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
        if(currentWeapon.type == type_sword){

            attackup1 = setup("/Player/blue-boy/attack/boy_attack_up_1",gp.tilesize, gp.tilesize*2);
            attackup2 = setup("/Player/blue-boy/attack/boy_attack_up_2",gp.tilesize, gp.tilesize*2);
            attackdown1 = setup("/Player/blue-boy/attack/boy_attack_down_1",gp.tilesize, gp.tilesize*2);
            attackdown2 = setup("/Player/blue-boy/attack/boy_attack_down_2",gp.tilesize, gp.tilesize*2);
            attackleft1 = setup("/Player/blue-boy/attack/boy_attack_left_1",gp.tilesize*2, gp.tilesize); 
            attackleft2 = setup("/Player/blue-boy/attack/boy_attack_left_2",gp.tilesize*2, gp.tilesize);
            attackright1 = setup("/Player/blue-boy/attack/boy_attack_right_1",gp.tilesize*2, gp.tilesize);
            attackright2 = setup("/Player/blue-boy/attack/boy_attack_right_2",gp.tilesize*2, gp.tilesize);
        }
        if(currentWeapon.type == type_axe){
            attackup1 = setup("/Player/blue-boy/attack/boy_axe_up_1",gp.tilesize, gp.tilesize*2);
            attackup2 = setup("/Player/blue-boy/attack/boy_axe_up_2",gp.tilesize, gp.tilesize*2);
            attackdown1 = setup("/Player/blue-boy/attack/boy_axe_down_1",gp.tilesize, gp.tilesize*2);
            attackdown2 = setup("/Player/blue-boy/attack/boy_axe_down_2",gp.tilesize, gp.tilesize*2);
            attackleft1 = setup("/Player/blue-boy/attack/boy_axe_left_1",gp.tilesize*2, gp.tilesize); 
            attackleft2 = setup("/Player/blue-boy/attack/boy_axe_left_2",gp.tilesize*2, gp.tilesize);
            attackright1 = setup("/Player/blue-boy/attack/boy_axe_right_1",gp.tilesize*2, gp.tilesize);
            attackright2 = setup("/Player/blue-boy/attack/boy_axe_right_2",gp.tilesize*2, gp.tilesize);
        }
     }
     public void update(){

        //check if the player is attacking
        if(attacking == true){
            attacking();
        }
        //check if any movement or action key is pressed
        else if (keyH.uppressed || keyH.downpressed  || keyH.leftpressed  || keyH.rightpressed || keyH.enterpressed ) {

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

       //CHECK INTRACTIVE TILE COLLISION
       gp.colichecker.checkEntity(this, gp.iTile);

       //CHECK EVENT
       gp.eHandler.checkEvent();

       
       //if collision is false,player can move
       if(collisionOn == false ){ 
           switch(direction){
               case"up": worldY -= speed; break;
               case"down": worldY += speed; break;
               case"left": worldX -=speed; break;
               case"right": worldX += speed; break;
               
            }
          
            //handle attack initiation
            if(keyH.enterpressed && attacking == false && attackCancled ==false){
                attacking = true;
                spriteCounter = 0;
                keyH.enterpressed = false;
                gp.PlaySE(7);
            }
            //attack sound handle


            attackCancled = false;
            gp.keyH.enterpressed = false;
            
            //animation handling
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

    } else{
        //if no key are pressed,stop the player and reset animation
        standCounter++;
        if(standCounter == 20){
            spriteNumber = 1;
            standCounter = 0;
        }
    }
    if(gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30 && projectile.haveResource(this) == true){

        //set default cooredinates, direction adn user
        projectile.set(worldX,worldY,direction,true,this);

        //subtract the cost (mana, ammo,etc)
        projectile.subtractResource(this);

        //add it to the tile
        gp.projectileList.add(projectile);
        shotAvailableCounter = 0;
        gp.PlaySE(12);
    }
        //handle invincibilitty timer
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
        if(mana > maxMana){
            mana = maxMana;
        }
    }
    public void attacking(){
        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNumber = 1; // start of attack
        } else if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNumber = 2; // attack in progress

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

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // check monster collision with updated worldx wolrdy and solidarea
            int monsterIndex = gp.colichecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            int iTileIndex = gp.colichecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);

            //restore original position and solidArea
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
            
        }else if(spriteCounter > 25){
            spriteNumber = 1; //end of attack
            spriteCounter = 0;
            attacking = false; // reset attacking flag

        }
    }
     public void pickUpObj(int i){
        if( i != 999){

              //PICK oNLY ITEMS
        if(gp.obj[i].type == type_pickUpOnly){

            gp.obj[i].use(this);
            gp.obj[i] = null;
        }
        //INVENTORY ITEMS
        else{

            String text;
            
            if(inventory.size() != maxInventorySize){
                inventory.add(gp.obj[i]);
                gp.PlaySE(1);
                text = "Got a "+ gp.obj[i].name + "!";
            }
            else{
                text = "Inventory full !!, Drop some Stuff. ";
            }
            gp.ui.addMessage(text);
            gp.obj[i] = null; 
        }
    }
}
     public void interactNpc(int i){
        if(gp.keyH.enterpressed == true){

         if(i !=999){
                    attackCancled = true;
                    gp.gameState = gp.dialogueState;
                    gp.Npc[i].speak();                 
                } 
            }
        }
     public void contactMonster(int i){
        if(i != 999){
            if(invincible == false && gp.monster[i].dying == false){
                gp.PlaySE(6);

                int damage = attack - gp.monster[i].attack - defense;
                if(damage < 0 ){
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }
        }
     }
     public void damageMonster(int i,int attack){
        if(i != 999){
            if(gp.monster[i].invincible == false){

                gp.PlaySE(5);

                int damage = attack - gp.monster[i].defense;
                if(damage < 0 ){
                    damage = 0;
                }

                gp.monster[i].life -= damage;
                gp.ui.addMessage(damage + " damage!!");

                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if(gp.monster[i].life <= 0){
                   gp.monster[i].dying = true;
                   gp.ui.addMessage("Killed the " + gp.monster[i].name + "!");
                   gp.ui.addMessage("Exp " + gp.monster[i].exp );
                    exp += gp.monster[i].exp;
                    checkLevelUp();
                }
            }
        }
     }
     public void damageInteractiveTile(int i){
        if(i != 999 && gp.iTile[i].destructible == true && gp.iTile[i].isCorrectItem(this) == true && gp.iTile[i].invincible == false){

            gp.iTile[i].life--;
            gp.iTile[i].invincible = true;
            
            if(gp.iTile[i].life == 0){
                gp.iTile[i] = gp.iTile[i].getDestroyedForm();
                gp.PlaySE(13);
            }
        }
     }
     public void checkLevelUp(){
        if(exp >= nextLevelExp){
            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife += 2;
            Strength ++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();
            gp.PlaySE(9);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "Level Up" + level;
            
        }
     } 
     public void selectItem(){
        int itemIndex = gp.ui.getItemSlotIndex();
        if(itemIndex < inventory.size()){
            entity selectedItem = inventory.get(itemIndex);

            if(selectedItem.type == type_sword || selectedItem.type == type_axe){
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if(selectedItem.type == type_shield){
                currentsheild = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_consumable){
                
                selectedItem.use(this);
                inventory.remove(itemIndex);
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
