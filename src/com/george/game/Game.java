package com.george.game;

import com.george.game.entities.Crate;
import com.george.game.entities.Enemy;
import com.george.game.entities.Player;
import com.george.game.entities.Block;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.Graphics2D;
import com.george.game.id.ID;
import com.george.game.gfx.BufferedImageLoader;
import java.awt.event.MouseListener;
import com.george.game.input.MouseInput;
import java.awt.event.KeyListener;
import com.george.game.input.KeyInput;
import com.george.game.window.Window;
import java.awt.image.BufferedImage;
import com.george.game.gfx.Camera;
import java.awt.Canvas;

public class Game extends Canvas implements Runnable
{
    private static final long serialVersionUID = 1L;
    private Thread thread;
    private boolean isRunning;
    private Handler handler;
    private Camera camera;
    private BufferedImage level;
    public int ammo;
    
    public Game() {
        this.isRunning = false;
        this.level = null;
        this.ammo = 0;
        new Window("Game", 563, 1000, this);
        this.start();
        this.handler = new Handler();
        this.camera = new Camera(0.0f, 0.0f);
        this.addKeyListener(new KeyInput(this.handler));
        this.addMouseListener(new MouseInput(this.handler, this.camera, this));
        final BufferedImageLoader loader = new BufferedImageLoader();
        this.loadLevel(this.level = loader.loadImage("/textures/Arena.png"));
    }
    
    private synchronized void start() {
        this.isRunning = true;
        (this.thread = new Thread(this)).start();
    }
    
    private synchronized void stop() {
        this.isRunning = false;
        try {
            this.thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        final double ns = 1.0E9 / amountOfTicks;
        double delta = 0.0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (this.isRunning) {
        	
            final long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1.0) {
                this.tick();
                --delta;
            }
            this.render();
            ++frames;
            if (System.currentTimeMillis() - timer > 1000L) {
                timer += 1000L;
                frames = 0;
            }
        }
        this.stop();
    }
    
    public void tick() {
        for (int i = 0; i < this.handler.object.size(); ++i) {
            if (this.handler.object.get(i).getId() == ID.Player) {
                this.camera.tick(this.handler.object.get(i));
            }
        }
        this.handler.tick();
    }
    
    private void render() {
        final BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        final Graphics g = bs.getDrawGraphics();
        final Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, 1000, 563);
        g2d.translate(-this.camera.getX(), -this.camera.getY());
        this.handler.render(g);
        g2d.translate(-this.camera.getX(), -this.camera.getY());
        g.dispose();
        bs.show();
    }
    
    private void loadLevel(final BufferedImage image) {
        final int w = image.getWidth();
        final int h = image.getHeight();
        for (int xx = 0; xx < w; ++xx) {
            for (int yy = 0; yy < h; ++yy) {
                final int pixel = image.getRGB(xx, yy);
                final int red = pixel >> 16 & 0xFF;
                final int green = pixel >> 8 & 0xFF;
                final int blue = pixel & 0xFF;
                if (red == 255) {
                    this.handler.addObject(new Block(xx * 32, yy * 32, ID.Block));
                }
                if (blue == 255 && green == 0) {
                    this.handler.addObject(new Player(xx * 32, yy * 32, ID.Player, this.handler, this));
                }
                if (green == 255 && blue == 0) {
                    this.handler.addObject(new Enemy(xx * 32, yy * 32, ID.Enemey, this.handler));
                }
                if (blue == 255 && green == 255) {
                    this.handler.addObject(new Crate(xx * 32, yy * 32, ID.Crate));
                }
            }
        }
    }
    
    public static void main(final String[] args) {
        new Game();
    }
}
