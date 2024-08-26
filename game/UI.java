package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import entity.entity;
import game.object.heart;


import java.awt.BasicStroke;


public class UI{
    
    gamepanel gp;
    Graphics2D g2;
    Font MineFont, prstartkFont ;
    public BufferedImage heart_full, heart_half, heart_blank;
    public boolean MeassageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinish = false;
    public String currentDialogue = "";
    public int commandNum = 0;

    public UI(gamepanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/res/Fonts/Minecraftia-Regular.ttf");
            MineFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        //CREATE HUD OBJECT
        entity Heart = new heart(gp);
        heart_full = Heart.image;
        heart_half = Heart.image2;
        heart_blank = Heart.image3;

    }
    public void showMessage(String text){
        message = text;
        MeassageOn = true;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(MineFont);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        g2.setColor(Color.white);
        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //PLAY STATE
        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }
        //PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPauseSCreen();
            drawPlayerLife();
        }
        //DAILOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
            drawPlayerLife();
        }
    }
    public void drawPlayerLife(){

        // gp.Player.life = 5;

        int x = gp.tilesize/2;
        int y = gp.tilesize/2;
        int i = 0;

        //DRAW MAX LIFE
        while(i < gp.Player.maxLife/2){
            g2.drawImage(heart_blank, x, y,null);
            i++;
            x += gp.tilesize;
        }
        //RESET
        x = gp.tilesize/2;
        y = gp.tilesize/2;
        i = 0;
        //DRAW CURRENT LIFE
        while(i < gp.Player.life){
            g2.drawImage(heart_half, x, y, null); 
            i++;
            if(i < gp.Player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tilesize;
        }
    }
    public void drawTitleScreen(){
        //BACKGROUND COLOR
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        String text = "Blue boy Adventure";
        int x = TextCenterX(text);
        int y = gp.tilesize*3;
        //SHAADOW COLOR
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);
        //MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //BLUE BOY IMAGE
        x= gp.screenWidth/2 - (gp.tilesize*2)/2;
        y += gp.tilesize*2;
        g2.drawImage(gp.Player.down1, x, y, gp.tilesize*2, gp.tilesize*2, null);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

       String[] options = {"New Game", "Load Game", "Quit"};
       y+= gp.tilesize*3;
       for(int i = 0; i < options.length; i++){
        x = TextCenterX(options[i]);
        y += gp.tilesize;
        g2.drawString(options[i], x, y+5);
        if(commandNum == i){
            g2.drawString(">",x-gp.tilesize , y);
        }
       }
    }
    public void drawPauseSCreen(){

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = TextCenterX(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }
    public void drawDialogueScreen(){
        //window
        int x = gp.tilesize*2;
        int y = gp.tilesize/2;
        int width = gp.screenWidth -(gp.tilesize*4);
        int height= gp.tilesize*4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 23F));
        x += gp.tilesize;
        y += gp.tilesize;

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }

    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    
    }
    public int TextCenterX(String text){
        int length =(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x= gp.screenWidth/2 - length/2;
        return x;
    }
}
