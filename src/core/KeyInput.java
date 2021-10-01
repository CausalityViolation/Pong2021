package core;


import entities.Ball;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static core.Game.HEIGHT;
import static core.Game.WIDTH;

public class KeyInput extends KeyAdapter {

    private final Handler handler;


    public KeyInput(Handler handler) {
        this.handler = handler;
    }


    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            if (tempObject.getId() == ID.Player) {

                if (key == KeyEvent.VK_UP) tempObject.setSpeedY(-5);
                else if (key == KeyEvent.VK_DOWN) tempObject.setSpeedY(5);

            } else if (tempObject.getId() == ID.Player2) {

                if (key == KeyEvent.VK_W) tempObject.setSpeedY(-5);
                else if (key == KeyEvent.VK_S) tempObject.setSpeedY(5);


            }
        }

        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if (key == KeyEvent.VK_ENTER) {
            for (int i = 0; i < handler.objects.size(); i++) {
                GameObject tempObject = handler.objects.get(i);

                if (tempObject.getId() == ID.Ball) {

                    handler.removeObject(tempObject);
                    Ball ball = new Ball(WIDTH / 2, HEIGHT / 2, ID.Ball, handler);
                    ball.setSpeedY(ball.randomSpeed());
                    ball.setSpeedX(ball.randomSpeed());
                    handler.addObject(ball);

                }
            }

        }
    }

    public void keyReleased(KeyEvent event) {
        int key = event.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            if (tempObject.getId() == ID.Player) {

                if (key == KeyEvent.VK_UP) tempObject.setSpeedY(0);
                else if (key == KeyEvent.VK_DOWN) tempObject.setSpeedY(0);
                else if (key == KeyEvent.VK_LEFT) tempObject.setSpeedX(0);
                else if (key == KeyEvent.VK_RIGHT) tempObject.setSpeedX(0);

            } else if (tempObject.getId() == ID.Player2) {

                if (key == KeyEvent.VK_W) tempObject.setSpeedY(0);
                else if (key == KeyEvent.VK_S) tempObject.setSpeedY(0);
                else if (key == KeyEvent.VK_A) tempObject.setSpeedX(0);
                else if (key == KeyEvent.VK_D) tempObject.setSpeedX(0);

            }
        }
    }

}
