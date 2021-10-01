package core;

import entities.Ball;
import entities.Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1080, HEIGHT = WIDTH / 16 * 9;
    private Thread thread;
    private boolean running = false;
    private final Handler handler;
    private final Menu menu;

    public enum STATE {
        Menu,
        Game
    }

    public STATE gameState = STATE.Menu;

    public Game() {

        this.setFocusable(true);


        handler = new Handler();
        menu = new Menu(this, handler);


        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);

        new Window(WIDTH, HEIGHT, "PONG 2021", this);

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

        if (gameState == STATE.Menu) {
            menu.tick();
        }

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


        if (gameState == STATE.Game) {

            handler.render(graphics);

        } else if (gameState == STATE.Menu) {
            menu.render(graphics);
        }


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
        playMusic();

    }

    public static void playMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/music/yt5s.com - Splash (320 kbps).wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error playing music file.");
            ex.printStackTrace();
        }
    }
}
