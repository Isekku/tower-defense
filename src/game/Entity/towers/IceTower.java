package game.Entity.towers;
import game.geometry.Coordinates;

import static game.ui.Style.*;

public class IceTower extends Tower{
    public IceTower(Coordinates coordinates){
        super("Archer de glace", stringCouleurCyan,20, 90,  "T", 75, coordinates, "resources/assets/monstres/4/S_Walk.gif", "resources/assets/monstres/4/S_Walk.gif", "resources/assets/monstres/4/S_Walk.gif");
    }
}
