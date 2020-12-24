package org.rud.tennis.model;

import java.awt.*;

public class BorderModel {
    private int x;
    private int y;
    public int WIDTH;
    public int HEIGHT;

    private Rectangle hitBox;

    public BorderModel(int x, int y, int WIDTH, int HEIGHT) {
        this.x = x;
        this.y = y;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        hitBox = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
}
