package core;

import java.awt.*;

public class HUD extends GameObject {

    public HUD(int x, int y, ID id) {
        super(x, y, id);
    }

    public void tick() {

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 80, 32);
    }

    public void render(Graphics graphics) {

        if (id == ID.PlayerScore) {

            graphics.fillRect(1024 / 2 - 60, 15, 80, 32);
            graphics.setColor(Color.GREEN);
            graphics.draw3DRect(1024 / 2 - 60, 15, 80, 32, true);
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            graphics.drawString("5", 1024 / 2 - 25, 38);


        } else if (id == ID.Player2Score) {

            graphics.setColor(Color.white);
            graphics.fillRect(1024 / 2 + 60, 15, 80, 32);
            graphics.setColor(Color.MAGENTA);
            graphics.draw3DRect(1024 / 2 + 60, 15, 80, 32, true);
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            graphics.drawString("5", 1024 / 2 + 95, 38);
        }


    }

}
