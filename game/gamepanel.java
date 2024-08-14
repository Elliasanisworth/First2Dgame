package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Tile.TileManager;
import entity.player;
import game.object.superObject;

public class gamepanel extends JPanel implements Runnable {
// SCREEN SETTINGS
final int originalTitleSize = 16; // 16x16 Tile size
final int scale = 3;

 public final int tilesize = originalTitleSize * scale;
public final int maxScreenCol = 16;
public final int maxScreenRow = 12;
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
keyhandler keyH = new keyhandler();

//SOUND
sound Music = new sound();
sound SE = new sound();

public collision colichecker = new collision(this);
public AssetSetter aSetter = new AssetSetter(this);
Thread gameThread;

// ENTITY AND OBJECT
public player Player =new player(this,keyH);
public superObject obj[] = new superObject[10];


public gamepanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true); // with this, this gamepanel can be "focused" to receive key input.
}

public void setUpGame(){
    aSetter.setObject();
    playMusic(0);
}

public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
}

@Override
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
    Player.update();

}
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;

    //TILE
    tilem.draw(g2);

    //OBJECT
    for(int i = 0; i< obj.length; i++){
        if(obj[i] != null){
            obj[i].draw(g2, this);
        }
    }

    //PLAYER
     Player.draw(g2);

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
