package game.Entity.Mobs;

import game.geometry.Coordinates;
import game.ui.Model.SpriteSheet;
import game.ui.Vue.Playing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

import static game.ui.Style.*;

public class StrongerMob extends Mob{
    ImageIcon image;
    public StrongerMob(Coordinates coordinates){
        super("Mob Fort", (stringGras + stringCouleurRouge), 20, 100, coordinates, 20);
        try{
            image = SpriteSheet.createImageIcon(ImageIO.read(getClass().getResourceAsStream("../../../resources/assets"+"/monstres/4/S_Walk.png")), false);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            System.out.println("Stronger Mob : " + (image == null));
        }

        super.setEntityImage(image);
    }
}
