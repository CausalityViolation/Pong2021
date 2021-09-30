package entities;

import core.Game;
import core.GameObject;
import core.Handler;
import core.ID;

import java.awt.*;

public class Player extends GameObject {

    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 8, 64);
    }


    public void tick() {

        x += speedX;
        y += speedY;

        x = Game.clamp(x, 0, Game.WIDTH - 32);
        y = Game.clamp(y, 0, Game.HEIGHT - 100);

        collision();

    }

    private void collision() {

        for (int i = 0; i < handler.objects.size(); i++) {

            GameObject tempObject = handler.objects.get(i);

            if (tempObject.getId() == ID.Ball) {

                if (getBounds().intersects(tempObject.getBounds())) {
                    //collision code


                }
            }

        }

    }


    public void render(Graphics graphics) {

        if (id == ID.Player) {
            graphics.setColor(Color.MAGENTA);

        } else if (id == ID.Player2) {
            graphics.setColor(Color.GREEN);
        }

        graphics.fillRect(x, y, 8, 64);

    }


}
