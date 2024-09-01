package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyhandler implements KeyListener {
    
    gamepanel gp;
    public boolean uppressed, downpressed, leftpressed, rightpressed, enterpressed,shotKeyPressed;
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
            titleState(code);
        }
      //PLAYSTATE
      else if(gp.gameState == gp.playState){
         playState(code);
        }
      //PAUSE STATE
      else if(gp.gameState == gp.pauseState){
        pauseState(code);
        }
      //DIALOGUE STATE
      else if(gp.gameState == gp.dialogueState){
        dialogueState(code);
      }

      //CHARACTER STAT STATE
      else if(gp.gameState == gp.charStatState){
       charStatState(code);
      }
    }
    public void titleState(int code){

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
    public void playState(int code){
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
        if(code == KeyEvent.VK_C){
          gp.gameState = gp.charStatState;
        }
        if(code == KeyEvent.VK_ENTER){
          enterpressed = true;
        }
        if(code == KeyEvent.VK_F){
            shotKeyPressed = true;
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
    public void pauseState(int code){
        if(code == KeyEvent.VK_P){ 
            gp.gameState = gp.playState;
        }
    }
    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.playState;
           } 
    }
    public void charStatState(int code){
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.playState;
        }
        //cursor movement
        if(code == KeyEvent.VK_W){
            if(gp.ui.slotRow != 0){
                gp.ui.slotRow--;
                gp.PlaySE(10);
            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.slotCol != 0){
                gp.ui.slotCol--;
                gp.PlaySE(10);
            }
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.slotRow != 3){
                gp.ui.slotRow++;
                gp.PlaySE(10);
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.slotCol != 4){
                gp.ui.slotCol++;
                gp.PlaySE(10);
            }
        }
        if(code == KeyEvent.VK_ENTER){
            gp.Player.selectItem();
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
        if(code == KeyEvent.VK_F){
            shotKeyPressed = false;
        }
       
    }
    
}
