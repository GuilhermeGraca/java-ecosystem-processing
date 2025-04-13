package ca;

import ecosystem.WorldConstants;
import processing.core.PApplet;
import processing.core.PImage;

public class Cell {
    private int row, col;
    protected int state;
    private Cell[] neighbors;
    protected CellularAutomata ca;
    private int color;

    private static PImage obstacleImage;
    private static PImage foodImage;

    public Cell(CellularAutomata ca,int row,int col){
        this.row = row;
        this.col = col;
        this.state = 0;
        this.neighbors = null;
        this.ca = ca;
    }

    public void setNeighbors(Cell[] neigh){
        this.neighbors = neigh;
    }

    public Cell[] getNeighbors(){
        return neighbors;
    }
    public void setState(int state){
        this.state = state;
    }
    public int getState(){
        return state;
    }


    /*
    public void display(PApplet p){
        p.pushStyle();
        p.noStroke();
        p.fill(ca.getStateColors()[state]);
        p.rect(ca.xmin + col*ca.cellWidth,ca.ymin + row*ca.cellHeight,ca.cellWidth,ca.cellHeight);
        p.popStyle();
    }

     */
    public static void loadImages(PApplet p) {
        obstacleImage = p.loadImage("Imagens/sea-waves.png");
        /*foodImages = new PImage[]{
                p.loadImage("Imagens/apple.png"),
                p.loadImage("Imagens/cherries.png")
        };
         */

        foodImage = p.loadImage("Imagens/cherries.png");
    }

    public void display(PApplet p){
        p.pushStyle();
        p.noStroke();
        p.fill(ca.getStateColors()[state]);
        float x = ca.xmin + col * ca.cellWidth;
        float y = ca.ymin + row * ca.cellHeight;
        p.rect(x, y, ca.cellWidth, ca.cellHeight);

        float centerX = x + ca.cellWidth / 2;
        float centerY = y + ca.cellHeight / 2;

        if (state == WorldConstants.PatchType.OBSTACLE.ordinal() && obstacleImage != null) {
            p.imageMode(PApplet.CENTER);
            p.image(obstacleImage, centerX, centerY, ca.cellWidth, ca.cellHeight);
        } else if (state == WorldConstants.PatchType.FOOD.ordinal() && foodImage != null) {
            p.imageMode(PApplet.CENTER);
            p.image(foodImage, centerX, centerY, ca.cellWidth, ca.cellHeight);
        }

        p.popStyle();
    }
}



