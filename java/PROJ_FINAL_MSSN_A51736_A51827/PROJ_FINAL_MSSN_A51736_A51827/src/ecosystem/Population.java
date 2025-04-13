package ecosystem;

import agentes_autonomos.AvoidObstacle;
import agentes_autonomos.Eye;
import agentes_autonomos.Pursuit;
import agentes_autonomos.Wander;
import physics.Body;
import processing.core.PVector;
import tools.SubPlot;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;

public class Population {
    private List<Animal> allAnimals;
    private double[] window;
    private boolean mutate = true;

    public Population(PApplet parent,SubPlot plt, Terrain terrain){
        this.window = plt.getWindow();
        allAnimals = new ArrayList<Animal>();

        List<Body> obstacles = terrain.getObstacles();

        for(int i = 0; i < WorldConstants.INI_PREY_POPULATION; i++){
            PVector pos = new PVector(parent.random((float) window[0], (float) window[1]),
                    parent.random((float) window[2], (float) window[3]));
            int color = parent.color(
                    WorldConstants.PREY_COLOR[0],
                    WorldConstants.PREY_COLOR[1],
                    WorldConstants.PREY_COLOR[2]);
            //PVector() foi adicional por causa que boid contem a vel como parametro
            Animal a = new Prey(pos, WorldConstants.PREY_MASS, WorldConstants.PREY_SIZE, color, parent, plt);
            a.addBehavior(new Wander(1));
            a.addBehavior(new AvoidObstacle(0));
            Eye eye = new Eye(a, obstacles);
            a.setEye(eye);
            allAnimals.add(a);
        }
        for(int i = 0; i < WorldConstants.INI_PREDATOR_POPULATION; i++){
            PVector pos = new PVector(parent.random((float) window[0], (float) window[1]),
                    parent.random((float) window[2], (float) window[3]));
            int color = parent.color(
                    WorldConstants.PREDATOR_COLOR[0],
                    WorldConstants.PREDATOR_COLOR[1],
                    WorldConstants.PREDATOR_COLOR[2]);
            //PVector() foi adicional por causa que boid contem a vel como parametro
            Animal a = new Predador(pos, WorldConstants.PREDATOR_MASS, WorldConstants.PREDATOR_SIZE, color, parent, plt);
            a.addBehavior(new Wander(1));
            a.addBehavior(new Pursuit(2));
            Eye eye = new Eye(a, this.getPreyBodies());
            a.setEye(eye);
            allAnimals.add(a);
        }
    }

    public void update(float dt, Terrain terrain){
        move(terrain, dt);
        eat(terrain);
        energy_consumption(dt, terrain);
        reproduce(mutate);
        die();
    }

    public void move(Terrain terrain, float dt){
        for(Animal a : allAnimals){
            a.applyBehaviors(dt);
        }
    }

    public void eat(Terrain terrain){
        for (Animal a : allAnimals) {
            if (a instanceof Predador) {
                 a.eatPrey(this);
            } else {
                a.eat(terrain);
            }
        }
    }

    public void energy_consumption(float dt, Terrain terrain){
        for(Animal a : allAnimals){
            a.energy_consumption(dt, terrain);
        }
    }

    public void die(){
        for(int i= allAnimals.size()-1; i>=0; i--){
            Animal a = allAnimals.get(i);
            if(a.die()){
                allAnimals.remove(a);
            }
        }
    }

    public void reproduce(boolean mutate){
        for(int i= allAnimals.size()-1; i>=0; i--){
            Animal a = allAnimals.get(i);
            Animal child = a.reproduce(mutate);
            if(child != null){
                allAnimals.add(child);
            }
        }
    }

    public void display(PApplet p, SubPlot plt){
        for(Animal a : allAnimals){
            a.display(p, plt);
        }
    }

    public int getNumAnimals(){
        return allAnimals.size();
    }
    //media de velocidade maxima
    public float getMeanMaxSpeed(){
        float sum = 0;
        for(Animal a : allAnimals){
            sum += a.getDNA().maxSpeed;
        }
        return sum/allAnimals.size();
    }
    //desvio padrao de velocidade maxima
    public float getStdMaxSpeed(){
        float mean = getMeanMaxSpeed();
        float sum = 0;
        for(Animal a : allAnimals){
            sum += Math.pow(a.getDNA().maxSpeed - mean, 2);
        }
        return (float) Math.sqrt(sum/allAnimals.size());
    }
    public float[] getMeanWeights(){
        float[] sums = new float[2];
        for (Animal a : allAnimals){
            sums[0] += a.getBehaviors().get(0).getWeight();
            sums[1] += a.getBehaviors().get(1).getWeight();
        }
        sums[0] /= allAnimals.size();
        sums[1] /= allAnimals.size();
        return sums;
    }

    //novo
    public List<Animal> getPreys() {
        List<Animal> preys = new ArrayList<>();
        for (Animal animal : allAnimals) {
            if (animal instanceof Prey) {
                preys.add(animal);
            }
        }
        return preys;
    }

    public List<Body> getPreyBodies() {
        List<Body> preyBodies = new ArrayList<>();
        // Percorre todas as presas
        for (Animal prey : this.getPreys()) {
            // Cria um Body baseado na posição de cada presa
            Body b = new Body(prey.getPos());
            preyBodies.add(b);
        }
        return preyBodies;
    }

    public float[] getMeanWeightsPrey() {
        float[] sums = new float[2];
        int count = 0;
        for (Animal a : allAnimals) {
            if (a instanceof Prey) {
                sums[0] += a.getBehaviors().get(0).getWeight(); // Wander
                sums[1] += a.getBehaviors().get(1).getWeight(); // AvoidObstacle
                count++;
            }
        }
        if (count > 0) {
            sums[0] /= count;
            sums[1] /= count;
        }
        return sums;
    }


    public float[] getMeanWeightsPredators() {
        float[] sums = new float[2];
        int count = 0;
        for (Animal a : allAnimals) {
            if (a instanceof Predador) {
                sums[0] += a.getBehaviors().get(0).getWeight(); // Wander
                sums[1] += a.getBehaviors().get(1).getWeight(); // Pursuit
                count++;
            }
        }
        if (count > 0) {
            sums[0] /= count;
            sums[1] /= count;
        }
        return sums;
    }
}
