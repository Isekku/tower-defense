package game.Entity.towers;

import game.geometry.Coordinates;

import static game.ui.Style.*;

public class BasicTower extends Tower{
    public BasicTower(Coordinates coordinates){
        super("Archer basique", stringBase, 10, 50, "T", 25, coordinates);
    }
}
