package game.ui.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class DynamicImage {
    private JLabel gifLabel;
    private Timer animationTimer;
    private int currentFrame = 0;
    private BufferedImage[] frames;

    public DynamicImage(ImageIcon image){
        frames = loadGifFrames(image);
        gifLabel = new JLabel(new ImageIcon(frames[currentFrame]));

        animationTimer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                currentFrame = (currentFrame + 1) % frames.length;
                gifLabel.setIcon(new ImageIcon(frames[currentFrame]));
            }
        });

        animationTimer.start();
    }

    private BufferedImage[] loadGifFrames(ImageIcon image){
        if(image != null){
            BufferedImage[] frames = new BufferedImage[image.getIconHeight()];

            for(int i = 0; i < frames.length; i++){
                image.setImageObserver(gifLabel);
                frames[i] = new BufferedImage(image.getIconWidth(), image.getIconHeight(), BufferedImage.TYPE_INT_ARGB);

                Graphics g = frames[i].getGraphics();
                image.paintIcon(gifLabel, g, 0, 0);
                g.dispose();

                image.setImageObserver(null);
                image.setImage(image.getImage().getScaledInstance(image.getIconWidth(),image.getIconHeight(), Image.SCALE_DEFAULT));
            }
        }
        return frames;
    }
}
