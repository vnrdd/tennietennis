package org.rud.tennis.view.objects;

import org.rud.tennis.model.BallModel;
import org.rud.tennis.model.PlayerModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Ball {
    private BallModel model;
    private BufferedImage texture;

    public Ball(int x, int y) {
        try {
            texture = ImageIO.read(getClass().getResourceAsStream("/ball.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model = new BallModel(x, y, 35, 35);
    }

    public BallModel getModel() {
        return model;
    }

    public void draw(Graphics2D g) {
        g.drawImage(texture, model.getX(), model.getY(), 35, 35, null);
    }
}
