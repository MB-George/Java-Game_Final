package com.george.game.entities;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import com.george.game.id.ID;
import com.george.game.Game;
import com.george.game.Handler;
import com.george.game.GameObject;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Player extends GameObject
{
    Handler handler;
    Game game;
    
    int hp = 100;
    
    public Player(final int x, final int y, final ID id, final Handler handler, final Game game) {
        super(x, y, id);
        this.handler = handler;
        this.game = game;
    }
    
    @Override
    public void tick() {
        this.x += (int)this.velX;
        this.y += (int)this.velY;
        this.collision();
        
        if (this.handler.isUp()) {
            this.velY = -8.0f;
        }
        else if (!this.handler.isDown()) {
            this.velY = 0.0f;
        }
        if (this.handler.isDown()) {
            this.velY = 8.0f;
        }
        else if (!this.handler.isUp()) {
            this.velY = 0.0f;
        }
        if (this.handler.isRight()) {
            this.velX = 8.0f;
        }
        else if (!this.handler.isLeft()) {
            this.velX = 0.0f;
        }
        if (this.handler.isLeft()) {
            this.velX = -8.0f;
        }
        else if (!this.handler.isRight()) {
            this.velX = 0.0f;
        }
    }
    
    private void collision() {
        for (int i = 0; i < this.handler.object.size(); ++i) {
            final GameObject tempObject = this.handler.object.get(i);
            if (tempObject.getId() == ID.Block && this.getBounds().intersects(tempObject.getBounds())) {
                this.x += (int)(this.velX * -1.0f);
                this.y += (int)(this.velY * -1.0f);
            }
            if (tempObject.getId() == ID.Crate && this.getBounds().intersects(tempObject.getBounds())) {
                final Game game = this.game;
                game.ammo += 10;
                this.handler.removeObject(tempObject);
            }
            if (tempObject.getId() == ID.Enemey && this.getBounds().intersects(tempObject.getBounds())) {
            	this.hp -= 5;
            	if (hp <= 0) {
            		this.handler.removeObject(this);
            		// Pop Up Dialogue
            		final JFrame parent = new JFrame();
                    int RestartSelection = JOptionPane.showConfirmDialog(parent, "You Ded. Do You Want to Restart?", null, 0); 
                    
                    if (RestartSelection == 1){
                    	this.game.stop();
                    }
                    if (RestartSelection == 0) {
                    	new Game();
                    	this.game.stop();                  	
                    }
            	}
            }
        }
    }
    
    @Override
    public void render(final Graphics g) {
        g.setColor(Color.blue);
        g.fillOval(this.x, this.y, 32, 32);
        g.setColor(Color.black);
        g.drawOval(this.x, this.y, 32, 32);
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, 32, 32);
    }
}
