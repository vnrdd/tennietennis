package org.rud.tennis.view.states;

import org.rud.tennis.manage.GameStateManager;
import org.rud.tennis.network.Client;
import org.rud.tennis.view.objects.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

public class MPState extends GameState {
    private Background bg;
    private Client client;
    private String name;
    private String freeTables = "";
    private int currentChoice = 0;
    private ArrayList<String> tableOptions;
    private Font font = new Font("TT Hoves DemiBold", Font.PLAIN, 30);
    private Color uncheckedColor = new Color(219, 223, 225);
    private Color checkedColor = new Color(58, 134, 255);

    public MPState(GameStateManager gsm) {
        this.gsm = gsm;
        int pos = (new Random()).nextInt(14);
        name = "Player"+String.valueOf(pos);
        String[] options = {"localhost", "9876", name};
        try {
            bg = new Background("/gameBg.png");
            tableOptions = new ArrayList<String>();
            client = new Client(options);
            client.load(options);
            client.send(name + " connected");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void init() {
        tableOptions.clear();
        freeTables = "";
        while(!freeTables.contains("Table")) {
            client.send("@getTables");
            freeTables = client.receivedMessage;
        }
        String[] buf = freeTables.split("&");
        tableOptions.addAll(Arrays.asList(buf));
        tableOptions.remove(tableOptions.size()-1);
        tableOptions.add("+ New table");
    }

    public void init(int mod) {

    }

    public void update() {
        if(client.receivedMessage.trim().equals("newTable"))
            init();
    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setFont(font);
        for(int i = 0; i < tableOptions.size(); i++) {
            if (i == currentChoice)
                g.setColor(checkedColor);
            else
                g.setColor(uncheckedColor);
            g.drawString(tableOptions.get(i), 300, 200 + i * 50);
        }
    }

    public void select(){
        if(currentChoice == tableOptions.size()-1) {
            client.send("@new");
            init();
        }
    }

    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ESCAPE){
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if(k == KeyEvent.VK_ENTER)
           select();
        if(k == KeyEvent.VK_UP) {
            currentChoice--;
            if(currentChoice == -1)
                currentChoice = tableOptions.size() - 1;
        }
        if(k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if(currentChoice == tableOptions.size())
                currentChoice = 0;
        }
    }

    public void keyReleased(int k) {

    }
}
