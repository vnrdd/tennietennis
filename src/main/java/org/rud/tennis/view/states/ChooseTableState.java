package org.rud.tennis.view.states;

import org.rud.tennis.manage.GameStateManager;
import org.rud.tennis.network.Client;
import org.rud.tennis.view.objects.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ChooseTableState extends GameState {
    private Background bg;
    private int currentChoice = 0;
    private String[] options = {"Join the game", "New game"};
    private Font font;
    private Color uncheckedColor;
    private Color checkedColor;

    public ChooseTableState(GameStateManager gsm) {
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

    @Override
    public void init() {
    }

    @Override
    public void init(int mod) {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);

        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice)
                g.setColor(checkedColor);
            else
                g.setColor(uncheckedColor);
            g.drawString(options[i], 500 - 7 * options[i].length(), 300 + i * 50);
        }
    }

    public void select() {
        if (currentChoice == 0) {
            this.gsm.posInGame = 1;
            gsm.setState(GameStateManager.MULTIPLAYERSTATE);
        }

        if (currentChoice == 1) {
            this.gsm.posInGame = 0;
            gsm.setState(GameStateManager.MULTIPLAYERSTATE);
        }
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE)
            gsm.setState(GameStateManager.MENUSTATE);
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

    @Override
    public void keyReleased(int k) {

    }
}
