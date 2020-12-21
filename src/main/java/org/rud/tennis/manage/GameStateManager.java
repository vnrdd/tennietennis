package org.rud.tennis.manage;

import org.rud.tennis.states.GameState;
import org.rud.tennis.states.MenuState;
import org.rud.tennis.states.SPState;

import java.util.ArrayList;

    public class GameStateManager {

        private ArrayList<GameState> gameStates;
        private int currentState;

        public static final int MENUSTATE = 0;
        public static final int SINGLEPLAYERSTATE = 1;
        public static final int MULTIPLAYERSTATE = 2;
        public static final int HELPSTATE = 3;

        public GameStateManager() {
            gameStates = new ArrayList<GameState>();
            currentState = MENUSTATE;
            gameStates.add(new MenuState(this));
            gameStates.add(new SPState(this));
        }

        public void setState(int state) {
            currentState = state;
            gameStates.get(currentState).init();
        }

        public void update() {
            gameStates.get(currentState).update();
        }

        public void draw(java.awt.Graphics2D g) {
            gameStates.get(currentState).draw(g);
        }

        public void keyPressed(int k) {
            gameStates.get(currentState).keyPressed(k);
        }

        public void keyReleased(int k) {
            gameStates.get(currentState).keyReleased(k);
        }
    }
