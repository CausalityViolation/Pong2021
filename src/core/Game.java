package core;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 800, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;

    private final Handler handler;
    Random random = new Random();

    public Game() {
        new Window(WIDTH, HEIGHT, "2D Game", this);

        handler = new Handler();

        for (int i = 0; i < 20; i++) {
            handler.addObject(new Player(random.nextInt(WIDTH), random.nextInt(HEIGHT), ID.Player));
        }


    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {

            thread.join();
            running = false;

        } catch (Exception error) {
            error.printStackTrace();
        }

    }

    public void run() {

        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;

            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
    }

    private void render() {

        BufferStrategy bufferStrat = this.getBufferStrategy();
        if (bufferStrat == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrat.getDrawGraphics();

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(graphics);

        graphics.dispose();
        bufferStrat.show();

    }

    public static void main(String[] args) {
        new Game();
    }
}
