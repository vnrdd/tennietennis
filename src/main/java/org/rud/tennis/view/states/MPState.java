package org.rud.tennis.view.states;

import org.rud.tennis.manage.GameStateManager;
import org.rud.tennis.network.Client;
import org.rud.tennis.view.objects.Background;
import org.rud.tennis.view.objects.Ball;
import org.rud.tennis.view.objects.Border;
import org.rud.tennis.view.objects.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

public class MPState extends GameState implements Pitch {
    private Background bg;
    private ArrayList<Border> goalBorders;
    private ArrayList<Border> walls;
    private ArrayList<Player> players;
    private Ball ball;
    private int flag;
    private String nameMain;
    public Client client;
    private String name;
    private boolean gameStarted = false;
    private int currentChoice = 0;
    private String message = "";
    private int myPos;
    private ArrayList<String> tableOptions;
    private Font font = new Font("TT Hoves DemiBold", Font.PLAIN, 30);
    private Color uncheckedColor = new Color(219, 223, 225);
    private Color checkedColor = new Color(58, 134, 255);
    private int score1 = 0;
    private int score2 = 0;

    public MPState(GameStateManager gsm) {
        this.gsm = gsm;
        int pos = (new Random()).nextInt(14);
        name = "Player" + String.valueOf(pos);
        String[] options = {"localhost", "5555", name};
        try {
            bg = new Background("/splitBg.png");
            players = new ArrayList<Player>();
            players.add(new Player(124, 292, 1));
            players.add(new Player(888, 292, 2));
            tableOptions = new ArrayList<String>();
            client = new Client(options);
            client.load(options);
            client.send(name + " connected");
            goalBorders = new ArrayList<Border>();
            goalBorders.add(new Border(38, 48, 12, 612, "/goalBorder.png"));
            goalBorders.add(new Border(949, 48, 12, 612, "/goalBorder2.png"));
            walls = new ArrayList<Border>();
            walls.add(new Border(38, 37, 924, 12, "/wall.png"));
            walls.add(new Border(38, 660, 924, 12, "/wall.png"));
            setFlag(gsm.posInGame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFlag(int flag) {
        this.flag = flag;
        if (flag == 1)
            client.send("@f");
        if (flag == 0)
            client.send("@s");
    }

    public void init() {
        flag = gsm.posInGame;
        if (flag == 1) {
            nameMain = "@f";
            myPos = 0;
        }
        if (flag == 0) {
            nameMain = "@s";
            myPos = 1;
        }

        System.out.println(client.receivedMessage);
    }

    public void init(int mod) {
    }

    public void update() {
        players.get(0).getModel().set();
        players.get(1).getModel().set();

        message = client.receivedMessage;
        if (!gameStarted)
            ball = new Ball(480, 353);
        if (nameMain.equals("@f"))
            client.send(nameMain + " Y " + players.get(myPos).getModel().y + " " + ball.getModel().getX() + " " + ball.getModel().getY());
        else
            client.send(nameMain + " Y " + players.get(myPos).getModel().y);
        if (!message.equals("")) {
            if (message.contains("Y") && !message.split(" ")[0].trim().equals(nameMain)) {
                players.get((myPos + 1) % 2).getModel().y = Integer.parseInt(message.split(" ")[2].trim());
                players.get((myPos + 1) % 2).getModel().getHitBox().y = Integer.parseInt(message.split(" ")[2].trim());
                if (!gameStarted) {
                    gameStarted = true;
                    ball = new Ball(480, 353);
                    if (nameMain.equals("@f"))
                        ball.getModel().setSpeed(3, false);
                }
                if (gameStarted && nameMain.equals("@f")) {
                    ball.getModel().set(this);
                } else {
                    ball.getModel().setX(Integer.parseInt(message.split(" ")[3].trim()));
                    ball.getModel().setY(Integer.parseInt(message.split(" ")[4].trim()));
                }
            }
        }
    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        for (Player p : players)
            p.draw(g);
        if (!gameStarted) {
            g.setFont(font);
            g.setColor(new Color(252, 163, 17));
            g.drawString("Waiting for the opponent...", 350, 150);
        }
        for (Border b : goalBorders)
            b.draw(g);
        for (Border w : walls)
            w.draw(g);
        if (gameStarted) {
            ball.draw(g);
        }
    }

    public void select() {
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE) {
            reset();
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if (k == KeyEvent.VK_ENTER)
            select();
        if (k == KeyEvent.VK_UP) {
            players.get(myPos).getModel().ySpeed = -2;
        }
        if (k == KeyEvent.VK_DOWN) {
            players.get(myPos).getModel().ySpeed = 2;
        }
    }

    public void reset() {
        gameStarted = false;
        players.get(myPos).getModel().ySpeed = 0;
        score1 = 0;
        score2 = 0;
    }

    public void keyReleased(int k) {
        if (k == KeyEvent.VK_UP || k == KeyEvent.VK_DOWN)
            players.get(myPos).getModel().ySpeed = 0;
    }

    @Override
    public ArrayList<Border> getWalls() {
        return walls;
    }

    @Override
    public ArrayList<Border> getGoalBorders() {
        return goalBorders;
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return players;
    }

    @Override
    public int getScore(int player) {
        return 0;
    }

    @Override
    public void setScore(int player, int newScore) {

    }

    @Override
    public boolean getGameOver() {
        return false;
    }

    @Override
    public void setGameOver(boolean value) {

    }

    @Override
    public void goal(int pos) {
        ball = new Ball(480, 353);
        if (nameMain.equals("%s")) {
            ball.getModel().setSpeed(3, false);
        }
    }

    @Override
    public int getSingleScore() {
        return 0;
    }

    @Override
    public void setSingleScore(int score) {

    }
}
