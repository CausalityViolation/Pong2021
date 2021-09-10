package core;

import java.awt.*;

public class HUD extends GameObject {

    public HUD(int x, int y, ID id) {
        super(x, y, id);
    }

    public void tick() {

    }

    public void render(Graphics graphics) {

        if (id == ID.PlayerScore) {




            graphics.fillRect(1024/2 - 60, 15, 80, 32);
            graphics.setColor(Color.GREEN);
            graphics.draw3DRect(1024/2 - 60, 15, 80, 32, true);


        } else if (id == ID.Player2Score) {

            graphics.setColor(Color.white);
            graphics.fillRect(1024/2 + 60, 15, 80, 32);
            graphics.setColor(Color.MAGENTA);
            graphics.draw3DRect(1024/2 + 60, 15, 80, 32, true);
        }



    }

}
