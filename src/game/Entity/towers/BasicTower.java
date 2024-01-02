package game.Entity.towers;

import game.geometry.Coordinates;

import static game.ui.Style.*;

public class BasicTower extends Tower{
    public BasicTower(Coordinates coordinates){
        super("Archer basique", stringBase, 15, 100, 100, "T", 50, coordinates);
    }
}
