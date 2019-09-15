package com.george.game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler
{
    public LinkedList<GameObject> object;
    private boolean up;
    private boolean right;
    private boolean down;
    private boolean left;
    
    public Handler() {
        this.object = new LinkedList<GameObject>();
        this.up = false;
        this.right = false;
        this.down = false;
        this.left = false;
    }
    
    public boolean isUp() {
        return this.up;
    }
    
    public void setUp(final boolean up) {
        this.up = up;
    }
    
    public boolean isRight() {
        return this.right;
    }
    
    public void setRight(final boolean right) {
        this.right = right;
    }
    
    public boolean isDown() {
        return this.down;
    }
    
    public void setDown(final boolean down) {
        this.down = down;
    }
    
    public boolean isLeft() {
        return this.left;
    }
    
    public void setLeft(final boolean left) {
        this.left = left;
    }
    
    public void tick() {
        for (int i = 0; i < this.object.size(); ++i) {
            final GameObject tempObject = this.object.get(i);
            tempObject.tick();
        }
    }
    
    public void render(final Graphics g) {
        for (int i = 0; i < this.object.size(); ++i) {
            final GameObject tempObject = this.object.get(i);
            tempObject.render(g);
        }
    }
    
    public void addObject(final GameObject tempObject) {
        this.object.add(tempObject);
    }
    
    public void removeObject(final GameObject tempObject) {
        this.object.remove(tempObject);
    }
}
