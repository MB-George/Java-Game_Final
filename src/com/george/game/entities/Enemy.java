package com.george.game.entities;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import com.george.game.id.ID;
import java.util.Random;
import com.george.game.Handler;
import com.george.game.GameObject;

public class Enemy extends GameObject
{
    private Handler handler;
    Random r;
    int choose;
    int hp;
    
    public Enemy(final int x, final int y, final ID id, final Handler handler) {
        super(x, y, id);
        this.r = new Random();
        this.choose = 0;
        this.hp = 100;
        this.handler = handler;
    }
    
    @Override
    public void tick() {
        this.x += (int)this.velX;
        this.y += (int)this.velY;
        this.choose = this.r.nextInt(10);
        for (int i = 0; i < this.handler.object.size(); ++i) {
            final GameObject tempObject = this.handler.object.get(i);
            if (tempObject.getId() == ID.Block) {
                if (this.getBoundsBig().intersects(tempObject.getBounds())) {
                    this.x += (int)(this.velX * 5.0f * -1.0f);
                    this.y += (int)(this.velY * 5.0f * -1.0f);
                    this.velX *= -1.0f;
                    this.velY *= -1.0f;
                }
                else if (this.choose == 0) {
                    this.velX = (float)(this.r.nextInt(8) - 4);
                    this.velY = (float)(this.r.nextInt(8) - 4);
                }
            }
            if (tempObject.getId() == ID.Bullet && this.getBounds().intersects(tempObject.getBounds())) {
                this.hp -= 50;
                this.handler.removeObject(tempObject);
            }
        }
        if (this.hp <= 0) {
            this.handler.removeObject(this);
        }
    }
    
    @Override
    public void render(final Graphics g) {
        g.setColor(Color.green);
        g.fillOval(this.x, this.y, 32, 32);
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, 32, 32);
    }
    
    public Rectangle getBoundsBig() {
        return new Rectangle(this.x - 16, this.y - 16, 64, 64);
    }
}
