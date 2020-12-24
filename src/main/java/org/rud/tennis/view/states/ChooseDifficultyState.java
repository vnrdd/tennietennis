package org.rud.tennis.view.states;

import org.rud.tennis.view.objects.Background;
import org.rud.tennis.manage.GameStateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ChooseDifficultyState extends GameState {
    private Background bg;
    private String[] options = {"Vegan", "BBQ King"};
    private int currentChoice;
    private int choosedOption = -1;
    private int stateToCall = 0;
    private Font font;
    private Color uncheckedColor;
    private Color checkedColor;

    public ChooseDifficultyState(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            bg = new Background("/gameBg.png");
            font = new Font("TT Hoves DemiBold", Font.PLAIN, 30);
            uncheckedColor = new Color(219, 223, 225);
            checkedColor = new Color(58, 134, 255);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
    }

    public void init(int mod) {
        stateToCall = mod;
    }

    public void update() {
    }

    public int getDifficulty() {
        return choosedOption;
    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setFont(font);
        g.setColor(new Color(252, 163, 17));
        g.drawString("Choose difficulty", 385, 200);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice)
                g.setColor(checkedColor);
            else
                g.setColor(uncheckedColor);
            g.drawString(options[i], 500 - 7 * options[i].length(), 300 + i * 50);
        }
    }

    private void select() {
        choosedOption = currentChoice;
        gsm.setState(stateToCall, choosedOption);
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER)
            select();
        if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice == -1)
                currentChoice = options.length - 1;
        }
        if (k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if (currentChoice == options.length)
                currentChoice = 0;
        }
        if (k == KeyEvent.VK_ESCAPE)
            gsm.setState(GameStateManager.MENUSTATE);
    }

    public void keyReleased(int k) {
    }
}
