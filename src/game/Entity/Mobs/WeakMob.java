package game.Entity.Mobs;

import game.geometry.Coordinates;
import static game.ui.Style.*;

public class WeakMob extends Mob{
    public  WeakMob(Coordinates coordinates){
        super("Mob Faible", stringBase, 8, 40, coordinates, 5);
    }
}
