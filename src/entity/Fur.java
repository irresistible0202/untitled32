package entity;

import window.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Fur extends GameObject {

    int width, height;

    BufferedImage image;

    public Fur(Game game, int x, int y) {
        super(game, x, y);
        this.width = 64;
        this.height = 86;

        try {
            image = ImageIO.read(new
                    File("images/inventary.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.id = RpgObjectType.FUR;
    }


    public  void render(Graphics g) {

        g.drawImage(image.getSubimage(256, 132, 66, 97), x-game.offsetX, y-game.offsetY, width, height, null);
    }
}
