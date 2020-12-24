package org.rud.tennis.view.states;

import org.rud.tennis.view.objects.Background;
import org.rud.tennis.manage.GameStateManager;
import org.rud.tennis.view.objects.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SquashState extends GameState implements Pitch {
    private Background bg;
    private Ball ball;
    private ArrayList<Border> goalBorders;
    private ArrayList<Border> walls;
    private ArrayList<Player> players;
    private ArrayList<Integer> scores;
    private int singleScore = 0;
    private boolean gameOver = false;

    public SquashState(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            bg = new Background("/gameBg.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        scores = new ArrayList<Integer>();
        scores.add(0);
        players = new ArrayList<Player>();
        players.add(new Player(118, 292, 1));
        ball = new Ball(500, 353);
        goalBorders = new ArrayList<Border>();
        goalBorders.add(new Border(30, 48, 12, 612, "/goalBorder.png"));
        walls = new ArrayList<Border>();
        walls.add(new Border(30, 37, 924, 12, "/wall.png"));
        walls.add(new Border(30, 660, 924, 12, "/wall.png"));
        walls.add(new Border(953, 37, 12, 635, "/wall.png"));
    }

    public void init() {
    }

    public void init(int mod) {
        ball.getModel().setSpeed(mod + 2, true);
    }

    public void update() {
        if (!gameOver) {
            players.get(0).getModel().set();
            ball.getModel().set(this);
        }
    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setColor(new Color(219, 223, 225));
        g.setFont(new Font("TT Hoves DemiBold", Font.PLAIN, 100));
        g.drawString(String.valueOf(singleScore), 470, 150);
        players.get(0).draw(g);
        ball.draw(g);
        goalBorders.get(0).draw(g);
        for (Border b : walls)
            b.draw(g);
        if (gameOver) {
            bg.draw(g);
            g.setColor(new Color(252, 163, 17));
            g.setFont(new Font("TT Hoves DemiBold", Font.PLAIN, 50));
            g.drawString("Your score: " + String.valueOf(singleScore), 330, 200);

            g.setColor(new Color(219, 223, 225));
            g.setFont(new Font("TT Hoves DemiBold", Font.PLAIN, 30));
            g.drawString("Press ESC to leave", 340, 500);
        }
    }

    public ArrayList<Border> getWalls() {
        return walls;
    }

    public ArrayList<Border> getGoalBorders() {
        return goalBorders;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getScore(int player) {
        return scores.get(player);
    }

    public void setScore(int player, int score) {
        scores.set(player, score);
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean value) {
        gameOver = value;
    }

    public void goal(int pos) {
        scores.set(pos, scores.get(pos) + 1);
        ball = new Ball(506, 353);
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE) {
            reset();
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if (k == KeyEvent.VK_UP) {
            players.get(0).getModel().ySpeed = -2;
        }
        if (k == KeyEvent.VK_DOWN) {
            players.get(0).getModel().ySpeed = 2;
        }
    }

    public void keyReleased(int k) {
        if (k == KeyEvent.VK_UP || k == KeyEvent.VK_DOWN)
            players.get(0).getModel().ySpeed = 0;
    }

    public int getSingleScore() {
        return singleScore;
    }

    public void setSingleScore(int score) {
        singleScore = score;
    }

    private void reset() {
        ball = new Ball(500, 353);
        gameOver = false;
        scores.set(0, 0);
        singleScore = 0;
    }
}
