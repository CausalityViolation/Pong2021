package core;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1080, HEIGHT = WIDTH / 16 * 9;
    private Thread thread;
    private boolean running = false;


    private final Handler handler;

    public Game() {

        this.setFocusable(true);
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "PONG 2021", this);

        handler.addObject(new Player(WIDTH / 2 + 450, HEIGHT / 2 - 50, ID.Player));
        handler.addObject(new Player(WIDTH / 2 - 480, HEIGHT / 2 - 50,  ID.Player2));
        handler.addObject(new Ball(WIDTH / 2, HEIGHT / 2 - 80, ID.Ball));



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

    public static int clamp(int var, int min, int max) {

        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }

    public static void main(String[] args) {
        new Game();
    }
}
