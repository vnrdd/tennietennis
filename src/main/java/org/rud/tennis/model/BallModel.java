package org.rud.tennis.model;

import org.rud.tennis.view.states.SquashState;

import java.awt.*;
import java.util.Random;

public class BallModel {
    private int x;
    private int y;

    private int xSpeed = 2;
    private int ySpeed = 0;

    private Rectangle hitBox;

    public BallModel(int x, int y, int WIDTH, int HEIGHT){
        this.x = x;
        this.y = y;
        hitBox = new Rectangle(x, y, WIDTH, HEIGHT);
        while(ySpeed == 0)
            ySpeed = (new Random()).nextInt(6) - 3;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void set(SquashState pitch){
        if(hitBox.intersects(pitch.getPlayer().getModel().getHitBox())) {
            xSpeed = -xSpeed;
            pitch.score++;
        }
        for(int i = 0; i < pitch.getWalls().size(); ++i){
            if(hitBox.intersects(pitch.getWalls().get(i).getModel().getHitBox())) {
                if(i == 0 || i == 1)
                    ySpeed = -ySpeed;
                else
                    xSpeed = -xSpeed;
            }
        }
        if(hitBox.intersects(pitch.getGoalBorder().getModel().getHitBox())) {
            xSpeed = 0;
            ySpeed = 0;
            pitch.gameOver = true;
        }
        x += xSpeed;
        y += ySpeed;
        hitBox.x += xSpeed;
        hitBox.y += ySpeed;
    }

    public Rectangle getHitBox(){
        return hitBox;
    }
}
