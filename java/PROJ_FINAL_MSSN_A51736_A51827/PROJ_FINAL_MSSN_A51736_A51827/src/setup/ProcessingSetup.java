package setup;

import ecosystem.EcosystemApp;
import processing.core.PApplet;

public class ProcessingSetup extends PApplet {
    private static IProcessingApp app;
    private int lastUpdateTime;

    @Override
    public void settings() {
        size(1200, 700);
    }

    @Override
    public void setup() {
        app.setup(this);
        lastUpdateTime = millis(); //quantos milissegundos passaram desde que a Papplet foi iniciada
    }

    @Override
    public void draw() {
        int now = millis();
        float dt = (now - lastUpdateTime)/1000f; //tempo decorrido desde a última atualização
        lastUpdateTime = now;
        app.draw(this, dt);
    }

    @Override
    public void mousePressed() {
        app.mousePressed(this);
    }

    @Override
    public void keyPressed() {
        app.keyPressed(this);
    }

    public static void main(String[] args) {
        app =  new EcosystemApp();
        PApplet.main(ProcessingSetup.class);
    }
}

