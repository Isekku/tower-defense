package game.ui.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DynamicImage {

    private static final int FRAME_WIDTH = 32; // Largeur d'une frame
    private static final int FRAME_HEIGHT = 32; // Hauteur d'une frame
    private static final int TOTAL_FRAMES = 6; // Nombre total de frames dans l'image
    private int currentFrame = 0; // Frame actuelle

    public void drawDynamicImage(Graphics g, BufferedImage image){
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        int viewX = image.getWidth()/6;
        int viewY = 0;

        g.drawImage(image, 0, 0, imageWidth, imageHeight,
                currentFrame * FRAME_WIDTH, 0,
                (currentFrame + 1) * FRAME_WIDTH, FRAME_HEIGHT, null);
    }

    private void nextFrame(){
        currentFrame = (currentFrame + 1) % TOTAL_FRAMES;
    }
}
