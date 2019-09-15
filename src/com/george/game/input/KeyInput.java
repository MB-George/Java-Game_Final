package com.george.game.input;

import com.george.game.id.ID;
import com.george.game.GameObject;
import java.awt.event.KeyEvent;
import com.george.game.Handler;
import java.awt.event.KeyAdapter;

public class KeyInput extends KeyAdapter
{
    Handler handler;
    
    public KeyInput(final Handler handler) {
        this.handler = handler;
    }
    
    @Override
    public void keyPressed(final KeyEvent e) {
        final int key = e.getKeyCode();
        for (int i = 0; i < this.handler.object.size(); ++i) {
            final GameObject tempObject = this.handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                if (key == 87) {
                    this.handler.setUp(true);
                }
                if (key == 83) {
                    this.handler.setDown(true);
                }
                if (key == 65) {
                    this.handler.setLeft(true);
                }
                if (key == 68) {
                    this.handler.setRight(true);
                }
            }
        }
    }
    
    @Override
    public void keyReleased(final KeyEvent e) {
        final int key = e.getKeyCode();
        for (int i = 0; i < this.handler.object.size(); ++i) {
            final GameObject tempObject = this.handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                if (key == 87) {
                    this.handler.setUp(false);
                }
                if (key == 83) {
                    this.handler.setDown(false);
                }
                if (key == 65) {
                    this.handler.setLeft(false);
                }
                if (key == 68) {
                    this.handler.setRight(false);
                }
            }
        }
    }
}
