package agentes_autonomos;

import java.util.ArrayList;
import java.util.List;

import physics.Body;
import tools.SubPlot;
import processing.core.*;

public class Boid extends Body{

    private SubPlot plt;
    protected DNA dna;
    protected Eye eye;
    protected List<Behavior> behaviors;
    protected float phiWander;
    private double[] window;


    private float sumWeights;
    private PImage boidImage1;
    private PImage boidImage2;// Imagem do Boid


    protected Boid(PVector pos, float mass, float radius, int color, PApplet p, SubPlot plt) {
        super(pos, new PVector(), mass, radius, color);
        dna = new DNA();
        behaviors = new ArrayList<Behavior>();
        this.plt = plt;
        window = plt.getWindow();
        //adicionado
        this.eye = new Eye(this, new ArrayList<Body>());
    }


    public void mutateBehaviors(){
        for (Behavior behavior : behaviors){
            if(behavior instanceof AvoidObstacle){
                behavior.weight += DNA.random(-0.5f,0.5f);
                behavior.weight = Math.max(0,behavior.weight); //acima de 0
            }
            if(behavior instanceof Pursuit){
                behavior.weight += DNA.random(-0.5f,0.5f);
                behavior.weight = Math.max(0,behavior.weight);
                behavior.weight = Math.min(4, behavior.weight); //abaixo de 4
            }
        }
        updateSumWeights();
    }

    public List<Behavior> getBehaviors() {
        return behaviors;
    }

    public DNA getDNA() {
        return dna;
    }

    public void setPreyImage(PImage img) {
        this.boidImage1 = img;
    }

    public void setPredatorImage(PImage img) {
        this.boidImage2 = img;
    }

    public void setEye(Eye eye) {
        this.eye = eye;
    }

    public Eye getEye() {
        return eye;
    }

    private void updateSumWeights() {
        sumWeights = 0;
        for (Behavior beh : behaviors){
            sumWeights+= beh.getWeight();
        }
    }

    public void addBehavior(Behavior behavior) {
        behaviors.add(behavior);
        updateSumWeights();
    }

    public void removeBehavior(Behavior behavior) {
        if (behaviors.contains(behavior)) {
            behaviors.remove(behavior);
        }
        updateSumWeights();
    }

    public void applyBehavior(int i,float dt){
        //adicionado

        if (this.eye != null){
            eye.look();
        }
        //eye.look();

        Behavior behavior = behaviors.get(i);
        PVector vd = behavior.getDesiredVelocity(this);
        move(dt, vd);
    }
    public void applyBehaviors(float dt){
        //adicionado
        if (this.eye != null){
            eye.look();
        }
        //eye.look();

        PVector vd = new PVector();
        for (Behavior behavior : behaviors){
            PVector vdd = behavior.getDesiredVelocity(this);
            vdd.mult(behavior.getWeight()/sumWeights);
            vd.add(vdd);
        }
        move(dt, vd);
    }


    private void move(float dt, PVector vd) {
        vd.normalize().mult(dna.maxSpeed);
        PVector fs = PVector.sub(vd, vel);
        applyForce(fs.limit(dna.maxForce));
        super.move(dt);
        if (pos.x < window[0]) {
            pos.x += window[1] - window[0];
        }
        if (pos.y < window[2]) {
            pos.y += window[3] - window[2];
        }
        if (pos.x >= window[1]) {
            pos.x -= window[1] - window[0];
        }
        if (pos.y >= window[3]) {
            pos.y -= window[3] - window[2];
        }
    }

    @Override
    public void display(PApplet p, SubPlot plt) {
        float[] pp = plt.getPixelCoord(pos.x, pos.y);

        if (boidImage1 != null) {
            p.pushMatrix();
            p.translate(pp[0], pp[1]);
            //p.rotate(-vel.heading()); // Ajusta a rotação para a direção do Boid
            p.imageMode(PApplet.CENTER);
            p.image(boidImage1, 0, 0, 50, 50);
            p.popMatrix();
        }

        if (boidImage2 != null) {
            p.pushMatrix();
            p.translate(pp[0], pp[1]);
            //p.rotate(-vel.heading()); // Ajusta a rotação para a direção do Boid
            p.imageMode(PApplet.CENTER);
            p.image(boidImage2, 0, 0, 50,50); // Desenha a imagem na posição rotacionada
            p.popMatrix();
        }
    }


    public void setColor(int color) {
        this.color = color;
    }
    public float getRadius() {
        return this.radius;
    }
}