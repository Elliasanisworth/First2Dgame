package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyhandler implements KeyListener {
    
    gamepanel gp;
    public boolean uppressed, downpressed, leftpressed, rightpressed, enterpressed;
    boolean checkDrawTime = false;    // DEBUG

    public keyhandler(gamepanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
      int code = e.getKeyCode();
        //TITLE STATE
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum = (gp.ui.commandNum - 1 + 3) % 3; // Handles wrap-around
            } 
             if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            } 
             if (code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commandNum == 1){
                    //add later
                }
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
        }
        
      //PLAYSTATE
      else if(gp.gameState == gp.playState){
          if(code == KeyEvent.VK_W){
              uppressed = true;
          }
          if(code == KeyEvent.VK_S){
              downpressed =true;
          }
          if(code == KeyEvent.VK_A){
              leftpressed = true;
          }
          if(code == KeyEvent.VK_D){
              rightpressed = true;
          }
          if(code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;             
          }
          if(code == KeyEvent.VK_ENTER){
            enterpressed = true;
          }
  
          // DEBUG
          if(code == KeyEvent.VK_T){
              if(checkDrawTime == false){
                  checkDrawTime = true;
              }
              else if(checkDrawTime == true){
                  checkDrawTime = false;
              }
          }
      }
      //PAUSE STATE
      else if(gp.gameState == gp.pauseState){
          if(code == KeyEvent.VK_P){ 
            gp.gameState = gp.playState;
        }
    }
      //DIALOGUE STATE
      if(gp.gameState == gp.dialogueState){
       if(code == KeyEvent.VK_ENTER){
        gp.gameState = gp.playState;
       } 
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            uppressed = false;
        }
        if(code == KeyEvent.VK_S){
            downpressed =false;
        }
        if(code == KeyEvent.VK_A){
            leftpressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightpressed = false;
        }
       
    }
    
}
