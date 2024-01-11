package game.Entity.towers;
import game.geometry.Coordinates;

import static game.ui.Style.*;
public class ElectricTower extends Tower{
    public ElectricTower(Coordinates coordinates){
        super("Archer électrique", stringCouleurPourpre, 15, 70,"T", 50, coordinates, "resources/assets/archer/2_Idle/3.gif", "resources/assets/archer/3_Units/3/S_Idle.gif", "resources/assets/archer/3_Units/3/S_Attack.gif");
    }
}
