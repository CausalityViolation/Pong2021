package core;

import entities.Ball;
import entities.Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;


public class Menu extends MouseAdapter {

    private final Game game;
    private final Handler handler;

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent event) {

        int mx = event.getX();
        int my = event.getY();

        if (game.gameState == Game.STATE.Menu) {


            if (mouseOver(mx, my, 450, 200, 200, 64)) {

                game.gameState = Game.STATE.Game;

                Ball ball = new Ball(Game.WIDTH / 2, Game.HEIGHT / 2, ID.Ball, handler);
                handler.addObject(new Player(Game.WIDTH / 2 + 450, Game.HEIGHT / 2 - 50, ID.Player, handler));
                handler.addObject(new Player(Game.WIDTH / 2 - 480, Game.HEIGHT / 2 - 50, ID.Player2, handler));
                handler.addObject(ball);
                handler.addObject(new HUD(Game.WIDTH, Game.HEIGHT, ID.PlayerScore));
                handler.addObject(new HUD(Game.WIDTH, Game.HEIGHT, ID.Player2Score));
            } else if (mouseOver(mx, my, 450, 300, 200, 64)) {
                System.exit(0);
            }

        }

    }

    public void mouseReleased(MouseEvent event) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {

        if (mx > x && mx < x + width) {
            return my > y && my < y + height;
        } else return false;
    }

    public void tick() {

    }

    public void render(Graphics graphics) {


        graphics.setColor(Color.GREEN);
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        graphics.drawString("PONG 2021", 420, 100);

        graphics.setColor(Color.MAGENTA);
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        graphics.drawString("PLAY", 515, 245);
        graphics.drawString("EXIT", 520, 345);

        graphics.drawRect(450, 200, 200, 64);
        graphics.drawRect(450, 300, 200, 64);

    }



}
