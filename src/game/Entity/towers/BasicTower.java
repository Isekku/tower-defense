package game.Entity.towers;

import game.geometry.Coordinates;

import static game.ui.Style.*;

public class BasicTower extends Tower{
    public BasicTower(Coordinates coordinates){
        super("Archer basique", stringBase, 10, 50, "T", 25, coordinates, "resources/assets/archer/2_Idle/2.gif", "resources/assets/archer/3_Units/3/S_Idle.gif", "resources/assets/archer/3_Units/3/S_Attack.gif", 1500);
    }
}
