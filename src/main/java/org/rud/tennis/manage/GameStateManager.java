package org.rud.tennis.manage;

import org.rud.tennis.view.states.*;

import java.util.ArrayList;

    public class GameStateManager {

        private ArrayList<GameState> gameStates;
        private int currentState;

        public static final int MENUSTATE = 0;
        public static final int SINGLEPLAYERSTATE = 1;
        public static final int MULTIPLAYERSTATE = 2;
        public static final int HELPSTATE = 3;

        // SINGLEPLAYER LEVEL
        public static final int SQUASHSTATE = 2; // CHANGE ON 4 LATER
        public static final int PLAYWITHBOTSTATE = 3; //CHANGE ON 5 LATER

        public GameStateManager() {
            gameStates = new ArrayList<GameState>();
            currentState = MENUSTATE;
            gameStates.add(new MenuState(this));
            gameStates.add(new SPState(this));
            gameStates.add(new SquashState(this));
            gameStates.add(new PlayWithBotState(this));
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
