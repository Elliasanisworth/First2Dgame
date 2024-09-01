package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entity.entity;
import game.object.OBJ_mana;
import game.object.heart;


import java.awt.BasicStroke;


public class UI{
    
    gamepanel gp;
    Graphics2D g2;
    Font MineFont, prstartkFont ;
    public BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank;
    public boolean MeassageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinish = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenstate = 0;
    public int slotCol = 0;
    public int slotRow = 0;

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
        entity crystal = new OBJ_mana(gp);
        crystal_full = crystal.image;
        crystal_blank = crystal.image2;

    }
    public void addMessage(String text){
        
        message.add(text);
        messageCounter.add(0);
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
            drawMessage();
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
        //CHARACTER STATE
        if(gp.gameState == gp.charStatState){
            drawCharacterSreen();
            drawInventory();
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
        //DRAW MAX MANA
        x = (gp.tilesize/2)-5;
        y = (int) (gp.tilesize*1.5);
        i=0;
        while(i < gp.Player.maxMana){
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x += 35;
        }
        // draw mana
        x = (gp.tilesize/2)-5;
        y = (int) (gp.tilesize*1.5);
        i = 0;
        while(i < gp.Player.mana){
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x += 35;
        }
    }
    public void drawMessage(){
        int messageX = gp.tilesize;
        int messageY = gp.tilesize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for(int i = 0; i< message.size(); i++){
            if(message.get(i) != null){
                
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);
                int counter = messageCounter.get(i) +1; // messagecounter++
                messageCounter.set(i,counter); //set the counter to the array
                messageY += 50;

                if(messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
             }
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
    public void drawCharacterSreen(){
        // CREATE A FRAM
        final int framX = gp.tilesize;
        final int framY = gp.tilesize;
        final int framWidth = gp.tilesize*7;
        final int framHeight = gp.tilesize*11;
        drawSubWindow(framX, framY, framWidth, framHeight);
        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = framX + 20;
        int textY = framY + gp.tilesize*2;
        final int lineHeight = 38;

        //NAME
        g2.drawString("level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight + 10;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 10;
        g2.drawString("Sheild", textX, textY);
        textY += lineHeight;
        //VALUE
        int tailX =(framX +framWidth) - 30;
        //RESET
        textY = framY + gp.tilesize*2;
        String Value;

        Value = String.valueOf(gp.Player.level);
        textX = TextAlignToRight(Value, tailX);
        g2.drawString(Value, textX, textY);
        textY += lineHeight;
        
        Value = String.valueOf(gp.Player.life + "/" + gp.Player.maxLife);
        textX = TextAlignToRight(Value, tailX);
        g2.drawString(Value, textX, textY);
        textY += lineHeight;

        Value = String.valueOf(gp.Player.mana + "/" + gp.Player.maxMana);
        textX = TextAlignToRight(Value, tailX);
        g2.drawString(Value, textX, textY);
        textY += lineHeight;

        Value = String.valueOf(gp.Player.Strength);
        textX = TextAlignToRight(Value, tailX);
        g2.drawString(Value, textX, textY);
        textY += lineHeight;

        Value = String.valueOf(gp.Player.dexterity);
        textX = TextAlignToRight(Value, tailX);
        g2.drawString(Value, textX, textY);
        textY += lineHeight;

        Value = String.valueOf(gp.Player.attack);
        textX = TextAlignToRight(Value, tailX);
        g2.drawString(Value, textX, textY);
        textY += lineHeight;

        Value = String.valueOf(gp.Player.defense);
        textX = TextAlignToRight(Value, tailX);
        g2.drawString(Value, textX, textY);
        textY += lineHeight;

        Value = String.valueOf(gp.Player.exp);
        textX = TextAlignToRight(Value, tailX);
        g2.drawString(Value, textX, textY);
        textY += lineHeight;

        Value = String.valueOf(gp.Player.nextLevelExp);
        textX = TextAlignToRight(Value, tailX);
        g2.drawString(Value, textX, textY);
        textY += lineHeight;

        Value = String.valueOf(gp.Player.coin);
        textX = TextAlignToRight(Value, tailX);
        g2.drawString(Value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.Player.currentWeapon.down1, tailX-gp.tilesize, textY - 50, null);
        textY += gp.tilesize;

        g2.drawImage(gp.Player.currentsheild.down1, tailX-gp.tilesize, textY - 50,null);

    }
    public void drawInventory(){
        //FRAME
         int frameX = gp.tilesize *11;
         int frameY = gp.tilesize;
         int framewidth = gp.tilesize*6;
         int frameheight = gp.tilesize*5;
         drawSubWindow(frameX, frameY, framewidth, frameheight);

         //SLOT
         final int slotXStart = frameX + 20;
         final int slotYStrat = frameY +20;
         int slotX = slotXStart;
         int slotY = slotYStrat;
         int slotSize = gp.tilesize + 3;

         //draw PLAYER INTEMS

         for(int i = 0; i < gp.Player.inventory.size(); i++){
            //EQUIP CURSOR
            if(gp.Player.inventory.get(i) == gp.Player.currentWeapon || gp.Player.inventory.get(i) == gp.Player.currentsheild){
                g2.setColor(new Color(240, 190,90));
                g2.fillRoundRect(slotX, slotY,gp.tilesize, gp.tilesize,10,10);
            }
            g2.drawImage(gp.Player.inventory.get(i).down1, slotX, slotY, null);

            slotX += slotSize;

            if(i == 4  || i == 9 || i == 14){
                slotX = slotXStart;
                slotY += gp.tilesize; 
            }
         }


         //COURSOR
        int coursorX = slotXStart + (slotSize *slotCol) ;
        int coursorY = slotYStrat + (slotSize * slotRow); 
        int coursorWidth = gp.tilesize;
        int coursorHeight = gp.tilesize; 
        //Draw Cursor
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(coursorX, coursorY, coursorWidth, coursorHeight, 10 ,10);

        //DESCRIPTION FRAME
        int dFrameX = frameX;
        int dFrameY = frameY + frameheight;
        int dFrameWidth = framewidth;
        int dFrameHeight = gp.tilesize*3;
        
        //DESCRIPTION TEXT
        int textX = dFrameX +20;
        int textY = dFrameY + (gp.tilesize + 20);
        g2.setFont(g2.getFont().deriveFont(20F));
        
        int itemIndex = getItemSlotIndex();
        
        if(itemIndex < gp.Player.inventory.size()){

             drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            
             for(String line: gp.Player.inventory.get(itemIndex).description.split("\n")){
                g2.drawString(line, textX, textY);
                textY+= 32;
            }
         }

    }
        public int getItemSlotIndex(){
            int itemIndex = slotCol + (slotRow*5);
            return itemIndex;
        }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,210);
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
    public int TextAlignToRight(String text,int tailX){
        int length =(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x= tailX - length;
        return x;
    }
}
