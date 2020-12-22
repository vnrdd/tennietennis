package org.rud.tennis.view.states;

import org.rud.tennis.manage.Background;
import org.rud.tennis.manage.GameStateManager;
import org.rud.tennis.view.objects.Ball;
import org.rud.tennis.view.objects.Border;
import org.rud.tennis.view.objects.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class PlayWithBotState extends GameState implements Pitch {
    private Background bg;
    private Background gameOverBg;
    private Ball ball;
    private ArrayList<Border> goalBorders;
    private ArrayList<Border> walls;
    private ArrayList<Player> players;
    private ArrayList<Integer> scores;
    private int singleScore;
    private int ballsToWin = 2;
    private boolean gameOver = false;

    public PlayWithBotState(GameStateManager gsm){
        this.gsm = gsm;
        try {
            bg = new Background("/splitBg.png");
            gameOverBg = new Background("/gameBg.png");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        scores = new ArrayList<Integer>();
        scores.add(0);
        scores.add(0);
        players = new ArrayList<Player>();
        players.add(new Player(124, 292, 1));
        players.add(new Player(888, 292, 2));
        ball = new Ball(506, 353);
        goalBorders = new ArrayList<Border>();
        goalBorders.add(new Border(38, 48, 12, 612, "/goalBorder.png"));
        goalBorders.add(new Border(949, 48, 12, 612, "/goalBorder2.png"));
        walls = new ArrayList<Border>();
        walls.add(new Border(38, 37, 924, 12, "/wall.png"));
        walls.add(new Border(38, 660, 924, 12, "/wall.png"));
    }
    public void init() {

    }

    public void update() {
        if(ball.getModel().getYSpeed() > 0)
            players.get(1).getModel().ySpeed = 1;
        else
            players.get(1).getModel().ySpeed = -1;
        for(Player p : players)
            p.getModel().set();
        ball.getModel().set(this);
        winCheck();
    }

    public void winCheck(){
        if(scores.get(0) == ballsToWin || scores.get(1) == ballsToWin)
            gameOver = true;
    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setColor(new Color(219, 223, 225));
        g.setFont(new Font("TT Hoves DemiBold", Font.PLAIN, 100));
        g.drawString(String.valueOf(scores.get(0)), 380, 150);
        g.drawString(String.valueOf(scores.get(1)), 560, 150);
        for(Player p : players)
            p.draw(g);
        for(Border b : goalBorders)
            b.draw(g);
        for(Border w : walls)
            w.draw(g);
        ball.draw(g);

        if(gameOver){
            gameOverBg.draw(g);
            g.setColor(new Color(252, 163, 17));
            g.setFont(new Font("TT Hoves DemiBold", Font.PLAIN, 50));
            g.drawString("GAME OVER", 330, 200);

            g.setColor(new Color(219, 223, 225));
            g.setFont(new Font("TT Hoves DemiBold", Font.PLAIN, 30));
            g.drawString("Press ESC to leave", 340, 500);
            if(scores.get(0) == ballsToWin)
                g.drawImage(Images.blueWin, 300, 230, null);
            else
                g.drawImage(Images.redWin, 300, 230, null);
        }
    }

    public void goal(int pos){
        scores.set((pos + 1) % 2, scores.get((pos + 1) % 2) + 1);
        ball = new Ball(506, 353);
        players.set(1, new Player(860, 292, 2));
    }

    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ESCAPE) {
            reset();
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if(k == KeyEvent.VK_UP)
            players.get(0).getModel().ySpeed = -2;
        if(k == KeyEvent.VK_DOWN)
            players.get(0).getModel().ySpeed = 2;
    }

    public void keyReleased(int k) {
        if(k == KeyEvent.VK_UP || k == KeyEvent.VK_DOWN)
            players.get(0).getModel().ySpeed = 0;
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

    public void setScore(int player, int newScore) {
        scores.set(player, newScore);
    }

    public boolean getGameOver() {
        return false;
    }

    public void setGameOver(boolean value) {

    }

    public int getSingleScore(){
        return singleScore;
    }
    public void setSingleScore(int score){
        singleScore = score;
    }

    private void reset(){
        ball = new Ball(506, 353);
        players.set(1, new Player(860, 292, 2));
        scores.set(0, 0);
        scores.set(1, 0);
        gameOver = false;
    }
}