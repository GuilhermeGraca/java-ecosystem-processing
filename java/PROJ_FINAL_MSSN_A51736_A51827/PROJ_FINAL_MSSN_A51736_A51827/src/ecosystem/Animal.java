package ecosystem;

import agentes_autonomos.Behavior;
import agentes_autonomos.Boid;
import agentes_autonomos.DNA;
import agentes_autonomos.Eye;
import processing.core.PApplet;
import processing.core.PVector;
import tools.SubPlot;


public class Animal extends Boid implements IAnimal {
    protected float energy;
    //parametro velocidade adicionado
    protected Animal(PVector pos, float mass, float radius, int color, PApplet p, SubPlot plt) {
        super(pos, mass,radius,color,p,plt);
    }

    protected Animal(Animal a, boolean mutate, PApplet p, SubPlot plt){
        super(a.pos, a.mass,a.radius,a.color, p,plt);
        for(Behavior b : a.behaviors){
            this.addBehavior(b);
        }
        if (a.eye != null){
            eye = new Eye(this, a.eye);
        }
        dna = new DNA(a.dna, mutate);
    }

    @Override
    public boolean die() {
        return (energy < 0);
    }


    @Override
    public Animal reproduce(boolean mutate) {
        return null;
    }

    @Override
    public void eat(Terrain terrain) {

    }

    @Override
    public void eatPrey(Population populatuion) {

    }

    @Override
    public void energy_consumption(float dt, Terrain terrain) {
        energy -= dt; //basic metabolism
        energy -= mass*(float)Math.pow(vel.mag(),2) * dt; //é penalizado pela sua massa (energia cinetica)
        Patch patch = (Patch)terrain.world2Cell(pos.x,pos.y);
        if(patch.getState() == WorldConstants.PatchType.OBSTACLE.ordinal()){
            energy -= 50*dt;
        }
    }

    public void kill(){
        energy = 0;
    }



}
