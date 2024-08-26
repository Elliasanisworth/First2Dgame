package game;

import entity.entity;

public class collision {

    gamepanel gp;

    public collision(gamepanel gp){
     this.gp = gp;   
    }
    public void checkTile(entity entity){

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
    
        int entityLeftCol = entityLeftWorldX/gp.tilesize;
        int entityRightCol = entityRightWorldX/gp.tilesize;
        int entityTopRow =  entityTopWorldY/gp.tilesize;
        int entityBottomRow = entityBottomWorldY/gp.tilesize;

        

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tilesize;
                tileNum1 = gp.tilem.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tilem.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tilem.tiles[tileNum1].collision == true || gp.tilem.tiles[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
            break;
            case "down":
            entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tilesize;
            tileNum1 = gp.tilem.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tilem.mapTileNum[entityRightCol][entityBottomRow];
            if(gp.tilem.tiles[tileNum1].collision == true || gp.tilem.tiles[tileNum2].collision == true){
                entity.collisionOn = true;
            }
            break;
            case "left":
            entityLeftCol= (entityLeftWorldX - entity.speed)/gp.tilesize;
            tileNum1 = gp.tilem.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tilem.mapTileNum[entityLeftCol][entityBottomRow];
            if(gp.tilem.tiles[tileNum1].collision == true || gp.tilem.tiles[tileNum2].collision == true){
                entity.collisionOn = true;
            }
            break;
            case "right":
            entityRightCol = (entityRightWorldX + entity.speed)/gp.tilesize;
            tileNum1 = gp.tilem.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gp.tilem.mapTileNum[entityRightCol][entityBottomRow];
            if(gp.tilem.tiles[tileNum1].collision == true || gp.tilem.tiles[tileNum2].collision == true){
                entity.collisionOn = true;
            }
            break;
        }
    }
    public  int checkObject(entity entity, boolean player){

        int index = 999;

        for(int i=0; i < gp.obj.length; i++){

            if(gp.obj[i] != null){

                //entity solid Area postion
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //object solid area postion
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":entity.solidArea.y -= entity.speed;break;
                    case"down":entity.solidArea.y += entity.speed;break;
                    case"left":entity.solidArea.x -= entity.speed;break;
                    case"right":entity.solidArea.x += entity.speed;break;
                }
                if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                    if(gp.obj[i].collision == true){
                     entity.collisionOn = true;
                    }
                    if(player == true){
                     index = i;
                    }
                 }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    // noc or monster collision
    public int checkEntity(entity entity, entity[] target){
        int index = 999;

        for(int i=0; i < target.length; i++){

            if(target[i] != null){
                
                //entity solid Area postion
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //object solid area postion
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
               target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up":entity.solidArea.y -= entity.speed;break;
                    case"down":entity.solidArea.y += entity.speed;break;
                    case"left":entity.solidArea.x -= entity.speed;break;
                    case"right":entity.solidArea.x += entity.speed;break;
                }

                if(entity.solidArea.intersects(target[i].solidArea)){
                    if(target[i] != entity){
                        entity.collisionOn = true;
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    public boolean checkPlayer(entity entity){

        boolean contactPlayer = false;

           //entity solid Area postion
           entity.solidArea.x = entity.worldX + entity.solidArea.x;
           entity.solidArea.y = entity.worldY + entity.solidArea.y;
           //object solid area postion
           gp.Player.solidArea.x = gp.Player.worldX + gp.Player.solidArea.x;
          gp.Player.solidArea.y = gp.Player.worldY + gp.Player.solidArea.y;

           switch (entity.direction) {
               case "up":
               entity.solidArea.y -= entity.speed;
             
                   break;
               case"down":
               entity.solidArea.y += entity.speed;
            
                   break;
               case"left":
               entity.solidArea.x -= entity.speed;
              
                   break;
               case"right":
               entity.solidArea.x += entity.speed;
              
                    break;
           }
           if(entity.solidArea.intersects(gp.Player.solidArea)){
            entity.collisionOn = true;
            contactPlayer = true;
         }
           entity.solidArea.x = entity.solidAreaDefaultX;
           entity.solidArea.y = entity.solidAreaDefaultY;
           gp.Player.solidArea.x = gp.Player.solidAreaDefaultX;
           gp.Player.solidArea.y = gp.Player.solidAreaDefaultY;
           
        return contactPlayer;
       }
    }
