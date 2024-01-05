package game.Entity.Mobs;

import game.geometry.Coordinates;
import game.ui.Model.SpriteSheet;
import game.ui.Vue.Playing;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.IOException;

import static game.ui.Style.*;


public class NormalMob extends Mob{
    ImageIcon image;

    public NormalMob(Coordinates coordinates){
        super("Entite normal", stringGras, 12, 60, coordinates, 10);

        try{
            image = SpriteSheet.createImageIcon(ImageIO.read(getClass().getResourceAsStream("../../../resources/assets"+"/monstres/2/S_Walk.png")), false);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            System.out.println("Normal Mob : " + (image == null));
        }

        super.setEntityImage(image);
    }
}
