package game.Entity.Mobs;

import game.geometry.Coordinates;
import static game.ui.Style.*;

public class HeavyMob extends Mob{
    public HeavyMob(Coordinates coordinates){
        super("Mob Lourd", stringCouleurRouge, 16, 80, coordinates, 15);
    }
}
