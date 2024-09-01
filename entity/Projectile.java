package entity;

import game.gamepanel;

public class Projectile extends entity {

    entity user;
    public Projectile(gamepanel gp) {
        super(gp);
    }
    public void set(int worldX, int worldY, String direction, boolean alive, entity user){

        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life =  this.maxLife;
    }
    public void update(){

        if(user == gp.Player){
            int monsterIndex = gp.colichecker.checkEntity(this,gp.monster);
            if(monsterIndex != 999){
                gp.Player.damageMonster(monsterIndex, attack);
                alive = false;
            }
        }
        if(user != gp.Player){
            boolean contactPlayer = gp.colichecker.checkPlayer(this);

            if(gp.Player.invincible == false && contactPlayer == true){
                damagePlayer(attack);
                alive = false;
            }
        }

        switch(direction){
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }

        life--;
        if(life <= 0){
            alive = false;
        }

        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNumber == 1){
                spriteNumber = 2;
            }
            else if(spriteNumber == 2){
                spriteNumber =1;
            }
            spriteCounter = 0;
        }
    }
    public boolean haveResource(entity user){
        boolean haveResource =false;
        return haveResource;
    }
    public void subtractResource(entity user){}
}
