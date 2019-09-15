package com.george.game.entities;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import com.george.game.id.ID;
import com.george.game.Handler;
import com.george.game.GameObject;

public class Bullet extends GameObject
{
    private Handler handler;
    
    public Bullet(final int x, final int y, final ID id, final Handler handler, final int mx, final int my) {
        super(x, y, id);
        this.handler = handler;
        this.velX = (float)((mx - x) / 10);
        this.velY = (float)((my - y) / 10);
    }
    
    @Override
    public void tick() {
        this.x += (int)this.velX;
        this.y += (int)this.velY;
        for (int i = 0; i < this.handler.object.size(); ++i) {
            final GameObject tempObject = this.handler.object.get(i);
            if (tempObject.getId() == ID.Block && this.getBounds().intersects(tempObject.getBounds())) {
                this.handler.removeObject(this);
            }
        }
    }
    
    @Override
    public void render(final Graphics g) {
        g.setColor(Color.blue);
        g.fillOval(this.x, this.y, 8, 8);
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, 8, 8);
    }
}
