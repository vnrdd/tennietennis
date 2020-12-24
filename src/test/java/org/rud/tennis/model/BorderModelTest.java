package org.rud.tennis.model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BorderModelTest {
    private BorderModel testing = new BorderModel(0, 0, 20, 200);

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
    void getHitBox() {
        Rectangle newHitbox = new Rectangle(0, 0, 10, 10);
        Rectangle myHitbox = testing.getHitBox();
        boolean expected = true;
        boolean actual = newHitbox.intersects(myHitbox);
        assertEquals(expected, actual);
    }
}