package core;

import java.awt.*;

public class HUD extends GameObject {

    public static int playerOneScore = 0;
    public static int playerTwoScore = 0;

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

            graphics.setColor(Color.GREEN);
            graphics.drawRect(1024 / 2 - 60, 15, 80, 32);
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            graphics.drawString(playerOneToString(playerOneScore), 1024 / 2 - 25, 38);


        } else if (id == ID.Player2Score) {

            graphics.setColor(Color.MAGENTA);
            graphics.drawRect(1024 / 2 + 60, 15, 80, 32);
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            graphics.drawString(playerTwoToString(playerTwoScore), 1024 / 2 + 95, 38);
        }


    }

    public String playerOneToString(int p1) {

        return String.valueOf(p1);
    }

    public String playerTwoToString(int p2) {

        return String.valueOf(p2);
    }

}
