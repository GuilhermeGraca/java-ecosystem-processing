package tools;

import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;

public class ButtonBar {
    private List<Button> buttons;
    private int selectedState;
    private float margin = 10; // entre os botões e os limites da barra

    public ButtonBar() {
        buttons = new ArrayList<>();
        selectedState = -1; //estado selecionado
    }

    public void addButton(Button button) {
        buttons.add(button);
    }

    public void display(PApplet p) {
        //fundo da barra
        p.pushStyle();
        p.fill(120); //cinzento
        p.strokeWeight(3);
        p.rect(0, p.height - 70, p.width, 70); //70 é a altura da barra
        p.popStyle();

        //(largura - nr de margens laterais)/ nr de botões
        float buttonWidth = (p.width - (buttons.size() + 1) * margin) / buttons.size();
        float buttonHeight = 40;
        //p.height - 70 = y superior da barra
        //(70 - buttonHeight) / 2 = margin vertical
        float buttonY = p.height - 70 + (70 - buttonHeight) / 2; // Centraliza os botões verticalmente
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            //começa só com o espaço da margem e salta a largura de um botão mais a margem
            button.setPosition(margin + i * (buttonWidth + margin), buttonY);
            button.setSize(buttonWidth, buttonHeight);
            button.display(p);
        }
    }

    public void handleMousePressed(PApplet p) {
        for (Button button : buttons) {
            if (button.isMouseOver(p)) {
                //guarda o estado do botão selecionado
                selectedState = button.getState();
                for (Button btn : buttons) {
                    btn.setSelected(false); //deseleciona todos os botões
                }
                button.setSelected(true); //seleciona o botão clicado
                break;
            }
        }
    }

    public int getSelectedState() {
        return selectedState;
    }
}