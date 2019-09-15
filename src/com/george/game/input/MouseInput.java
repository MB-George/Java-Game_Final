
package com.george.game.input;

import com.george.game.entities.Bullet;
import com.george.game.id.ID;
import com.george.game.GameObject;
import java.awt.event.MouseEvent;
import com.george.game.Game;
import com.george.game.gfx.Camera;
import com.george.game.Handler;
import java.awt.event.MouseAdapter;

public class MouseInput extends MouseAdapter
{
    private Handler handler;
    private Camera camera;
    private Game game;
    
    public MouseInput(final Handler handler, final Camera camera, final Game game) {
        this.handler = handler;
        this.camera = camera;
        this.game = game;
    }
    
    @Override
    public void mousePressed(final MouseEvent e) {
        final int mx = (int)(e.getX() + this.camera.getX());
        final int my = (int)(e.getY() + this.camera.getY());
        for (int i = 0; i < this.handler.object.size(); ++i) {
            final GameObject tempObject = this.handler.object.get(i);
            if (tempObject.getId() == ID.Player && this.game.ammo >= 1) {
                this.handler.addObject(new Bullet(tempObject.getX() + 16, tempObject.getY() + 24, ID.Bullet, this.handler, mx, my));
                final Game game = this.game;
                --game.ammo;
            }
        }
    }
}
