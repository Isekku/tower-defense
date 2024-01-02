package game.Entity.Mobs;

import game.geometry.Coordinates;
import static game.ui.Style.*;

public class HeavyMob extends Mob{
    public HeavyMob(Coordinates coordinates){
        super("Mob Lourd", stringCouleurRouge, 35, 300, 300, coordinates, 300);
    }
}
