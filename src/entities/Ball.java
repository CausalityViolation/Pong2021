package entities;

import core.*;

import java.awt.*;
import java.util.Random;

import static core.Game.HEIGHT;
import static core.Game.WIDTH;

public class Ball extends GameObject {

    Handler handler;
    Boolean scored = false;

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

        if (y <= 0 || y >= HEIGHT - 60) {

            speedY *= -1;

        } else if (x == WIDTH - 30) {

            HUD.playerOneScore++;
            scored = true;

        } else if (x == WIDTH - 1080) {

            HUD.playerTwoScore++;
            scored = true;

        }

        if (scored) {

            handler.removeObject(this);
            Ball ball = new Ball(WIDTH / 2, HEIGHT / 2, ID.Ball, handler);
            ball.setSpeedX(randomSpeed());
            ball.setSpeedY(randomSpeed());
            handler.addObject(ball);
            scored = false;


        }

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

        graphics.fillOval(x, y, 16, 16);
    }

    public int randomSpeed() {

        java.util.Random random = new java.util.Random();
        int result = random.nextInt(2) + 1;
        int speed = -3;

        if (result == 1) {
            speed = 3;

            return speed;

        }

        return speed;
    }

}
