package org.rud.tennis.view.objects;

import org.rud.tennis.model.BorderModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Border {
    private BorderModel model;
    private BufferedImage texture;

    public Border(int x, int y, int WIDTH, int HEIGHT, String filename) {
        try {
            texture = ImageIO.read(getClass().getResourceAsStream(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model = new BorderModel(x, y, WIDTH, HEIGHT);
    }

    public BorderModel getModel() {
        return model;
    }

    public void draw(Graphics2D g) {
        g.drawImage(texture, model.getX(), model.getY(), model.WIDTH, model.HEIGHT, null);
    }
}
