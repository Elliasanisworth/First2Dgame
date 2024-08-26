package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import Tile.TileManager;
import entity.entity;
import entity.player;

public class gamepanel extends JPanel implements Runnable {
// SCREEN SETTINGS
final int originalTitleSize = 16; // 16x16 Tile size
final int scale = 3;

 public final int tilesize = originalTitleSize * scale;
public final int maxScreenCol = 16;  //game window width
public final int maxScreenRow = 13;  // game window heinght
public final int screenWidth = tilesize*maxScreenCol;
public final int screenHeight = tilesize*maxScreenRow;

//WORLD SETTINGS
public final int maxWorldCol = 50;
public final int maxWorldRow = 50;
// public final int worldWidth = tilesize * maxScreenCol;
// public final int worldHeight = tilesize * maxScreenRow;

//FPS
int FPS = 60;

//SYSTEM
TileManager tilem = new TileManager(this);
 public keyhandler keyH = new keyhandler(this);

//SOUND
sound Music = new sound();
sound SE = new sound();

public collision colichecker = new collision(this);
public AssetSetter aSetter = new AssetSetter(this);
public UI ui = new UI(this);
public EventHandler eHandler =new EventHandler(this);
Thread gameThread;

// ENTITY AND OBJECT
public player Player =new player(this,keyH);
public entity obj[] = new entity[10];
public entity Npc[] = new entity[10];
public entity monster[] = new entity[20];
ArrayList<entity> entityList = new ArrayList<>();

//GAME STATE
public int gameState;
public final int titleState = 0;
public final int playState = 1;
public final int pauseState = 2;
public final int dialogueState = 3;

public gamepanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true); // with this, this gamepanel can be "focused" to receive key input.
}

public void setUpGame(){
    aSetter.setObject();
    aSetter.setNPC(); 
    aSetter.setMonster();
    // playMusic(0);
    gameState = titleState;
}

public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
}

// @Override
                                                // sleep method
// public void run() {
    
//     double drawInterval = 1000000000/FPS;  // 0.01666666 seconds
//     double nextDrawTime = System.nanoTime() + drawInterval;

//     while (gameThread != null) {

      
//         //1 UPDATE: update information such as chracter positions
//         update();

//         // 2 DRAW: draw the screen with the updated information
//         repaint();

//         try {
//          double remainingTime = nextDrawTime - System.nanoTime();
//          remainingTime = remainingTime/1000000;

//          if(remainingTime < 0) {
//             remainingTime = 0;
//          }
          
//             Thread.sleep((long) remainingTime);

//             nextDrawTime += drawInterval;

//         } catch (InterruptedException e) {
//             
//             e.printStackTrace();
//         }
//     }
// }
public void run() {

    double drawInterval = 1000000000/FPS;  // 0.01666666 seconds
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;

    while(gameThread != null){

        currentTime = System.nanoTime();
        delta += (currentTime - lastTime) / drawInterval;

        lastTime = currentTime;

        if(delta >= 1){
            update();
            repaint();
            delta--;
        }

    }
}

public void update() {

    if(gameState == playState){
        //PLAYER
        Player.update();

        //NPC
        for(int i = 0;i<Npc.length; i++){
            if(Npc[i] != null){
                Npc[i].update();
            }
        }
        for(int i = 0; i < monster.length; i++){
            if(monster[i] != null){
                monster[i].update();
            }
        }
    }
    if(gameState == pauseState){
        //nothing
    }

}
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;

     //DEBUG
     long drawStart = 0;
     if(keyH.checkDrawTime == true){
         drawStart = System.nanoTime();
     }

     //TITLE SCREEN
     if(gameState == titleState){
        ui.draw(g2);
     }else{

    //TILE
    tilem.draw(g2);

    //ADD ENTITY TO LIST   
    entityList.add(Player);

    for(int i = 0; i < Npc.length; i++){
        if(Npc[i] != null){
            entityList.add(Npc[i]);
        }
    }
    for(int i = 0; i < obj.length; i++){
        if(obj[i] != null){
            entityList.add(obj[i]);
        }
    } 
    for(int i = 0; i < monster.length; i++){
        if(monster[i] != null){
            entityList.add(monster[i]);
        }
    }
    //SHORTING
    Collections.sort(entityList , new Comparator<entity>() {

        @Override
        public int compare(entity e1, entity e2) {
            
            int result = Integer.compare(e1.worldY, e2.worldY);
            return result;
        }
        
    });
    //draw entity
    for(int i = 0; i < entityList.size(); i++){
        entityList.get(i).draw(g2);
    }
    //empty entity list
    entityList.clear();

     //UI
     ui.draw(g2);
}
    //DEBUG
    if(keyH.checkDrawTime == true){
        long drawEnd = System.nanoTime();
        long passed = drawEnd - drawStart;
        g2.setColor(Color.white);
        g2.drawString("Draw TIme:" + passed, 10, 400);
        System.out.println("draw time: "+passed);
    }

    g2.dispose();
    }

    public void playMusic(int i){
        Music.setFile(i);
        Music.Play();
        Music.loop();
    }
    public void StopMusic(){
        
        Music.Stop();
    }
    public void PlaySE (int i)
    {
        SE.setFile(i);
        SE.Play();
    }
}
