package ecosystem;

import ca.Cell;
import ca.MajorityCA;
import physics.Body;
import processing.core.PApplet;
import processing.core.PImage;
import tools.SubPlot;

import java.util.ArrayList;
import java.util.List;

public class Terrain extends MajorityCA {
    public Terrain(PApplet p, SubPlot plt){
        super(p, plt, WorldConstants.NROWS, WorldConstants.NCOLS, WorldConstants.NSTATES, 1);
        //loadImages(p);
    }

    protected void createCells(){
        int minRT = (int) (WorldConstants.REGENERATION_TIME[0]*1000); //ms
        int maxRT = (int) (WorldConstants.REGENERATION_TIME[1]*1000);
        for (int i = 0; i < nrows; i++){
            for(int j = 0; j < ncols; j++){
                int timeToGrow = (int) (minRT+(maxRT-minRT)*Math.random()); //aleatorio entre o min e o max
                cells[i][j] = new Patch(this, i, j, timeToGrow);
            }
        }
        setMooreNeighbors();
    }

    public void regenerate(){
        for(int i = 0; i < nrows; i++){
            for(int j = 0; j < ncols; j++){
                ((Patch) cells[i][j]).regenerate();
            }
        }
    }

    public List<Body> getObstacles() {
        List<Body> bodies = new ArrayList<Body>();
        //percorrre todas as celulas
        for (int i = 0; i < nrows; i++){
            for (int j = 0; j < ncols; j++){
                //se a celula for um obstaculo
                if(cells[i][j].getState() == WorldConstants.PatchType.OBSTACLE.ordinal()){
                    //cria um bodie e adiciona na lista
                    Body b = new Body(this.getCenterCell(i,j));
                    bodies.add(b);
                }
            }
        }
        return bodies;
    }
}
