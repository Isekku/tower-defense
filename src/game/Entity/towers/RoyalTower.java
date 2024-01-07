package game.Entity.towers;

import game.geometry.Coordinates;

import static game.ui.Style.*;

public class RoyalTower extends Tower{
    public RoyalTower(Coordinates coordinates){
        super("Archer Royale", stringCouleurJaune,25, 110,"T", 100, coordinates, null, null, null);
    }
}
