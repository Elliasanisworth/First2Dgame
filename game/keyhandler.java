package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyhandler implements KeyListener {
    
    public boolean uppressed, downpressed, leftpressed, rightpressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
      int code = e.getKeyCode();
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
