package game.Entity.Mobs;

import game.geometry.Coordinates;
import static game.ui.Style.*;

public class StrongerMob extends Mob{
    public StrongerMob(Coordinates coordinates){
        super("Mob Fort", (stringGras + stringCouleurRouge), 50, 400, 400, coordinates, 400);
    }
}
