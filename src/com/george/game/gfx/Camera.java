package com.george.game.gfx;

import com.george.game.GameObject;

public class Camera
{
    private float x;
    private float y;
    
    public Camera(final float x, final float y) {
        this.x = x;
        this.y = y;
    }
    
    public void tick(final GameObject object) {
        this.x += (object.getX() - this.x - 500.0f) * 0.05f;
        this.y += (object.getY() - this.y - 281.0f) * 0.05f;
    }
    
    public float getX() {
        return this.x;
    }
    
    public void setX(final float x) {
        this.x = x;
    }
    
    public float getY() {
        return this.y;
    }
    
    public void setY(final float y) {
        this.y = y;
    }
}
