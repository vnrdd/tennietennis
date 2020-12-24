package org.rud.tennis.network;

import org.rud.tennis.view.objects.Player;

import java.awt.*;
import java.util.ArrayList;

public class Table implements Runnable {
    private ArrayList<Rectangle> players;
    private ArrayList<String> names;
    public boolean gameStarted = false;
    public int player1Y;
    public int player2Y;

    public Table() {
        players = new ArrayList<>();
        names = new ArrayList<>();
    }

    public void addPlayer(String name) {
        if (players.size() < 2 && !names.contains(name)) {
            if (players.size() == 0)
                players.add(new Rectangle(124, 292, 22, 123));
            else
                players.add(new Rectangle(888, 292, 22, 123));
            names.add(name);
        }
    }

    public int getPos() {
        return players.size();
    }

    public boolean canStart() {
        return players.size() == 2;
    }

    public void init() {

    }

    public void update() {
        System.out.println(player1Y);
    }

    @Override
    public void run() {
        update();
    }
}
