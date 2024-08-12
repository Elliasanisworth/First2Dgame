package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class gamepanel extends JPanel implements Runnable {
// screen setings 
final int originalTitleSize = 16; // 16x16 Tile size
final int scale = 3;

final int tilesize = originalTitleSize * scale;
final int maxScreenCol = 16;
final int maxScreenRow = 12;
final int screenWidth = tilesize*maxScreenCol;
final int screenHeight = tilesize*maxScreenRow;

//FPS
int FPS = 60;

keyhandler keyH = new keyhandler();
Thread gameThread;

// Set player default position
int playerX = 100;
int playerY = 100;
int playerSpeed = 4;

public gamepanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true); // with this, this gamepanel can be "focused" to receive key input.
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
    if(keyH.uppressed == true) {
        playerY -= playerSpeed; // playerY = playerY - playerSpeed;
    }
    else if(keyH.downpressed) {
      playerY += playerSpeed;  
    }
    else if(keyH.leftpressed) {
        playerX -= playerSpeed;
    }
    else if(keyH.rightpressed) {
        playerX += playerSpeed;
    }

}
public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D)g;
    g2.setColor(Color.white);

    g2.fillRect(playerX, playerY, tilesize, tilesize);

    g2.dispose();
}
}
