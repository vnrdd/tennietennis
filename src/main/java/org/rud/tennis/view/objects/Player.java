package org.rud.tennis.view.objects;

import org.rud.tennis.model.PlayerModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
    private PlayerModel model;
    private BufferedImage texture;

    public Player(int x, int y){
        try{
            texture = ImageIO.read(getClass().getResourceAsStream("/player.png"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        model = new PlayerModel(x, y, 22, 123);
    }

    public PlayerModel getModel(){
        return model;
    }

    public void draw(Graphics2D g){
        g.drawImage(texture, model.getX(), model.getY(), 22, 123, null);
    }
}
