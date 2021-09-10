package core;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    Random random = new Random();

    public Player(int x, int y, ID id) {
        super(x, y, id);

        speedX = random.nextInt(5)+1;
        speedY = random.nextInt(5)+1;

    }

    @Override
    public void tick() {

        x += speedX;
        y += speedY;

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.MAGENTA);
        graphics.fillRect(x, y, 32, 32);
    }

}
