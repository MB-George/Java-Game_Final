package com.george.game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.george.game.Game;
import com.george.game.GameObject;
import com.george.game.Handler;
import com.george.game.id.ID;

public class PlayerHealth extends GameObject
{
	
	Handler handler;
    Game game;

	public PlayerHealth(int x, int y, ID id, final Handler handler, final Game game) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
	}

	@Override
	public void tick() {
		this.x += (int)this.velX;
        this.y += (int)this.velY;
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


	@Override
	public void render(final Graphics g) {
		g.drawRect(this.x, this.y, 50, 200);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, 50, 200);
	}

}
