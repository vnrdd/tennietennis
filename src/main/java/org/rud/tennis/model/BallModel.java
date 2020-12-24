package org.rud.tennis.model;

import org.rud.tennis.view.states.Pitch;

import java.awt.*;
import java.util.Random;

public class BallModel {
    private int x;
    private int y;

    private double xSpeed = 2;
    private double ySpeed = 0;

    private Rectangle hitBox;

    public BallModel(int x, int y, int WIDTH, int HEIGHT) {
        this.x = x;
        this.y = y;
        hitBox = new Rectangle(x, y, WIDTH, HEIGHT);
        setSpeed(2, true);
    }

    public void setSpeed(int border, boolean random) {
        if (random) {
            int buf = (new Random()).nextInt(2) - 1;
            if (buf == 0)
                ySpeed = -border;
            else
                ySpeed = border;
        } else
            ySpeed = border;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getYSpeed() {
        return ySpeed;
    }

    public double getXSpeed() {
        return xSpeed;
    }

    public void set(Pitch pitch) {
        for (int i = 0; i < pitch.getPlayers().size(); ++i) {
            if (hitBox.intersects(pitch.getPlayers().get(i).getModel().getHitBox())) {
                xSpeed = -xSpeed;
                int newScore = pitch.getSingleScore() + 1;
                pitch.setSingleScore(newScore);
            }
        }
        for (int i = 0; i < pitch.getWalls().size(); ++i) {
            if (hitBox.intersects(pitch.getWalls().get(i).getModel().getHitBox())) {
                if (i == 0 || i == 1)
                    ySpeed = -ySpeed;
                else
                    xSpeed = -xSpeed;
            }
        }
        for (int i = 0; i < pitch.getGoalBorders().size(); ++i) {
            if (hitBox.intersects(pitch.getGoalBorders().get(i).getModel().getHitBox())) {
                pitch.goal(i);
                xSpeed = 0;
                ySpeed = 0;
                pitch.setGameOver(!pitch.getGameOver());
            }
        }

        x += xSpeed;
        y += ySpeed;
        hitBox.x += xSpeed;
        hitBox.y += ySpeed;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
}
