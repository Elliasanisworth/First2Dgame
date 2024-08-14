package Tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import game.gamepanel;

public class TileManager {
    gamepanel gp;
     public Tile[] tiles;
    public int mapTileNum[][];

    public TileManager(gamepanel gp) {
        this.gp = gp;
        tiles = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/map/worldmap01.txt");
    }
    public void getTileImage(){
        try {
            
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png"));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png"));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/earth.png"));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tree.png"));
            tiles[4].collision = true;

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/sand.png"));
        } catch (IOException e) {
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

                 g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tilesize, gp.tilesize, null);
             }

            worldCol++;
            

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;   
                worldRow++;
           
            }
        }

    }
}
