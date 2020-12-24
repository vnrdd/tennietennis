package org.rud.tennis.view.states;

import org.rud.tennis.view.objects.Border;
import org.rud.tennis.view.objects.Player;

import java.util.ArrayList;

public interface Pitch {
    public ArrayList<Border> getWalls();

    public ArrayList<Border> getGoalBorders();

    public ArrayList<Player> getPlayers();

    public int getScore(int player);

    public void setScore(int player, int newScore);

    public boolean getGameOver();

    public void setGameOver(boolean value);

    public void goal(int pos);

    public int getSingleScore();

    public void setSingleScore(int score);
}
