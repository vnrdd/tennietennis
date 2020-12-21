package org.rud.tennis.view.states;

import org.rud.tennis.manage.Background;
import org.rud.tennis.manage.GameStateManager;
import org.rud.tennis.view.objects.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SquashState extends GameState{
    private Background bg;
    private Ball ball;
    private Border goalBorder;
    private ArrayList<Border> walls;
    private Player player;
    private int score = 0;

    public SquashState(GameStateManager gsm){
        this.gsm = gsm;
        try {
            bg = new Background("/gameBg.png");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        player = new Player(118, 292);
        ball = new Ball(500, 353);
        goalBorder = new Border(30, 48, 12, 612, "/goalBorder.png");
        walls = new ArrayList<Border>();
        walls.add(new Border(30, 37, 924, 12, "/wall.png"));
        walls.add(new Border(30, 660, 924, 12, "/wall.png"));
        walls.add(new Border(953, 37, 12, 635, "/wall.png"));
    }

    public void init() {
    }

    public void update() {
        player.getModel().set();
    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setColor(new Color(219, 223, 225));
        g.setFont(new Font("TT Hoves DemiBold", Font.PLAIN, 100));
        g.drawString(String.valueOf(score), 470, 150);
        player.draw(g);
        ball.draw(g);
        goalBorder.draw(g);
        for(Border b : walls)
            b.draw(g);
    }

    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ESCAPE)
            gsm.setState(GameStateManager.MENUSTATE);
        if(k == KeyEvent.VK_UP) {
            player.getModel().ySpeed = -2;
        }
        if(k == KeyEvent.VK_DOWN) {
            player.getModel().ySpeed = 2;
        }
    }

    public void keyReleased(int k) {
        if(k == KeyEvent.VK_UP || k == KeyEvent.VK_DOWN)
            player.getModel().ySpeed = 0;
    }
}