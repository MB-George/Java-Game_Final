package com.george.game.gfx;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class BufferedImageLoader
{
    private BufferedImage image;
    
    public BufferedImage loadImage(final String path) {
        try {
            this.image = ImageIO.read(this.getClass().getResource(path));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return this.image;
    }
}
