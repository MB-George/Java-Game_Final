package com.george.game.entities;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import com.george.game.id.ID;
import com.george.game.GameObject;

public class Crate extends GameObject
{
    public Crate(final int x, final int y, final ID id) {
        super(x, y, id);
    }
    
    @Override
    public void tick() {
    }
    
    @Override
    public void render(final Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(this.x, this.y, 32, 32);
        g.setColor(Color.red);
        g.drawRect(this.x, this.y, 32, 32);
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, 32, 32);
    }
}
