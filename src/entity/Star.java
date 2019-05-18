package entity;

import window.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Star extends GameObject {

    BufferedImage image;
    public double angle;

    public Star(Game game, int x, int y) {
        super(game, x, y);
        this.width = 64;
        this.height = 64;

        try {
            image = ImageIO.read(new File("images/star.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }

        this.id = RpgObjectType.STAR;
        angle =
                Math.atan2(game.mouseManager.userPressX-game.width/2, game.mouseManager.userPressY-game.height/2);

    }

    @Override
    public void move() {
        x += (float) (2*Math.cos(angle) + 15 * Math.sin(angle));
        y += (float) (2*Math.sin(angle) + 15 * Math.cos(angle));

        checkRpgObjects();

    }

    private void checkRpgObjects() {

        for (GameObject item : game.listRpgObject){
            int obj_left = item.x;
            int obj_right = item.x + item.width;
            int obj_top = item.y;
            int obj_bottom = item.y + item.height;

            boolean xcoll = ((x<obj_right) && ((x+width)>obj_left));
            boolean ycoll = ((y+5<obj_bottom) && ((y+height-2)>obj_top));

            if (xcoll && ycoll) {
                if (item.id == RpgObjectType.ENEMY_ZOMBE) {
                    game.listRemoveObjects.add(item); //для удаления объект
                    game.listRemoveObjects.add(this); //для удаления пули
                }
            }
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x-game.offsetX, y-game.offsetY, width, height, null);
    }
}
