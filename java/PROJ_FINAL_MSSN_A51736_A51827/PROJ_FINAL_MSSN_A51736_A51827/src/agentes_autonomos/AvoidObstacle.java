package agentes_autonomos;

import physics.Body;
import processing.core.PVector;

public class AvoidObstacle extends Behavior{
    public AvoidObstacle(float weight) {
        super(weight);
    }

    @Override
    public PVector getDesiredVelocity(Boid me) {

        float s = hasObstacle(me);
        if (s == 0){
            return me.getVel().copy(); //não tem obstaculo, velocidade fica igual
        }
        PVector vd = new PVector(me.getVel().y,-me.getVel().x); //vetor ortogonal / perpendicular
        if(s > 0){
            vd.mult(-1); //se o obstaculo está a direita, vira a esquerda
        }
        return vd;
    }

    private float hasObstacle(Boid me){
        for(Body body : me.eye.getFarSight()){
            PVector r = PVector.sub(body.getPos(),me.getPos());
            PVector vd = new PVector(me.getVel().y,-me.getVel().x);
            return PVector.dot(vd,r); //produto interno
        }
        return 0;
    }
}
