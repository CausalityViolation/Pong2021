package entities;

import core.Game;
import core.GameObject;
import core.ID;

import java.awt.*;

public class Player extends GameObject {

    public Player(int x, int y, ID id) {
        super(x, y, id);


    }


    public void tick() {

        x += speedX;
        y += speedY;

        x = Game.clamp(x, 0, Game.WIDTH - 32);
        y = Game.clamp(y, 0, Game.HEIGHT - 100);

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
