package com.george.game;

import java.awt.Rectangle;
import java.awt.Graphics;
import com.george.game.id.ID;

public abstract class GameObject
{
    protected int x;
    protected int y;
    protected float velX;
    protected float velY;
    protected ID id;
    
    public GameObject(final int x, final int y, final ID id) {
        this.velY = 0.0f;
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public ID getId() {
        return this.id;
    }
    
    public void setId(final ID id) {
        this.id = id;
    }
    
    public abstract void tick();
    
    public abstract void render(final Graphics p0);
    
    public abstract Rectangle getBounds();
    
    public int getX() {
        return this.x;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public float getVelX() {
        return this.velX;
    }
    
    public void setVelX(final float velX) {
        this.velX = velX;
    }
    
    public float getVelY() {
        return this.velY;
    }
    
    public void setVelY(final float velY) {
        this.velY = velY;
    }
}
