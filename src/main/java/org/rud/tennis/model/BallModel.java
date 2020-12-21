package org.rud.tennis.model;

import java.awt.*;

public class BallModel {
    private int x;
    private int y;

    public int xSpeed = 0;
    public int ySpeed = 0;

    private Rectangle hitBox;

    public BallModel(int x, int y, int WIDTH, int HEIGHT){
        this.x = x;
        this.y = y;
        hitBox = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void set(){
    }

    public Rectangle getHitBox(){
        return hitBox;
    }
}
