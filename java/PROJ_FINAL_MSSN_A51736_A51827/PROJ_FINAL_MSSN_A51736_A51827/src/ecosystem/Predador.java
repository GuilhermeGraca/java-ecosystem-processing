package ecosystem;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import tools.SubPlot;

public class Predador extends Animal {
    private PApplet parent;
    private SubPlot plt;
    private PImage predatorImage;

    public Predador(PVector pos, float mass, float radius, int color, PApplet parent, SubPlot plt) {
        super(pos, mass, radius, color, parent, plt);
        this.parent = parent;
        this.plt = plt;
        this.predatorImage = parent.loadImage("Imagens/fox.png");
        setPredatorImage(predatorImage);
        energy = WorldConstants.INI_PREDATOR_ENERGY;
    }

    public Predador(Predador predador, boolean mutate, PApplet parent, SubPlot plt) {
        super(predador, mutate, parent, plt);
        this.parent = parent;
        this.plt = plt;
        this.predatorImage = parent.loadImage("Imagens/fox.png");
        setPredatorImage(predatorImage);
        energy = WorldConstants.INI_PREDATOR_ENERGY;
    }

    @Override
    public void eatPrey(Population population) {
        for (Animal prey : population.getPreys()) {
            if (PVector.dist(this.pos, prey.getPos()) < this.radius * 2 ) {
                energy += prey.energy;
                prey.kill();
                break;
            }
        }
    }

    @Override
    public Animal reproduce(boolean mutate) {
        Animal child = null;
        if(energy > WorldConstants.PREDATOR_ENERGY_TO_REPRODUCE){
            energy -= WorldConstants.INI_PREDATOR_ENERGY;
            child = new Predador(this,mutate, parent, plt);
            if (mutate){
                child.mutateBehaviors();
            }
        }
        return child;
    }
}
