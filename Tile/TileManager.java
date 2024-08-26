package Tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import game.gamepanel;
import game.utilityTool;

public class TileManager {
    gamepanel gp;
     public Tile[] tiles;
    public int mapTileNum[][];

    public TileManager(gamepanel gp) {
        this.gp = gp;
        tiles = new Tile[60];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/map/worldV2.txt");
    }
    public void getTileImage(){

            //PLACEHOLDER
            setUp(0, "grass00", false);
            setUp(1, "grass00", false);
            setUp(2, "grass00", false);
            setUp(3, "grass00", false);
            setUp(4, "grass00", false);
            setUp(5, "grass00", false);
            setUp(6, "grass00", false);
            setUp(7, "grass00", false);
            setUp(8, "grass00", false);
            setUp(9, "grass00", false);
            setUp(10, "grass00", false);
            setUp(11, "grass01", false);
            //PLACEHOLDER
            setUp(12, "water00", true);
            setUp(13, "water01", true);
            setUp(14, "water02", true);
            setUp(15, "water03", true);
            setUp(16, "water04", true);
            setUp(17, "water05", true);
            setUp(18, "water06", true);
            setUp(19, "water07", true);
            setUp(20, "water08", true);
            setUp(21, "water09", true);
            setUp(22, "water10", true);
            setUp(23, "water11", true);
            setUp(24, "water12", true);
            setUp(25, "water13", true);
            //placholder
            setUp(26, "road00", false);
            setUp(27, "road01", false);
            setUp(28, "road02", false);
            setUp(29, "road03", false);
            setUp(30, "road04", false);
            setUp(31, "road05", false);
            setUp(32, "road06", false);
            setUp(33, "road07", false);
            setUp(34, "road08", false);
            setUp(35, "road09", false);
            setUp(36, "road10", false);
            setUp(37, "road11", false);
            setUp(38, "road12", false);
            setUp(39, "earth", false);
            setUp(40, "wall", true);
            setUp(41, "tree", true);


    }
    public void setUp(int index, String imagename, boolean collision){
        utilityTool uTool = new utilityTool();

        try {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/New/"+ imagename +".png"));
            tiles[index].image = uTool.scaleImage(tiles[index].image, gp.tilesize, gp.tilesize);
            tiles[index].collision = collision;

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try {
          InputStream is = getClass().getResourceAsStream(filePath);
          BufferedReader br = new BufferedReader(new InputStreamReader(is));

          int col = 0;
          int row = 0;

          while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            String line = br.readLine();   // .readLine() Read a line of text

            while(col < gp.maxWorldCol) {
                String number[] = line.split(" ");  //String.split(String) splits this String around  mathces of the given regular expression. 
                
                int num = Integer.parseInt(number[col]);

                mapTileNum[col][row] = num;
                col++;
           
            }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
          }
          br.close();

        } catch (Exception e) {
           
        }
    }
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol*gp.tilesize;
            int worldY = worldRow*gp.tilesize;
            int screenX = worldX - gp.Player.worldX + gp.Player.screenX;
            int screenY = worldY - gp.Player.worldY + gp.Player.screenY;

            if(worldX + gp.tilesize > gp.Player.worldX - gp.Player.screenX && 
             worldX -gp.tilesize < gp.Player.worldX + gp.Player.screenX && 
             worldY + gp.tilesize > gp.Player.worldY - gp.Player.screenY && 
             worldY - gp.tilesize < gp.Player.worldY + gp.Player.screenY){

                 g2.drawImage(tiles[tileNum].image, screenX, screenY,  null);
             }

            worldCol++;
            

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;   
                worldRow++;
           
            }
        }

    }
}
