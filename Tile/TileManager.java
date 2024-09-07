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
        loadMap("/res/map/worldV3.txt");
    }
    public void getTileImage(){

            //PLACEHOLDER
            setUp(0, "/New/grass00", false);
            setUp(1, "/New/grass00", false);
            setUp(2, "/New/grass00", false);
            setUp(3, "/New/grass00", false);
            setUp(4, "/New/grass00", false);
            setUp(5, "/New/grass00", false);
            setUp(6, "/New/grass00", false);
            setUp(7, "/New/grass00", false);
            setUp(8, "/New/grass00", false);
            setUp(9, "/New/grass00", false);
            setUp(10, "/New/grass00", false);
            setUp(11, "/New/grass01", false);
            //PLACEHOLDER
            setUp(12, "/New/water00", true);
            setUp(13, "/New/water01", true);
            setUp(14, "/New/water02", true);
            setUp(15, "/New/water03", true);
            setUp(16, "/New/water04", true);
            setUp(17, "/New/water05", true);
            setUp(18, "/New/water06", true);
            setUp(19, "/New/water07", true);
            setUp(20, "/New/water08", true);
            setUp(21, "/New/water09", true);
            setUp(22, "/New/water10", true);
            setUp(23, "/New/water11", true);
            setUp(24, "/New/water12", true);
            setUp(25, "/New/water13", true);
            //placholder
            setUp(26, "/New/road00", false);
            setUp(27, "/New/road01", false);
            setUp(28, "/New/road02", false);
            setUp(29, "/New/road03", false);
            setUp(30, "/New/road04", false);
            setUp(31, "/New/road05", false);
            setUp(32, "/New/road06", false);
            setUp(33, "/New/road07", false);
            setUp(34, "/New/road08", false);
            setUp(35, "/New/road09", false);
            setUp(36, "/New/road10", false);
            setUp(37, "/New/road11", false);
            setUp(38, "/New/road12", false);
            setUp(39, "/New/earth", false);
            setUp(40, "/New/wall", true);
            setUp(41, "/New/tree", true);
            // setUp(42, "/Tiles_Interactive/trunk", true);

    }
    public void setUp(int index, String imagename, boolean collision){
        utilityTool uTool = new utilityTool();

        try {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles"+ imagename +".png"));
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
