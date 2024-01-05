package game.ui.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteSheet {

    public static void drawSprite(Graphics g, BufferedImage image, int frames){
        BufferedImage spriteSheet = image;
        int spriteWidth = image.getWidth()/frames;
        int spriteHeight = image.getHeight();

        for(int i = 0; i < frames; i++){
            int x = i * spriteWidth;
            int y = 0;

            g.drawImage(spriteSheet, 0, 0, spriteWidth, spriteHeight, x, y, x + spriteWidth, y + spriteHeight, null);

            try{
                Thread.sleep(100);
            }
            catch(InterruptedException e ){
                e.printStackTrace();
            }

            g.clearRect(0, 0, image.getWidth(), image.getHeight());
        }
    }

    public static ImageIcon createImageIcon(Image img, boolean isGround){
        BufferedImage image = (BufferedImage) img;
        BufferedImage bufferedImage = compatibleImage(image, isGround);
        return new ImageIcon(bufferedImage);
    }

    public static BufferedImage compatibleImage(BufferedImage image, boolean isGround){
        GraphicsConfiguration gfxConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        int transparence = image.getColorModel().getTransparency();
        BufferedImage res = gfxConfig.createCompatibleImage(image.getWidth(), image.getHeight(), transparence);

        Graphics g = res.createGraphics();
        if(isGround) g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        else drawSprite(g, image, 5);
        g.dispose();

        return res;
    }
}