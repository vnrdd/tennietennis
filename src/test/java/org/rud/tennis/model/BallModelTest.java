package org.rud.tennis.model;

import java.awt.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BallModelTest {
    private BallModel testing = new BallModel(0, 0, 10, 10);

    @Test
    void setSpeed() {
        testing.setSpeed(3, false);
        double expected = 3;
        double actual = testing.getYSpeed();
        assertEquals(expected, actual);
    }

    @Test
    void setX() {
        testing.setX(100);
        int expected = 100;
        int actual = testing.getX();
        assertEquals(expected, actual);
    }

    @Test
    void setY() {
        testing.setY(100);
        int expected = 100;
        int actual = testing.getY();
        assertEquals(expected, actual);
    }

    @Test
    void getX() {
        testing.setX(100);
        int expected = 100;
        int actual = testing.getX();
        assertEquals(expected, actual);
    }

    @Test
    void getY() {
        testing.setY(100);
        int expected = 100;
        int actual = testing.getY();
        assertEquals(expected, actual);
    }

    @Test
    void getYSpeed() {
        testing.setSpeed(2, false);
        double actual = testing.getYSpeed();
        double expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    void getXSpeed() {
        double actual = testing.getXSpeed();
        double expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    void getHitBox() {
        Rectangle newHitbox = new Rectangle(0, 0, 10, 10);
        Rectangle myHitbox = testing.getHitBox();
        boolean expected = true;
        boolean actual = newHitbox.intersects(myHitbox);
        assertEquals(expected, actual);
    }
}