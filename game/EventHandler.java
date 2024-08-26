package game;

public class EventHandler {
     gamepanel gp;
     eventRect eventRect[][];  

     int previousEventX, previousEventY;
     boolean canTouchEvent =true;

     public EventHandler(gamepanel gp){
        this.gp=gp;

        eventRect = new eventRect[gp.maxWorldCol][gp.maxWorldRow];

        int Col = 0;
        int Row = 0;
        while(Col < gp.maxWorldCol && Row < gp.maxWorldRow){

        eventRect[Col][Row] = new eventRect();
        eventRect[Col][Row].x = 23;
        eventRect[Col][Row].y = 23;
        eventRect[Col][Row].width =2;
        eventRect[Col][Row].height=2;
        eventRect[Col][Row].eventRectDefaultX = eventRect[Col][Row].x;
        eventRect[Col][Row].eventRectDefaultY = eventRect[Col][Row].y;

        Col++;
        if(Col == gp.maxWorldCol){
         Col = 0;
         Row ++;
        }
      }
     }
     public void checkEvent(){

      //check the player one block distance
      int xDistance = Math.abs(gp.Player.worldX - previousEventX);
      int yDistance = Math.abs(gp.Player.worldY - previousEventY);
      int distance = Math.max(xDistance, yDistance);
      if(distance > gp.tilesize){
         canTouchEvent = true;
      } 
      if(canTouchEvent == true){
         if(hit(27,16,"right") == true){damagePit( 27, 16,gp.dialogueState);} 
         if(hit(23,12,"any")==true){healingPool(23, 12, gp.dialogueState);}       
      }
     }
      public boolean hit(int Col, int Row, String reqDirection){

        boolean hit = false;
         // Update player's solid area position
         gp.Player.solidArea.x = gp.Player.worldX + gp.Player.solidArea.x;
         gp.Player.solidArea.y = gp.Player.worldY + gp.Player.solidArea.y;
         // Update eventRect position based on eventCol and eventRow
         eventRect[Col][Row].x = Row * gp.tilesize + eventRect[Col][Row].x;
         eventRect[Col][Row].y = Col * gp.tilesize + eventRect[Col][Row].y;
  

        if(gp.Player.solidArea.intersects(eventRect[Col][Row]) && eventRect[Col][Row].eventDone == false){
            if(gp.Player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;

                previousEventX= gp.Player.worldX;
                previousEventY = gp.Player. worldY;
            }

         }
            gp.Player.solidArea.x = gp.Player.solidAreaDefaultX;
            gp.Player.solidArea.y = gp.Player.solidAreaDefaultY;
            eventRect[Col][Row].x = eventRect[Col][Row].eventRectDefaultX;
            eventRect[Col][Row].y = eventRect[Col][Row].eventRectDefaultY;
       
            return hit;
     }
     public void damagePit(int col, int row, int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Why did i always do this!!!";
        gp.Player.life -= 1;
      //   eventRect[col][row].eventDone = true;
      canTouchEvent = false;

     }
     public void healingPool(int col, int row, int gameState){
      if(gp.keyH.enterpressed == true){
         gp.gameState = gameState;
         gp.ui.currentDialogue = "The water is clean hear. \nohh!! it have healing ability";
         gp.Player.life += 1 ;
       
      }
     }
}
