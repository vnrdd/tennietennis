package org.rud.tennis.view.states;

import org.rud.tennis.view.objects.Background;
import org.rud.tennis.manage.GameStateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {
    private Background bg;
    private int currentChoice = 0;
    private String[] options = {"Singleplayer", "Multiplayer", "Settings", "Quit"};
    private Font font;
    private Color uncheckedColor;
    private Color checkedColor;

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            bg = new Background("/mainBg.png");
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

    }

    public void update() {
    }

    public void draw(Graphics2D g) {
        bg.draw(g);

        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice)
                g.setColor(checkedColor);
            else
                g.setColor(uncheckedColor);
            g.drawString(options[i], 500 - 7 * options[i].length(), 400 + i * 50);
        }
    }

    private void select() {
        if (currentChoice == 0)
            gsm.setState(GameStateManager.SINGLEPLAYERSTATE);
        if (currentChoice == 1) {
            gsm.setState(GameStateManager.CHOOSETABLESTATE);
        }
        if (currentChoice == 2)
            gsm.setState(GameStateManager.SETTINGSSTATE);
        if (currentChoice == 3)
            System.exit(0);
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
    }

    public void keyReleased(int k) {
    }
}