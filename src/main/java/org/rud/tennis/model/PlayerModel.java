package org.rud.tennis.model;

import java.awt.*;

public class PlayerModel {
    private int x;
    public int y;
    public boolean bot;

    public double ySpeed = 0;

    private Rectangle hitBox;

    public PlayerModel(int x, int y, int WIDTH, int HEIGHT) {
        this.x = x;
        this.y = y;
        hitBox = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void set() {
        hitBox.y += ySpeed;
        if (hitBox.intersects(new Rectangle(30, 37, 924, 12))
                || hitBox.intersects(new Rectangle(30, 660, 924, 12))) {
            hitBox.y -= ySpeed;
            ySpeed = 0;
            y = hitBox.y;
        }
        y += ySpeed;
    }

    public void set(BallModel ball, int speedBorder) {
        if (ball.getY() < this.y)
            ySpeed = -2.5;
        else if (ball.getY() > this.y)
            ySpeed = 2.5;
        if (ball.getXSpeed() < 0)
            ySpeed = 0;

        hitBox.y += ySpeed;
        if (hitBox.intersects(new Rectangle(30, 37, 924, 12))
                || hitBox.intersects(new Rectangle(30, 660, 924, 12))) {
            hitBox.y -= ySpeed;
            ySpeed = 0;
            y = hitBox.y;
        }
        y += ySpeed;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
}
