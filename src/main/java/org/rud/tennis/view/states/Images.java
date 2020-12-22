package org.rud.tennis.view.states;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {
    static BufferedImage blueWin;

    static {
        try {
            blueWin = ImageIO.read(Images.class.getResourceAsStream("/blueWin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static BufferedImage redWin;

    static {
        try {
            redWin = ImageIO.read(Images.class.getResourceAsStream("/redWin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
