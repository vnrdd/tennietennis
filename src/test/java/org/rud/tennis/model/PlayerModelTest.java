package org.rud.tennis.model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerModelTest {
    private PlayerModel testing = new PlayerModel(0, 0, 100, 100);

    @Test
    void getX() {
        int expected = 0;
        int actual = testing.getX();
        assertEquals(expected, actual);
    }

    @Test
    void getY() {
        int expected = 0;
        int actual = testing.getY();
        assertEquals(expected, actual);
    }

    @Test
    void set() {
        testing.ySpeed = 2;
        testing.y = 37;
        testing.getHitBox().y = 37;
        testing.set();
        double expected = 0;
        double actual = testing.ySpeed;
        assertEquals(expected, actual);
    }

    @Test
    void testSet() {
        BallModel ball = new BallModel(100, 100, 15, 15);
        ball.setSpeed(2, false);
        testing.y = 37;
        testing.getHitBox().y = 37;
        testing.set(ball, 2);
        double expected = 0;
        double actual = testing.ySpeed;
        assertEquals(expected, actual);
    }

    @Test
    void getHitBox() {
        Rectangle newHitbox = new Rectangle(0, 0, 10, 10);
        Rectangle myHitbox = testing.getHitBox();
        testing.getHitBox().x = 400;
        testing.getHitBox().y = 500;
        boolean expected = false;
        boolean actual = newHitbox.intersects(myHitbox);
        assertEquals(expected, actual);
    }
}