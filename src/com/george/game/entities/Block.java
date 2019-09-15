package com.george.game.entities;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import com.george.game.id.ID;
import com.george.game.GameObject;

public class Block extends GameObject
{
    public Block(final int x, final int y, final ID id) {
        super(x, y, id);
    }
    
    @Override
    public void tick() {
    }
    
    @Override
    public void render(final Graphics g) {
        g.setColor(Color.black);
        g.fillRect(this.x, this.y, 32, 32);
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, 32, 32);
    }
}
