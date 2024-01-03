package game.Entity.Mobs;

import game.geometry.Coordinates;
import static game.ui.Style.*;


public class NormalMob extends Mob{
    public NormalMob(Coordinates coordinates){
    super("Entite normal", stringGras, 12, 60, coordinates, 10);
    }
}
