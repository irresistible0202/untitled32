package entity;

import window.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Flag extends GameObject {
    BufferedImage image;


    public Flag(Game game, int x, int y) {
        super(game, x, y);
        this.width = 40;
        this.height = 40;

        try {
            image = ImageIO.read(new File("images/inventary.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.id = RpgObjectType.FLAG;
    }

    @Override
    public void render(Graphics g) {
       g.drawImage(image.getSubimage(161, 195, 32, 56), x-game.offsetX, y-game.offsetY,null);
    }
}
