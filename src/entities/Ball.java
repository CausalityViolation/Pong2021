package entities;

import core.Game;
import core.GameObject;
import core.ID;

import java.awt.*;
import java.util.Random;

public class Ball extends GameObject {

    Random random = new Random();


    public Ball(int x, int y, ID id) {
        super(x, y, id);

        speedX = 3;
        speedY = 3;

    }

    public void tick() {

        x += speedX;
        y += speedY;

        if (y <= 0 || y >= Game.HEIGHT - 60) speedY *= -1;
        else if (x <= 0 || x >= Game.WIDTH - 30) speedX *= -1;

    }

    public void render(Graphics graphics) {

        if (id == ID.Ball) {
            graphics.setColor(Color.WHITE);
        }

        graphics.fillRect(x, y, 16, 16);
    }
}
