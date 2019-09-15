package com.george.game.window;

import java.awt.Dimension;
import javax.swing.JFrame;
import com.george.game.Game;

public class Window
{
    public int width;
    public int height;
    String title;
    
    public Window(final String title, final int height, final int width, final Game game) {
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        
        frame.add(game);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
