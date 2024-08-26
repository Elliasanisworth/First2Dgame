package game.object;



import entity.entity;
import game.gamepanel;

public class heart extends entity {
    gamepanel gp;
      public heart(gamepanel gp){
         
        super(gp);
        this.gp = gp;
        name= "Heart";
        image = setup("/objects/heart_full",gp.tilesize, gp.tilesize);
        image2 = setup("/objects/heart_half",gp.tilesize, gp.tilesize);
        image3 = setup("/objects/heart_blank",gp.tilesize, gp.tilesize);
      
    }
}
