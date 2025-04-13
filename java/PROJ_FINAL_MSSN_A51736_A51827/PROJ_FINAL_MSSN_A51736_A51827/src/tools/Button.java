package tools;

import processing.core.PApplet;

public class Button {
    private float x, y, width, height;
    private int state;
    private int color;
    private boolean selected;
    private String label;

    public Button(float x, float y, float width, float height, int state, int color, String label) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.state = state;
        this.color = color;
        this.selected = false;
        this.label = label;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void display(PApplet p) {
        p.pushStyle();
        p.strokeWeight(selected ? 4 : 1); //se estiver selecinado, aumenta a borda do btn
        p.stroke(0);
        p.fill(color);
        p.rect(x, y, width, height);
        p.fill(0);
        p.textAlign(PApplet.CENTER, PApplet.CENTER);
        p.textSize(20);
        p.text(label, x + width / 2, y + height / 2); // Adiciona o texto no centro do botão
        p.popStyle();
    }

    public boolean isMouseOver(PApplet p) {
        return p.mouseX > x && p.mouseX < x + width && p.mouseY > y && p.mouseY < y + height;
    }

    public int getState() {
        return state;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}