package ecosystem;

import ca.Cell;
import processing.core.PApplet;
import setup.IProcessingApp;
import tools.Button;
import tools.ButtonBar;
import tools.SubPlot;

public class EcosystemApp implements IProcessingApp {
    private float[] viewport = {0f, 0.1f, 1f, 0.9f};
    private float[] buttonBarViewport = {0f, 0f, 1f, 0.1f};
    private SubPlot buttonBarPlt;
    private ButtonBar buttonBar;

    private float timer, updateGraphTime;
    private float intervalUpdate = 1;

    //ecosistema
    private SubPlot plt;

    private Terrain terrain;
    private Population population;

    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(WorldConstants.WINDOW, viewport, p.width, p.height);
        buttonBarPlt = new SubPlot(WorldConstants.WINDOW, buttonBarViewport, p.width, p.height);
        Cell.loadImages(p); // Carrega as imagens para as células
        initializeEcosystem(p);
        initializeButtonBar(p);
    }

    private void initializeEcosystem(PApplet p) {
        terrain = new Terrain(p, plt);
        terrain.setStateColors(getColors(p));
        terrain.initRandomCustom(WorldConstants.PATCH_TYPE_PROB);
        for (int i = 0; i < 5; i++) {
            terrain.majorityRule();
        }
        population = new Population(p, plt, terrain);

        //timer e update
        timer = 0;
        updateGraphTime = timer + intervalUpdate;
    }

    private void initializeButtonBar(PApplet p) {
        buttonBar = new ButtonBar();
        int[] colors = terrain.getStateColors();
        String[] labels = {"Vazio", "Obstáculo", "Comida"};

        for (int i = 0; i < labels.length; i++) {
             //salta a cor do terreno fertil pq n está nos botões
            Button button = new Button(0, 0, 0, 0, i, colors[i == 2 ? i+1:i], labels[i]);
            buttonBar.addButton(button);
        }
    }

    @Override
    public void draw(PApplet p, float dt) {
        timer += dt;

        terrain.regenerate();
        population.update(dt, terrain);

        terrain.display(p);

        population.display(p, plt);
        buttonBar.display(p);

        //System.out.println("numAnimals = " + population.getNumAnimals());

        if (timer > updateGraphTime) {
            System.out.println(String.format("Time = %ds", (int) timer));
            System.out.println("numAnimals = " + population.getNumAnimals());
            System.out.println("meanMaxSpeed = " + population.getMeanMaxSpeed());
            System.out.println("stdMaxSpeed = " + population.getStdMaxSpeed());
            System .out.println("PRESAS: "+ "meanWeightWander = " + population.getMeanWeightsPrey()[0] +
                    " meanWeightPursuit = " + population.getMeanWeightsPrey()[1]);
            System.out.println("PREDADORES: "+ "meanWeightWander = " + population.getMeanWeightsPredators()[0] +
                    " meanWeightPursuit = " + population.getMeanWeightsPredators()[1]);
            System.out.println("");

        }
    }

    @Override
    public void mousePressed(PApplet p) {
        if (p.mouseY > p.height * 0.9) { //botões
            buttonBar.handleMousePressed(p);
        } else { //terreno
            Cell clickedCell = terrain.pixel2Cell(p.mouseX, p.mouseY);
            if (clickedCell != null) {
                int selectedState = buttonBar.getSelectedState();
                if (selectedState != -1) {
                    clickedCell.setState(selectedState);
                }
            }
        }
    }



    @Override
    public void mouseReleased(PApplet p) {
    }

    @Override
    public void mouseDragged(PApplet p) {
    }

    @Override
    public void keyPressed(PApplet p) {
        if (p.key == ' ') {
            initializeEcosystem(p);
        }
    }

    private int[] getColors(PApplet p) {
        int[] colors = new int[WorldConstants.NSTATES];
        for (int i = 0; i < WorldConstants.NSTATES; i++) {
            colors[i] = p.color(WorldConstants.TERRAIN_COLORS[i][0],
                    WorldConstants.TERRAIN_COLORS[i][1],
                    WorldConstants.TERRAIN_COLORS[i][2]);
        }
        return colors;
    }
}