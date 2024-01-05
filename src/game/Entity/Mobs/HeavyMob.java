package game.Entity.Mobs;

import game.geometry.Coordinates;
import game.ui.Model.SpriteSheet;
import game.ui.Vue.Playing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static game.ui.Style.*;

public class HeavyMob extends Mob{
    ImageIcon image;
    public HeavyMob(Coordinates coordinates){
        super("Mob Lourd", stringCouleurRouge, 16, 80, coordinates, 15);
        try{
            System.out.println(getClass().getResource("../../"));
            image = SpriteSheet.createImageIcon(ImageIO.read(getClass().getResourceAsStream("../../../resources/assets"+"/monstres/3/S_Walk.png")), false);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            System.out.println("Heavy Mob : " + (image == null));
        }

        super.setEntityImage(image);
    }
}
