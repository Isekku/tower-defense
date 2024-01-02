package game.Entity.towers;

import game.geometry.Coordinates;

import static game.ui.Style.*;

public class RoyalTower extends Tower{
    public RoyalTower(Coordinates coordinates){
        super("Archer Royale", stringCouleurJaune,30, 450, 45,"T", 200, coordinates);
    }
}
