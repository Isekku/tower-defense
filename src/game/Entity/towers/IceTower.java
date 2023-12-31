package game.Entity.towers;
import game.geometry.Coordinates;

import static game.ui.Style.*;

public class IceTower extends Tower{
    public IceTower(Coordinates coordinates){
        super("Archer de glace", stringCouleurCyan,15, 65, 65,  "T", 150, coordinates);
    }
}
