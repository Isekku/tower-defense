package game.Entity.Mobs;

import game.geometry.Coordinates;
import game.ui.Model.SpriteSheet;
import game.ui.Vue.Playing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static game.ui.Style.*;

public class WeakMob extends Mob {

    ImageIcon image;

    public WeakMob(Coordinates coordinates){
        super("Mob Faible", stringBase, 8, 40, coordinates, 5);
        try{
            System.out.println(getClass().getResource("../../../resources/assets/monstres/1/S_Walk.png"));
            image = SpriteSheet.createImageIcon(ImageIO.read(getClass().getResourceAsStream("../../../resources/assets/monstres/1/S_Walk.png")), false);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            System.out.println("Weak Mob : " + (image == null));
        }

        super.setEntityImage(image);
    }
}
