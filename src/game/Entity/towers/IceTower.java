package game.Entity.towers;
import game.geometry.Coordinates;

import static game.ui.Style.*;

public class IceTower extends Tower{
    public IceTower(Coordinates coordinates){
        super("Archer de glace", stringCouleurCyan,20, 90,  "T", 75, coordinates, "resources/assets/archer/2_Idle/5.gif", "resources/assets/archer/3_Units/3/S_Idle.gif", "resources/assets/archer/3_Units/3/S_Attack.gif", 2000);
    }
}
