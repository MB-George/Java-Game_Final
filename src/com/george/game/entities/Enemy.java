package com.george.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.george.game.Game;
import com.george.game.GameObject;
import com.george.game.Handler;
import com.george.game.id.ID;

public class Enemy extends GameObject
{
    private Handler handler;
    Random r;
    int choose;
    int hp;
    
    //Adding in temporary Game field
    Game game;
    Random randomNumber;
    int counter;
    double direction;
    
    public Enemy(final int x, final int y, final ID id, final Handler handler) {
        super(x, y, id);
        this.r = new Random();
        this.choose = 0;
        this.hp = 100;
        this.handler = handler;
        this.randomNumber = new Random();
        this.game = game;
        this.counter = counter;
        this.direction = 0;
    }
    
    @Override
    public void tick() {
    	
        this.x += (int)this.velX;
        this.y += (int)this.velY;
        this.choose = this.r.nextInt(10);
        
      //System.out.println("The direction is: " + direction);
        if(counter % 10 == 0) {
     	  direction = randomNumber.nextDouble() * 100;
        }
         //Adding in Player Code
         this.collision();
         if (direction < 25) {
             this.velY = -7.0f;
         }
         else if (!(direction < 25)) {
             this.velY = 0.0f;
         }
         if ((direction > 25) && (direction < 50)) {
             this.velY = 7.0f;
         }
         else if (!((direction > 25) && (direction < 50))) {
             this.velY = 0.0f;
         }
         if ((direction > 50) && (direction < 75)) {
         	System.out.print("MOVING RIGHT");
             this.velX = 7.0f;
         }
         else if (!((direction > 50) && (direction < 75))) {
             this.velX = 0.0f;
         }
         if ((direction > 75) && (direction < 100)) {
             this.velX = -7.0f;
         }
         else if (!((direction > 75) && (direction < 100))) {
             this.velX = 0.0f;
         }
        for (int i = 0; i < this.handler.object.size(); ++i) {
            final GameObject tempObject = this.handler.object.get(i);
            
            if (tempObject.getId() == ID.Bullet && this.getBounds().intersects(tempObject.getBounds())) {
                this.hp -= 50;
                this.handler.removeObject(tempObject);
            }
        
        if (this.hp <= 0) {
            this.handler.removeObject(this);
        }
        counter++;
        }
    }
    
    private void collision() {
        for (int i = 0; i < this.handler.object.size(); ++i) {
            final GameObject tempObject = this.handler.object.get(i);
            if (tempObject.getId() == ID.Block && this.getBounds().intersects(tempObject.getBounds())) {
                this.x += (int)(this.velX * -1.0f);
                this.y += (int)(this.velY * -1.0f);
            }
 //           if (tempObject.getId() == ID.Crate && this.getBounds().intersects(tempObject.getBounds())) {
 //               final Game game = this.game;
 //               game.ammo += 10;
 //               this.handler.removeObject(tempObject);
 //           }
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
