package ca;

import processing.core.PApplet;
import processing.core.PVector;
import tools.CustomRandomGenerator;
import tools.SubPlot;

public class CellularAutomata {
    public int nrows;
    public int ncols;
    protected int nStates;
    protected int radiusNeigh;
    protected Cell[][] cells;
    protected int[] colors;

    protected float cellWidth, cellHeight; //pixels
    protected float xmin, ymin;
    protected SubPlot plt;

    public CellularAutomata(PApplet p, SubPlot plt, int nrows, int ncols, int nStates, int radiusNeigh){
        this.nrows = nrows;
        this.ncols = ncols;
        this.nStates = nStates;
        this.radiusNeigh= radiusNeigh;
        cells = new Cell[nrows][ncols];
        colors = new int[nStates];
        float[] bb = plt.getBoundingBox();
        xmin = bb[0];
        ymin = bb[1];
        cellWidth = bb[2]/ncols;
        cellHeight = bb[3]/nrows;
        this.plt = plt;
        createCells();
        setStateColors(p);
    }

    //Cor aleatória para cada estado
    public void setStateColors(PApplet p){
        for(int i = 0; i<nStates;i++){
            colors[i] = p.color(p.random(255),p.random(255),p.random(255));
        }
    }

    public void setStateColors(int[] colors){
        this.colors = colors;
    }

    public int[] getStateColors(){
        return colors;
    }

    public Cell[][] getCells() {
        return cells;
    }

    protected void createCells(){
        for(int i=0;i<nrows;i++){
            for(int j=0; j<ncols;j++){
                cells[i][j] = new Cell(this,i,j);
            }
        }
        setMooreNeighbors();
    }

    public void initRandom(){
        for(int i=0;i<nrows;i++){
            for(int j=0; j<ncols;j++){
                cells[i][j].setState((int)(nStates*Math.random()));
            }
        }
    }

    public void initRandomCustom(double[] pmf){
        CustomRandomGenerator crg = new CustomRandomGenerator(pmf);
        for (int i = 0; i < nrows; i++){
            for (int j = 0; j < ncols; j++){
                cells[i][j].setState(crg.getRandomClass());
            }
        }
    }

    protected void setMooreNeighbors(){
        int NN = (int)Math.pow(2*radiusNeigh+1, 2);
        for(int i=0;i<nrows;i++){
            for(int j=0; j<ncols;j++){
                Cell[] neigh = new Cell[NN];
                int n = 0;
                for(int ii=-radiusNeigh;ii<=radiusNeigh;ii++){
                    int row = (i + ii + nrows) % nrows; //certifica q quando há indices negativos dá a volta (aritmetica circular)
                    for(int jj=-radiusNeigh; jj<=radiusNeigh;jj++){
                        int col = (j + jj + ncols) % ncols;
                        neigh[n++] = cells[row][col];
                    }
                }
                cells[i][j].setNeighbors(neigh);
            }
        }
    }

    /*private void setMooreNeighbors() {
        int NN = (int)Math.pow(2 * radiusNeigh + 1, 2) - 1; // Total de 8 vizinhos
        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncols; j++) {
                Cell[] neigh = new Cell[NN];
                int n = 0;
                for (int ii = -radiusNeigh; ii <= radiusNeigh; ii++) {
                    for (int jj = -radiusNeigh; jj <= radiusNeigh; jj++) {
                        if (ii != 0 || jj != 0) { // Exclui a célula atual
                            int row = (i + ii + nrows) % nrows; // aritmética circular para as bordas
                            int col = (j + jj + ncols) % ncols;
                            neigh[n++] = cells[row][col];
                        }
                    }
                }
                cells[i][j].setNeighbors(neigh); // Define os vizinhos para a célula
            }
        }
    }*/

    //adicionado
    public PVector getCenterCell(int row, int col){
        float x = (col + 0.5f) * cellWidth;
        float y = (row + 0.5f) * cellHeight;
        double[] w = plt.getWorldCoord(x, y);
        return new PVector((float)w[0],(float)w[1]);
    }

    public Cell world2Cell(double x, double y){
        float[] xy = plt.getPixelCoord(x,y);
        return pixel2Cell(xy[0], xy[1]);
    }

    public Cell pixel2Cell(float x,float y){
        int row = (int) ((y - ymin)/cellHeight);
        int col = (int)((x - xmin)/cellWidth);
        if(row >= nrows) row = nrows - 1;
        if(col >= ncols) col = ncols -1;
        if(row < 0) row = 0;
        if (col < 0) col = 0;
        return cells[row][col];
    }
    public void display(PApplet p){
        for(int i=0;i<nrows;i++){
            for(int j=0; j<ncols;j++){
                cells[i][j].display(p);
            }
        }
    }
}

