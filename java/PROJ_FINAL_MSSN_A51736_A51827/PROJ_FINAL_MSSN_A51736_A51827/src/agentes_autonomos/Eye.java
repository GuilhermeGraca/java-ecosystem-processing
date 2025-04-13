package agentes_autonomos;

import physics.Body;
import processing.core.PApplet;
import processing.core.PVector;
import tools.SubPlot;

import java.util.ArrayList;
import java.util.List;

public class Eye {
    private List<Body> allTrackingBodies;
    private List<Body> farSight; // visão ao longe
    private List<Body> nearSight; //visão ao perto
    private Boid me;
    protected Body target;

    public Eye(Boid me, List<Body> allTrackingBodies){
        this.me = me;
        this.allTrackingBodies = allTrackingBodies;
        //target = allTrackingBodies.get(0);
        if(allTrackingBodies.size() > 0){
            target = allTrackingBodies.get(0);
        }
    }

    //copia
    public Eye(Boid me, Eye eye){
        this.allTrackingBodies = eye.allTrackingBodies;
        this.me = me;
        target = eye.target;
    }

    public List<Body> getFarSight(){
        return farSight;
    }

    public List<Body> getNearSight(){
        return nearSight;
    }

    public void look(){
        farSight = new ArrayList<Body>();
        nearSight = new ArrayList<Body>();
        Body closestPrey = null;
        float minDistance = Float.MAX_VALUE; //valor maximo de um float

        for (Body b : allTrackingBodies){
            if (farSight(b.getPos())){
                farSight.add(b);
                float distance = PVector.dist(me.getPos(), b.getPos());
                if (distance < minDistance) {
                    minDistance = distance;
                    closestPrey = b;
                }
            }
            if (nearSight(b.getPos())){
                nearSight.add(b);
            }
        }

        if (closestPrey != null) {
            target = closestPrey;
        }
    }
    private boolean inSight(PVector target, float maxDistance, float maxAngle){
        PVector r = PVector.sub(target, me.getPos());
        float d = r.mag();
        float angle = PVector.angleBetween(r, me.getVel());
        return ((d>0) && (d<maxDistance) && (angle<maxAngle)); // d>0 faz com que eu me exclua do campo de visão
    }
    private boolean farSight(PVector t){
        return inSight(t, me.dna.visionDistance,me.dna.visionAngle);
    }
    private boolean nearSight(PVector t){
        return inSight(t, me.dna.visionSafeDistance, (float)Math.PI);
    }

    //adicionado do video
    public void display(PApplet p, SubPlot plt){
        p.pushStyle();
        p.pushMatrix();
        float[] pp = plt.getPixelCoord(me.getPos().x, me.getPos().y);
        p.translate(pp[0], pp[1]);
        p.rotate(-me.getVel().heading());
        p.noFill();
        p.stroke(255,0,0);
        p.strokeWeight(3);
        float[] dd1 = plt.getVectorCoord(me.dna.visionDistance, me.dna.visionDistance);
        float[] dd2 = plt.getVectorCoord(me.dna.visionSafeDistance, me.dna.visionSafeDistance);
        p.rotate(me.dna.visionAngle);
        p.line(0,0,dd1[0],0);
        p.rotate(-2*me.dna.visionAngle);
        p.line(0,0,dd1[0],0);
        p.rotate(me.dna.visionAngle);
        p.arc(0,0,2*dd1[0],2*dd1[0],-me.dna.visionAngle,me.dna.visionAngle);
        p.stroke(255,0,255);
        p.circle(0,0,2*dd2[0]);
        p.popMatrix();
        p.popStyle();
    }
}
