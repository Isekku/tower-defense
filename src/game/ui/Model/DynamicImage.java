package game.ui.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class DynamicImage {
    public JLabel gifLabel;
    private Timer animationTimer;
    private int currentFrame = 0;
    public Image gif;

    public DynamicImage(Image image){
        this.gif = image;
        gifLabel = drawGifImage();
    }

    private JLabel drawGifImage(){
        return new JLabel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(gif, gif.getWidth(null), gif.getHeight(null), this);
            }
        };
    }
}
