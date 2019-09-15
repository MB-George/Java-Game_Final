package com.george.game.window;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.george.game.Game;

public class Window
{
    public int width;
    public int height;
    String title;
    
    public Window(final String title, final int height, final int width, final Game game) {
    	ImageIcon icon = new ImageIcon("C:\\Users\\ggl20\\Documents\\GitHub\\Java-Game_Final\\res\\images\\icon.jpg");
    	
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        
        //Icon Code
        frame.setIconImage(icon.getImage());
        
        frame.add(game);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
