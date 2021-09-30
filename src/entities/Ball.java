package entities;

import core.Game;
import core.GameObject;
import core.Handler;
import core.ID;

import java.awt.*;

public class Ball extends GameObject {

    Handler handler;

    public Ball(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        speedX = 3;
        speedY = 3;
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public void tick() {

        x += speedX;
        y += speedY;

        if (y <= 0 || y >= Game.HEIGHT - 60) speedY *= -1;
        else if (x <= 0 || x >= Game.WIDTH - 30) speedX *= -1;

        collision();

    }

    private void collision() {

        for (int i = 0; i < handler.objects.size(); i++) {

            GameObject tempObject = handler.objects.get(i);

            if (tempObject.getId() == ID.Player || tempObject.getId() == ID.Player2) {

                if (getBounds().intersects(tempObject.getBounds())) {

                    speedX *= -1;

                }
            }

        }

    }

    public void render(Graphics graphics) {

        if (id == ID.Ball) {
            graphics.setColor(Color.WHITE);
        }

        graphics.fillRect(x, y, 16, 16);
    }
}
