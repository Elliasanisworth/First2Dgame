package entity;

import game.gamepanel;
import  java.util.Random;

public class NPC_OldMan extends entity{

    public NPC_OldMan(gamepanel gp){
        super(gp);
        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }
     public void getImage(){

        up1 = setup("/NPC/oldman_up_1",gp.tilesize, gp.tilesize);
        up2 = setup("/NPC/oldman_up_2",gp.tilesize, gp.tilesize);
        down1 = setup("/NPC/oldman_down_1",gp.tilesize, gp.tilesize);
        down2 = setup("/NPC/oldman_down_2",gp.tilesize, gp.tilesize);
        left1 = setup("/NPC/oldman_left_1",gp.tilesize, gp.tilesize);
        left2 = setup("/NPC/oldman_left_2",gp.tilesize, gp.tilesize);
        right1 = setup("/NPC/oldman_right_1",gp.tilesize, gp.tilesize);
        right2 = setup("/NPC/oldman_right_2",gp.tilesize, gp.tilesize);

     }
     public void setDialogue(){
      dialogues[0] = "Hello, lad."; 
      dialogues[1] = "So you're come to this island to find \nthe treasure?"; 
      dialogues[2] = "I used to be a great wizard but now... \nI'm bit too old for taking an adventure."; 
      dialogues[3] = "Well, good luck on you."; 
     }
     public void setAction(){

       actionLockCounter++;

       if(actionLockCounter == 120) {

           
           Random random = new Random();
           int i = random.nextInt(100)+1;
           
           if(1 < 25){
               direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;
          }
        }
    public void speak(){
     super.speak();
    }
}